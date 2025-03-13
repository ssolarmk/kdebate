package com.web.kdebate.common.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web.common.util.HttpUtil;
import com.web.kdebate.common.common.domain.SmsInfoVO;
import com.web.kdebate.common.common.domain.SmsStatusVO;
import com.web.kdebate.common.common.service.SmsService;

@Component
@Profile("test")
@EnableScheduling
public class SmsController {

	static final Logger logger = LoggerFactory.getLogger(SmsController.class);

	@Autowired
	private SmsService mapper;
	
	
	@Scheduled(fixedDelayString="6000")
	@SuppressWarnings("unchecked")
	public void getMsg() throws Exception{
		String url = "http://smsmsgr.ione24.com/slma_action.ashx";
		List<SmsInfoVO> infoVos = new ArrayList<>();
		Gson json = new Gson();
		infoVos = mapper.getMsgSendRetrieve();

		if(infoVos.size() > 10) {
			HashMap<String, String> pList = new HashMap<String, String>();
			String sData = "";                          
			JSONArray jParent = new JSONArray();
			for(SmsInfoVO msg : infoVos) {
				JSONObject jChild = new JSONObject();
				if(msg.getMtType().equals("SM")) {
					jChild.put("pslma", "0");
					jChild.put("pid", msg.getSms_uid());
					jChild.put("ppwd", msg.getSms_uid());

					if(msg.getReservedDate() == null) {
						jChild.put("pintime", "");
					}else {
						jChild.put("pintime", msg.getReservedDate());
					}

					jChild.put("pdestphone", msg.getPhoneNumber());
					jChild.put("psendphone", msg.getCallback());
					jChild.put("pmsg", msg.getMessage());
					jChild.put("pseq", msg.getSeq());
				}else if(msg.getMtType().equals("LM")) {
					jChild.put("pslma", "1");
					jChild.put("pid", msg.getSms_uid());
					jChild.put("ppwd", msg.getSms_uid());

					if(msg.getReservedDate() == null) {
						jChild.put("pintime", "");
					}else {
						jChild.put("pintime", msg.getReservedDate());
					}

					jChild.put("pdestphone", msg.getPhoneNumber());
					jChild.put("psendphone", msg.getCallback());
					jChild.put("psubject", msg.getTitle());
					jChild.put("pmsg", msg.getMessage());
					jChild.put("pseq", msg.getSeq());
				}
				jParent.put(jChild);
				
				msg.setStatus("2");
				mapper.setSetMsgStatus(msg);
			}
			sData = JSONObject.valueToString(jParent);
			pList.put("pkind", "bigdataproc");
			pList.put("pbigdata", sData);
			
			
			String sResult = callSms(url, pList);
			
			
			JSONArray jsonArray = new JSONArray(sResult);
			
			
			for(SmsInfoVO msg : infoVos) {
				for(int i=0;i<jsonArray.length() ;i++){

					JSONObject jsonObj = (JSONObject)jsonArray.get(i);
					
					if(msg.getSeq().equals(jsonObj.get("seqno").toString())) {
						if("".equals(jsonObj.get("msg").toString())) {
							msg.setStatus("21");
							msg.setStatus_msg((String) jsonObj.get("msg"));
							mapper.setSetMsgStatus(msg);
						} else {
							msg.setStatus("29");
							mapper.setSetMsgStatus(msg);
						}
						
					}
					
				}
				
			}
			
		} else {
			for(SmsInfoVO msg : infoVos) {
				HashMap<String, String> pList = new HashMap<String, String>();

				if(msg.getMtType().equals("SM")) {
					pList.put("pslma", "0");
					pList.put("pid", msg.getSms_uid());
					pList.put("ppwd", msg.getSms_uid());

					if(msg.getReservedDate() == null) {
						pList.put("pintime", "");
					}else {
						pList.put("pintime", msg.getReservedDate());
					}

					pList.put("pdestphone", msg.getPhoneNumber());
					pList.put("psendphone", msg.getCallback());
					pList.put("pmsg", msg.getMessage());
					pList.put("pseq", msg.getSeq());
				}else if(msg.getMtType().equals("LM")) {
					pList.put("pslma", "1");
					pList.put("pid", msg.getSms_uid());
					pList.put("ppwd", msg.getSms_uid());

					if(msg.getReservedDate() == null) {
						pList.put("pintime", "");
					}else {
						pList.put("pintime", msg.getReservedDate());
					}

					pList.put("pdestphone", msg.getPhoneNumber());
					pList.put("psendphone", msg.getCallback());
					pList.put("psubject", msg.getTitle());
					pList.put("pmsg", msg.getMessage());
					pList.put("pseq", msg.getSeq());
				}

				msg.setStatus("2");
				mapper.setSetMsgStatus(msg);

				String sResult = callSms(url, pList);
				if(!sResult.equals("")) {
					msg.setStatus("29");
					msg.setStatus_msg(sResult);
					mapper.setSetMsgStatus(msg);

					System.out.println("smsError=============================================================================");
					System.out.println("seq : " + msg.getSeq() + " : " + sResult);
			    	System.out.println("=====================================================================================");
				}else {
					msg.setStatus("21");
					mapper.setSetMsgStatus(msg);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Scheduled(fixedDelayString="600000")
	public void getMsgStatus() throws Exception{
		Gson json = new Gson();

		Date date = new Date();
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmss");

		Calendar day = Calendar.getInstance();
		day.setTime(date);
		String penddt = form.format(day.getTime());

		day.add(Calendar.MINUTE, -180);
		String pstrdt = form.format(day.getTime());

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getAcaSmsUidList();

			for(int i=0; i< list.size(); i++) {
				String smsUid = list.get(i).get("sms_uid").toString();
				String SMS_STATUS_DATA_API_URL = "http://smsmsgr.ione24.com/_support/mem_check_owra.ashx?pkind=2&pid=" + smsUid + "&pstrdt=" + pstrdt + "&penddt=" + penddt;

				HashMap<String, String> hashmapResponse = (HashMap<String, String>) HttpUtil.callURLEuckr(SMS_STATUS_DATA_API_URL, null, null, "EUC-KR");

				String data = "[" + hashmapResponse.get("responseBody") + "]";
				List<SmsStatusVO> statusList = json.fromJson(data, new TypeToken<List<SmsStatusVO>>() {}.getType());

				for(SmsStatusVO vo : statusList) {
					mapper.setMsgFinalStatus(vo);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String callSms(String pUrl, HashMap<String, String> pList) throws Exception {
		HttpURLConnection con = null;
		OutputStreamWriter wr = null;
		InputStreamReader ir = null;
		BufferedReader rd = null;
		StringBuffer bf = null;
		StringBuilder bd = null;

		String myResult = "";

		try {
			URL url = new URL(pUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setDefaultUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			bf = new StringBuffer();

			if(pList != null) {
				Set key = pList.keySet();

				for(Iterator iterator = key.iterator(); iterator.hasNext();) {
					String keyName = (String)iterator.next();
					String valueName = pList.get(keyName);
					bf.append(keyName).append("=").append(URLEncoder.encode(String.valueOf(valueName), "EUC-KR"));
					bf.append("&");
				}
			}

			wr = new OutputStreamWriter(con.getOutputStream(), "EUC-KR");
			wr.write(bf.toString().substring(0, bf.toString().length()-1));
			wr.flush();

			int conResult = con.getResponseCode();

			if(conResult == HttpURLConnection.HTTP_OK){
				ir = new InputStreamReader(con.getInputStream(), "EUC-KR");
				rd = new BufferedReader(ir);
				bd = new StringBuilder();
				String str;

				while((str = rd.readLine()) != null){
					bd.append(str + "\n");
				}

				myResult = bd.toString();
			}else {
				System.out.println("conResult=============================================================================");
				System.out.println(conResult);
		    	System.out.println("=====================================================================================");
			}

			wr.close();
			rd.close();
		}catch(MalformedURLException e) {
			e.printStackTrace();
			if (wr != null) wr.close();
			if (ir != null) ir.close();
			if (rd != null) rd.close();
			return myResult;
		}catch(IOException e) {
			e.printStackTrace();
			if (wr != null) wr.close();
			if (ir != null) ir.close();
			if (rd != null) rd.close();
			return myResult;
		}

		return myResult;
	}

	@SuppressWarnings("unchecked")
	@Scheduled(cron="0 0 1 * * *")
	public void msgStatusUpdate() throws Exception{
		Gson json = new Gson();

		String pstrdt = getDateYmd(-2) + "000000";
		String penddt = getDateYmd(-1) + "235959";

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getAcaSmsUidList();

			for(int i=0; i< list.size(); i++) {
				String smsUid = list.get(i).get("sms_uid").toString();
				String SMS_STATUS_DATA_API_URL = "http://smsmsgr.ione24.com/_support/mem_check_owra.ashx?pkind=2&pid=" + smsUid + "&pstrdt=" + pstrdt + "&penddt=" + penddt;

				HashMap<String, String> hashmapResponse = (HashMap<String, String>) HttpUtil.callURLEuckr(SMS_STATUS_DATA_API_URL, null, null, "EUC-KR");

				String data = "[" + hashmapResponse.get("responseBody") + "]";
				List<SmsStatusVO> statusList = json.fromJson(data, new TypeToken<List<SmsStatusVO>>() {}.getType());

				for(SmsStatusVO vo : statusList) {
					mapper.setMsgFinalStatus(vo);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDateYmd(int cnt) {
		Calendar day = Calendar.getInstance();
	    day.add(Calendar.DATE , cnt);

	    String date = new java.text.SimpleDateFormat("yyyyMMdd").format(day.getTime());

		return date;
	}
}
