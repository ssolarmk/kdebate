package com.web.kdebate.student.students.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.common.util.DateUtil;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.student.students.domain.StudentRegistVO;
import com.web.kdebate.student.students.service.StudentRegistService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/student/students/StudentRegistMng")
public class StudentRegistController {
	
	@Value("${global.fileStorePath}")
	String origin_fileStorePath;

	@Autowired
	private StudentRegistService mapper;
	
	@RequestMapping(value="/view")
	public String view() {

		return "/single/student/students/studentRegistMngView";
	}
	
	/**
	 * 원생 등록
	 * @param studentVO
	 * @param bindResult
	 * @param session
	 * @param multiRequest
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/studentInfoCreate")
	public @ResponseBody ReturnDataVO studentInfoCreate(@ModelAttribute("StudentRegistVO") @Valid StudentRegistVO studentVO, BindingResult bindResult, HttpSession session, MultipartHttpServletRequest multiRequest, HttpServletResponse response) {
		
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		studentVO.setUpt_user_id(member.getUser_id());
		String std_url = "";				
		
		try {
			
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
			    long _size = file.getSize();
			    String orginFileName = file.getOriginalFilename();
			    int pos = orginFileName.lastIndexOf( "." );
			    String fileExt = orginFileName.substring( pos + 1 );
			    String newName = "file_" + DateUtil.getTimeStamp();
			    filePath = dateFolder + File.separator + newName+ "."+fileExt;
			    file.transferTo(new File(filePath));
			    std_url = "/upload/"+date+"/"+newName+"."+fileExt;
			}
			
			if(!std_url.equals("")) {
				studentVO.setStd_url(std_url);
			}
			
			if(studentVO.getStd_pw().equals("")) {
				studentVO.setStd_pw(studentVO.getStd_id());
			}
			
			mapper.userInfoCreate(studentVO);
			mapper.studentInfoCreate(studentVO);
			//mapper.stdClassCreate(studentVO);
			mapper.stdInfoChgLogCreate(studentVO);
			
			
			if(!studentVO.getParent_hp_no().equals("")) {
				studentVO.setParent_gb_cd(studentVO.getParent_gb_cd());
				studentVO.setParent_hp_no(studentVO.getParent_hp_no());
				studentVO.setStd_parent_nm(studentVO.getRep_parent_nm());

				if(studentVO.getParent_hp_no_cnfm().equals("C")) {
					String std_parent_id = mapper.getUserId(studentVO);
					studentVO.setStd_parent_id(std_parent_id);

					List<HashMap<String, Object>> stdBroList = mapper.stdBroList(studentVO);

					if(stdBroList.size() > 0) {
						for(int i=0; i< stdBroList.size(); i++) {
							String bro_std_id = stdBroList.get(i).get("bro_std_id").toString();
							studentVO.setStd_bro_id(bro_std_id);

							mapper.stdBroCreate(studentVO);
							mapper.broStdCreate(studentVO);
						}
					}
				}else {
					String std_parent_id = mapper.getStdParentId();
					studentVO.setStd_parent_id(std_parent_id);

					mapper.parentInfoCreate(studentVO);
				}

				mapper.parentCreate(studentVO);

			}
						
			result.setData(studentVO.getStd_id());
			result.setResultCode("S000");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	/**
	 * 아이디 중복체크
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getStudentIdConfirm")
	public @ResponseBody ReturnDataVO getStudentIdConfirm(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();

		int chk_cnt = 0;

		try {
			chk_cnt = mapper.getStudentIdConfirm(hashmapParam);

			result.setResultCode("S000");
			result.setData(chk_cnt);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 학부모 연락처 중복체크
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getParentHpNoConfirm")
	public @ResponseBody ReturnDataVO getParentHpNoConfirm(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();

		int chk_cnt = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> parentInfo = new HashMap<String, Object>();

		try {
			chk_cnt = mapper.getParentHpNoConfirm(hashmapParam);

			if(chk_cnt > 0) {
				parentInfo = mapper.getParentInfo(hashmapParam);

				if(parentInfo != null && !parentInfo.isEmpty()) {
					resultMap.put("parentCnfm", "Y");
					resultMap.put("parentId", parentInfo.get("parent_id"));
					resultMap.put("parentNm", parentInfo.get("parent_nm"));
				}else {
					resultMap.put("parentCnfm", "N");
				}

			}else {
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
	
	/**
	 * 출결코드 중복체크
	 * @param hashmapParam
	 * @return
	 */
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

}
