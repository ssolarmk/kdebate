package com.web.kdebate.student.students.controller;

import org.springframework.stereotype.Component;

import com.web.common.util.HttpUtil;
import com.web.kdebate.student.students.domain.AcaStdDataVo;
import com.web.kdebate.student.students.domain.AcaStdRequestVo;
import com.web.kdebate.student.students.domain.CreateStudentInfoVO;
import com.web.kdebate.student.students.service.StudentInfoMngService;
import com.web.kdebate.student.students.service.StudentTransService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@Component
@Profile("real")
@EnableScheduling
public class StudentTransComonent {

    @Value("${kdebate.aca2000.url}")
    private String studentUrl;

    @Autowired
    private StudentTransService studentTransService;

    @Autowired
    private StudentInfoMngService studentInfoMngService;

    @SuppressWarnings("unchecked")
    @Scheduled(fixedRate = 300000)
    public void getStudentInfo() {
         
        try{
            URL url;
            HttpURLConnection urlConnection;
            String charSet = "UTF-8";
            List<AcaStdRequestVo> reqList = new ArrayList<AcaStdRequestVo>();
            reqList = studentTransService.getAcaRequestInfo();

            for (AcaStdRequestVo reqVo : reqList) {
                HashMap<String, String> postData = new HashMap<String, String>();
                postData.put("ver", reqVo.getVer());
                postData.put("custCode", reqVo.getCustCode());    
                postData.put("id", reqVo.getId());
                postData.put("pw", reqVo.getPw());
                postData.put("cmd", reqVo.getCmd());

                String postParams = HttpUtil.MapToQeuryString(postData, charSet);
                url = new URL(studentUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setInstanceFollowRedirects(false);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty("charset", charSet);

                byte[] bytePostData = postParams.getBytes(charSet);
                int postDataLength = bytePostData.length;
                urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                urlConnection.setUseCaches(false);
                urlConnection.getOutputStream().write(bytePostData);

                StringBuilder sb = new StringBuilder();
                Reader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                for (int c = 0; (c = in.read()) >= 0;) {
                    sb.append((char) c);
                }
                String responseBody = sb.toString();
                int httpStatus = urlConnection.getResponseCode();
                
                System.out.println(responseBody);
                 ObjectMapper objectMapper = new ObjectMapper();
                 HashMap<String, Object> responseMap = objectMapper.readValue(responseBody, HashMap.class);
                 List<HashMap<String, Object>> dataList = (List<HashMap<String, Object>>) responseMap.get("stdInfoList");
                 for(HashMap<String, Object> map : dataList){
                    map.put("custCode", reqVo.getCustCode());
                    studentTransService.UpdateAcaStdData(map);
                 }
            }

            List<AcaStdDataVo> acaStdDataList = new ArrayList<AcaStdDataVo>();
            acaStdDataList = studentTransService.getAcaStdData();
            for(AcaStdDataVo acaStdDataVo : acaStdDataList){
                CreateStudentInfoVO stdinfo = new CreateStudentInfoVO();
                stdinfo.setInfo_std_id(acaStdDataVo.getCardNum());
                stdinfo.setInfo_std_pwd(acaStdDataVo.getCardNum());
                stdinfo.setStd_nm(acaStdDataVo.getName());
                stdinfo.setSch_nm(acaStdDataVo.getScName());
                stdinfo.setHp_no(acaStdDataVo.getHp());
                stdinfo.setParent_hp_no(acaStdDataVo.getPHp());
                stdinfo.setC_parent_hp_no(acaStdDataVo.getPHp());
                stdinfo.setAddr(acaStdDataVo.getAddr());
                stdinfo.setAddr_dtl(acaStdDataVo.getAddrDetail());
                stdinfo.setStd_use_yn("Y");
                stdinfo.setAca_id(acaStdDataVo.getAcaId());
                
                if("재원생".equals(acaStdDataVo.getStdStatus())){
                    stdinfo.setInfo_std_status("01");
                } else if("입학상담생".equals(acaStdDataVo.getStdStatus())) {
                    stdinfo.setInfo_std_status("00");
                } else if("퇴원생".equals(acaStdDataVo.getStdStatus())) {
                    stdinfo.setInfo_std_status("03");
                } else {
                    stdinfo.setInfo_std_status("00");
                }
                studentInfoMngService.userInfoCreate(stdinfo);
                studentInfoMngService.studentInfoCreate(stdinfo);

                String std_parent_id = studentInfoMngService.getStdParentId();
                if(!"".equals(stdinfo.getParent_hp_no())) {
						
					if(!"".equals(stdinfo.getC_parent_id()) && stdinfo.getC_parent_id() != null ) {
						stdinfo.setParent_id(stdinfo.getC_parent_id());
					} else {
						//createStdInfoVO.setParent_id("P" + createStdInfoVO.getParent_hp_no());
						stdinfo.setParent_id(std_parent_id);
					}

					studentInfoMngService.createParentInfo(stdinfo);

					studentInfoMngService.createParent(stdinfo);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
