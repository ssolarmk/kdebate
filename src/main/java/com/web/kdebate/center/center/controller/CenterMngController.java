package com.web.kdebate.center.center.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.common.LoginManager;
import com.web.common.util.DateUtil;
import com.web.common.util.HttpUtil;
import com.web.common.util.StringUtil;
import com.web.common.util.ValidateUtil;
import com.web.kdebate.center.center.domain.CenterMngVo;
import com.web.kdebate.center.center.service.CenterMngService;
import com.web.kdebate.common.common.domain.FileVo;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/center/center/centerMng")
public class CenterMngController {

	@Autowired
	private CenterMngService mapper;

	@RequestMapping(value="/view")
	public String view() {
		return "pages/center/center/centerMngView";
	}

	@RequestMapping(value="/getCenterMngListRetrieve")
	public @ResponseBody HashMap<String, Object> getCenterMngListRetrieve(@RequestBody HashMap<String, Object> hashmapParam, HttpSession session){
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
			hashmapParam.put("user_id", member.getUser_id());

			resultList =  mapper.getCenterMngListRetrieve(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

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



	
	
	/**
	 * 학원 select2 목록 조회
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getAcaSrchList", produces = "application/json")
	public @ResponseBody HashMap<String, Object> getAcaSrchList(@RequestBody HashMap<String, Object> hashmapParam) {
		
		List<HashMap<String, String>> listParam = new ArrayList<HashMap<String, String>>();
    	HashMap<String, Object> hashmapResult = new HashMap<String, Object>();	
    	
    	try {
    		
    		listParam = mapper.getAcaSrchList(hashmapParam);
    		hashmapResult.put("data", listParam);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return hashmapResult;
	}
	
	/**
	 * 자매학원 등록
	 * @param hashmapParam
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/allianceInfoCreate")
	public @ResponseBody ReturnDataVO allianceInfoCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session) {
		
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerMngVo.setUpt_user_id(member.getUser_id());
		
		try {
			
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}			
			
			if(centerMngVo.getAlliance_gb().equals("01")) {						
				if(mapper.allianceInfoCreate(centerMngVo) > 0 && mapper.allianceMatchingInfoCreate(centerMngVo) > 0) {
					result.setResultCode("S000");
				}								
			} else if(centerMngVo.getAlliance_gb().equals("02")) {					
				if(mapper.allianceInfoCreate(centerMngVo) > 0) {
					result.setResultCode("S000");
				}							
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}


	
	/**
	 * 본사코드 복사
	 * @param hashmapParam
	 * @param HttpSession
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="centerCodeCopy", method=RequestMethod.POST)
	public @ResponseBody ReturnDataVO centerCodeCopy(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("user_id", member.getUser_id());
		
		if(member.getP_aca_id() != null) {
			hashmapParam.put("p_aca_id", member.getP_aca_id());
		}else {
			hashmapParam.put("p_aca_id", member.getAca_id());
		}
		
		try {
			String codeGb = (String) hashmapParam.get("copy_gb");

			/* 본사코드 복사 */
			if("code".equals(codeGb)) {
				mapper.centerCodeCopy(hashmapParam);
			
			/* 본사레벨 복사 */
			}else if("lvl".equals(codeGb)) {
				mapper.centerLvlCopy(hashmapParam);
			
			/* 본사강의과정 복사 */	
			}else {
				
				if(mapper.getCenterCourseCnt(hashmapParam) == 0) {
					mapper.centerCourseCopy(hashmapParam);
				
				}else {
					result.setResultCode("S999");
					result.setResultMsg("강의과정이 이미 존재합니다.");
					return result;
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
	@RequestMapping(value="/centerInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO centerInfoCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());
    	centerMngVo.setEnt_user_id(member.getUser_id());

    	if(member.getP_aca_id() != null) {
    		centerMngVo.setP_aca_id(member.getP_aca_id());
    	}else {
    		centerMngVo.setP_aca_id(member.getAca_id());
    	}

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			String acaId =  mapper.getNewAcaID();
			centerMngVo.setAca_id(acaId);

			if(mapper.centerInfoCreate(centerMngVo) == 1 && mapper.bossUserCreate(centerMngVo) == 1 && mapper.salesCorpCreate(centerMngVo) == 1 && mapper.empUserCreate(centerMngVo) == 1 && mapper.empAuthCreate(centerMngVo) == 1){

				centerMngVo.setCorp_cd(member.getAca_id());

				if(mapper.acaSalesCorpCreate(centerMngVo) == 1) {

					centerMngVo.setCorp_cd(acaId);

					if(mapper.acaSalesCorpCreate(centerMngVo) == 1) {

						mapper.corpGoodsCreate(centerMngVo);
						mapper.centerLogCreate(centerMngVo);

						result.setResultCode("S000");
					}
				}
			}

			result.setData(acaId);
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
	@RequestMapping(value="/centerInfoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO centerInfoUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}
			if(mapper.centerInfoUpdate(centerMngVo) == 1 && mapper.salesCorpUpdate(centerMngVo) == 1 && mapper.bossUserUpdate(centerMngVo) == 1){

				mapper.centerLogCreate(centerMngVo);

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

	@Transactional
	@RequestMapping(value="/ledgerInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO ledgerInfoCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());
    	centerMngVo.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.ledgerInfoCreate(centerMngVo) == 1){
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

    @Transactional
	@RequestMapping(value="/ledgerInfoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO ledgerInfoUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());


		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.ledgerInfoUpdate(centerMngVo) == 1){
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

	@Transactional
	@RequestMapping(value="/contractInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO contractInfoCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());
    	centerMngVo.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.contractInfoCreate(centerMngVo) == 1){
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

    @Transactional
	@RequestMapping(value="/contractInfoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO contractInfoUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());


		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.contractInfoUpdate(centerMngVo) == 1){
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

	@Transactional
	@RequestMapping(value="/counselInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO counselInfoCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());
    	centerMngVo.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.counselInfoCreate(centerMngVo) == 1){
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

    @Transactional
	@RequestMapping(value="/counselInfoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO counselInfoUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());


		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}
			if(mapper.counselInfoUpdate(centerMngVo) == 1){
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

	@Transactional
	@RequestMapping(value="/prizeInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO prizeInfoCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());
    	centerMngVo.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.prizeInfoCreate(centerMngVo) == 1){
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

    @Transactional
	@RequestMapping(value="/prizeInfoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO prizeInfoUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	centerMngVo.setUpt_user_id(member.getUser_id());


		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}
			if(mapper.prizeInfoUpdate(centerMngVo) == 1){
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
	 * 단말기 등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/posCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO posCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerMngVo.setPos_ent_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.posCreate(centerMngVo) == 1){
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
	 * 단말기 수정
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/posUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO posUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerMngVo.setPos_upt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.posUpdate(centerMngVo) == 1){
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
	 * PG 결제정보 등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/pgInfoCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO pgInfoCreate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerMngVo.setPg_upt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.pgInfoCreate(centerMngVo) == 1 && mapper.smsAuthInfoUpdate(centerMngVo) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

    @Value("${global.fileStorePath}")
	String origin_fileStorePath;

    @RequestMapping(value="ledgerMultiimageUpload")
   	public @ResponseBody ReturnDataVO ledgerMultiimageUpload(MultipartHttpServletRequest multiRequest, HttpServletResponse response, ModelMap model, SessionStatus status, HttpSession session) {
       	String storePathString = origin_fileStorePath;
       	String filePath = "";
       	ReturnDataVO result = new ReturnDataVO();
       	try {
       		SessionVO userSession = (SessionVO) session.getAttribute("S_USER");
       		String data_no = multiRequest.getParameter("ledg_seq");
   			final Map<String, MultipartFile> files = multiRequest.getFileMap();
   			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
   			MultipartFile file;
   			response.setContentType("text/html;charset=UTF-8");
   			while (itr.hasNext()) {

   				FileVo fileVo = new FileVo();
   			    Entry<String, MultipartFile> entry = itr.next();
   			    file = entry.getValue();
   			    System.out.println(file.getName());
   	    		if (!file.isEmpty()) {
   	    			System.out.println(file.getOriginalFilename());

   	   			    File saveFolder = new File(storePathString);

   	    			if (!saveFolder.exists() || saveFolder.isFile()) {
   	    			    saveFolder.mkdirs();
   	    			}

   	    			System.out.println(storePathString);

   	    			File dateFolder = new File(storePathString+data_no);
   	    			if (!dateFolder.exists() || dateFolder.isFile()) {
   	    				dateFolder.mkdirs();
   	    			}

   	    			System.out.println(storePathString+data_no);
   	    			Long _size = file.getSize();
   	    			String orginFileName = file.getOriginalFilename();
   	    			@SuppressWarnings("null")
					int pos = orginFileName.lastIndexOf( "." );
   	    			String fileExt = "application/pdf".equals(file.getContentType()) ? "jpg" : orginFileName.substring( pos + 1 );
   	    			String newName = data_no+"_" + DateUtil.getTimeStamp();
   	    			filePath = dateFolder+File.separator + newName+ "."+fileExt;

   					file.transferTo(new File(filePath));

   		    		fileVo.setUser_id(userSession.getUser_id());
   					fileVo.setBbs_seq(data_no);
   	    			fileVo.setOriginal_nm(orginFileName);
   	    			fileVo.setFile_nm(newName+ "."+fileExt);
   	    			fileVo.setFile_path(filePath);
   	    			fileVo.setSave_path("/upload/"+data_no+"/"+newName+"."+fileExt);
   		    		fileVo.setFile_size(Long.toString(_size));
   		    		result.setData(fileVo);
   		    		result.setResultCode("S000");

   		    		mapper.ledgerDataFileCreate(fileVo);
   			    }
   			}
       	} catch (Exception e) {


       		e.printStackTrace();
       		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
       		result.setResultCode("S999");
   			result.setResultMsg("파일저장에 실패하였습니다.");

   			return result;
   		}
   		return result;
   	}

    @SuppressWarnings("unused")
	@RequestMapping(value="/getLedgerMultiFileList")
    public @ResponseBody ReturnDataVO getLedgerMultiFileList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
    	SessionVO userSession = (SessionVO) session.getAttribute("S_USER");
			try {
				resultList =  mapper.getLedgerMultiFileList(hashmapParam);
				result.setData(resultList);
				result.setResultCode("S000");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
    }

    @SuppressWarnings("unused")
	@RequestMapping(value="/ledgerDataFileDelete")
    public @ResponseBody ReturnDataVO ledgerDataFileDelete(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO userSession = (SessionVO) session.getAttribute("S_USER");
			try {
				mapper.ledgerDataFileDelete(hashmapParam);
				result.setResultCode("S000");
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		return result;
    }

    @RequestMapping(value="/getLedgerDataFileList")
	public @ResponseBody ReturnDataVO getLedgerDataFileList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("upt_user_id", member.getUser_id());

		try {
			list = mapper.getLedgerDataFileList(hashmapParam);
			result.setData(list);
			result.setResultCode("S000");

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

    @SuppressWarnings("null")
	@Transactional
	@RequestMapping(value="/optionInfoUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO optionInfoUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session, MultipartHttpServletRequest multiRequest, HttpServletResponse response){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		String logo_img_path = "";

		centerMngVo.setUpt_user_id(member.getUser_id());

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
				centerMngVo.setLogo_img_path(logo_img_path);
			}

			System.out.println("QQQQQQQ   :    :::   " + centerMngVo);

//			if(mapper.optionInfoUpdate(centerMngVo) == 1){
//				centerMngVo.setAca_id(centerMngVo.getOption_aca_id());
//				if(mapper.centerLogCreate(centerMngVo) == 1) {
//					result.setResultCode("S000");
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

    @Transactional
	@RequestMapping(value="/licInfoUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO licInfoUpdate(@ModelAttribute("CenterMngVo") @Valid CenterMngVo centerMngVo, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		centerMngVo.setUpt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);

			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.licInfoUpdate(centerMngVo) == 1){
				if(mapper.centerLogCreate(centerMngVo) == 1) {
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

    @SuppressWarnings("unchecked")
	@Transactional
	@RequestMapping(value="/smsUidInfoUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO smsUidInfoUpdate(@RequestParam HashMap<String, String> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("upt_user_id", member.getUser_id());

		String url = "http://smsmsgr.ione24.com/_support/mem_check_kdebate.ashx?pkind=1&pid="+ hashmapParam.get("sms_uid") + "&ppwd=" + hashmapParam.get("sms_uid");

		try {
			HashMap<String, String> hashmapResponse = (HashMap<String, String>) HttpUtil.callURL(url, null, null, "EUC_KR");

			if ("200".equals(hashmapResponse.get("httpStatus"))){
				String regYn = String.valueOf(hashmapResponse.get("responseBody"));

				if(regYn.equals("Y")) {
					mapper.acaSmsUidInfoUpdate(hashmapParam);
					result.setResultCode("S000");
				}else {
					result.setResultCode("S111");
				}
			}else{
				result.setResultCode("S999");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

    @RequestMapping(value="/sessionChange")
    public  @ResponseBody ReturnDataVO sessionChange(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request, HttpServletResponse response){
    	ReturnDataVO result = new ReturnDataVO();
    	String isLogon 	= (String) session.getAttribute("S_LOGIN_YN");
		SessionVO loginUserVo =  (SessionVO) session.getAttribute("S_USER");

		String org_id = loginUserVo.getUser_id();

		String org_yn = StringUtil.nullCheck((String) hashmapParam.get("org_yn"));


		if(isLogon != null && isLogon.equals("Y") && loginUserVo != null && !("").equals(loginUserVo.getUser_id()))
		{
			session.setAttribute("S_USER", 	null);
			session.setAttribute("S_LOGIN_YN", 	null);

			session.removeAttribute("S_USER");
			session.removeAttribute("S_LOGIN_YN");

			//쿠키 제거
			for(Cookie cookie : request.getCookies())
			{
				if(cookie.getName().startsWith("owra_c_"))
				{
					cookie.setValue(null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}

			session.invalidate();

		}


    	HttpSession session2 = request.getSession(true);
		loginUserVo = new SessionVO();
		LoginManager loginManager = LoginManager.getInstance();
		loginUserVo = mapper.getUserRetrieve(hashmapParam);

		List<String> authGrp = new ArrayList<String>();
		authGrp = (List<String>) mapper.getUserGrpRetrieve(hashmapParam);
		loginUserVo.setUser_group(authGrp);
		List<HashMap<String, Object>> menuList = new ArrayList<HashMap<String,Object>>();
		menuList = mapper.getMenuRetrieve(loginUserVo);

		loginUserVo.setMenu(menuList);
		session2.setAttribute("S_USER", loginUserVo);
		session2.setAttribute("S_LOGIN_YN"	, "Y");
		session2.setAttribute("theme", "default");
		if(!"Y".equals(org_yn)){
			session2.setAttribute("org_id", org_id);
		}
		loginManager.setSession(session2, (String) loginUserVo.getUser_id());
		return result ;
    }
    
    /**
     * 자매학원 세션변경(로그인)
     * @param hashmapParam
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/allianceSessionChg")
    public  @ResponseBody ReturnDataVO allianceSessionChg(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request, HttpServletResponse response){
    	
    	ReturnDataVO result = new ReturnDataVO();
    	String isLogon 	= (String) session.getAttribute("S_LOGIN_YN");
		SessionVO loginUserVo =  (SessionVO) session.getAttribute("S_USER");
		
		try {
			
			if(isLogon != null && isLogon.equals("Y") && loginUserVo != null && !("").equals(loginUserVo.getUser_id()))
			{				
				loginUserVo.setAca_id(hashmapParam.get("aca_id").toString());
				loginUserVo.setAca_nm(hashmapParam.get("aca_nm").toString());				
				
				HttpSession session2 = request.getSession(true);
				session2.setAttribute("S_USER", loginUserVo);
			}
						
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
    	
    }
}
