package com.web.kdebate.student.students.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
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
import org.springframework.web.servlet.ModelAndView;

import com.web.common.util.DateUtil;
import com.web.common.util.StringUtil;
import com.web.common.util.ValidateUtil;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.student.students.domain.CreateStudentInfoVO;
import com.web.kdebate.student.students.domain.StudentInfoMngVO;
import com.web.kdebate.student.students.service.StudentInfoMngService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(value="/student/students/studentInfoMng")
public class StudentInfoMngController {

	@Value("${global.fileStorePath}")
	String origin_fileStorePath;

	@Autowired
	private StudentInfoMngService mapper;

	@RequestMapping(value="/infoView")
	//public ModelAndView infoView(@RequestParam(value="std_id", required=true) String std_id) {
	public @ResponseBody ReturnDataVO infoView(@RequestParam(value="std_id", required=true) String std_id) {
		//ModelAndView mv = new ModelAndView();
		ReturnDataVO result = new ReturnDataVO();

		HashMap<String, Object> stdInfo = new HashMap<>();
		stdInfo = mapper.getStudentDtlRetrieve(std_id);

		// mv.addObject("std_id", std_id);
		// mv.addObject("stdMap", stdInfo);
		// mv.setViewName("/single/student/students/studentInfoMngView");
		result.setData(stdInfo);
		result.setResultCode("S000");	

		//return mv;
		return result;
	}

