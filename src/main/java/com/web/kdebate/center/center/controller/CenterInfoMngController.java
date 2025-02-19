package com.web.kdebate.center.center.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.common.util.DateUtil;
import com.web.common.util.StringUtil;
import com.web.common.util.ValidateUtil;
import com.web.kdebate.center.center.domain.CenterInfoMngVO;
import com.web.kdebate.center.center.service.CenterInfoMngService;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(value="/center/center/centerInfoMng")
public class CenterInfoMngController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CenterInfoMngService mapper;

	@RequestMapping(value="/view")
	public String view() {
		return "pages/center/center/centerInfoMngView";
	}

	/**
	 * 학원정보 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getCenterInfoRetrieve")
	public @ResponseBody ReturnDataVO getCenterInfoRetrieve(HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();

    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("user_id", member.getUser_id());
		hashmapParam.put("aca_id", member.getAca_id());

		try {
			map = (HashMap<String, Object>) mapper.getCenterInfoRetrieve(hashmapParam);
			result.setResultCode("S000");
			result.setData(map);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 강의실 목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getClassListRetrieve")
	public @ResponseBody HashMap<String, Object> getClassListRetrieve(@RequestBody HashMap<String, Object> hashmapParam, HttpSession session){

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("user_id", member.getUser_id());

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);

			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			list = mapper.getClassListRetrieve(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
	}

	/**
	 * 독서실 Room 정보 가져오기
	 * @param hashmapParam
	 * @param pageing
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getClsRoomListRetrieve")
	public @ResponseBody HashMap<String, Object> getClsRoomListRetrieve(@RequestBody HashMap<String, Object> hashmapParam, HttpSession session){

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("user_id", member.getUser_id());
		
		if(hashmapParam.get("aca_id") == null) {
			hashmapParam.put("aca_id", member.getAca_id());
		}

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			list = mapper.getClsRoomListRetrieve(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
	}

	/**
	 * 계약정보 목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getContListRetrieve")
	public @ResponseBody HashMap<String, Object> getContListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("user_id", member.getUser_id());

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			list = mapper.getContListRetrieve(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
	}

	/**
	 * 코드관리 코드 목록 가져오기
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getGroupCodeRetrieve", method= RequestMethod.POST)
    public @ResponseBody HashMap<String, Object> groupCodeRetrieve(@RequestParam HashMap<String, Object> hashmapParam){
    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
    	HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		try {
			resultList =  mapper.getGroupCodeRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
    }

	/**
	 * 하위코드 목록 조회
	 * @author 김솔람
	 * @since 2019.03.20
	 * @param hashmapParam, PageingVO
	 * @return json
	 */
	@RequestMapping(value="/getLowerCodeRetrieve", method= RequestMethod.POST)
    public @ResponseBody HashMap<String, Object> lowerCodeRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
    	HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

    	SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {

			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			if("Y".equals(member.getPcode_use_yn())) {
				resultList =  mapper.getLowerCodeRetrieve(hashmapParam);
			}else {
				resultList =  mapper.getLowerCodeRetrieve2(hashmapParam);
			}
			int records = mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
    }

	@Transactional
	@RequestMapping(value="/codeAcaCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO codeAcaCreate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}
			if("Y".equals(member.getPcode_use_yn())) {
				if(mapper.codeAcaCreate(centerInfoMngVO) == 1){
					result.setResultCode("S000");
				}
			}else {
				if(mapper.codeCreate(centerInfoMngVO) == 1){
					result.setResultCode("S000");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@Transactional
	@RequestMapping(value="/codeAcaUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO codeAcaUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}
			if("Y".equals(member.getPcode_use_yn())) {
				if(mapper.codeAcaUpdate(centerInfoMngVO) == 1){
					result.setResultCode("S000");
				}
			}else {
				if(mapper.codeUpdate(centerInfoMngVO) == 1){
					result.setResultCode("S000");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }




	/**
	 * 학원정보 수정
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/centerUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO centerUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(centerInfoMngVO.getAca_boss_hp_chg().equals("Y")) {
				mapper.centerBossMobileUpdate(centerInfoMngVO);
			}

			if(mapper.centerUpdate(centerInfoMngVO) == 1){

				mapper.centerLogCreate(centerInfoMngVO);

					result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	/**
	 * 강의실정보 등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/classCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO classCreate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.classCreate(centerInfoMngVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	/**
	 * 강의실정보 수정
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/classUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO classUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.classUpdate(centerInfoMngVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	/**
	 * 독서실 정보 등록
	 * @param centerInfoMngVO
	 * @param bindResult
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/clsRoomCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO clsRoomCreate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.clsRoomCreate(centerInfoMngVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	/**
	 * 독서실 정보 수정
	 * @param centerInfoMngVO
	 * @param bindResult
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/clsRoomUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO clsRoomUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.clsRoomUpdate(centerInfoMngVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }


	@RequestMapping(value="/getCorpCdList")
	public @ResponseBody ReturnDataVO getCorpCdList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {
			hashmapParam.put("p_aca_id", member.getP_aca_id());

			list = mapper.getCorpCdList(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 청구항목 등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/goodsCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO goodsCreate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());
		centerInfoMngVO.setP_aca_id(member.getP_aca_id());
		centerInfoMngVO.setAca_id(member.getAca_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			String goodsCorpCd = mapper.getCorpCd(centerInfoMngVO);
			centerInfoMngVO.setGds_goods_cd(goodsCorpCd);
			
			String goods_tp = centerInfoMngVO.getGds_goods_tp();

			if(mapper.goodsCreate(centerInfoMngVO) == 1){

				if(member.getAca_id().equals("AC00001") && centerInfoMngVO.getGds_pay_div_chk().equals("Y")) {
					mapper.centerGoodsCreate(centerInfoMngVO);
				}
				
				if(goods_tp.equals("02")) {
					
					HashMap<String, Object> corpInfo = mapper.getSelectCorpInfo(centerInfoMngVO.getGds_corp_cd());
					centerInfoMngVO.setVendor_nm((String) corpInfo.get("corp_nm"));
					centerInfoMngVO.setVendor_tel((String) corpInfo.get("tel_no"));
					
					mapper.bookInfoCreate(centerInfoMngVO);
					if(Integer.valueOf(centerInfoMngVO.getGds_unit_value()) > 0) {
						mapper.bookStockInfoCreate(centerInfoMngVO);
					}
				}
				
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	/**
	 * 청구항목 수정
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/goodsUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO goodsUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.goodsUpdate(centerInfoMngVO) == 1){

				if(member.getAca_id().equals("AC00001") && centerInfoMngVO.getGds_pay_div_chk().equals("Y")) {
					mapper.centerGoodsUpdate(centerInfoMngVO);
				}

				if(member.getAca_id().equals("AC00001") && centerInfoMngVO.getGds_pay_div_chk().equals("N") && centerInfoMngVO.getOrg_pay_div_chk().equals("Y")) {
					mapper.centerGoodsInfoUpdate(centerInfoMngVO);
				}

				if("02".equals(centerInfoMngVO.getGds_goods_tp())) {
					mapper.bookInfoUpdate(centerInfoMngVO);
				}

				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }
			

	/**
	 * 판매처 등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/salesCorpCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO salesCorpCreate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());
		centerInfoMngVO.setAca_id(member.getAca_id());
		centerInfoMngVO.setP_aca_id(member.getP_aca_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}
						
			int duplChk = mapper.getCorpBizNoDuplChk(centerInfoMngVO);
			
			if(duplChk > 0) {
				throw new Exception("등록된 사업자번호 입니다.");
			}
			

			String corp_cd = mapper.getSalesCorpCd();
			centerInfoMngVO.setSal_crp_corp_cd(corp_cd);

			if(mapper.salesCorpCreate(centerInfoMngVO) == 1 && mapper.acaSalesCorpCreate(centerInfoMngVO) == 1) {
				if(mapper.userInfoCreate(centerInfoMngVO) == 1 && mapper.userAuthCreate(centerInfoMngVO) == 1) {
					result.setResultCode("S000");
				}
			} else {
				throw new Exception("오류가 발생했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg(e.getMessage());
		}
    	return result;
    }

	/**
	 * 판매처 수정
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/salesCorpUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO salesCorpUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());
		centerInfoMngVO.setAca_id(member.getAca_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.salesCorpUpdate(centerInfoMngVO) == 1 && mapper.acaSalesCorpUpdate(centerInfoMngVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@Transactional
	@RequestMapping(value="/clsSeatPoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO clsSeatPoUpdate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {
			hashmapParam.put("upt_user_id", member.getUser_id());

			if(mapper.clsSeatPoUpdate(hashmapParam) == 1){
				result.setResultCode("S000");
			};

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@RequestMapping(value="/getMngClassTeacherList")
	public @ResponseBody ReturnDataVO getMngClassTeacherList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getMngClassTeacherList(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}


	@Transactional
	@RequestMapping(value="/mngClassCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO mngClassCreate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.mngClassCreate(centerInfoMngVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@Transactional
	@RequestMapping(value="/mngClassUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO mngClassUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUser_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.mngClassUpdate(centerInfoMngVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	/**
	 * 학원정보 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getClassTeacherInfoRetrieve")
	public @ResponseBody ReturnDataVO getClassTeacherInfoRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getClassTeacherInfoRetrieve(hashmapParam);
			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 강의실 시간표 목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getClassTimeListRetrieve")
	public @ResponseBody ReturnDataVO getClassTimeListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		List<HashMap<String, Object>> clsTimelist = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, Object>> clsTeachList = new ArrayList<HashMap<String,Object>>();

		String maxTtime = "";

		try {
			maxTtime = mapper.getMaxClsTtime(hashmapParam);
			clsTimelist = mapper.getClassTimeListRetrieve(hashmapParam);
			clsTeachList = mapper.getClassTeacherInfoRetrieve(hashmapParam);

			resultMap.put("max_ttime", maxTtime);
			resultMap.put("cls_tm_list", clsTimelist);
			resultMap.put("cls_teach_list", clsTeachList);

			result.setResultCode("S000");
			result.setData(resultMap);
		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 강의실시간표 등록
	 * @param hashmapParam
	 * @return json
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@RequestMapping(value="/classTimeCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO classTimeCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;

		Object obj2 = StringUtil.JsonStringToObject(hashmapParam.get("wdays").toString());
		List<String> wday_list = (List<String>) obj2;


		String time_nm = "";
		String wday_nm = "";
		String cls_prefix_nm = "";

		String cls_tm_max_ttime = "";
		int del_cls_tm_cnt = 0;

		try {

			HashMap<String, Object> ttime_map = new HashMap<String, Object>();

			ttime_map.put("aca_id", list.get(0).get("aca_id"));
			ttime_map.put("cls_tm_room_no", list.get(0).get("cls_tm_room_no"));

			for(String wday : wday_list) {

				ttime_map.put("cls_tm_wday", wday);
				cls_tm_max_ttime = mapper.getMaxClsWdayTtime(ttime_map);

				del_cls_tm_cnt = Integer.parseInt(cls_tm_max_ttime) - list.size();

				if(del_cls_tm_cnt > 0) {

					for(int i = 1; i <= del_cls_tm_cnt; i++) {
						ttime_map.put("cls_tm_ttime", list.size() + i);
						mapper.classTimeDelete(ttime_map);
						mapper.deleteStdClsTimeTable(ttime_map);
						mapper.deleteStdAtdTimeTable(ttime_map);
					}
				}

				if(wday.equals("1")) {
					wday_nm = "월";
				}else if(wday.equals("2")) {
					wday_nm = "화";
				}else if(wday.equals("3")) {
					wday_nm = "수";
				}else if(wday.equals("4")) {
					wday_nm = "목";
				}else if(wday.equals("5")) {
					wday_nm = "금";
				}else if(wday.equals("6")) {
					wday_nm = "토";
				}

				for(HashMap<String, Object> map : list) {

					cls_prefix_nm = map.get("cls_tm_cls_nm").toString();

					time_nm = cls_prefix_nm.trim() + " " + wday_nm + " " + map.get("cls_tm_str_tm");

					map.put("cls_tm_wday", wday);
					map.put("cls_tm_time_nm", time_nm);
					map.put("user_id", member.getUser_id());
					mapper.classTimeCreate(map);
					mapper.updateStdAtdTimeTable(map);
				}
			}

				result.setResultCode("S000");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@Value("${global.fileStorePath}")
	String origin_fileStorePath;

	/**
	 * 옵션관리 저장
	 * @param centerMngVo
	 * @param bindResult
	 * @param session
	 * @return
	 */
	@SuppressWarnings("null")
	@Transactional
	@RequestMapping(value="/optionInfoUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO optionInfoUpdate(@ModelAttribute("CenterInfoMngVO") @Valid CenterInfoMngVO centerInfoMngVO, BindingResult bindResult, HttpSession session, MultipartHttpServletRequest multiRequest, HttpServletResponse response){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerInfoMngVO.setUpt_user_id(member.getUser_id());

		String logo_img_path = "";

		try {
			if (multiRequest.getFile("logo_img_path") != null) {
			    MultipartFile file;
			    file = multiRequest.getFile("logo_img_path");

			    String storePathString = origin_fileStorePath;
			       String filePath = "";
			       String date = (DateUtil.getTodateYmd().replaceAll("-", ""));

			       File saveFolder = new File(storePathString);

			    if (!saveFolder.exists() || saveFolder.isFile()) {
			        saveFolder.mkdirs();
			    }
			    File dateFolder = new File(storePathString+date);
			    if (!dateFolder.exists() || dateFolder.isFile()) {
			     dateFolder.mkdirs();
			    }
			    String orginFileName = file.getOriginalFilename();
			    int pos = orginFileName.lastIndexOf( "." );
			    String fileExt = orginFileName.substring( pos + 1 );
			    String newName = "file_" + DateUtil.getTimeStamp();
			    filePath = dateFolder + File.separator + newName+ "."+fileExt;
			    file.transferTo(new File(filePath));
			    logo_img_path = "/upload/"+date+"/"+newName+"."+fileExt;
			}

			if(!logo_img_path.equals("")) {
				centerInfoMngVO.setLogo_img_path(logo_img_path);
			}
			if(centerInfoMngVO.getLevel_use_yn() == null) {
				centerInfoMngVO.setLevel_use_yn("N");
			}
			if(centerInfoMngVO.getCurri_use_yn() == null) {
				centerInfoMngVO.setCurri_use_yn("N");
			}
			if(centerInfoMngVO.getAca_oper_tp_camp() == null) {
				centerInfoMngVO.setAca_oper_tp_camp("N");
			}
			if(centerInfoMngVO.getAca_oper_tp_lib() == null) {
				centerInfoMngVO.setAca_oper_tp_lib("N");
			}
			centerInfoMngVO.setAca_oper_tp(centerInfoMngVO.getAca_oper_tp_camp() + "," + centerInfoMngVO.getAca_oper_tp_lib());

			if(centerInfoMngVO.getExam_sys_use_yn() == null) {
				centerInfoMngVO.setExam_sys_use_yn("N");
			}
			if(centerInfoMngVO.getRating_sys_use_yn() == null) {
				centerInfoMngVO.setRating_sys_use_yn("N");
			}

			if(mapper.getOptionInfoCnt(centerInfoMngVO) > 0){
				if(mapper.optionInfoUpdate(centerInfoMngVO) == 1){
					centerInfoMngVO.setAca_id(centerInfoMngVO.getOption_aca_id());

					if(centerInfoMngVO.getOffice_of_education() != null) {
						List<String> list = Arrays.asList(centerInfoMngVO.getOffice_of_education().split(","));
						mapper.deleteTbAcaSchMgt(centerInfoMngVO);
						for(int i=0; i<list.size(); i++) {
							centerInfoMngVO.setOffice_of_education(list.get(i));
							mapper.insertTbAcaSchMgt(centerInfoMngVO);;

						}
					}

					mapper.centerLogCreate(centerInfoMngVO);
					result.setResultCode("S000");
				}
			}else {
				if(mapper.optionInfoInsert(centerInfoMngVO) == 1){
					centerInfoMngVO.setAca_id(centerInfoMngVO.getOption_aca_id());

					if(centerInfoMngVO.getOffice_of_education() != null ) {
						List<String> list = Arrays.asList(centerInfoMngVO.getOffice_of_education().split(","));
						mapper.deleteTbAcaSchMgt(centerInfoMngVO);
						for(int i=0; i<list.size(); i++) {
							centerInfoMngVO.setOffice_of_education(list.get(i));
							mapper.insertTbAcaSchMgt(centerInfoMngVO);;

						}
					}

					mapper.centerLogCreate(centerInfoMngVO);
					result.setResultCode("S000");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	/**
	 * SMS 잔여포인트 조회
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getCheckUserPoint")
	public @ResponseBody ReturnDataVO getCheckUserPoint(@RequestParam HashMap<String, Object> hashmapParam) {

		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> userPoint = new HashMap<String,Object>();

		try {

			userPoint = mapper.getCheckUserPoint(hashmapParam);
			result.setData(userPoint);
			result.setResultCode("S000");

		} catch(Exception e) {
			result.setResultCode("S999");
			e.printStackTrace();
		}

		return result;

	}
}