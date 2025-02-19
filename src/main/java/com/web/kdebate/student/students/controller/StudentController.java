package com.web.kdebate.student.students.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationConstraint.OperatorType;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web.common.util.DateUtil;
import com.web.common.util.StringUtil;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.student.students.domain.InvoiceMsgVO;
import com.web.kdebate.student.students.domain.StudentVO;
import com.web.kdebate.student.students.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/student/students/studentMng")
public class StudentController {

	@Value("${global.fileStorePath}")
	String origin_fileStorePath;

	@Autowired
	private StudentService mapper;

	@RequestMapping(value="/view")
	public ModelAndView view(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		mv.addObject("sessionVo", member);
		mv.setViewName("pages/student/students/studentMngView");
		return mv;
	}
	
	@RequestMapping("/getSelectSchMst")
	public @ResponseBody ReturnDataVO getSelectSchMst(@RequestParam HashMap<String, Object> hashmapParam) {
		
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		
		try {
			
			resultList = mapper.getSelectSchMst(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");
			
		} catch(Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 원생목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getStudentListRetrieve")
	public @ResponseBody HashMap<String, Object>  getStudentListRetrieve(@RequestBody HashMap<String, Object> hashmapParam,HttpSession session){

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("p_aca_id", member.getP_aca_id());
		hashmapParam.put("my_aca_id", member.getAca_id());
		hashmapParam.put("user_id", member.getUser_id());

		int srch_status_cnt = 0;
		List<String> srch_status = (List<String>) hashmapParam.get("srch_status");
		if(srch_status != null){
			srch_status_cnt = srch_status.size();
		}else{
			srch_status_cnt = 0;
		}

		hashmapParam.put("srch_status_cnt", srch_status_cnt);
		hashmapParam.put("srch_status_list", srch_status);
		
		List<String> auth_grp_cd = member.getUser_group();
		hashmapParam.put("auth_grp_cd", auth_grp_cd.get(0));

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

			resultList =  mapper.getStudentListRetrieve(hashmapParam);
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
	 * 학습과정
	 * @param hashmapParam
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getSrchClassList")
	public @ResponseBody ReturnDataVO getSrchClassList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String  pcodeuseYn = "";
		pcodeuseYn =  mapper.getPcodeUseYn(hashmapParam);

		try {
			if(pcodeuseYn != null && !pcodeuseYn.equals("N") ) {
				System.out.println("getSrchClassListNNNNN");
				list = mapper.getSrchAcdCodeList(hashmapParam);
			} else {
				System.out.println("getSrchClassListYYYYY");
				list = mapper.getSrchTbCodeList(hashmapParam);

			}

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
	 * 학습과정delete
	 * @param hashmapParam
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getMngClassDtlList")
	public @ResponseBody ReturnDataVO getMngClassDtlList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String  pcodeuseYn = "";
		pcodeuseYn =  mapper.getPcodeUseYn(hashmapParam);

		System.out.println("pcodeuseYn::: " +  pcodeuseYn);

			try {
				if(pcodeuseYn != null && !pcodeuseYn.equals("N") ) {
					System.out.println("getMngClassListNNNNN");
					list = mapper.getMngClassDtlList1(hashmapParam);
				} else {
					System.out.println("getMngClassListYYYYY");
					list = mapper.getMngClassDtlList(hashmapParam);

				}

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
	 * 반
	 * @param hashmapParam
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getMngClassList")
	public @ResponseBody ReturnDataVO getMngClassList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		
		String pcodeuseYn = member.getPcode_use_yn();

		try {
			
			hashmapParam.put("my_aca_id", member.getAca_id());

			if(pcodeuseYn != null && !pcodeuseYn.equals("N") ) {
				
				List<String> auth_grp_cd = member.getUser_group();
				hashmapParam.put("auth_grp_cd", auth_grp_cd.get(0));
				hashmapParam.put("user_id", member.getUser_id());
				
				list = mapper.getMngClassList1(hashmapParam);
				
			} else {
				list = mapper.getMngClassList(hashmapParam);
			}

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();

		}
		return result;
	}

	@RequestMapping(value="/getSrchInstructor")
	public @ResponseBody ReturnDataVO getSrchInstructor(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getSrchInstructor(hashmapParam);

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

	/**
	 * 원생정보 등록
	 * @param hashmapParam
	 * @return json
	 */
	@SuppressWarnings("null")
	@Transactional
	@RequestMapping(value="/studentInfoCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO studentInfoCreate(@ModelAttribute("StudentVO") @Valid StudentVO studentVO, BindingResult bindResult, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		String std_url = "";

		try {

			if(!std_url.equals("")) {
				studentVO.setStd_url(std_url);
			}

			//String std_id = studentVO.getStd_id();
			
			String std_id = studentVO.getStd_id();
			if(std_id.equals("")) {
				 std_id = mapper.getStdId();
			}
			
			if(mapper.chkUserId(std_id) == 0) {
				studentVO.setStd_id(std_id);
				studentVO.setEnt_user_id(member.getUser_id());

				if(studentVO.getStd_pwd().equals("")) {
					//studentVO.setStd_pwd("1234");
					studentVO.setStd_pwd(std_id);
				}
				
				studentVO.setCreate_tp("create");

				mapper.userInfoCreate(studentVO);
				mapper.studentInfoCreate(studentVO);
				// mapper.stdClassCreate(studentVO);
				mapper.stdInfoChgLogCreate(studentVO);

				String std_parent_id = mapper.getStdParentId();
				
				studentVO.setSms_cont_tp("01,02,03,04,06,05,09,10,11");

				if(!studentVO.getParent_hp_no().equals("")) {
					studentVO.setStd_parent_gb_cd(studentVO.getParent_gb_cd());
					studentVO.setStd_parent_hp_no(studentVO.getParent_hp_no());
					studentVO.setStd_parent_nm(studentVO.getParent_nm());
					studentVO.setStd_parent_id(studentVO.getParent_hp_no());
					
					if(!studentVO.getC_parent_id().equals("") && studentVO.getC_parent_id() != null) {
						studentVO.setStd_parent_id(studentVO.getC_parent_id());
					} else {
						//studentVO.setStd_parent_id("P" +studentVO.getParent_hp_no());
						studentVO.setStd_parent_id(std_parent_id);
					}

					if(studentVO.getParent_hp_no_cnfm().equals("C")) {						

						List<HashMap<String, Object>> stdBroList = mapper.stdBroList(studentVO);

						if(stdBroList.size() > 0) {
							for(int i=0; i< stdBroList.size(); i++) {
								String bro_std_id = stdBroList.get(i).get("bro_std_id").toString();
								studentVO.setStd_bro_id(bro_std_id);

								mapper.stdBroCreate(studentVO);
								mapper.broStdCreate(studentVO);
							}
						}
					} else {						
						mapper.parentInfoCreate(studentVO);
					}

					mapper.parentCreate(studentVO);

				} else {
					
					if(!studentVO.getParent_nm().equals("")) {
						
						studentVO.setStd_parent_gb_cd(studentVO.getParent_gb_cd());						
						studentVO.setStd_parent_nm(studentVO.getParent_nm());
						
						studentVO.setStd_parent_id(std_parent_id);

						mapper.parentInfoCreate(studentVO);
						mapper.parentCreate(studentVO);
					}					
					
				}
				
				result.setData(studentVO.getStd_id());
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
	 * 원생정보 등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/studentBatchCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO studentBatchCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		Gson json = new Gson();

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		List<StudentVO> list = new ArrayList<StudentVO>();

		list = json.fromJson((String) hashmapParam.get("data"), new TypeToken<List<StudentVO>>() {}.getType());

		try {

			for(StudentVO vo : list) {
				
				String std_id = vo.getStd_id();
				if(std_id.equals("") && std_id != null) {
					 std_id = mapper.getStdId();
				}
				
				if(vo.getStd_pwd().equals("")) {
					vo.setStd_pwd(std_id);
				}
				
				if(vo.getStd_status().equals("")) {
					vo.setStd_status("00");
				}
				
				vo.setCreate_tp("batch");
				vo.setStd_id(std_id);
				vo.setEnt_user_id(member.getUser_id());

				mapper.userInfoCreate(vo);
				mapper.studentInfoCreate(vo);

				if(vo.getStd_parent_nm() != null && !vo.getStd_parent_nm().isEmpty()) {
					String std_parent_id = mapper.getStdParentId();
					
					if(vo.getStd_parent_hp_no() != null && !vo.getStd_parent_hp_no().isEmpty()) {
						int hpDupChk = mapper.hpDupChk(vo);

						if(hpDupChk == 0) {							
							//vo.setStd_parent_id("P" + vo.getStd_parent_hp_no());
							vo.setStd_parent_id(std_parent_id);
							vo.setStd_parent_gb_cd("01,02,03,04,06,05,09,10,11");

							mapper.parentInfoCreate(vo);
							mapper.parentCreate(vo);
						}
					} else {
						vo.setStd_parent_id(std_parent_id);
						vo.setStd_parent_gb_cd("01,02,03,04,06,05,09,10,11");

						mapper.parentInfoCreate(vo);
						mapper.parentCreate(vo);
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
	 * 원생 청구서 일괄등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/invoiceBatchCreate", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO invoiceInfoCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request){

		Gson json = new Gson();

		ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");

		List<StudentVO> inv_list = new ArrayList<StudentVO>();
		List<HashMap<String, Object>> inv_dtl_list = new ArrayList<HashMap<String, Object>>();

		inv_list = json.fromJson((String) hashmapParam.get("data"), new TypeToken<List<StudentVO>>() {}.getType());
		inv_dtl_list = json.fromJson((String) hashmapParam.get("data2"), new TypeToken<List<HashMap<String, Object>>>() {}.getType());

		try {

			String noti_yn = hashmapParam.get("invoice_noti_yn").toString();
			String pay_gb_check = hashmapParam.get("pay_gb_check").toString();

			for(StudentVO vo : inv_list) {

				String inv_no = mapper.getInvNo();
				String inv_enc = inv_no;
				String inv_nm = "";

				if(pay_gb_check.equals("Y")) {
					inv_nm = DateUtil.setDateFormat(DateUtil.StringToDateConverte((String)hashmapParam.get("invoice_learn_fdt"), "yyyy-MM-dd"), "yyyy-MM") + "월 " + vo.getStd_nm() + " " + vo.getAca_nm() + " 청구서";
				}else {
					inv_nm = DateUtil.convertDate(new Date(),"yyyy-MM") + "월 " + vo.getStd_nm() + " " + vo.getAca_nm() + " 청구서";
				}

				vo.setInv_no(inv_no);
				vo.setInv_enc(inv_enc);
				vo.setInv_nm(inv_nm);
				vo.setInv_tp("EDU");
				vo.setInv_learn_fdt((String)hashmapParam.get("invoice_learn_fdt"));
				vo.setInv_learn_tdt((String)hashmapParam.get("invoice_learn_tdt"));
				vo.setInv_issue_dt((String)hashmapParam.get("invoice_issue_dt"));
				vo.setInv_recv_limit_dt((String)hashmapParam.get("invoice_recv_limit_dt"));
				vo.setInv_tot_amt((String)hashmapParam.get("invoice_tot_amt"));
				vo.setInv_pay_yn("N");
				vo.setInv_noti_yn(noti_yn);
				vo.setEnt_user_id(member.getUser_id());

				for(HashMap<String, Object> map : inv_dtl_list) {

					vo.setInv_corp_cd(map.get("corp_cd").toString());
					vo.setInv_goods_cd(map.get("goods_cd").toString());
					vo.setInv_price(map.get("price").toString());
					vo.setInv_cnt(map.get("cnt").toString());
					vo.setInv_dc_cd(map.get("dc_cd").toString());
					vo.setInv_dc_tp(map.get("dc_tp").toString());
					vo.setInv_dc_amt(map.get("dc_amt").toString());
					vo.setInv_tot_price(map.get("tot_price").toString());

					mapper.invoiceDtlInfoCreate(vo);
				}

				mapper.invoiceInfoCreate(vo);

				hashmapParam.put("std_id", vo.getStd_id());

				String parent_id = mapper.getParentId(hashmapParam);

				if(parent_id != null) {

					vo.setParent_id(parent_id);

					String url = request.getRequestURL().toString().replace(request.getRequestURI(),"");

					String url2 = "/pay/payMngOnlineMnul/".concat(vo.getInv_enc());
						   url = url.concat(url2);

					String cnts	= inv_nm + "\n";
						   cnts += "\n";
						   cnts += "안녕하세요. "+ vo.getStd_nm() +" 학부모님" + "\n";
						   cnts += vo.getAca_nm() + "입니다. "+ "\n";
						   cnts += inv_nm +"를 발행하였습니다." + "\n";
						   cnts += vo.getAca_nm() + "에 내방하셔서 결제하시거나 아래 링크를 클릭하셔서 결제하실 수 있습니다." + "\n";
						   cnts += "\n";
						   cnts += "결제 하기 : " + url + "\n";
						   cnts += "\n";
						   cnts += "감사합니다.";

					vo.setTitle(inv_nm);
					vo.setCnts(cnts);
					vo.setNoti_tp("02");
					vo.setSend_tp("02");
					vo.setStatus("0");

					String noti_seq = mapper.getNotiSeq();

					vo.setNoti_seq(noti_seq);

					if(mapper.notiInfoCreate(vo) == 1) {
						mapper.notiReadCreate(vo);
					}

					if(noti_yn.equals("Y")) {
						if(cnts.getBytes("EUC-kr").length > 80) {
							vo.setMt_type("LM");
						} else {
							vo.setMt_type("SM");
						}

						//vo.setReserved_date((String)hashmapParam.get("invoice_issue_dt"));

						vo.setSms_cont_tp("02");

						mapper.smsInfoCreate(vo);
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

    /*
     * 원생 일괄등록 샘플 양식
     * */
    @RequestMapping(value="sampleExcelExport")
	public @ResponseBody ReturnDataVO sampleExcelExport(@RequestParam HashMap<String, Object> hashmapParam, HttpServletRequest request, HttpServletResponse response, HttpSession session){

    	ReturnDataVO  result = new ReturnDataVO();
		String mimetype = "application/x-msdownload";

		try {

			String title = (String) hashmapParam.get("title");
			String fileNm = (String) hashmapParam.get("fileNm");
			String status_combo = (String) hashmapParam.get("status_combo");			

			String[] titleList = title.split("\\|");
			String[] statusList = status_combo.split(",");			

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet=workbook.createSheet();

			//엑셀의 행
			Row row=null;
			//엑셀의 셀
			Cell cell=null;

			CellStyle headerStyle = workbook.createCellStyle();
			CellStyle formatStyle = workbook.createCellStyle();
			CellStyle textStyle = workbook.createCellStyle();
			Font headerFont = workbook.createFont();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
			headerFont.setColor(IndexedColors.WHITE.getIndex());
			headerFont.setBold(true);
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerStyle.setAlignment(HorizontalAlignment.CENTER);

			DataFormat dataFormat = workbook.createDataFormat();
			textStyle.setDataFormat(dataFormat.getFormat("@"));
			formatStyle.setDataFormat(dataFormat.getFormat("####-##-##"));

			headerStyle.setFont(headerFont);
			row=sheet.createRow((short)0);

			for(int k=0;k<titleList.length;k++){

				if(k == 7) {
					sheet.setColumnWidth(k, 9000);
				}else {
					sheet.setColumnWidth(k, 4000);
				}

                cell=row.createCell(k);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(String.valueOf(new String(titleList[k].getBytes("EUC-KR"), "EUC-KR")));

                /*if(k != 3) {
                	sheet.setDefaultColumnStyle(k, textStyle);
                }*/
            }

			DataValidation dataValidation1 = null;
			DataValidationConstraint constraint1 = null;
			DataValidationHelper validationHelper1 = null;

			validationHelper1 = new XSSFDataValidationHelper((XSSFSheet) sheet);
			CellRangeAddressList addressList1 = new CellRangeAddressList(1, 999, 9, 9);
			constraint1 = validationHelper1.createExplicitListConstraint(statusList);
			dataValidation1 = validationHelper1.createValidation(constraint1, addressList1);
			dataValidation1.setSuppressDropDownArrow(true);
			dataValidation1.setShowErrorBox(true);
			dataValidation1.createErrorBox("ERROR", "부적합한 값이 입력되었습니다.");

			sheet.addValidationData(dataValidation1);
			

			DataValidation dataValidation3 = null;
			DataValidationConstraint constraint3 = null;
			DataValidationHelper validationHelper3 = null;

			validationHelper3 = new XSSFDataValidationHelper((XSSFSheet) sheet);
			CellRangeAddressList addressList3 = new CellRangeAddressList(1, 999, 3, 3);
			constraint3 = validationHelper3.createDateConstraint(OperatorType.BETWEEN, "=DATE(1900,1,1)", "=DATE(2119,12,31)", "yyyy-mm-dd");
			dataValidation3 = validationHelper3.createValidation(constraint3, addressList3);
			dataValidation3.setShowErrorBox(true);
			dataValidation3.createErrorBox("ERROR", "일자를 yyyy-mm-dd 형식으로 입력해주십시오.");
			sheet.addValidationData(dataValidation3);

		    row=sheet.createRow((int)1);

		    for(int k = 0; k < 16; k++) {
	        	cell=row.createCell(k);

	        	String set_val = "";

	        	switch(k) {
	        		case 0 : set_val = "hola인";
	        			break;
	        		case 1 : set_val = "hola";
	        			break;
	        		case 2 : set_val = "1234";
	        			break;
	        		case 3 : set_val = "2017-02-23";
	        			break;
	        		case 4 : set_val = "031-613-8196";
	        			break;
	        		case 5 : set_val = "010-8015-0000";
	        			break;
	        		case 6 : set_val = "hola@hola.holaedu.net";
	        			break;
	        		case 7 : set_val = "서울시 가산디지털로";
	        			break;
	        		case 8 : set_val = "파트너스타워 10층";	        		
        				break;
	        		case 9 : set_val = "대기";
	        			break;
	        		case 10 : set_val = "Y";
	        			break;	        		
	        		case 11 : set_val = "hola인";
	        			break;
	        		case 12 : set_val = "hola 어머님";
        				break;
	        		case 13 : set_val = "010-0000-0000";
        				break;
	        	}

	        	cell.setCellValue(set_val);
	        }

			Date today = new Date();
			SimpleDateFormat format;

			format = new SimpleDateFormat("yyyyMMddHHmmss");

			if (fileNm.endsWith(".xlsx")){
				fileNm = fileNm.replaceAll(".xlsx", "_" + format.format(today) + ".xlsx");
			}else{
				fileNm += "_" + format.format(today) + ".xlsx";
			}
			String encodedFilename = "";
			response.setContentType(mimetype);
			response.setContentType("text/html;charset=UTF-8");
			String browser = getBrowser(request);
			switch (browser) {
			case "MSIE":
			case "Trident":
				encodedFilename = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
				break;

			case "Firefox":
			case "Opera":
				encodedFilename = "\"" + new String(fileNm.getBytes("UTF-8"), "8859_1") + "\"";
				response.setContentType("application/octet-stream;charset=UTF-8");
				break;

			case "Chrome":
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < fileNm.length(); i++) {
					char c = fileNm.charAt(i);
					if (c > '~') {
						sb.append(URLEncoder.encode("" + c, "UTF-8"));
					} else {
						sb.append(c);
					}
				}
				encodedFilename = sb.toString();
				break;

			default:
				break;
			}

			response.setHeader("Content-Disposition", "attachment; filename=" + encodedFilename);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			response.setContentLength(baos.size());
			OutputStream out = response.getOutputStream();
			out.write(baos.toByteArray());
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.gc();
		}
		return result;
	}

    /**
	 * 원생 일괄등록 엑셀 파싱
	 * @param hashmapParam
	 * @return json
	 */
    @SuppressWarnings("null")
	@Transactional
	@RequestMapping(value="/stdExcelParsing", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO stdExcelParsing(@RequestParam HashMap<String, Object> hashmapParam, MultipartHttpServletRequest fileReq, HttpSession session){

    	ReturnDataVO result = new ReturnDataVO();
    	List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

    	Workbook workbook = null;

    	MultipartFile mFile = fileReq.getFile("std_multi_file");

		try {
			workbook = WorkbookFactory.create(mFile.getInputStream());

			Sheet sheet = null;
			Row row = null;
			Cell cell = null;

			for(int i = 0 ; i < workbook.getNumberOfSheets(); i++) {

				sheet = workbook.getSheetAt(i);

				for(int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {

					if(j != 0) {

						row = sheet.getRow(j);
						String value;

						HashMap<String, Object> map = new HashMap<String, Object>();
                    	List<Integer> valid = new ArrayList<Integer>();


                        // cell 탐색 for 문
                        for(int k = 0; k < row.getLastCellNum(); k++) {
                            cell = row.getCell(k);

                            if(true) {
                            	if(k == 3) {
                            		if(cell == null) {
                            			value = "";
                            		}else {
										if(cell.getCellType() == CellType.NUMERIC) {
                            				value = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                            			}else {
                            				value = cell.getStringCellValue();
                            			}
                            		}
                            	}else {
                            		if(cell == null) {
                            			value = "";
                            		}else {
                            			if(cell.getCellType() == CellType.NUMERIC) {
                            				value = Integer.toString((int)cell.getNumericCellValue());
                            			}else {
                            				value = cell.getStringCellValue();
                            			}

                            		}
                            	}

                                map.put("std_seq", (j - 1));
                                map.put("valid", "");

                                // 현재 column index에 따라서 vo에 입력
                                switch (k) {
                                case 0: // 이름
                                	if(value.equals("")) {
                                		valid.add(0);
                                	}

                                    map.put("std_nm", value);
                                    break;
                                case 1: // 아이디
                                	if(value.equals("")) {
                                		valid.add(1);
                                	}else {
                                		int chk = 0;

                                		chk = mapper.chkUserId(value);

                                		if(chk != 0) {
                                			valid.add(2);
                                		}
                                	}

                                	map.put("std_id", value);
                                    break;
                                case 2: // 비밀번호
                                	map.put("std_pwd", value);
                                    break;

                                case 3: // 생년월일
                                	map.put("std_birth", value);
                                    break;

                                case 4: // 전화번호
                                	if(!value.equals("")) {

                                		String tel_no = DateUtil.strFilter(value);

                                    	if(StringUtil.isStringNumberChk(tel_no)) {
                                    		if(tel_no.length() > 8) {
                                    			value = StringUtil.makeHp(tel_no);
                                    		}
                                    	}
                                	}

                                	map.put("std_tel_no", value);
                                    break;

                                case 5: // 휴대전화
                                	if(!value.equals("")) {

                                		String hp_no = DateUtil.strFilter(value);

                                    	if(StringUtil.isStringNumberChk(hp_no)) {
                                    		if(hp_no.length() == 10 || hp_no.length() == 11) {
                                    			value = StringUtil.makeHp(hp_no);
                                    		}
                                    	}else {
                                    		valid.add(4);
                                    	}
                                	}

                                	map.put("std_hp_no", value);
                                    break;

                                case 6: // 이메일
                                	map.put("std_email", value);
                                    break;

                                case 7: // 주소
                                	map.put("std_addr", value);
                                    break;

                                case 8: // 재원상태
                                	map.put("std_addr_dtl", value);
                                    break;
                                case 9: // 회원유형
                                	if(value.equals("")) {
                                		valid.add(5);
                                	}

                                	map.put("std_status_nm", value);
                                    break;
                                case 10: // 학습케어                                	
                                	map.put("study_care_yn", value);
                                    break;

                                case 11: // 별명
                                	map.put("std_nick_nm", value);
                                    break;

                                case 12: // 대표학부모명
                                	map.put("std_parent_nm", value);
                                    break;

                                case 13: // 대표학부모번호
                                	//if(!value.equals("")) {

                                		String hp_no = DateUtil.strFilter(value);

                                    	//if(StringUtil.isStringNumberChk(hp_no)) {
                                    		if(hp_no.length() == 10 || hp_no.length() == 11) {
                                    			value = StringUtil.makeHp(hp_no);
                                    		}
                                    	//}else {
                                    		//valid.add(7);
                                    	//}
                                	//}else {
                                	//	valid.add(6);
                                	//}
                                    		
                                    hashmapParam.put("parent_hp_no", value);		
                                    int chkDupl = mapper.getParentHpNoConfirm(hashmapParam);		
                                    
                                    if(chkDupl > 0) {
                                    	valid.add(6);                                    	
                                    }

                                	map.put("std_parent_hp_no", value);
                                    break;

                                default:
                                    break;
                                }
                            }
                        }

                        map.put("std_valid", valid);

                        list.add(map);
					}
				}
			}

			result.setData(list);
			result.setResultCode("S000");

		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} finally {
            try {
                if( workbook!= null) workbook.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	return result;
    }

    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) {	// IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

    @RequestMapping(value="/getStudentIdConfirm")
	public @ResponseBody ReturnDataVO getStudentIdConfirm(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		//int chk_cnt = 0;

		try {
			//chk_cnt = mapper.getStudentIdConfirm(hashmapParam);
			//result.setResultCode("S000");
			//result.setData(chk_cnt);
			
			resultMap = mapper.getStudentIdConfirm(hashmapParam);

			result.setResultCode("S000");
			result.setData(resultMap);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

    @RequestMapping(value="/getParentHpNoConfirm")
	public @ResponseBody ReturnDataVO getParentHpNoConfirm(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();

		int chk_cnt = 0;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();		
		List<HashMap<String, Object>> parentInfo = new ArrayList<HashMap<String, Object>>();

		try {
			chk_cnt = mapper.getParentHpNoConfirm(hashmapParam);

			if(chk_cnt > 0) {				
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

    @RequestMapping(value="/getAcaListRetrieve")
	public @ResponseBody ReturnDataVO getAcaListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getAcaListRetrieve(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

    @RequestMapping(value="/getClassListRetrieve")
	public @ResponseBody ReturnDataVO getClassListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getClassListRetrieve(hashmapParam);

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
	 * 원생 시간표 목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getClsTimeListRetrieve")
	public @ResponseBody ReturnDataVO getClsTimeListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		String maxTtime = "";

		try {
			maxTtime = mapper.getMaxTtime(hashmapParam);
			list = mapper.getClsTimeListRetrieve(hashmapParam);

			map.put("max_ttime", maxTtime);
			map.put("cls_time_list", list);

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
	 * 원생 시간표 목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/getStdClsTimeListRetrieve")
	public @ResponseBody ReturnDataVO getStdClsTimeListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getStdClsTimeListRetrieve(hashmapParam);

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
	 * 원생 시간표 등록
	 * @param hashmapParam
	 * @return json
	 */
	@Transactional
	@RequestMapping(value="/stdClsTimeCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO stdClsTimeCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		Gson json = new Gson();

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		list = json.fromJson((String) hashmapParam.get("data"), new TypeToken<List<HashMap<String, Object>>>() {}.getType());

		String std_id = "";

		try {

			std_id = list.get(0).get("std_id").toString();

			mapper.stdClsTimeDelete(list.get(0));

			for(HashMap<String, Object> map : list) {

				map.put("ent_user_id", member.getUser_id());

				if(mapper.stdClsTimeCreate(map) == 1) {

					mapper.stdChgLogInfoInsert(map);

					HashMap<String, Object> callApiLmsTeacherClsSyncParam = new HashMap<String, Object>();

					callApiLmsTeacherClsSyncParam.put("userId", map.get("std_id"));
					callApiLmsTeacherClsSyncParam.put("acaId", map.get("aca_id"));
					callApiLmsTeacherClsSyncParam.put("roomNo", map.get("cls_room_no"));
					callApiLmsTeacherClsSyncParam.put("ttime", map.get("cls_ttime"));
					callApiLmsTeacherClsSyncParam.put("wday", map.get("cls_wday"));
					callApiLmsTeacherClsSyncParam.put("resultCode", "");
					callApiLmsTeacherClsSyncParam.put("resultMsg", "");

					mapper.callApiLmsTeacherClsSync(callApiLmsTeacherClsSyncParam);

					if((Integer)callApiLmsTeacherClsSyncParam.get("resultCode") != 0) {
						result.setResultCode("S999");
						result.setResultMsg((String)callApiLmsTeacherClsSyncParam.get("resultMsg"));

						return result;
					}
				}
			}

			// 현재 수강중인 경우, 변경된 수업에 대한 출석 테이블 정보 변경

			//if(!end_cnt.equals("") && Integer.parseInt(end_cnt) > 0) {

				HashMap<String, Object> callPrcParam = new HashMap<String, Object>();

				callPrcParam.put("std_id", std_id);
				callPrcParam.put("str_dt", hashmapParam.get("str_dt"));
				callPrcParam.put("end_dt", hashmapParam.get("end_dt"));
				callPrcParam.put("user_id", member.getUser_id());

				mapper.callPrcCreateStdTimeTable(callPrcParam);
			//}

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
	@RequestMapping(value="/stdAcaClsMoveCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO stdAcaClsMoveCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		Gson json = new Gson();

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		list = json.fromJson((String) hashmapParam.get("data"), new TypeToken<List<HashMap<String, Object>>>() {}.getType());

		String std_id = "";

		try {

			std_id = list.get(0).get("std_id").toString();

			if(list.get(0).get("today_yn").equals("Y")) {
				mapper.userInfoUpdate(list.get(0));
				mapper.stdInfoUpdate(list.get(0));
				mapper.stdParentInfoUpdate(list.get(0));
				mapper.stdMoveClsTimeDelete(list.get(0));

				for(HashMap<String, Object> map : list) {
					map.put("aca_id", map.get("move_aca_id"));
					map.put("ent_user_id", member.getUser_id());

					mapper.stdClsTimeCreate(map);
					mapper.stdChgLogInfoInsert(map);
				}

				String end_cnt = list.get(0).get("end_cnt").toString();

				if(!end_cnt.equals("") && Integer.parseInt(end_cnt) > 0) {

					HashMap<String, Object> callPrcParam = new HashMap<String, Object>();

					callPrcParam.put("std_id", std_id);
					callPrcParam.put("str_dt", list.get(0).get("std_move_dt"));
					callPrcParam.put("end_dt", list.get(0).get("end_dt"));
					callPrcParam.put("user_id", member.getUser_id());

					mapper.callPrcCreateStdTimeTable(callPrcParam);
				}

				for(HashMap<String, Object> map : list) {
					HashMap<String, Object> callApiLmsTeacherClsSyncParam = new HashMap<String, Object>();

					callApiLmsTeacherClsSyncParam.put("userId", map.get("std_id"));
					callApiLmsTeacherClsSyncParam.put("acaId", map.get("aca_id"));
					callApiLmsTeacherClsSyncParam.put("roomNo", map.get("cls_room_no"));
					callApiLmsTeacherClsSyncParam.put("ttime", map.get("cls_ttime"));
					callApiLmsTeacherClsSyncParam.put("wday", map.get("cls_wday"));
					callApiLmsTeacherClsSyncParam.put("resultCode", "");
					callApiLmsTeacherClsSyncParam.put("resultMsg", "");

					mapper.callApiLmsTeacherClsSync(callApiLmsTeacherClsSyncParam);

					if((Integer)callApiLmsTeacherClsSyncParam.get("resultCode") != 0) {
						result.setResultCode("S999");
						result.setResultMsg((String)callApiLmsTeacherClsSyncParam.get("resultMsg"));

						return result;
					}
				}
			}else {
				for(HashMap<String, Object> map : list) {
					map.put("aca_id", map.get("move_aca_id"));
					map.put("ent_user_id", member.getUser_id());
					mapper.stdInfoUpdate(map);
					mapper.stdChgResvCreate(map);
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

	@RequestMapping(value="/getTeacherList")
	public @ResponseBody ReturnDataVO getTeacherList(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getTeacherList(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/getClassTmList")
	public @ResponseBody ReturnDataVO getClassTmList(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getClassTmList(hashmapParam);

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
	@RequestMapping(value="/clsSpcCreate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO clsSpcCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
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
	   			 if(mapper.clsSpcCreate(hashmapParam) == 1) {
	   				 hashmapParam.put("class_reason_cd", "05001");
	   				 mapper.addStdInfoLogChg(hashmapParam);
	   				 result.setResultCode("S000");
	   			 }else {
	   				 result.setResultMsg("저장중 오류가 발생했습니다.");
	   				 result.setResultCode("S999");
	   			 }
	   		 }else {
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

	@RequestMapping(value="/getAtdListRetrieve")
	public @ResponseBody ReturnDataVO getAtdListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getAtdListRetrieve(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/deleteStd")
	public @ResponseBody ReturnDataVO deleteStd(@RequestParam(value="std_id", required=true) String std_id){

		ReturnDataVO result = new ReturnDataVO();

		try {
			HashMap<String, Object> callPrcParam = new HashMap<>();
			callPrcParam.put("std_id", std_id);
			mapper.deleteStdUser(callPrcParam);
			mapper.deleteStdInfo(callPrcParam);
			mapper.deleteStdParents(callPrcParam);

			result.setResultCode("S000");

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 원생 배정학급 리스트 조회
	 * @param hashmapParam
	 * @param HttpSession
	 * @return ReturnDataVO
	 */
	@Transactional
	@RequestMapping(value = "/getStdClassBatchList")
	public @ResponseBody ReturnDataVO getStdClassBatchList(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {
		ReturnDataVO result = new ReturnDataVO();

		try {

			SessionVO member = (SessionVO) session.getAttribute("S_USER");

			List<String> auth_grp_cd = member.getUser_group();
			hashmapParam.put("auth_grp_cd", auth_grp_cd.get(0));
			hashmapParam.put("user_id", member.getUser_id());

			List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
			
			resultList = mapper.getStdClassBatchList(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 원생 학급 배정/이동 가능여부 조회
	 * @param HashMap
	 * @return HashMap
	 */
	@RequestMapping(value = "/getStdClsBatchSrchInfo")
	public @ResponseBody HashMap<String, Object> getStdClsBatchSrchInfo(@RequestParam HashMap<String, Object> hashmapParam) {
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			String[] srch_std_arr = String.valueOf(hashmapParam.get("std_id")).split(",");
			hashmapParam.put("srch_std_arr", srch_std_arr);
			
			/* 이동 */
			if("move".equals(hashmapParam.get("gbVal"))) {
				resultList = mapper.getStdClsBatchMoveInfo(hashmapParam);

			/* 배정 */
			}else {
				resultList = mapper.getStdClsBatchSrchInfo(hashmapParam);
			}

			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;
	}

	/**
	 * 원생 학급 일괄 배정
	 * @param hashmapParam
	 * @return ReturnDataVO
	 */
	@Transactional
	@RequestMapping(value = "/addClsStdBatchInfo")
	public @ResponseBody ReturnDataVO addClsStdBatchInfo(@RequestParam HashMap<String, Object> hashmapParam) {
		ReturnDataVO result = new ReturnDataVO();
		
		Gson json = new Gson();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		try {
			resultList = json.fromJson((String) hashmapParam.get("data"), new TypeToken<List<HashMap<String, Object>>>() {}.getType());
			resultList.forEach((map) -> {
				
				mapper.addClsStdBatchInfo(map);

				String cls_apply_status = String.valueOf(map.get("cls_apply_status"));
				if("01".equals(cls_apply_status)) {
					map.put("chg_reason_cd", "05001");
					map.put("chg_reason_memo", "학급 배정");

					mapper.addStdInfoLogChg(map);
				}
			});

			result.setResultCode("S000");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 원생 학급 일괄 이동
	 * @param hashmapParam
	 * @return ReturnDataVO
	 */
	@Transactional
	@RequestMapping(value = "/addClsStdBatchMoveInfo")
	public @ResponseBody ReturnDataVO addClsStdBatchMoveInfo(@RequestParam HashMap<String, Object> hashmapParam) {
		ReturnDataVO result = new ReturnDataVO();
		
		Gson json = new Gson();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		try {
			
			resultList = json.fromJson((String) hashmapParam.get("data"), new TypeToken<List<HashMap<String, Object>>>() {}.getType());
			
			resultList.forEach((map) -> {
				
				mapper.updateCurrClsState(map);

				String cls_apply_status = String.valueOf(map.get("cls_apply_status"));
				if("01".equals(cls_apply_status)) {
					
					map.put("chg_class_cd", map.get("curr_class_cd"));
					map.put("chg_reason_cd", "05002");
					map.put("chg_reason_memo", "학급 이동");
					mapper.addStdInfoLogChg(map);
				
				}else {
					mapper.addClsStdBatchInfo(map);
				}
			});

			result.setResultCode("S000");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getStdExamList")
	public @ResponseBody HashMap<String, Object> getStdExamList(@RequestParam HashMap<String, Object> hashmapParam, HttpServletRequest request) {

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;

		try {

			String cls_std_id = "";
			for(HashMap<String, Object> map : list) {
				cls_std_id += "'"+ map.get("std_id") + "',";
			}

			cls_std_id = cls_std_id.replaceAll(",$", "");
			hashmapParam.put("cls_std_id", cls_std_id);

			resultList = mapper.getStdExamList(hashmapParam);
			hashmapResult.put("rows", resultList);


		} catch(Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;

	}

	/**
	 * 원생관리>학급목록
	 * @param hashmapParam
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getStdClassList")
	public @ResponseBody HashMap<String, Object> getStdClassList(@RequestParam HashMap<String, Object> hashmapParam, HttpServletRequest request) {

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;

		try {

			String cls_std_id = "";
			for(HashMap<String, Object> map : list) {
				cls_std_id += "'"+ map.get("std_id") + "',";
			}

			cls_std_id = cls_std_id.replaceAll(",$", "");
			hashmapParam.put("cls_std_id", cls_std_id);


			resultList = mapper.getStdClassList(hashmapParam);
			hashmapResult.put("rows", resultList);


		} catch(Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;

	}

	/**
	 * 원생 시험배정
	 * @param hashmapParam
	 * @param session
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/setStdExamAdd")
	public @ResponseBody ReturnDataVO setStdExamAdd(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request) {

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	hashmapParam.put("user_id", member.getUser_id());


		try {

			if(mapper.setStdExamAdd(hashmapParam) == 1) {

				hashmapParam.put("class_reason_cd", "06001");
				hashmapParam.put("cls_spc_aca_id", hashmapParam.get("cls_exam_aca_id"));
				hashmapParam.put("cls_spc_std_id", hashmapParam.get("cls_exam_std_id"));
				hashmapParam.put("class_std_status", hashmapParam.get("cls_exam_std_status"));
				hashmapParam.put("cls_spc_memo", hashmapParam.get("cls_exam_memo"));

				mapper.addStdInfoLogChg(hashmapParam);
				result.setResultCode("S000");
			}else {
				result.setResultCode("S999");
				result.setResultMsg("오류가 발생했습니다.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 원생 시험 일괄 배정
	 * @param hashmapParam
	 * @param session
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@RequestMapping(value="/addExamBatchCreate")
	public @ResponseBody ReturnDataVO addExamBatchCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session, HttpServletRequest request) {

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	hashmapParam.put("user_id", member.getUser_id());

    	Object obj = StringUtil.JsonStringToObject(hashmapParam.get("data").toString());
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) obj;

		try {

			for(HashMap<String, Object> map : list) {

				if(map.get("exam_no") != "") {

					int chkStdExamData = mapper.chkStdExamData(map);

					if(chkStdExamData == 0) {

						map.put("cls_exam_info", map.get("exam_no"));
						map.put("cls_exam_std_id", map.get("std_id"));
						map.put("user_id", member.getUser_id());
						if(mapper.setStdExamAdd(map) == 1) {

							map.put("class_reason_cd", "06001");
							map.put("cls_spc_aca_id", map.get("aca_id"));
							map.put("cls_spc_std_id", map.get("std_id"));
							map.put("class_std_status", map.get("std_status"));
							map.put("cls_spc_memo", hashmapParam.get("cls_exam_apply_memo"));

							mapper.addStdInfoLogChg(map);
							result.setResultCode("S000");

						} else {
							result.setResultCode("S999");
							result.setResultMsg("오류가 발생했습니다.");
							break;
						}

					}
				}

			}


		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;

	}


	/**
	 * live class 생성 권한 가져오기
	 * @param hashmapParam
	 * @param session
	 * @return ReturnDataVO
	 */
	@RequestMapping(value="/getAcaLiveClassOperYn")
	public @ResponseBody ReturnDataVO getAcaLiveClassOperYn(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			list = mapper.getAcaLiveClassOperYn(hashmapParam);
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
	 * 원생 상태 일괄수정
	 * @param hashmapParam
	 * @param session
	 * @return ReturnDataVO
	 */
	@Transactional
   	@RequestMapping(value="/chgLogInfoBatchCreate", method= RequestMethod.POST)
   	public@ResponseBody ReturnDataVO chgLogInfoBatchCreate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
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

			if(!hashmapParam.get("chg_std_status").equals("02")) {
   				hashmapParam.put("return_dt", null);
   			}
			
			String[] stdArr = hashmapParam.get("chg_log_std_id").toString().split(",");
			for(int i=0; i<stdArr.length; i++) {
				hashmapParam.put("chg_log_std_id", stdArr[i]);
				
				mapper.chgLogInfoBatchCreate(hashmapParam);

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
				
				//String std_mbr_tp = mapper.getStudentMbrTp(hashmapParam);   			
	   			//hashmapParam.put("std_mbr_tp", std_mbr_tp);
	   			
				if(mapper.stdStatusUpdate(hashmapParam) == 1) {
	   				result.setResultCode("S000");
	   			}else {
	   				result.setResultCode("S999");
	   				result.setResultMsg("오류가 발생했습니다.");
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
	
	/**
	 * 청구서 문자전송 view
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/invoiceSmsMng")
	public ModelAndView invoiceSmSMng(HttpSession session) {
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/single/common/common/invoiceSmsMng");
		return mv;
	}
	
	/**
	 * 청구서 문자전송
	 * @param hashmap
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/sendInvoiceSmsReceipt")
	public @ResponseBody ReturnDataVO sendInvoiceSmsReceipt(@RequestParam HashMap<String, String> hashmap, HttpSession session, HttpServletRequest request) {
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		Gson json = new Gson();
		
		List<HashMap<String, String>> invList = new ArrayList<HashMap<String, String>>();
		List<InvoiceMsgVO> stdList = new ArrayList<InvoiceMsgVO>();
		stdList = json.fromJson(hashmap.get("data"), new TypeToken<List<InvoiceMsgVO>>() {}.getType());
		
		try {
			
			if(stdList.size() > 0) {
				
				String title = hashmap.get("title");				
				String isInvoice = hashmap.get("isInvoice");
				String isPay = hashmap.get("isPay");
				
				
				for(InvoiceMsgVO data : stdList) {
					
					String stdId = data.getStd_id();
					String message = hashmap.get("message");
					message = message.replaceAll("\\@\\{std_nm\\}", data.getStd_nm());
					message = message.replaceAll("\\@\\{aca_nm\\}", member.getAca_nm());
					
					// 청구항목
					invList = mapper.getStdUnpayInvoiceList(stdId);
					
					String strInv = "";
					String strUrl = "";
					String url = request.getRequestURL().toString().replace(request.getRequestURI(),"");
					if(invList.size() > 0) {
						int num = 1;
						String REQ_NO = mapper.getInvoiceReqNo();
						for(HashMap<String, String> inv : invList) {
							HashMap<String, String> invMap = new HashMap<String, String>();
							
							String strEnc = REQ_NO;
							invMap.put("REQ_NO", REQ_NO);
							invMap.put("INVOICE_NO", inv.get("INVOICE_NO"));
							
							// base64 encode
							byte[] encodeBytes = Base64.getEncoder().encode(strEnc.getBytes());
				        	String REQ_ENC = new String(encodeBytes);
				        	invMap.put("REQ_ENC", REQ_ENC);
				        	invMap.put("upt_user_Id", member.getUser_id());
							
				        	mapper.createInvoiceReqList(invMap);
				        	
				        	strUrl = url + "/payment/online/payMngOnlineMnulBundle/".concat(REQ_ENC);
				        	
				        	if(isPay !=null && isPay.equals("Y")) {
								message = message.replaceAll("\\@\\{invoice_url\\}", strUrl);
							}
				        	
							strInv +=  num + ". " + inv.get("INVOICE_NM") + "\n";
							num++;
						}
					}
										
					if(isInvoice != null && isInvoice.equals("Y")) {
						message = message.replaceAll("\\@\\{invoice_list\\}", strInv);
					}
										
					
					// 문자 발송 저장
					data.setAca_id((String) member.getAca_id());
					data.setMessage(message);
					data.setCallback((String)hashmap.get("callback"));
					data.setPhone_number(data.getParent_tel());
					data.setTitle(title);
					data.setSms_send_gb((String)hashmap.get("sms_send_gb"));
					data.setSms_cont_tp("02");

					if(hashmap.get("sms_send_gb").equals("R")) {
						data.setSms_send_day(hashmap.get("sms_send_day"));
						data.setSms_send_tm(hashmap.get("sms_send_tm"));
					}

					data.setEnt_user_id(member.getUser_id());

					if(data.getMessage().getBytes("EUC-kr").length > 80) {
						data.setMt_type("LM");
					} else {
						data.setMt_type("SM");
					}
					data.setStatus("0");
					
					mapper.insertSmsList(data);
				}
				
				
				result.setResultCode("S000");
				
			} else {
				result.setResultCode("S999");
				result.setResultMsg("청구서를 전송할 원생(목록)이 없습니다.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("처리중 에러가 발생했습니다.");
		}
		
		return result;
	}

   /**
	 * 전원 처리 저장
	 * @param session
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/addStdChgAca")
	public @ResponseBody ReturnDataVO addStdChgAca(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {
		
		ReturnDataVO result = new ReturnDataVO();

		Gson json = new Gson();
		
		try {
			
			List<HashMap<String, Object>> chgStdInfo = new ArrayList<HashMap<String, Object>>();
			chgStdInfo = json.fromJson(String.valueOf(hashmapParam.get("data")), new TypeToken<List<HashMap<String, Object>>>() {}.getType());

			for(HashMap<String, Object> hashmap : chgStdInfo) {
				mapper.updateChgAcaUserInfo(hashmap);
				mapper.updateChgAcaStdInfo(hashmap);
				mapper.addStdChgAca(hashmap);
			}

			result.setResultCode("S000");

		}catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			result.setResultCode("S999");
		}

		return result;
	}

}