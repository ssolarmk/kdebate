package com.web.kdebate.common.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.common.common.service.MainService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class MainController {

	@Autowired
	private MainService mapper;

	@RequestMapping(value="/main")
	public String mainPage() {

		return "pages/mainPage";
	}

	@RequestMapping(value="/main/getCenterStatusCntList")
	public @ResponseBody ReturnDataVO getCenterStatusCntList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		if(member.getP_aca_id() != null) {
			hashmapParam.put("p_aca_id", member.getP_aca_id());
    	}else {
    		hashmapParam.put("p_aca_id", member.getAca_id());
    	}

		try {
			list = mapper.getCenterStatusCntList(hashmapParam);
			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg(null);
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/main/getBossPtListRetrieve")
	public @ResponseBody HashMap<String, Object> getBossPtListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {
			hashmapParam.put("aca_id", member.getAca_id());

			resultList =  mapper.getBossPtListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
	}

	@RequestMapping(value="/main/getParentPtListRetrieve")
	public @ResponseBody HashMap<String, Object> getParentPtListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {
			hashmapParam.put("user_id", member.getUser_id());

			resultList =  mapper.getParentPtListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
	}

	@RequestMapping(value="/main/getAnnualSalesTrendRetrieve")
	public @ResponseBody ReturnDataVO getAnnualSalesTrendRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		if(member.getP_aca_id() != null) {
			hashmapParam.put("p_aca_id", member.getP_aca_id());
    	}else {
    		hashmapParam.put("p_aca_id", member.getAca_id());
    	}

		try {
			hashmapParam.put("aca_id", member.getAca_id());

			if(member.getP_aca_id() != null) {
				hashmapParam.put("p_aca_id_yn", member.getP_aca_id());
			}

			list = mapper.getAnnualSalesTrendRetrieve(hashmapParam);
			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg(null);
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/main/getUserStatusCntList")
	public @ResponseBody ReturnDataVO getUserStatusCntList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());

		try {
			map = mapper.getUserStatusCntList(hashmapParam);
			result.setResultCode("S000");
			result.setData(map);

		} catch (Exception e) {
			result.setResultMsg(null);
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}


	@RequestMapping(value="/main/getClassStdListRetrieve")
	public @ResponseBody HashMap<String, Object> getClassStdListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			resultList =  mapper.getClassStdListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
	}


	@RequestMapping(value="/main/getBoardDataFileListRetrieve")
	public @ResponseBody ReturnDataVO getBoardDataFileListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("upt_user_id", member.getUser_id());

		try {
			list = mapper.getBoardDataFileListRetrieve(hashmapParam);

			if(mapper.boardDataCntUpdate(hashmapParam) == 1){
				result.setResultCode("S000");
			}

			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg(null);
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/main/teacherList")
	public @ResponseBody List<HashMap<String, Object>> teacherList(@RequestParam HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			list = (List<HashMap<String, Object>>) mapper.teacherList(hashmapParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * sms 사용청구서 생성
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/main/smsPayInvCheck", method= {RequestMethod.POST,RequestMethod.GET})
	public ReturnDataVO smsPayInvCheck(@RequestParam HashMap<String, String> paramMap, HttpServletResponse response, HttpSession session){
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());
		
		ReturnDataVO result = new ReturnDataVO();
		
		try {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map = mapper.smsPayInvCheck(hashmapParam);
			result.setResultCode("S000");
			result.setData(map);
			
		}catch(Exception e) {
			result.setData(null);
		}
		
		
		return result;
	}
	/**
	 * sms 사용청구서 생성
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/main/smsPayRead", method= {RequestMethod.POST,RequestMethod.GET})
	public ReturnDataVO smsPayRead(@RequestParam HashMap<String, String> paramMap, HttpServletResponse response, HttpSession session){
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());
		hashmapParam.put("user_id", member.getUser_id());
		
		ReturnDataVO result = new ReturnDataVO();
		
		try {
			
			mapper.smsPayReadCreate(hashmapParam);
			
			result.setResultCode("S000");
			
		}catch(Exception e) {
			result.setResultCode("S999");
		}
		
		
		return result;
	}
	/**
	 * sms 사용청구서 생성
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/main/smsPayReadCheck", method= {RequestMethod.POST,RequestMethod.GET})
	public ReturnDataVO smsPayReadCheck(@RequestParam HashMap<String, String> paramMap, HttpServletResponse response, HttpSession session){
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());
		hashmapParam.put("user_id", member.getUser_id());
		
		ReturnDataVO result = new ReturnDataVO();
		
		try {
			
			int cnt = 0;
			cnt = mapper.smsPayReadCheck(hashmapParam);
			result.setResultCode("S000");
			result.setData(cnt);
			
		}catch(Exception e) {
			result.setResultCode("S999");
			result.setData(null);
		}
		
		
		return result;
	}
}
