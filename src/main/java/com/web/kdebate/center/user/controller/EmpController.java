package com.web.kdebate.center.user.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
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
import com.web.common.util.ValidateUtil;
import com.web.kdebate.center.user.domain.EmpMngVO;
import com.web.kdebate.center.user.service.EmpService;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/center/user/empMng")
public class EmpController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Value("${global.fileStorePath}")
	String origin_fileStorePath;

	@Autowired
	private EmpService mapper;

	@RequestMapping(value="/view")
	public String view() {
		return "pages/center/user/empView";
	}

	/**
	 * 학원정보 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/acaIdChk")
	public @ResponseBody ReturnDataVO acaIdChk(HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("user_id", member.getUser_id());

		try {
			map = mapper.acaIdChk(hashmapParam);
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
	 * 직원 목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getEmpListRetrieve")
	public @ResponseBody HashMap<String, Object> getEmpListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);

			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			list = mapper.getEmpListRetrieve(hashmapParam);
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
	 * 직원/강사 등록
	 * @param hashmapParam
	 * @return json
	 */
	@SuppressWarnings("null")
	@Transactional
	@RequestMapping(value="/empCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO empCreate(@ModelAttribute("EmpMngVO") @Valid EmpMngVO empMngVO, BindingResult bindResult, HttpSession session, MultipartHttpServletRequest multiRequest, HttpServletResponse response){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		empMngVO.setUser_id(member.getUser_id());

		String auth_grp_cd = empMngVO.getEmp_auth_grp_cd();
		String dept = empMngVO.getEmp_dept();
		String work_cd = empMngVO.getEmp_work_cd();
		String img_url = "";

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if (multiRequest.getFile("img_path") != null) {

			    MultipartFile file;
			    file = multiRequest.getFile("img_path");

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
			    img_url = "/upload/"+date+"/"+newName+"."+fileExt;

			}

			String emp_aca_id = empMngVO.getEmp_aca_id();
			System.out.println("+++" + emp_aca_id + "+++");
			if(emp_aca_id == null) {
				empMngVO.setEmp_aca_id(empMngVO.getAca_id());
			}
			
			if("04".equals(empMngVO.getEmp_tp())) {
				String kiosk_atd_chk_tp = empMngVO.getKiosk_atd_chk_tp1()+","+empMngVO.getKiosk_atd_chk_tp2()+",N,N,N";
				
				empMngVO.setKiosk_atd_chk_tp(kiosk_atd_chk_tp);
			}

			mapper.userCreate(empMngVO);

			if(!img_url.equals("")) {
				empMngVO.setImg_url(img_url);
			}

			mapper.empCreate(empMngVO);
			
			
			if(auth_grp_cd != null) {

				String[] auth_grp_cds = auth_grp_cd.split(",");

				for(int i = 0; i < auth_grp_cds.length; i++) {

					empMngVO.setEmp_auth_grp_cd(auth_grp_cds[i]);
					mapper.userAuthGrpCreate(empMngVO);
					
					// 인라이브 강사 & 권한 온라인 강사일 경우 조교 생성
					if(empMngVO.getEmp_tp().equals("01")) {						
						if(auth_grp_cds[i].equals("AG0005")) {
							
							for(int j=1;j<4;j++) {	
								
								empMngVO.setEmp_sub_user_id(empMngVO.getEmp_user_id() + "_as" + j);
								empMngVO.setEmp_sub_user_nm(empMngVO.getEmp_user_nm() + "의 조교" + j);
								empMngVO.setEmp_sub_tp("09");
								empMngVO.setEmp_sub_user_gb_cd("01");
								empMngVO.setEmp_sub_auth_grp_cd("AG0005");
								empMngVO.setEmp_sub_work_cd("01");
								empMngVO.setEmp_sub_memo("자동생성 조교");
								empMngVO.setEmp_sub_use_yn("Y");
								
								mapper.subUserCreate(empMngVO);
								mapper.subEmpCreate(empMngVO);
								mapper.subAuthGrpCreate(empMngVO);
								mapper.subEmpWorkCdCreate(empMngVO);
							}
							
						}
					}
					
				}
			}

			if(dept != null) {

				String[] depts = dept.split(",");

				for(int i = 0; i < depts.length; i++) {

					empMngVO.setEmp_dept(depts[i]);
					mapper.empJisaGrpCreate(empMngVO);
				}
			}

			if(work_cd != null) {

				String[] work_cds = work_cd.split(",");

				for(int i = 0; i < work_cds.length; i++) {

					empMngVO.setEmp_work_cd(work_cds[i]);
					mapper.empWorkCdCreate(empMngVO);
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

	/**
	 * 직원/강사 수정
	 * @param hashmapParam
	 * @return json
	 */
	@SuppressWarnings("null")
	@Transactional
	@RequestMapping(value="/empUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO empUpdate(@ModelAttribute("EmpMngVO") @Valid EmpMngVO empMngVO, BindingResult bindResult, HttpSession session, HttpServletResponse response){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		empMngVO.setUser_id(member.getUser_id());

		String auth_grp_cd = empMngVO.getEmp_auth_grp_cd();
		String dept = empMngVO.getEmp_dept();
		String work_cd = empMngVO.getEmp_work_cd();
		String img_url = "";

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}
			
			if("04".equals(empMngVO.getEmp_tp())) {
				String kiosk_atd_chk_tp = empMngVO.getKiosk_atd_chk_tp1()+","+empMngVO.getKiosk_atd_chk_tp2()+",N,N,N";
				
				empMngVO.setKiosk_atd_chk_tp(kiosk_atd_chk_tp);
			}

			mapper.userUpdate(empMngVO);

			mapper.userAuthGrpDelete(empMngVO);

			if(auth_grp_cd != null) {

				String[] auth_grp_cds = auth_grp_cd.split(",");

				if(auth_grp_cds.length > 0) {

					for(int i = 0; i < auth_grp_cds.length; i++) {

						empMngVO.setEmp_auth_grp_cd(auth_grp_cds[i]);
						mapper.userAuthGrpCreate(empMngVO);
					}
				}
			}

			if(!img_url.equals("")) {
				empMngVO.setImg_url(img_url);
			}

			mapper.empUpdate(empMngVO);

			mapper.empJisaGrpDelete(empMngVO);

			if(dept != null) {

				String[] depts = dept.split(",");

				if(depts.length > 0) {

					for(int i = 0; i < depts.length; i++) {

						empMngVO.setEmp_dept(depts[i]);
						mapper.empJisaGrpCreate(empMngVO);
					}
				}
			}

			mapper.empWorkCdDelete(empMngVO);

			if(work_cd != null) {

				String[] work_cds = work_cd.split(",");

				if(work_cds.length > 0) {

					for(int i = 0; i < work_cds.length; i++) {

						empMngVO.setEmp_work_cd(work_cds[i]);
						mapper.empWorkCdCreate(empMngVO);
					}
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

	@Transactional
	@RequestMapping(value="/cnctInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO cnctInfoCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	hashmapParam.put("ent_user_id", member.getUser_id());

		try {
			if(mapper.cnctInfoCreate(hashmapParam) == 1){
				if(mapper.loginChkInfoCreate(hashmapParam) == 1) {
					result.setResultCode("S000");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@RequestMapping(value="/getEmpCnctLogRetrieve")
	public @ResponseBody HashMap<String, Object> getEmpCnctLogRetrieve(@RequestBody HashMap<String, Object> hashmapParam){

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);

			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			list = mapper.getEmpCnctLogRetrieve(hashmapParam);
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
	 *
	  * @Method Name : getAtdChkCodeRetrieve
	  * @Method 설명 : jiyubs
	  * @작성일 : 2022. 1. 10.
	  * @param paramMap
	  * @return :
	  * HashMap<String,Object> :
	 */
	@RequestMapping(value="/getAtdChkCodeRetrieve")
	public @ResponseBody HashMap<String,Object> getAtdChkCodeRetrieve (@RequestBody HashMap<String,Object>paramMap, HttpSession session){
		HashMap<String,Object>resultMap = new HashMap<String,Object>();
		try {
			SessionVO member = (SessionVO) session.getAttribute("S_USER");
			paramMap.put("ACA_ID", member.getAca_id());
			int duplicateChk =mapper.getAtdChkCodeRetrieve(paramMap);
			if(duplicateChk>0) {
				resultMap.put("CHK", "DUPLICATE");
			}else {
				resultMap.put("CHK", "NOT_DUPLICATE");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