	@RequestMapping(value="/getSchdYearList")
	public @ResponseBody ReturnDataVO getSchdYearList(){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getSchdYearList();
			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value="/getInvoicePaymentListRetrieve")
	public @ResponseBody ReturnDataVO getInvoicePaymentListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpServletRequest request){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list =  mapper.getInvoicePaymentListRetrieve(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/getMngClassList")
	public @ResponseBody ReturnDataVO getMngClassList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {

			hashmapParam.put("my_aca_id", member.getAca_id());
			hashmapParam.put("p_aca_id", member.getP_aca_id());

			list = mapper.getMngClassList(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/getCorpListRetrieve")
	public @ResponseBody ReturnDataVO getCorpListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getCorpListRetrieve(hashmapParam);

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
	 * 청구 항목 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getGoodsListRetrieve")
	public @ResponseBody ReturnDataVO getGoodsListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getGoodsListRetrieve(hashmapParam);

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
	 * 원생 상담자 목록 조회
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getCnslUserListRetrieve")
	public @ResponseBody ReturnDataVO getCnslUserListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){
		
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		try {
			list = mapper.getCnslUserListRetrieve(hashmapParam);
			
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
	 * 원생정보 등록
	 * @param CreateStudentInfoVO
	 * @param BindingResult
	 * @param HttpSession
	 * @param MultipartHttpServletRequest
	 * @param HttpServletResponse
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/studentInfoCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO studentInfoCreate(@ModelAttribute("CreateStudentInfoVO") @Valid CreateStudentInfoVO createStdInfoVO, BindingResult bindResult, HttpSession session, MultipartHttpServletRequest multiRequest, HttpServletResponse response) {
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		
		String std_url = "";
		try {

			if (multiRequest.getFile("std_url") != null) {
			    MultipartFile file;
			    file = multiRequest.getFile("std_url");
	
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
			    std_url = "/upload/"+date+"/"+newName+"."+fileExt;
			}
	
			if(!std_url.equals("")) {
				createStdInfoVO.setStd_url(std_url);
			}

			String std_id = createStdInfoVO.getInfo_std_id();
			//std_id 자동 생성
			if("".equals(std_id)) {
				 std_id = mapper.getStdId();
			}
			
			if(mapper.chkUserId(std_id) == 0) {
				
				createStdInfoVO.setStd_use_yn("Y");
				
				/* 원생 생성 */
				createStdInfoVO.setInfo_std_id(std_id);
				createStdInfoVO.setEnt_user_id(member.getUser_id());

				if("".equals(createStdInfoVO.getInfo_std_pwd())) {
					createStdInfoVO.setInfo_std_pwd(std_id);
				}

				mapper.userInfoCreate(createStdInfoVO);
				mapper.studentInfoCreate(createStdInfoVO);
				mapper.stdClassInfoCreate(createStdInfoVO);
				mapper.stdInfoChgLogCreate(createStdInfoVO);

				/* 학부모 생성 */
				String std_parent_id = mapper.getStdParentId();
				
				if(!"".equals(createStdInfoVO.getParent_hp_no())) {
						
					if(!"".equals(createStdInfoVO.getC_parent_id()) && createStdInfoVO.getC_parent_id() != null ) {
						createStdInfoVO.setParent_id(createStdInfoVO.getC_parent_id());
					} else {
						//createStdInfoVO.setParent_id("P" + createStdInfoVO.getParent_hp_no());
						createStdInfoVO.setParent_id(std_parent_id);
					}

					// 학부모 연결
					if("C".equals(createStdInfoVO.getParent_hp_no_cnfm())) {
						
						StudentInfoMngVO studentMngVO = new StudentInfoMngVO();
						studentMngVO.setParent_reg_user_id(createStdInfoVO.getParent_id());
						
						List<HashMap<String, Object>> stdBroList = mapper.stdBroList(studentMngVO);

						if(stdBroList.size() > 0) {
							for(int i=0; i< stdBroList.size(); i++) {
								
								String bro_std_id = stdBroList.get(i).get("bro_std_id").toString();
								
								createStdInfoVO.setStd_bro_id(bro_std_id);

								mapper.stdBroCreate(createStdInfoVO);
								mapper.broStdCreate(createStdInfoVO);
							}
						}
					}else {						
						mapper.createParentInfo(createStdInfoVO);
					}

					mapper.createParent(createStdInfoVO);

				}else {
					
					if(!"".equals(createStdInfoVO.getRep_parent_nm())) {
						createStdInfoVO.setParent_id(std_parent_id);

						mapper.createParentInfo(createStdInfoVO);
						mapper.createParent(createStdInfoVO);
					}					
				}
				
				result.setData(createStdInfoVO.getInfo_std_id());
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
	 * 원생정보 수정
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/studentInfoUpdate")
	public@ResponseBody ReturnDataVO studentInfoUpdate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		String memo_yn = (String) hashmapParam.get("memo_yn");
		if("Y".equals(memo_yn)) {
			try {
				mapper.stdMemoUpdate(hashmapParam);
				result.setResultCode("S000");
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				result.setResultCode("S999");
				result.setResultMsg("에러가 발생하였습니다.");
			}
			return result;
		}
		
		String std_url = "";
		String std_mng_file = "";
		String[] status_chk = {"01", "04", "06"};

		try {
			if(!std_url.equals("")) {
				hashmapParam.put("std_url", std_url);
			}

			if(!std_mng_file.equals("")) {
				hashmapParam.put("std_mng_file", std_mng_file);
			}

			hashmapParam.put("upt_user_id", member.getUser_id());

			String flag = "N";

			//if(hashmapParam.get("cnct_pwd").equals("")) {
			//	hashmapParam.put("cnct_pwd", "1234");
			//	flag = "Y";
			//}
			
			String std_status = hashmapParam.get("std_status_hid").toString();			
			if(!Arrays.asList(status_chk).contains(std_status)) {
				hashmapParam.put("class_seat_yn", "N");
				hashmapParam.put("class_room", "");
				hashmapParam.put("room_seat_no", "");
			} else {
				// 지정석 관리  :: 재원(재원, 휴원대기, 퇴원대기) 상태 아닐 경우 지정석 해제 및 지정석 좌석 업데이트	
				if(hashmapParam.get("class_seat_yn") != null) {
					String class_seat_yn = hashmapParam.get("class_seat_yn").toString();
					
					if(class_seat_yn.equals("Y")) {
						
						// 기존 지정좌석 원생 있는지 체크
						String seatStdId = mapper.getStdSeatInfo(hashmapParam);
						
						if(seatStdId != null && !seatStdId.equals("")) {
							if(!seatStdId.equals(hashmapParam.get("std_id").toString())) {
								HashMap<String, Object> map = new HashMap<String, Object>();
								
								map.put("stdId", seatStdId);
								map.put("class_room", hashmapParam.get("class_room"));
								map.put("room_seat_no", hashmapParam.get("room_seat_no"));
								map.put("upt_user_id", member.getUser_id());

								// 지정좌석 해제
								mapper.updateRoomSeatInfo(map);
							}
						}
																		
					}
				}				
			}
			
			mapper.studentInfoUpdate(hashmapParam);
			mapper.userInfoUpdate(hashmapParam);
			//mapper.studyLinckInfoUpdate(hashmapParam);
			
			if(hashmapParam.get("parent_hp_no") != null && !hashmapParam.get("parent_hp_no").equals("")) {
				if(hashmapParam.get("rep_parent_id") != null && !hashmapParam.get("rep_parent_id").equals("")) {
					mapper.parentUserInfoUpdate(hashmapParam);
				} else {
					
					if(!hashmapParam.get("c_parent_id").equals("") && hashmapParam.get("c_parent_id") != null) {
						hashmapParam.put("parent_id", hashmapParam.get("c_parent_id"));

						StudentInfoMngVO studentInfoMngVO = new StudentInfoMngVO();
						
						studentInfoMngVO.setParent_reg_user_id(hashmapParam.get("c_parent_id").toString());
						List<HashMap<String, Object>> stdBroList = mapper.stdBroList(studentInfoMngVO);
						
						if(stdBroList.size() > 0) {
							for(int i=0; i< stdBroList.size(); i++) {
								String bro_std_id = stdBroList.get(i).get("bro_std_id").toString();
								studentInfoMngVO.setBro_find_info_std_id(bro_std_id);
								studentInfoMngVO.setBro_find_user_id(hashmapParam.get("std_id").toString());
								studentInfoMngVO.setBro_find_resp_type_cd("01");

								mapper.broCreate(studentInfoMngVO);
								mapper.broSameCreate(studentInfoMngVO);
							}
						}
						
					} else {
						hashmapParam.put("parent_id", "P" + hashmapParam.get("parent_hp_no"));						
						mapper.stdParentInfoCreate(hashmapParam);
					}
					
					hashmapParam.put("sms_cont_tp", "01,02,03,04,06,05,09,10,11");
					mapper.stdParentCreate(hashmapParam);
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

	@RequestMapping(value="/getParentHpNoConfirm")
	public @ResponseBody ReturnDataVO getParentHpNoConfirm(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	ReturnDataVO result = new ReturnDataVO();

		int chk_cnt = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> parentInfo = new ArrayList<HashMap<String, Object>>();

		try {
									
			chk_cnt = mapper.getParentHpNoConfirm(hashmapParam);
				
			if(chk_cnt > 0) {
				hashmapParam.put("aca_id", member.getAca_id());
				parentInfo = mapper.getParentInfo(hashmapParam);

				int parentCnt = parentInfo.size();
				if(parentInfo != null && !parentInfo.isEmpty()) {
					if(parentCnt > 1) {
						resultMap.put("parentCnfm", "Y");
						resultMap.put("parentList", "Y");
						resultMap.put("parentList", parentInfo);
							
					} else {
						resultMap.put("parentCnfm", "Y");
						resultMap.put("parentId", parentInfo.get(0).get("parent_id"));
						resultMap.put("parentNm", parentInfo.get(0).get("parent_nm"));
					}
				} else {
					resultMap.put("parentCnfm", "N");
				}

			} else {
				resultMap.put("parentCnfm", "N");
			}

			resultMap.put("chkCnt", chk_cnt);

			result.setResultCode("S000");
			result.setData(resultMap);
			

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/getStdAtdCdConfirm")
	public @ResponseBody ReturnDataVO getStdAtdCdConfirm(@RequestParam HashMap<String, Object> hashmapParam){
		ReturnDataVO result = new ReturnDataVO();
		int chk_cnt = 0;

		try {
			chk_cnt = mapper.getStdAtdCdConfirm(hashmapParam);

			result.setResultCode("S000");
			result.setData(chk_cnt);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/getStdCnslListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdCnslListRetrieve(@RequestBody HashMap<String, Object> hashmapParam, HttpSession session){
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

			hashmapParam.put("aca_id", member.getAca_id());

			resultList =  mapper.getStdCnslListRetrieve(hashmapParam);
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

	@Transactional
	@RequestMapping(value="/cnslInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO cnslInfoCreate(@ModelAttribute("StudentMngVO") @Valid StudentInfoMngVO studentMngVO, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	studentMngVO.setUpt_user_id(member.getUser_id());
    	studentMngVO.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.cnslInfoCreate(studentMngVO) == 1){
				if(studentMngVO.getCnsl_sms_yn() != null && studentMngVO.getCnsl_sms_yn().equals("on")) {
					HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
					hashmapParam.put("std_id", studentMngVO.getCnsl_std_id());

					String parent_id = mapper.getParentId(hashmapParam);

					if(parent_id != null) {
						String title = studentMngVO.getCnsl_dt() + " " + studentMngVO.getCnsl_std_nm() + " 학생 상담정보";
						String cnts = title + "\n";
							   cnts += "\n";
							   cnts += studentMngVO.getCnsl_cnts();

						hashmapParam.put("invoice_nm", title);
						hashmapParam.put("aca_id", member.getAca_id());
						hashmapParam.put("parent_id", parent_id);
						hashmapParam.put("cnts", cnts);
						hashmapParam.put("ent_user_id", studentMngVO.getEnt_user_id());
						hashmapParam.put("sms_cont_tp", "05");

						if(cnts.getBytes("EUC-kr").length > 80) {
							hashmapParam.put("mt_type", "LM");
						} else {
							hashmapParam.put("mt_type", "SM");
						}

						mapper.smsInfoCreate(hashmapParam);
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

    @Transactional
	@RequestMapping(value="/cnslInfoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO cnslInfoUpdate(@ModelAttribute("StudentMngVO") @Valid StudentInfoMngVO studentMngVO, BindingResult bindResult, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	studentMngVO.setUpt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}
			if(mapper.cnslInfoUpdate(studentMngVO) == 1){
				
				if(studentMngVO.getCnsl_sms_yn() != null && studentMngVO.getCnsl_sms_yn().equals("on")) {
					HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
					hashmapParam.put("std_id", studentMngVO.getCnsl_std_id());

					String parent_id = mapper.getParentId(hashmapParam);

					if(parent_id != null) {
						String title = studentMngVO.getCnsl_dt() + " " + studentMngVO.getCnsl_std_nm() + " 학생 상담정보";
						String cnts = title + "\n";
							   cnts += "\n";
							   cnts += studentMngVO.getCnsl_cnts();

						hashmapParam.put("invoice_nm", title);
						hashmapParam.put("aca_id", member.getAca_id());
						hashmapParam.put("parent_id", parent_id);
						hashmapParam.put("cnts", cnts);
						hashmapParam.put("ent_user_id", studentMngVO.getEnt_user_id());
						hashmapParam.put("sms_cont_tp", "05");

						if(cnts.getBytes("EUC-kr").length > 80) {
							hashmapParam.put("mt_type", "LM");
						} else {
							hashmapParam.put("mt_type", "SM");
						}

						mapper.smsInfoCreate(hashmapParam);
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

	@RequestMapping(value="/getStdInvoiceListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdInvoiceListRetrieve(@RequestBody HashMap<String, Object> hashmapParam, HttpServletRequest request){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdInvoiceListRetrieve(hashmapParam);

			String url = "";
			String url2 = "";

			for(HashMap<String, Object> map : resultList) {

				url = "";
				url2 = "";

				if(map.get("invoice_enc") != null) {
					url = request.getRequestURL().toString().replace(request.getRequestURI(),"");
					url2 = "/pay/payMngOnlineMnul/".concat(map.get("invoice_enc").toString());
					url = url.concat(url2);
				}

				map.put("invoice_url", url);
			}

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

	@RequestMapping(value="/getStdInvoiceDtlListRetrieve")
	public @ResponseBody ReturnDataVO getStdInvoiceDtlListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getStdInvoiceDtlListRetrieve(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@RequestMapping(value="/invoiceInfoCreate", method= RequestMethod.POST)
	public @ResponseBody ReturnDataVO invoiceDtlInfoCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		String invoice_no = mapper.getInvNo();

		Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;

		try {
			for(HashMap<String, Object> map : list) {
				map.put("upt_user_id", member.getUser_id());
				map.put("ent_user_id", member.getUser_id());
				map.put("invoice_no", invoice_no);

				mapper.invoiceDtlInfoCreate(map);
			}

			list.get(0).put("upt_user_id", member.getUser_id());
			list.get(0).put("ent_user_id", member.getUser_id());
			list.get(0).put("invoice_no", invoice_no);
			list.get(0).put("invoice_enc", invoice_no);

			mapper.invoiceInfoCreate(list.get(0));

			String parent_id = mapper.getParentId(list.get(0));

			if(parent_id != null) {
				list.get(0).put("parent_id", parent_id);

				String url = request.getRequestURL().toString().replace(request.getRequestURI(),"");

				String url2 = "/pay/payMngOnlineMnul/".concat(list.get(0).get("invoice_enc").toString());
					   url = url.concat(url2);

				String cnts	= list.get(0).get("invoice_nm").toString() + "\n";
					   cnts += "\n";
					   cnts += "안녕하세요. "+ list.get(0).get("std_nm").toString() +" 학부모님" + "\n";
					   cnts += list.get(0).get("aca_nm").toString() + "입니다. "+ "\n";
					   cnts += list.get(0).get("invoice_nm").toString() +"를 발행하였습니다." + "\n";
					   cnts += list.get(0).get("aca_nm").toString() + "에 내방하셔서 결제하시거나 아래 링크를 클릭하셔서 결제하실 수 있습니다." + "\n";
					   cnts += "\n";
					   cnts += "결제 하기 : " + url + "\n";
					   cnts += "\n";
					   cnts += "감사합니다.";

				list.get(0).put("cnts", cnts);

				String noti_seq = mapper.getNotiSeq();
				list.get(0).put("noti_seq", noti_seq);

				mapper.notiInfoCreate(list.get(0));
				mapper.notiReadCreate(list.get(0));

				if(list.get(0).get("noti_yn").equals("Y")) {
					if(cnts.getBytes("EUC-kr").length > 80) {
						list.get(0).put("mt_type", "LM");
					} else {
						list.get(0).put("mt_type", "SM");
					}

					if(list.get(0).get("reserve").equals("Y")) {
						list.get(0).put("reserved_date", list.get(0).get("issue_dt"));
					}

					list.get(0).put("sms_cont_tp", "02");

					mapper.smsInfoCreate(list.get(0));
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
	@RequestMapping(value="/invoiceInfoUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO invoiceInfoUpdate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;

		try {
			String invoice_no = list.get(0).get("invoice_no").toString();

			if(mapper.getInvoicePayCheck(invoice_no) == 0) {

				mapper.invoiceDtlDelete(list.get(0));

				for(HashMap<String, Object> map : list) {
					map.put("upt_user_id", member.getUser_id());
					map.put("ent_user_id", member.getUser_id());
					mapper.invoiceDtlInfoCreate(map);
				}

				list.get(0).put("upt_user_id", member.getUser_id());
				mapper.invoiceInfoUpdate(list.get(0));

				String parent_id = mapper.getParentId(list.get(0));

				if(parent_id != null) {
					list.get(0).put("parent_id", parent_id);

					String url = request.getRequestURL().toString().replace(request.getRequestURI(),"");

					String url2 = "/pay/payMngOnlineMnul/".concat(invoice_no);
						   url = url.concat(url2);

					String cnts	= list.get(0).get("invoice_nm").toString() + "\n";
						   cnts += "\n";
						   cnts += "안녕하세요. "+ list.get(0).get("std_nm").toString() +" 학부모님" + "\n";
						   cnts += list.get(0).get("aca_nm").toString() + "입니다. "+ "\n";
						   cnts += list.get(0).get("invoice_nm").toString() +"를 발행하였습니다." + "\n";
						   cnts += list.get(0).get("aca_nm").toString() + "에 내방하셔서 결제하시거나 아래 링크를 클릭하셔서 결제하실 수 있습니다." + "\n";
						   cnts += "\n";
						   cnts += "결제 하기 : " + url + "\n";
						   cnts += "\n";
						   cnts += "감사합니다.";

					list.get(0).put("cnts", cnts);

					String noti_seq = mapper.getNotiSeq();
					list.get(0).put("noti_seq", noti_seq);

					mapper.notiInfoCreate(list.get(0));
					mapper.notiReadCreate(list.get(0));

					if(list.get(0).get("noti_yn").equals("Y")) {
						if(cnts.getBytes("EUC-kr").length > 80) {
							list.get(0).put("mt_type", "LM");
						} else {
							list.get(0).put("mt_type", "SM");
						}

						list.get(0).put("sms_cont_tp", "02");

						mapper.smsInfoCreate(list.get(0));
					}
				}

				result.setResultCode("S000");
			}else {
				result.setResultCode("S998");
				result.setResultMsg("결제된 청구서는 수정이 불가합니다.");
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
	@RequestMapping(value="/invoicePayYnUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO invoicePayYnUpdate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {
			hashmapParam.put("upt_user_id", member.getUser_id());
			mapper.invoicePayYnUpdate(hashmapParam);

			result.setResultCode("S000");

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@RequestMapping(value="/getStdParentsListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdParentsListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdParentsListRetrieve(hashmapParam);
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

	@RequestMapping(value="/getStdParentInfoRetrieve")
	public @ResponseBody ReturnDataVO getStdParentInfoRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getStdParentInfoRetrieve(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/getStdParentsSrchListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdParentsSrchListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
            hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdParentsSrchListRetrieve(hashmapParam);
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

	@RequestMapping(value="/getRepParentChk")
	public @ResponseBody ReturnDataVO getRepParentChk(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getRepParentChk(hashmapParam);
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
	@RequestMapping(value="/parentsCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO parentsCreate(@ModelAttribute("StudentMngVO") @Valid StudentInfoMngVO studentMngVO, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	studentMngVO.setUpt_user_id(member.getUser_id());
    	studentMngVO.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.parentsCreate(studentMngVO) == 1){
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
	@RequestMapping(value="/parentInfoCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO parentInfoCreate(@ModelAttribute("StudentMngVO") @Valid StudentInfoMngVO studentMngVO, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	studentMngVO.setAca_id(member.getAca_id());
    	studentMngVO.setUpt_user_id(member.getUser_id());
    	studentMngVO.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}
			
			String parentId = mapper.getStdParentId();
			
			if(!studentMngVO.getParent_reg_hp_no().equals("")) {
								
				studentMngVO.setParent_reg_hp_no(studentMngVO.getParent_reg_hp_no());	
				if(!studentMngVO.getParent_reg_user_id().equals("") && studentMngVO.getParent_reg_user_id() != null) {
					studentMngVO.setParent_id(studentMngVO.getParent_reg_user_id());
				} else {
					//studentMngVO.setParent_id("P" + studentMngVO.getParent_reg_hp_no());
					studentMngVO.setParent_id(parentId);
				}
				
				if(studentMngVO.getParent_reg_hp_no_cnfm().equals("C")) {	
					
					List<HashMap<String, Object>> stdBroList = mapper.stdBroList(studentMngVO);
					
					if(stdBroList.size() > 0) {
						for(int i=0; i< stdBroList.size(); i++) {
							String bro_std_id = stdBroList.get(i).get("bro_std_id").toString();
							studentMngVO.setBro_find_info_std_id(bro_std_id);
							studentMngVO.setBro_find_user_id(studentMngVO.getParent_reg_std_id());
							studentMngVO.setBro_find_resp_type_cd("01");

							mapper.broCreate(studentMngVO);
							mapper.broSameCreate(studentMngVO);
						}
					}
					
				} else {
					mapper.parentInfoCreate(studentMngVO);
				}

				if(studentMngVO.getParent_yn().equals("Y")) {
					mapper.updateRepParentInfo(studentMngVO);
				}
				
				mapper.parentCreate(studentMngVO);
				
				result.setResultCode("S000");
				
			} else {
				
				studentMngVO.setParent_id(parentId);
				studentMngVO.setParent_reg_user_id(parentId);

				if(studentMngVO.getParent_yn().equals("Y")) {
					mapper.updateRepParentInfo(studentMngVO);
				}

				if(mapper.parentInfoCreate(studentMngVO) == 1 && mapper.parentCreate(studentMngVO) == 1){
					result.setResultCode("S000");
				}
			}

		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("이미 등록된 번호입니다.");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }

	@Transactional
	@RequestMapping(value="/parentInfoUpdate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO parentInfoUpdate(@ModelAttribute("StudentMngVO") @Valid StudentInfoMngVO studentMngVO, BindingResult bindResult, HttpSession session, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	studentMngVO.setEnt_user_id(member.getUser_id());
    	studentMngVO.setUpt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			/* 연결 학부모인 경우 */
			if("C".equals(studentMngVO.getParent_reg_hp_no_cnfm())) {
				List<HashMap<String, Object>> stdBroList = new ArrayList<HashMap<String,Object>>();
				
				/* 기존 형제/학부모 삭제 */
				String prev_user_id = studentMngVO.getParent_prev_user_id();
				String reg_user_id = studentMngVO.getParent_reg_user_id();

				studentMngVO.setParent_reg_user_id(prev_user_id);
				
				stdBroList = mapper.stdBroList(studentMngVO);
				
				if(stdBroList.size() > 0) {
					
					HashMap<String, Object> delMap = new HashMap<String, Object>();
					
					for(int i=0; i< stdBroList.size(); i++) {
						delMap.put("bro_std_id", stdBroList.get(i).get("bro_std_id").toString());
						delMap.put("std_id", studentMngVO.getParent_reg_std_id());
						
						mapper.broDelete(delMap);
						mapper.broSameDelete(delMap);
					}
				}
				studentMngVO.setParent_reg_user_id(reg_user_id);
				
				mapper.cParentDelete(studentMngVO);

				/* 형제/학부모 연결 */
				stdBroList = mapper.stdBroList(studentMngVO);
				
				if(stdBroList.size() > 0) {
					for(int i=0; i< stdBroList.size(); i++) {
						String bro_std_id = stdBroList.get(i).get("bro_std_id").toString();
						studentMngVO.setBro_find_info_std_id(bro_std_id);
						studentMngVO.setBro_find_user_id(studentMngVO.getParent_reg_std_id());
						studentMngVO.setBro_find_resp_type_cd("01");

						mapper.broCreate(studentMngVO);
						mapper.broSameCreate(studentMngVO);
					}
				}

				mapper.cParentCreate(studentMngVO);
				
			}else {
				 
				if(studentMngVO.getParent_yn().equals("Y")) {
					mapper.updateRepParentInfo(studentMngVO);
				}
				
				mapper.parentInfoUpdate(studentMngVO);
				mapper.parentUpdate(studentMngVO);
			}
			
			/* 
			if(mapper.parentInfoUpdate(studentMngVO) == 1 && mapper.parentUpdate(studentMngVO) == 1){
				result.setResultCode("S000");
			} */
			
			result.setResultCode("S000");
			
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
    	return result;
    }
	
	@RequestMapping(value="/getRepParentInfo")
	public @ResponseBody ReturnDataVO getRepParentInfo(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getRepParentInfo(hashmapParam);
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
	 * 원생상세 수강정보 목록
	 * @param hashmapParam
	 * @param pageing
	 * @return
	 */
	@RequestMapping(value="/getStdLectureList")
	public @ResponseBody HashMap<String, Object> getStdLectureList(@RequestBody HashMap<String, Object> hashmapParam, HttpSession session) {
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());
		
		try {

			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList = mapper.getStdLectureList(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", resultList);

		} catch(Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;
	}
	
	/**
	 * 원생 자매학원 수강정보 목록
	 * @param hashmapParam
	 * @param pageing
	 * @return
	 */
	@RequestMapping(value="/getStdAllianceLectureList")
	public @ResponseBody HashMap<String, Object> getStdAllianceLectureList(@RequestBody HashMap<String, Object> hashmapParam, HttpSession session) {
		
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());
		
		try {
			
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());
			
			resultList = mapper.getStdAllianceLectureList(hashmapParam);
			int records =  mapper.getQueryTotalCnt();			
			
			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", resultList);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return hashmapResult;
		
	}

	@RequestMapping(value="/getStdBroListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdBroListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdBroListRetrieve(hashmapParam);
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

	@RequestMapping(value="/getStdBroSrchListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdBroSrchListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdBroSrchListRetrieve(hashmapParam);
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

	@Transactional
	@RequestMapping(value="/broCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO broCreate(@ModelAttribute("StudentMngVO") @Valid StudentInfoMngVO studentMngVO, BindingResult bindResult, HttpSession session
    		, HttpServletRequest request){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	studentMngVO.setEnt_user_id(member.getUser_id());

		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}

			if(mapper.broCreate(studentMngVO) == 1 && mapper.broSameCreate(studentMngVO) == 1){
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
	@RequestMapping(value="/broDelete", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO changeStudent(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();

		Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;

		try {

			for(HashMap<String, Object> map : list) {
				mapper.broDelete(map);
				mapper.broSameDelete(map);
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

    @RequestMapping(value="/getStdMsgListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdMsgListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		try {
			hashmapParam.put("aca_id", member.getAca_id());
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdMsgListRetrieve(hashmapParam);
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

    @RequestMapping(value="/getGoodsInfo")
	public @ResponseBody ReturnDataVO getGoodsInfo(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> map = new HashMap<String, Object>();

		try {
			map = mapper.getGoodsInfo(hashmapParam);

			result.setResultCode("S000");
			result.setData(map);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

    @RequestMapping(value="/getLessonEndDate")
	public @ResponseBody ReturnDataVO getLessonEndDate(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getLessonEndDate(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

    @RequestMapping(value="/getStdChgLogListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdChgLogListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdChgLogListRetrieve(hashmapParam);
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
    @RequestMapping(value="/getStdAtdListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdAtdListRetrieve(@RequestBody HashMap<String, Object> hashmapParam){
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdAtdListRetrieve(hashmapParam);
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
     * 원생 출석정보 목록
     * @param hashmapParam
     * @param pageing
     * @return
     */
    @RequestMapping(value="/getStdAtdListDtlRetrieve")
    public @ResponseBody HashMap<String, Object> getStdAtdListDtlRetrieve(@RequestBody HashMap<String, Object> hashmapParam) {

    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

    	try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList =  mapper.getStdAtdListDtlRetrieve(hashmapParam);
			int records =  mapper.getQueryTotalCnt();

			pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", resultList);

    	} catch(Exception e) {
    		e.printStackTrace();
    	}


    	return hashmapResult;
    }


    @RequestMapping(value="/getChgReasonList")
   	public @ResponseBody ReturnDataVO getChgReasonList(@RequestParam HashMap<String, Object> hashmapParam){

   		ReturnDataVO result = new ReturnDataVO();
   		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

   		try {
   			list = mapper.getChgReasonList(hashmapParam);

   			result.setResultCode("S000");
   			result.setData(list);

   		} catch (Exception e) {
   			result.setResultMsg("에러가 발생하였습니다.");
   			result.setResultCode("S999");
   			e.printStackTrace();
   		}
   		return result;
   	}

    @RequestMapping(value="/getStdAtdLeftCnt")
	public @ResponseBody ReturnDataVO getStdAtdLeftCnt(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();

		int left_cnt = 0;

		try {
			left_cnt = mapper.getStdAtdLeftCnt(hashmapParam);

			result.setResultCode("S000");
			result.setData(left_cnt);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

    @Transactional
	@RequestMapping(value="/stdAtdRestore", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO stdAtdRestore(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {
			hashmapParam.put("ent_user_id", member.getUser_id());
			hashmapParam.put("return_dt", null);

			if(mapper.chgLogInfoCreate(hashmapParam) == 1) {
				mapper.stdAtdInfoCreate(hashmapParam);
				mapper.stdAtdInfoUpdate(hashmapParam);

				String invoiceNo = mapper.getMaxInvoiceNo(hashmapParam);
				hashmapParam.put("invoice_no", invoiceNo);

				mapper.stdLernDtlUpdate(hashmapParam);
				mapper.stdStatusUpdate(hashmapParam);

				HashMap<String, Object> callPrcSyncUserParam = new HashMap<String, Object>();

				callPrcSyncUserParam.put("mode", "U");
				callPrcSyncUserParam.put("userId", hashmapParam.get("chg_log_std_id"));
				callPrcSyncUserParam.put("resultCode", "");
				callPrcSyncUserParam.put("resultMsg", "");

				mapper.callPrcSyncUser(callPrcSyncUserParam);

				if((Integer)callPrcSyncUserParam.get("resultCode") == 0) {
					result.setResultCode("S000");
				}else {
					result.setResultCode("S999");
					result.setResultMsg((String)callPrcSyncUserParam.get("resultMsg"));
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

    @Transactional
	@RequestMapping(value="/stdAtdNotRestore", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO stdAtdNotRestore(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {
			hashmapParam.put("ent_user_id", member.getUser_id());
			hashmapParam.put("return_dt", null);

			if(mapper.chgLogInfoCreate(hashmapParam) == 1) {

				mapper.stdAtdInfoUpdate(hashmapParam);
				//mapper.stdLernDtlUpdate(hashmapParam);
				mapper.stdStatusUpdate(hashmapParam);

				HashMap<String, Object> callPrcSyncUserParam = new HashMap<String, Object>();

				callPrcSyncUserParam.put("mode", "U");
				callPrcSyncUserParam.put("userId", hashmapParam.get("chg_log_std_id"));
				callPrcSyncUserParam.put("resultCode", "");
				callPrcSyncUserParam.put("resultMsg", "");

				mapper.callPrcSyncUser(callPrcSyncUserParam);

				if((Integer)callPrcSyncUserParam.get("resultCode") == 0) {
					result.setResultCode("S000");
				}else {
					result.setResultCode("S999");
					result.setResultMsg((String)callPrcSyncUserParam.get("resultMsg"));
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

    @Transactional
   	@RequestMapping(value="/chgLogInfoCreate", method= RequestMethod.POST)
   	public@ResponseBody ReturnDataVO chgLogInfoCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

   		ReturnDataVO result = new ReturnDataVO();
   		SessionVO member = (SessionVO) session.getAttribute("S_USER");
   		hashmapParam.put("ent_user_id", member.getUser_id());

   		try {
   			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
   			Date time = new Date();
   			String today = fmt.format(time);

   			Date now_dt = fmt.parse(today);
   			Date chg_dt = fmt.parse(hashmapParam.get("chg_dt").toString());

   			int compare = chg_dt.compareTo(now_dt);

			if(compare < 0) {
				hashmapParam.put("compare_now", "N");
			}else {
				hashmapParam.put("compare_now", "Y");
			}

			//hashmapParam.put("aca_id", member.getAca_id());
			//mapper.chgLogInfoUpdate(hashmapParam);

			if(!hashmapParam.get("chg_std_status").equals("02")) {
   				hashmapParam.put("return_dt", null);
   			}

   			mapper.chgLogInfoCreate(hashmapParam);

   			if(compare > 0) {
				if(hashmapParam.get("chg_std_status").equals("02")) {
					hashmapParam.put("chg_std_status", "04");
				}else if(hashmapParam.get("chg_std_status").equals("03")) {
					hashmapParam.put("chg_std_status", "06");
				}
			}else{
				if(hashmapParam.get("chg_std_status").equals("02")) {
	   				String chk_cd2 = mapper.getReasonChkCd(hashmapParam);

	   				if(chk_cd2 != null) {
	   					mapper.stdAtdChkUpdate(hashmapParam);
	   				}
	   			}
			}
   			
   			String std_mbr_tp = mapper.getStudentMbrTp(hashmapParam);   			
   			hashmapParam.put("std_mbr_tp", std_mbr_tp);
   			if(mapper.stdStatusUpdate(hashmapParam) == 1) {
   				result.setResultCode("S000");
   			}else {
   				result.setResultCode("S999");
   				result.setResultMsg("오류가 발생했습니다.");
   			}

   			/*
   			HashMap<String, Object> callPrcSyncUserParam = new HashMap<String, Object>();

			callPrcSyncUserParam.put("mode", "U");
			callPrcSyncUserParam.put("userId", hashmapParam.get("chg_log_std_id"));
			callPrcSyncUserParam.put("resultCode", "");
			callPrcSyncUserParam.put("resultMsg", "");

			mapper.callPrcSyncUser(callPrcSyncUserParam);

			if((Integer)callPrcSyncUserParam.get("resultCode") == 0) {
				result.setResultCode("S000");
			}else {
				result.setResultCode("S999");
				result.setResultMsg((String)callPrcSyncUserParam.get("resultMsg"));
			}
			*/
   		} catch (Exception e) {
   			e.printStackTrace();
   			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
   			result.setResultCode("S999");
   			result.setResultMsg("에러가 발생하였습니다.");
   		}
       	return result;
   }

    @Transactional
   	@RequestMapping(value="/chgLogInfoDelete", method= RequestMethod.POST)
   	public@ResponseBody ReturnDataVO chgLogInfoDelete(@RequestParam HashMap<String, Object> hashmapParam){

   		ReturnDataVO result = new ReturnDataVO();

   		try {
   			if(mapper.chgLogInfoDelete(hashmapParam) == 1) {
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
   	@RequestMapping(value="/chgLogRetInfoUpdate", method= RequestMethod.POST)
   	public@ResponseBody ReturnDataVO chgLogRetInfoUpdate(@RequestParam HashMap<String, Object> hashmapParam){

   		ReturnDataVO result = new ReturnDataVO();

   		try {
   			if(mapper.chgLogRetInfoUpdate(hashmapParam) == 1) {
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

    @RequestMapping(value="/getNextInvPeriod")
	public @ResponseBody ReturnDataVO getNextInvPeriod(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();

		try {
			String nextPeriod = mapper.getNextInvPeriod(hashmapParam);

			result.setResultCode("S000");
			result.setData(nextPeriod);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

    @RequestMapping(value="/getStdStatusInfo")
   	public @ResponseBody ReturnDataVO getStdStatusInfo(@RequestParam HashMap<String, Object> hashmapParam){

   		ReturnDataVO result = new ReturnDataVO();

   		try {
   			String stdStatus = mapper.getStdStatusInfo(hashmapParam);

   			result.setResultCode("S000");
   			result.setData(stdStatus);

   		} catch (Exception e) {
   			result.setResultMsg("에러가 발생하였습니다.");
   			result.setResultCode("S999");
   			e.printStackTrace();
   		}
   		return result;
   	}

    /**
     * 셔틀버스 운행일정
     * @param hashmapParam
     * @param checkDay
     * @param pageing
     * @param request
     * @return
     */
    @RequestMapping(value="/getShuttleScheduleList")
    public @ResponseBody HashMap<String, Object> getShuttleScheduleList(@RequestParam HashMap<String, Object> hashmapParam,
    		@RequestParam(value="stt_sch_day",required=false) List<String> checkDay,
    		@ModelAttribute("pageing") PageingVO pageing, HttpServletRequest request) {

    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {

			int checkDayCnt = 0;
			if(checkDay != null){
				checkDayCnt = checkDay.size();
			}else{
				checkDayCnt = 0;
			}

			hashmapParam.put("checkDayCnt", checkDayCnt);
			hashmapParam.put("checkDay", checkDay);

			resultList = mapper.getShuttleScheduleList(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch(Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;

    }

    /**
     * 원생 셔틀버스 일정 리스트
     * @param hashmapParam
     * @return
     */
    @RequestMapping(value="/getSelectStdShuttleSchList")
    public @ResponseBody HashMap<String, Object> getSelectStdShuttleSchList(@RequestParam HashMap<String, Object> hashmapParam) {

    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();


    	try {

    		resultList = mapper.getSelectStdShuttleSchList(hashmapParam);
    		hashmapResult.put("rows", resultList);

    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    	return hashmapResult;

    }

    /**
     * 원생 셔틀버스 탑승일정 추가
     * @param hashmapParam
     * @param session
     * @return
     */
    @Transactional
    @RequestMapping(value="/setStdShuttleSchedule", method=RequestMethod.POST)
    public @ResponseBody ReturnDataVO setStdShuttleSchedule(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;


    	try {

    		int chkStt=0, chkSch=0, chkBoard=0, chkStat=0;
    		for(HashMap<String, Object> map : list) {

    			String stdStatus = mapper.getChkStdStatus(map);

    			switch(stdStatus) {
    				case "00":
    				case "04":
    				case "02":
    				case "05":
    				case "03":
    				case "06":
    					chkStat++;
    					break;
    			}

    			if(chkStat == 1) {
    				result.setResultMsg("재원중인 원생만 이용 가능합니다.");
    	    		result.setResultCode("S999");
    	    		break;
    			}

    			chkStt = mapper.chkStdShuttleSChedule(map);

    			if(chkStt == 1) {
    				result.setResultMsg("이미 등록된 배차일정 입니다.");
    	    		result.setResultCode("S999");
    	    		break;
    			}
    			/*
    			chkSch = mapper.chkStdShttleDuplicateSchedule(map);

    			if(chkSch == 1){
    				result.setResultMsg("탑승장소는 하루 한 곳만 선택할 수 있습니다.");
    	    		result.setResultCode("S999");
    	    		break;
    			}
    			*/
    			String seatStatus = mapper.getChkBusSeatStatus(map);
    			if(seatStatus.equals("over")) {
    				result.setResultMsg("잔여 좌석이 존재하지 않습니다.");
    	    		result.setResultCode("S999");
    				chkBoard++;
    				break;
    			}

    		}

    		if(chkStat == 0 && chkStt == 0 && chkSch == 0 && chkBoard == 0) {
	    		for(HashMap<String, Object> map : list) {

	    			map.put("user_id", member.getUser_id());
	    			map.put("board_gb", "BOARD");
	    			map.put("board_memo", "원생 탑승");
	    			if(mapper.addStdShuttleMember(map) == 1) {
	    				result.setResultMsg("배차가 완료되었습니다.");
	    				result.setResultCode("S000");
	    			}

	    		}
    		}

    	} catch(Exception e) {
    		e.printStackTrace();
    		result.setResultMsg(e.getMessage());
    		result.setResultCode("S999");
    	}

    	return result;

    }

    /**
     * 원생 셔틀버스 탑승일정 삭제
     * @param hashmapParam
     * @return
     */
    @Transactional
    @RequestMapping(value="/delStdShuttleBoardSch")
    public @ResponseBody ReturnDataVO delStdShuttleBoardSch(@RequestParam HashMap<String, Object> hashmapParam) {


    	ReturnDataVO result = new ReturnDataVO();

    	try {


    		if(mapper.delStdShuttleBoardSch(hashmapParam) == 1) {
    			result.setResultCode("S000");
    		}


    	} catch(Exception e) {
    		result.setResultCode("S999");
    		e.printStackTrace();
    	}


    	return result;

    }

    /**
     * 원생 셔틀버스 배정 확인
     * @param hashmapParam
     * @return
     */
    @RequestMapping(value="/getSelectStdShuttleSchedule")
    public @ResponseBody ReturnDataVO getSelectStdShuttleSchedule(@RequestParam HashMap<String, Object> hashmapParam) {

    	ReturnDataVO result = new ReturnDataVO();
    	HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
    	HashMap<String, Object> resultFailed = new HashMap<String, Object>();

    	try {

    		hashmapResult = mapper.getSelectStdShuttleSchedule(hashmapParam);

    		String sttChk = "";
    		if(hashmapResult != null) {
    			sttChk = "Y";
    			hashmapResult.put("sttChk", sttChk);
    			result.setData(hashmapResult);
    		}else {
    			sttChk = "N";
    			resultFailed.put("sttChk", sttChk);
    			result.setData(resultFailed);
    		}

    		result.setResultCode("S000");

    	} catch(Exception e) {
    		result.setResultCode("S999");
    		e.printStackTrace();
    	}

    	return result;

    }

    @RequestMapping(value="/getAcaClassList")
    public @ResponseBody ReturnDataVO getAcaClassList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {

    	ReturnDataVO result = new ReturnDataVO();
    	List<HashMap<String, Object>> hashmapResult = new ArrayList<HashMap<String, Object>>();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");

    	try {

			List<String> auth_grp_cd = member.getUser_group();
			hashmapParam.put("auth_grp_cd", auth_grp_cd.get(0));
			hashmapParam.put("user_id", member.getUser_id());

    		hashmapResult = mapper.getAcaClassList(hashmapParam);
    		result.setData(hashmapResult);
    		result.setResultCode("S000");

    	} catch(Exception e) {
    		result.setResultCode("S999");
    		e.printStackTrace();
    	}

    	return result;
    }

    /**
     * 원생 학급배정 추가
     * @param hashmapParam
     * @param session
     * @return
     */
    @Transactional
    @RequestMapping(value="/setStdClassAdd")
    public @ResponseBody ReturnDataVO setStdClassAdd(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {

    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	List<HashMap<String, Object>> hashmapResult = new ArrayList<HashMap<String, Object>>();
    	hashmapParam.put("user_id", member.getUser_id());
    	HashMap<String, Object> classInfo = new HashMap<String, Object>();

    	try {

    		 int tot_cnt = 0;
    		 classInfo = mapper.getClassSeatInfo(hashmapParam);
    		 int quota = Integer.parseInt(String.valueOf(classInfo.get("quota")));
    		 int std_cnt = Integer.parseInt(String.valueOf(classInfo.get("std_cnt")));

    		 int stdClsChk = mapper.getChkStdDuplicateClass(hashmapParam);

    		 if(stdClsChk > 0) {
    			 result.setResultMsg("이미 등록된 학급 입니다.");
    			 tot_cnt++;
    		 }

    		 if(quota <= std_cnt) {
    			 result.setResultMsg("정원 초과된 학급입니다.");
    			 tot_cnt++;
    		 }

    		 if(tot_cnt == 0) {
    			 if(mapper.setStdClassAdd(hashmapParam) > 0) {
    				 hashmapParam.put("class_reason_cd", "05001");
    				 if(mapper.addStdInfoLogChg(hashmapParam) == 1) {
    					 result.setResultCode("S000");
    				 }
    			 }else {
    				 result.setResultMsg("저장중 오류가 발생했습니다.");
    				 result.setResultCode("S999");
    			 }
    		 }else {
    			 result.setResultCode("S999");
    		 }

    	} catch(Exception e) {
    		result.setResultCode("S999");
    		e.printStackTrace();
    	}

    	return result;

    }

    /**
     * 원생 학급배정 취소
     * @param hashmapParam
     * @return
     */
    @Transactional
    @RequestMapping(value="/excludeStdClassInfo")
    public @ResponseBody ReturnDataVO excludeStdClassInfo(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {

    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	List<HashMap<String, Object>> hashmapResult = new ArrayList<HashMap<String, Object>>();

    	try {

    		if(mapper.excludeStdClassInfo(hashmapParam) == 1) {
    			hashmapParam.put("user_id", member.getUser_id());
    			hashmapParam.put("class_reason_cd", "05099");
    			hashmapParam.put("class_memo", "학급배정 취소");
    			if(mapper.addStdInfoLogChg(hashmapParam) == 1) {
	    			result.setResultMsg("학급배정이 취소 되었습니다.");
	    			result.setResultCode("S000");
    			}
    		} else {
    			result.setResultMsg("삭제중 오류가 발생했습니다.");
    			result.setResultCode("S999");
    		}


    	}catch (Exception e) {
    		result.setResultCode("S999");
    		e.printStackTrace();
    	}

    	return result;

    }

    /**
     * 응시가능한 시험목록 조회
     * @param hashmapParam
     * @return
     */
    @RequestMapping(value="/getSelectAcaExamList")
    public @ResponseBody ReturnDataVO getSelectAcaExamList(@RequestParam HashMap<String, Object> hashmapParam) {

    	ReturnDataVO result = new ReturnDataVO();
    	List<HashMap<String, Object>> hashmapResult = new ArrayList<HashMap<String, Object>>();


    	try {

    		hashmapResult = mapper.getSelectAcaExamList(hashmapParam);
    		result.setData(hashmapResult);
    		result.setResultCode("S000");

    	} catch(Exception e) {
    		result.setResultCode("S999");
    		result.setResultMsg("오류가 발생했습니다.");
    		e.printStackTrace();
    	}

    	return result;

    }

    @RequestMapping(value="/getStdExamResultList")
    public @ResponseBody HashMap<String, Object> getStdExamResultList(@RequestParam HashMap<String, Object> hashmapParam) {

    	List<HashMap<String, Object>> hashmapList = new ArrayList<HashMap<String, Object>>();
    	HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

    	try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

    		hashmapList = mapper.getStdExamResultList(hashmapParam);
    		int records =  mapper.getQueryTotalCnt();

    		pageing.setRecords(records);
            pageing.setTotal((int) Math.ceil((double) records / (double) pageing.getLength()));

            hashmapResult.put("draw", pageing.getDraw());
            hashmapResult.put("recordsTotal", pageing.getRecords());
            hashmapResult.put("recordsFiltered", pageing.getRecords());
            hashmapResult.put("data", hashmapList);


    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    	return hashmapResult;

    }


    @Transactional
    @RequestMapping(value="/setStdExamApply")
    public @ResponseBody ReturnDataVO setStdExamApply(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session ) {

    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");

    	try {

    		hashmapParam.put("user_id", member.getUser_id());
    		if(mapper.setStdExamApply(hashmapParam) == 1) {

   			 	hashmapParam.put("class_std_id", hashmapParam.get("exam_std_id"));
   			 	hashmapParam.put("class_std_status", hashmapParam.get("exam_std_status"));
   			 	hashmapParam.put("class_reason_cd", "06001");
	   			hashmapParam.put("class_memo", hashmapParam.get("cls_exam_message"));
	   			hashmapParam.put("class_aca_id", hashmapParam.get("exam_aca_id"));

    			mapper.addStdInfoLogChg(hashmapParam);
    			result.setResultCode("S000");
    		}else {
    			result.setResultCode("S999");
    			result.setResultMsg("오류가 발생했습니다.");
    		}

    	} catch(Exception e) {
    		result.setResultCode("S999");
    		result.setResultMsg("오류가 발생했습니다.");
    		e.printStackTrace();
    	}

    	return result;

    }


    /**
	 * 관리그룹 목록 가져오기
	 * @param hashmapParam
	 * @param session
	 * @return ReturnDataVO
	 */
	@RequestMapping(value="/getAcaSchMgtListRetrieve")
	public @ResponseBody ReturnDataVO getAcaSchMgtListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			list = mapper.getAcaSchMgtListRetrieve(hashmapParam);
			hashmapResult.put("rows", list);
			result.setData(hashmapResult);
			result.setResultMsg(null);
			result.setResultCode("S000");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
		}
		return result;
	}


	/**
	 * 관리그룹 학교 목록 가져오기
	 * @param hashmapParam
	 * @param session
	 * @return ReturnDataVO
	 */
	@RequestMapping(value="/getSchMstListRetrieve", produces = "application/json")
	public @ResponseBody ReturnDataVO getSchMstListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		
		
		List<HashMap<String, String>> listParam = new ArrayList<HashMap<String, String>>();
    	ReturnDataVO result = new ReturnDataVO();	

		try {
			listParam = mapper.getSchMstListRetrieve(hashmapParam);
			result.setData(listParam);
			result.setResultCode("S000");
			
		} catch (Exception e) {
			result.setResultCode("S999");
			e.printStackTrace();			
		}
		return result;
	}


	/**
	 * 학생회원 유형 목록 가져오기
	 * @param hashmapParam
	 * @param session
	 * @return ReturnDataVO
	 */
	@RequestMapping(value="/getStdMbrTpListRetrieve")
	public @ResponseBody ReturnDataVO getStdMbrTpListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			list = mapper.getStdMbrTpListRetrieve(hashmapParam);
			hashmapResult.put("rows", list);
			result.setData(hashmapResult);
			result.setResultMsg(null);
			result.setResultCode("S000");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
		}
		return result;
	}

	/**
	 * 원생 독서레벨 조회
	 * @param hashmapParam
	 * @param HttpSession
	 * @return ReturnDataVO
	 */
	@RequestMapping(value="/getStdReadingLvlRetrieve")
	public @ResponseBody ReturnDataVO getStdReadingLvlRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());
		
		try {
			
			if("1".equals(hashmapParam.get("depth"))) {
				list = mapper.getStdReadingLvlRetrieve(hashmapParam);
			}else {
				list = mapper.getStdReadingLvl2Depth(hashmapParam);
			}

			result.setData(list);
			result.setResultCode("S000");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
		}
		
		return result;
	}
	
	/**
	 * 결제정보수정
	 * @param hashmap
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/updatePaymentInfo")
	public @ResponseBody ReturnDataVO getPaymentInfoRetrieve(@RequestParam HashMap<String, Object> hashmap, HttpSession session) {
		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> info = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		
		try {
			
			hashmap.put("upt_user_id", member.getUser_id());
			mapper.updateSaleMstInfo(hashmap);
			mapper.updatePaymentInfo(hashmap);
			mapper.updateInvoiceInfo(hashmap);
			result.setResultCode("S000");
			result.setData(info);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 입학정보 탭 조회
	 * @param hashmap
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/getStdWaitingListRetrieve")
	public @ResponseBody HashMap<String, Object> getStdWaitingListRetrieve(@RequestParam HashMap<String, Object> hashmapParam) {
		
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		
		try {
			PageingVO pageing = new PageingVO();
            pageing.setPageingVO(hashmapParam);
			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

            hashmapParam.put("start", pageing.getStart());
            hashmapParam.put("end", pageing.getLength());

			resultList = mapper.getStdWaitingListRetrieve(hashmapParam);
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
	 * 독서 진단 결과 학급 조회
	 * @param HashMap
	 * @return ReturnDataVO
	 */
	@RequestMapping(value="/getDebateClassListRetrieve")
	public @ResponseBody ReturnDataVO getDebateClassListRetrieve(@RequestParam HashMap<String, Object> hashmapParam) {
		ReturnDataVO result = new ReturnDataVO();

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		try {
			resultList = mapper.getDebateClassListRetrieve(hashmapParam);

			result.setData(resultList);
			result.setResultCode("S000");
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
		}

		return result;
	}
}
