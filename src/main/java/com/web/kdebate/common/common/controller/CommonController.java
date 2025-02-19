package com.web.kdebate.common.common.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.web.common.util.DateUtil;
import com.web.common.util.HttpUtil;
import com.web.kdebate.center.center.mapper.CenterInfoMngMapper;
import com.web.kdebate.common.common.domain.FileVo;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.common.common.domain.SmsInfoVO;
import com.web.kdebate.common.common.service.CommonService;
import com.web.kdebate.learning.learn.mapper.TestBookRsltMngMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping(value="/common/common/")
public class CommonController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1104092362325122191L;

	private static Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService mapper;
	
	@Autowired
	private CenterInfoMngMapper centerInfoMngMapper;

	@Autowired
	private TestBookRsltMngMapper testBookRsltMngMapper;

	/**
	 * 코드목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/codelist")
	public @ResponseBody ReturnDataVO codeList(@RequestParam HashMap<String, String> hashmapParam){
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			list = (List<HashMap<String, Object>>) mapper.getCodeList(hashmapParam);
			result.setData(list);
			result.setResultCode("S000");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "authFalse")
	public String authFalseJspReturn() {
		return "/common/common/authFalse";
	}

	/**
	 * 화면에 쓸 전체 코드 목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/totalCodelist")
	public @ResponseBody ReturnDataVO totalCodelist(@RequestParam HashMap<String, String> hashmapParam){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		ReturnDataVO result = new ReturnDataVO();
		try {
			list = mapper.getTotalCodelist(hashmapParam);
			result.setResultCode("S000");
			result.setData(list);
		} catch (Exception e) {
			result.setResultMsg(null);
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 센터리스트 조회
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/centerListRetrieve")
	public @ResponseBody ReturnDataVO centerListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		ReturnDataVO result = new ReturnDataVO();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("user_id", member.getUser_id());
		hashmapParam.put("aca_id", member.getAca_id());
		hashmapParam.put("p_aca_id", member.getP_aca_id());
		// hashmapParam.put("corp_cd", member.getCorp_cd());

		try {
			list = mapper.centerListRetrieve(hashmapParam);
			result.setResultCode("S000");
			result.setData(list);
		} catch (Exception e) {
			result.setResultMsg(null);
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/commStdListRetrieve", method= RequestMethod.POST)
	public @ResponseBody ReturnDataVO stdListRetrieve(@RequestParam HashMap<String, Object> hashmapParam,HttpSession session){
		ReturnDataVO result = new ReturnDataVO();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		if(member != null) {
			hashmapParam.put("userId", member.getUser_id());
			hashmapParam.put("acaID", member.getAca_id());
		}

		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		
		try {
			// String p_aca_yn = mapper.pacaCheck(hashmapParam);
			// hashmapParam.put("p_aca_yn", p_aca_yn);

			hashmapParam.put("p_aca_id", member.getP_aca_id());

			List<String> auth_grp_cd = member.getUser_group();
			hashmapParam.put("auth_grp_cd", auth_grp_cd.get(0));
			
			list = mapper.commStdListRetrieve(hashmapParam);
			result.setResultCode("S000");
			result.setData(list);
		} catch (Exception e) {
			result.setResultMsg(null);
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/excelExport")
	public @ResponseBody ReturnDataVO excelExport(@RequestParam HashMap<String, Object> hashmapParam, HttpServletRequest request, @RequestParam(value="srch_status", required=false) List<String> srch_status, HttpServletResponse response, HttpSession session){
		ReturnDataVO  result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		//File xlsFile = null;
		String mimetype = "application/x-msdownload";
		try {
//			boolean isCnslAdmin = false;
//			// 상담사 관리자 권한이 있는지 체크
//			for(String auth_grp : member.getUser_group()){
//				if(auth_grp.equals("AG0002")){
//					isCnslAdmin = true;
//				}
//			}
//
//			if(!isCnslAdmin){
//				result.setResultMsg("권한이 없습니다.");
//				result.setResultCode("S999");
//				return result;
//			}

			List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();

			String title = (String) hashmapParam.get("title");
			String fileNm = (String) hashmapParam.get("fileNm");
			String keys = (String) hashmapParam.get("keys");
			String queryId = (String) hashmapParam.get("queryId");

			String[] titleList = title.split("\\|");
			String[] keysList = keys.split("\\|");

			if(titleList.length != keysList.length){
				result.setResultMsg("타이틀과 값이 다름");
				result.setResultCode("S999");
				return result;
			}
			
			int srch_status_cnt = 0;
			if(srch_status != null){
				srch_status_cnt = srch_status.size();
			}else{
				srch_status_cnt = 0;
			}

			hashmapParam.put("srch_status_cnt", srch_status_cnt);
			hashmapParam.put("srch_status_list", srch_status);
			
			hashmapParam.put("start", 0);
			hashmapParam.put("end", 5000);			
			
			list = (List<HashMap<String, Object>>) mapper.getListRetrieve(queryId, hashmapParam);

			int size = list.size();

			if(size > 0){
				SXSSFWorkbook workbook=new SXSSFWorkbook();
				Sheet sheet=workbook.createSheet();
				//엑셀의 행
				Row row=null;
				//엑셀의 셀
				Cell cell=null;

				if(list !=null &&list.size() >0){
					CellStyle headerStyle = workbook.createCellStyle();
					CellStyle percentStyle = workbook.createCellStyle();
					CellStyle numberStyle = workbook.createCellStyle();
					CellStyle formatStyle = workbook.createCellStyle();
					percentStyle.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
					numberStyle.setDataFormat(workbook.createDataFormat().getFormat("0"));
					formatStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
					Font headerFont = workbook.createFont();
					headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
					headerFont.setColor(IndexedColors.WHITE.getIndex());
					headerFont.setBold(true);
					headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					headerStyle.setAlignment(HorizontalAlignment.CENTER);

					headerStyle.setFont(headerFont);
					row=sheet.createRow((short)0);
					for(int k=0;k<titleList.length;k++){
		                //생성된 row에 컬럼을 생성한다
		                cell=row.createCell(k);
		                cell.setCellStyle(headerStyle);
		                //map에 담긴 데이터를 가져와 cell에 add한다
		                cell.setCellValue(String.valueOf(new String(titleList[k].getBytes("EUC-KR"), "EUC-KR")));
		            }
				    int i=0;
				    for(Map<String,Object>mapobject : list){
				        // 시트에 하나의 행을 생성한다(i 값이 0이면 첫번째 줄에 해당)
				        row=sheet.createRow((int)i+1);
				        i++;
				        if(keysList !=null && keysList.length >0){
				            for(int j=0;j<keysList.length;j++){
				                //생성된 row에 컬럼을 생성한다
				                cell=row.createCell(j);
				                //map에 담긴 데이터를 가져와 cell에 add한다
								if(mapobject.get(keysList[j]) != null){
									if((mapobject.get(keysList[j]).toString().contains(","))
										&& StringUtils.isNumeric(String.valueOf(mapobject.get(keysList[j]).toString().replace(",", "")))) {
										cell.setCellValue (Double.parseDouble(String.valueOf(mapobject.get(keysList[j]).toString().replace(",", ""))));
										cell.setCellStyle(formatStyle);
									} else if(mapobject.get(keysList[j]).toString().endsWith("%") // % 체크
										&& mapobject.get(keysList[j]).toString().contains(".")){ // . 체크
										cell.setCellValue (Double.parseDouble(String.valueOf(mapobject.get(keysList[j]).toString().replace("%", ""))) / 100.00);
										cell.setCellStyle(percentStyle);
									} else if(NumberUtils.isNumber(String.valueOf(mapobject.get(keysList[j])))){
										cell.setCellValue (Double.parseDouble(String.valueOf(mapobject.get(keysList[j]).toString())));
										cell.setCellStyle(numberStyle);
									} else {
										cell.setCellValue (String.valueOf(mapobject.get(keysList[j])));
									}
								} else {
									cell.setCellValue ("");
								}

				            }
				        }
				    }

				    for(int s=0; s<keysList.length; s++){
				    	double width = SheetUtil.getColumnWidth(sheet, s, false);

				        if (width != -1){
				        	width *= 256;
				            int maxColumnWidth = 255*256;
				            if (width > maxColumnWidth){
				                width = maxColumnWidth;
				                sheet.setColumnWidth(s, 2000);
				            } else {
				            	sheet.setColumnWidth(s, (short) sheet.getColumnWidth(s) + 512);
				            }
				        }
					}
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.gc();
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
	 @Value("${global.fileStorePath}")
	String origin_fileStorePath;

	@RequestMapping(value="imageUpload")
	public @ResponseBody HashMap<String, Object> boardImageUpload(MultipartHttpServletRequest multiRequest, HttpServletResponse response, ModelMap model, SessionStatus status) {
    	String storePathString = origin_fileStorePath;
    	String filePath = "";
    	String date = (DateUtil.getTodateYmd().replaceAll("-", ""));
    	HashMap<String, Object> result = new HashMap<>();
    	int seq = 0;
    	try {
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
			MultipartFile file;
			response.setContentType("text/html;charset=UTF-8");
			while (itr.hasNext()) {
				FileVo fileVo = new FileVo();
			    Entry<String, MultipartFile> entry = itr.next();
			    file = entry.getValue();
	    		if (!file.isEmpty()) {

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

	    			fileVo.setOriginal_nm(orginFileName);
	    			fileVo.setFile_nm(newName+ "."+fileExt);
	    			fileVo.setFile_path(filePath);
	    			fileVo.setSave_path("/upload/"+date+"/"+newName+"."+fileExt);
		    		fileVo.setFile_size(Long.toString(_size));
		    		fileVo.setBbs_seq(String.valueOf(seq));

		    		result.put("link", fileVo.getSave_path());

			    }
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	

	@RequestMapping(value = "courseComplete")
	public ModelAndView courseComplete(@RequestParam HashMap<String, Object> hashmapParam,HttpSession session) {
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/common/common/courseComplete");
		mv.addObject("data", hashmapParam);
		return mv ;
	}
	@RequestMapping(value = "courseComplete2")
	public ModelAndView courseComplete2(@RequestParam HashMap<String, Object> hashmapParam,HttpSession session) {
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/common/common/courseComplete2");
		mv.addObject("data", hashmapParam);
		return mv ;
	}

	/**
	 * Achievement Test 결과 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="achievementTestResult")
	public ModelAndView achievementTestResult(@RequestParam HashMap<String, Object> hashmapParam){

		ModelAndView mv = new ModelAndView();

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		HashMap<String, Object> return_map = new HashMap<String, Object>();

		try {

			map = mapper.getAchievementTestInfoRetrieve(hashmapParam);

			if(map != null) {

				int acTestTotalCnt = mapper.getAchievementTestTotalCnt(hashmapParam);
				int acTestRankCnt = mapper.getAchievementTestRankCnt(hashmapParam);

				int totalRank = (int) Math.round(((double)(acTestRankCnt - 1) / (double)(acTestTotalCnt - 1)) * 100);

				list = mapper.getAchievementTestListRetrieve(hashmapParam);

				map.put("total_rank", totalRank);

				return_map.put("achievementTestInfo", map);
				return_map.put("achievementTestList", list);
			}

			mv.setViewName("/common/common/achievementTestResult");
			mv.addObject("data", return_map);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping(value="studyReportDtlView")
	public ModelAndView studyReportDtlView(@RequestParam HashMap<String, Object> hashmapParam) {
		ModelAndView mv = new ModelAndView();

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> r_map = new HashMap<String, Object>();
		HashMap<String, Object> p_map = null;

		String tbl_name = "";
		String tab_name = "";
		String learning_title = "";
		String learning_type = "";

		try {

			learning_title = hashmapParam.get("title").toString().toUpperCase();
			learning_type = hashmapParam.get("type").toString().substring(0, 1).toUpperCase() + hashmapParam.get("type").toString().substring(1).toLowerCase();

			tab_name = hashmapParam.get("tab_name").toString().toUpperCase();
			tbl_name = hashmapParam.get("tbl_name").toString().toUpperCase();

			if(tab_name.equals("WORD")) {

				if(tbl_name.equals("CURRI")) {
					list = mapper.getCurriWordInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("FREE")) {
					list = mapper.getFreeWordInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("HOMEWORK")) {
					list = mapper.getHomeworkWordInfoRetrieve(hashmapParam);
				}

			}else if(tab_name.equals("SENTENCE")) {

				if(tbl_name.equals("CURRI")) {
					list = mapper.getCurriSentenceInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("FREE")) {
					list = mapper.getFreeSentenceInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("HOMEWORK")) {
					list = mapper.getHomeworkSentenceInfoRetrieve(hashmapParam);
				}

			}else if(tab_name.equals("DUBBING")) {

				if(tbl_name.equals("CURRI")) {
					r_map = mapper.getCurriDubbingInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("FREE")) {
					r_map = mapper.getFreeDubbingInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("HOMEWORK")) {
					r_map = mapper.getHomeworkDubbingInfoRetrieve(hashmapParam);
				}

				/*
				 * learning_time 의 칼럼에 데이터 의미는 다음과 같다.
				 * 총 학습시간_X_전체시간_총 녹음시간*점수 ( 두번 째 값은 의미 X )
				 * ex) 82_19_38_19*30/19*30 : 총 학습시간:82초, 전체시간:38초, 평균녹음시간:19초, 점수:30
				 * '/' 개수 + 1 은 도전횟수이다.
				 */

				if(r_map != null) {

					int try_cnt = r_map.get("learning_time").toString().split("/").length;
					int rate = (int) Math.floor(100 / try_cnt);
					String all_time = r_map.get("learning_time").toString().split("_")[2];

					for(int i = 0; i < try_cnt; i++) {

						p_map = new HashMap<String, Object>();

						p_map.put("try_no", i + 1);
						p_map.put("students_no", r_map.get("students_no"));
						p_map.put("str_eng", r_map.get("str_eng"));
						p_map.put("rate", rate);
						p_map.put("all_time", all_time);
						p_map.put("section", r_map.get("section"));
						p_map.put("activity_no", r_map.get("activity_no"));
						p_map.put("native_file", r_map.get("native_file"));
						p_map.put("reg_dt", r_map.get("reg_dt"));

						String data = r_map.get("learning_time").toString().split("/")[i];

						if(i == 0) {
							p_map.put("avg_time", data.split("_")[3].split("\\*")[0]);
							p_map.put("score", data.split("_")[3].split("\\*")[1]);
						}else {
							p_map.put("avg_time", data.split("\\*")[0]);
							p_map.put("score", data.split("\\*")[1]);
						}

						list.add(p_map);
					}
				}

			}else if(tab_name.equals("QNA")) {

				if(tbl_name.equals("CURRI")) {
					list = mapper.getCurriQnaInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("FREE")) {
					list = mapper.getFreeQnaInfoRetrieve(hashmapParam);
				}else if(tbl_name.equals("HOMEWORK")) {
					list = mapper.getHomeworkQnaInfoRetrieve(hashmapParam);
				}

			}

			mv.setViewName("/common/common/studyReportDtlView");
			mv.addObject("tab_name", tab_name);
			mv.addObject("tbl_name", tbl_name);
			mv.addObject("learning_title", learning_title);
			mv.addObject("learning_type", learning_type);
			mv.addObject("data", list);

		}catch(Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping(value="getWdInfoListRetrieve")
	public @ResponseBody HashMap<String, Object> getWdInfoListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			resultList =  mapper.getWdInfoListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;
	}

	@RequestMapping(value="getStInfoListRetrieve")
	public @ResponseBody HashMap<String, Object> getStInfoListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			resultList =  mapper.getStInfoListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;
	}

	@RequestMapping(value="getDuInfoListRetrieve")
	public @ResponseBody HashMap<String, Object> getDuInfoListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			resultList =  mapper.getDuInfoListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;
	}

	@RequestMapping(value="getQaInfoListRetrieve")
	public @ResponseBody HashMap<String, Object> getQaInfoListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			resultList =  mapper.getQaInfoListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;
	}

	@RequestMapping(value="getAsInfoListRetrieve")
	public @ResponseBody HashMap<String, Object> getAsInfoListRetrieve(@RequestParam HashMap<String, Object> hashmapParam){

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();

		try {
			resultList =  mapper.getAsInfoListRetrieve(hashmapParam);
			hashmapResult.put("rows", resultList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashmapResult;
	}

	@RequestMapping(value="studyReportView")
	public ModelAndView studyReportView(@RequestParam(value="rpt_seq_enc", required=true) String rpt_seq_enc, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		HashMap<String, Object> param = new HashMap<String, Object>();

		/* 학생정보, 학원수강정보 */
		HashMap<String, Object> stdInfo = new HashMap<>();
		List<HashMap<String, Object>> stdAcaInfo = null ;

		String jsonStdAcaInfo = null ;

		/* 학습 시간 */
		HashMap<String, Object> stdStdTotTime = new HashMap<>();
		HashMap<String, Object> stdAtdAvgDt = new HashMap<>();
		HashMap<String, Object> stdAtdTopAvg = new HashMap<>();
		HashMap<String, Object> stdAtdTopTotAvg = new HashMap<>();

		/* 학습 시간 일별 모니터링 */
		List<HashMap<String, Object>> stdChartDt = null ;
		List<HashMap<String, Object>> stdChartDayStd = null ;
		List<HashMap<String, Object>> stdChartDayAca = null ;
		List<HashMap<String, Object>> stdChartDayTop = null ;

		String jsonStdChartDt = null ;
		String jsonStdChartDayStd = null ;
		String jsonStdChartDayAca = null ;
		String jsonStdChartDayTop = null ;

		/* 요일별 평균 학습 모니터링 */
		List<HashMap<String, Object>> stdChartDayAvgAca = null ;
		List<HashMap<String, Object>> stdChartDayAvgHom = null ;
		List<HashMap<String, Object>> stdChartDayAvgOwn = null ;

		String jsonStdChartDayAvgAca = null ;
		String jsonStdChartDayAvgHom = null ;
		String jsonStdChartDayAvgOwn = null ;

		List<HashMap<String, Object>> stdStdDayAvgAca = null ;
		List<HashMap<String, Object>> stdStdDayAvgAcaSub = null ;
		List<HashMap<String, Object>> stdStdDayAvgHom = null ;
		List<HashMap<String, Object>> stdStdDayAvgHomSub = null ;
		List<HashMap<String, Object>> stdStdDayAvgOwn = null ;

		String jsonStdDayAvgAca = null ;
		String jsonStdDayAvgHom = null ;
		String jsonStdDayAvgOwn = null ;

		/* 학생 학습통계 */
		List<HashMap<String, Object>> stdStdTotBarPlan = null ;
		List<HashMap<String, Object>> stdStdTotBarImp = null ;
		List<HashMap<String, Object>> stdStdTotImpPer = null ;
		List<HashMap<String, Object>> stdStdTotStat = null ;
		
		/* 레포트 정보 */
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
		HashMap<String, Object> planInfo = new HashMap<>();

		String jsonStdTotBarPlan = null ;
		String jsonStdTotBarImp = null ;
		String jsonStdTotImpPer = null ;
		String jsonStdTotStat = null ;

		try {
			ObjectMapper jsonMapper = new ObjectMapper();

			/* 학생정보, 학원수강정보 */
			stdInfo = mapper.getStudentInfo(rpt_seq_enc);
			stdAcaInfo = mapper.getStudentAcaInfoList(rpt_seq_enc);

			jsonStdAcaInfo = jsonMapper.writeValueAsString(stdAcaInfo);

			/* 학습 시간 */
			stdStdTotTime = mapper.getStudentTotTime(rpt_seq_enc);
			
			// 센터평균 방문일수
			String aca_id = mapper.getSelectAcaId(rpt_seq_enc);
			param.put("aca_id", aca_id);
			param.put("rpt_seq_enc", rpt_seq_enc);	
			stdAtdAvgDt = mapper.getStudentAtdAvgDt(param);
			stdAtdTopAvg = mapper.getStudentAtdTopAvg(rpt_seq_enc);
			stdAtdTopTotAvg = mapper.getStudentAtdTopTotAvg(rpt_seq_enc);

			/* 학습 시간 일별 모니터링 */
			stdChartDt = mapper.getStudentChartDt(rpt_seq_enc);
			stdChartDayStd = mapper.getStudentChartDayStd(rpt_seq_enc);
			stdChartDayAca = mapper.getStudentChartDayAca(rpt_seq_enc);
			stdChartDayTop = mapper.getStudentChartDayTop(rpt_seq_enc);

			jsonStdChartDt = jsonMapper.writeValueAsString(stdChartDt);
			jsonStdChartDayStd = jsonMapper.writeValueAsString(stdChartDayStd);
			jsonStdChartDayAca = jsonMapper.writeValueAsString(stdChartDayAca);
			jsonStdChartDayTop = jsonMapper.writeValueAsString(stdChartDayTop);

			/* 요일별 평균 학습 모니터링 */
			stdChartDayAvgAca = mapper.getStudentChartDayAvgAca(rpt_seq_enc);
			stdChartDayAvgHom = mapper.getStudentChartDayAvgHom(rpt_seq_enc);
			stdChartDayAvgOwn = mapper.getStudentChartDayAvgOwn(rpt_seq_enc);

			stdStdDayAvgAca = mapper.getStudentDayAvgAcaList(rpt_seq_enc);
			stdStdDayAvgAcaSub = mapper.getStudentDayAvgAcaSubList(rpt_seq_enc);
			stdStdDayAvgHom = mapper.getStudentDayAvgHomList(rpt_seq_enc);
			stdStdDayAvgHomSub = mapper.getStudentDayAvgHomSubList(rpt_seq_enc);
			stdStdDayAvgOwn = mapper.getStudentDayAvgOwnList(rpt_seq_enc);

			jsonStdChartDayAvgAca = jsonMapper.writeValueAsString(stdChartDayAvgAca);
			jsonStdChartDayAvgHom = jsonMapper.writeValueAsString(stdChartDayAvgHom);
			jsonStdChartDayAvgOwn = jsonMapper.writeValueAsString(stdChartDayAvgOwn);

			jsonStdDayAvgAca = jsonMapper.writeValueAsString(stdStdDayAvgAca);
			jsonStdDayAvgHom = jsonMapper.writeValueAsString(stdStdDayAvgHom);
			jsonStdDayAvgOwn = jsonMapper.writeValueAsString(stdStdDayAvgOwn);

			/* 학생 학습통계 */
			stdStdTotBarPlan = mapper.getStudentTotBarPlan(rpt_seq_enc);
			stdStdTotBarImp = mapper.getStudentTotBarImp(rpt_seq_enc);
			stdStdTotImpPer = mapper.getStudentTotImpPer(rpt_seq_enc);
			stdStdTotStat = mapper.getStudentTotStat(rpt_seq_enc);
			
			/* 레포트 정보 */			
			hashmapParam.put("rpt_seq_enc", rpt_seq_enc);
			planInfo = mapper.getDailyPlanInfo(hashmapParam);

			jsonStdTotBarPlan = jsonMapper.writeValueAsString(stdStdTotBarPlan);
			jsonStdTotBarImp = jsonMapper.writeValueAsString(stdStdTotBarImp);
			jsonStdTotImpPer = jsonMapper.writeValueAsString(stdStdTotImpPer);
			jsonStdTotStat = jsonMapper.writeValueAsString(stdStdTotStat);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* 학생정보, 학원수강정보 */
		mv.addObject("stdInfo", stdInfo);
		mv.addObject("jsonStdAcaInfo", jsonStdAcaInfo);

		/* 학습 시간 */
		mv.addObject("stdStdTotTime", stdStdTotTime);
		mv.addObject("stdAtdAvgDt", stdAtdAvgDt);
		mv.addObject("stdAtdTopAvg", stdAtdTopAvg);
		mv.addObject("stdAtdTopTotAvg", stdAtdTopTotAvg);

		/* 학습 시간 일별 모니터링 */
		mv.addObject("jsonStdChartDt", jsonStdChartDt);
		mv.addObject("jsonStdChartDayStd", jsonStdChartDayStd);
		mv.addObject("jsonStdChartDayAca", jsonStdChartDayAca);

		/* 요일별 평균 학습 모니터링 */
		mv.addObject("jsonStdChartDayAvgAca", jsonStdChartDayAvgAca);
		mv.addObject("jsonStdChartDayAvgHom", jsonStdChartDayAvgHom);
		mv.addObject("jsonStdChartDayAvgOwn", jsonStdChartDayAvgOwn);

		mv.addObject("stdStdDayAvgAcaSub", stdStdDayAvgAcaSub);
		mv.addObject("stdStdDayAvgHomSub", stdStdDayAvgHomSub);

		mv.addObject("jsonStdDayAvgAca", jsonStdDayAvgAca);
		mv.addObject("jsonStdDayAvgHom", jsonStdDayAvgHom);
		mv.addObject("jsonStdDayAvgOwn", jsonStdDayAvgOwn);
		mv.addObject("jsonStdChartDayTop", jsonStdChartDayTop);

		/* 학생 학습통계 */
		mv.addObject("jsonStdTotBarPlan", jsonStdTotBarPlan);
		mv.addObject("jsonStdTotBarImp", jsonStdTotBarImp);
		mv.addObject("jsonStdTotImpPer", jsonStdTotImpPer);
		mv.addObject("jsonStdTotStat", jsonStdTotStat);
		
		/* 레포트 정보 */
		mv.addObject("planInfo", planInfo);

		mv.setViewName("/common/common/studyReportView");

		return mv;
	}
	
	/**
	 * 데일리 레포트
	 * @param rpt_seq_enc
	 * @return
	 */
	@RequestMapping(value="studyDailyReportView")
	public ModelAndView studyDailyReportView(@RequestParam HashMap<String, Object> hashmapParam) {
		
		ModelAndView mv = new ModelAndView();
		
		/* 학생정보 */
		HashMap<String, Object> stdInfo = new HashMap<>();
		
		/* 레포트 정보 */
		HashMap<String, Object> planInfo = new HashMap<>();
		
		/* 데일리 학습시간 통계 */
		HashMap<String, Object> stdAcaTot = new HashMap<>();
		HashMap<String, Object> stdAcaHworkTot = new HashMap<>();
		List<HashMap<String, Object>> stdOutTot = null;
		HashMap<String, Object> dailyReultTime = new HashMap<String, Object>();
				
		/* 데일리 학습플래너 */
		List<HashMap<String, Object>> stdDailyPlan = null;
		
		/* 데일리 학습교재 */
		List<HashMap<String, Object>> stdReadBookInfo = null; 
		
		String jsonStdActiveTime = null;
		String jsonStdDailyPlan = null;
		String jsonStdReadBookInfo = null;
		String jsonStdDailyTimeline = null;
		
		
		try {
			
			ObjectMapper jsonMapper = new ObjectMapper();
			
			/* 학생정보 */
			String rpt_seq_enc = "";
			rpt_seq_enc = (String) hashmapParam.get("rpt_seq_enc");
			stdInfo = mapper.getStudentInfo(rpt_seq_enc);
			
			/* 레포트 정보 */
			planInfo = mapper.getDailyPlanInfo(hashmapParam);
			
			/* 데일리 학습시간 통계 */
			stdAcaTot = mapper.getDailyAcaTotalTime(hashmapParam);
			stdAcaHworkTot = mapper.getDailyAcaHomeworkTime(hashmapParam);
			stdOutTot = mapper.getDailyStdOutTime(hashmapParam);
			
			int tot_gap = 0;
			String[] atd_rsn = {"11", "12", "49"};
			for(HashMap<String, Object> map : stdOutTot) {				
				if(map.get("ATD_IO").equals("2")) {					
					for(int i=0;i<atd_rsn.length;i++) {
						if(atd_rsn[i].equals(map.get("ATD_RSN_CD"))) {
							if(map.get("TIME_GAP") != "") {
							tot_gap = tot_gap + Integer.parseInt(String.valueOf(map.get("TIME_GAP")));
							}
						}
					}					
				}				
			}					

			
			/* 데일리 학습플래너 */
			stdDailyPlan = mapper.getDailyStudyPlan(hashmapParam);
			
			/* 학습교재 */
			stdReadBookInfo = mapper.getDailyStudyBookInfo(hashmapParam);
			
			dailyReultTime.put("acaTot", stdAcaTot);
			dailyReultTime.put("acaHomeWork", stdAcaHworkTot);
			dailyReultTime.put("stdOutTime", tot_gap);
						
			jsonStdDailyPlan = jsonMapper.writeValueAsString(stdDailyPlan);
			jsonStdReadBookInfo = jsonMapper.writeValueAsString(stdReadBookInfo);
			jsonStdActiveTime = jsonMapper.writeValueAsString(dailyReultTime);
			jsonStdDailyTimeline = jsonMapper.writeValueAsString(stdOutTot);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		mv.addObject("stdInfo", stdInfo);
		mv.addObject("planInfo", planInfo);
		mv.addObject("jsonDailyReultTime", jsonStdActiveTime);
		mv.addObject("jsonStdDailyPlan", jsonStdDailyPlan);
		mv.addObject("jsonStdReadBookInfo", jsonStdReadBookInfo);
		mv.addObject("jsonStdDailyTimeline", jsonStdDailyTimeline);
		
		
		mv.setViewName("/common/common/studyDailyReportView");
		
		return mv;
		
	}
	
	
	@RequestMapping(value="studyDailyReportView/{rpt_seq_enc}/{date}")
	public ModelAndView studyDailyReportViewSec(@PathVariable String rpt_seq_enc, @PathVariable String date, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
		hashmapParam.put("rpt_seq_enc", rpt_seq_enc);
		hashmapParam.put("date", date);		
		
		/* 학생정보 */
		HashMap<String, Object> stdInfo = new HashMap<>();
		
		/* 레포트 정보 */
		HashMap<String, Object> planInfo = new HashMap<>();
		
		/* 데일리 학습시간 통계 */
		HashMap<String, Object> stdAcaTot = new HashMap<>();
		HashMap<String, Object> stdAcaHworkTot = new HashMap<>();
		List<HashMap<String, Object>> stdOutTot = null;
		HashMap<String, Object> dailyReultTime = new HashMap<String, Object>();
				
		/* 데일리 학습플래너 */
		List<HashMap<String, Object>> stdDailyPlan = null;
		
		/* 데일리 학습교재 */
		List<HashMap<String, Object>> stdReadBookInfo = null; 
		
		String jsonStdActiveTime = null;
		String jsonStdDailyPlan = null;
		String jsonStdReadBookInfo = null;
		String jsonStdDailyTimeline = null;
		
		
		try {
			
			ObjectMapper jsonMapper = new ObjectMapper();
			
			/* 학생정보 */
			//String rpt_seq_enc = "";
			//rpt_seq_enc = (String) hashmapParam.get("rpt_seq_enc");
			stdInfo = mapper.getStudentInfo(rpt_seq_enc);
			
			/* 레포트 정보 */
			planInfo = mapper.getDailyPlanInfo(hashmapParam);
			
			/* 데일리 학습시간 통계 */
			stdAcaTot = mapper.getDailyAcaTotalTime(hashmapParam);
			stdAcaHworkTot = mapper.getDailyAcaHomeworkTime(hashmapParam);
			stdOutTot = mapper.getDailyStdOutTime(hashmapParam);
			
			int tot_gap = 0;
			String[] atd_rsn = {"11", "12", "49"};
			for(HashMap<String, Object> map : stdOutTot) {				
				if(map.get("ATD_IO").equals("2")) {					
					for(int i=0;i<atd_rsn.length;i++) {
						if(atd_rsn[i].equals(map.get("ATD_RSN_CD"))) {
							if(map.get("TIME_GAP") != "") {
							tot_gap = tot_gap + Integer.parseInt(String.valueOf(map.get("TIME_GAP")));
							}
						}
					}					
				}				
			}					

			
			/* 데일리 학습플래너 */
			stdDailyPlan = mapper.getDailyStudyPlan(hashmapParam);
			
			/* 학습교재 */
			stdReadBookInfo = mapper.getDailyStudyBookInfo(hashmapParam);
			
			dailyReultTime.put("acaTot", stdAcaTot);
			dailyReultTime.put("acaHomeWork", stdAcaHworkTot);
			dailyReultTime.put("stdOutTime", tot_gap);
						
			jsonStdDailyPlan = jsonMapper.writeValueAsString(stdDailyPlan);
			jsonStdReadBookInfo = jsonMapper.writeValueAsString(stdReadBookInfo);
			jsonStdActiveTime = jsonMapper.writeValueAsString(dailyReultTime);
			jsonStdDailyTimeline = jsonMapper.writeValueAsString(stdOutTot);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		mv.addObject("stdInfo", stdInfo);
		mv.addObject("planInfo", planInfo);
		mv.addObject("jsonDailyReultTime", jsonStdActiveTime);
		mv.addObject("jsonStdDailyPlan", jsonStdDailyPlan);
		mv.addObject("jsonStdReadBookInfo", jsonStdReadBookInfo);
		mv.addObject("jsonStdDailyTimeline", jsonStdDailyTimeline);
		
		
		mv.setViewName("/common/common/studyDailyReportView");
		
		return mv;
		
	}
	
	@RequestMapping(value="/getSeatStatusInfoRetrieve")
	public @ResponseBody ReturnDataVO getSeatStatusInfoRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session){

		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		hashmapParam.put("aca_id", member.getAca_id());

		try {
			list = mapper.getSeatStatusInfoRetrieve(hashmapParam);

			result.setResultCode("S000");
			result.setData(list);

		} catch (Exception e) {
			result.setResultMsg("에러가 발생하였습니다.");
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="studyPlanFormView")
	public ModelAndView studyPlanFormView(@RequestParam HashMap<String, Object> hashmapParam) {
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> stdInfo = new HashMap<>();
		List<HashMap<String, Object>> stdSchInfo = null ;
		String jsonStdSchInfo = null ;

		try {
			ObjectMapper jsonMapper = new ObjectMapper();

			stdInfo = mapper.getStudyPlanStdInfo(hashmapParam);
			stdSchInfo = mapper.getStudySchInfo(hashmapParam);

			jsonStdSchInfo = jsonMapper.writeValueAsString(stdSchInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		/* 학생정보, 학원수강정보 */
		mv.addObject("stdInfo", stdInfo);
		mv.addObject("jsonStdSchInfo", jsonStdSchInfo);
		mv.setViewName("/common/common/studyPlanFormView");
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "pgCashResult")
	public @ResponseBody HashMap<String, Object> pgCashResult(@RequestParam HashMap<String, String> hashmapParam,HttpSession session) {
		HashMap<String, Object> result  = new HashMap<>();
		System.out.println("## pgCashResult");
		result = (HashMap<String, Object>) HttpUtil.callURL("https://pg.paynuri.com/paymentgateway/app/mnul/payment.do", null, hashmapParam, "EUC-KR");

		return result;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "pgCashRejectResult")
	public @ResponseBody HashMap<String, Object> pgCashRejectResult(@RequestParam HashMap<String, String> hashmapParam,HttpSession session) {
		HashMap<String, Object> result  = new HashMap<>();
		System.out.println("## pgCashRejectResult");
		result = (HashMap<String, Object>) HttpUtil.callURL("https://pg.paynuri.com/paymentgateway/cancelPayment.do", null, hashmapParam, "EUC-KR");

		return result;
	}

	@RequestMapping(value="/getEmpList")
	public @ResponseBody ReturnDataVO getEmpList(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();

		try {
			resultList =  mapper.getEmpList(hashmapParam);

			result.setData(resultList);
			result.setResultCode("S000");

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
		return result;

	}

	@RequestMapping(value="/getTeacherList")
	public @ResponseBody ReturnDataVO getTeacherList(@RequestParam HashMap<String, Object> hashmapParam){

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();

		try {
			resultList =  mapper.getTeacherList(hashmapParam);

			result.setData(resultList);
			result.setResultCode("S000");

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러가 발생하였습니다.");
		}
		return result;

	}
	@RequestMapping(value = "educationPayCertificate")
	public ModelAndView educationPayCertificate(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		HashMap<String, Object> cf_map = new HashMap<String, Object>();
		List<HashMap<String, Object>> cf_list = new ArrayList<HashMap<String, Object>>();

		System.out.println("hashmapParam => " + hashmapParam);
		
		try {

			String[] invoice_nos = hashmapParam.get("invoice_no").toString().split(",");
			
			hashmapParam.put("invoice_nos", invoice_nos);
			
			cf_map = mapper.getPayCertificateInfoRetrieve(hashmapParam);
			cf_list = mapper.getPayCertificateListRetrieve(hashmapParam);
			
			mv.addObject("cf_map", cf_map);
			mv.addObject("cf_list", cf_list);
			mv.setViewName("/common/common/educationPayCertificate2");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return mv;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/acaTelNo")
	public @ResponseBody String acaTelNo(@RequestParam(value="pid", required=true) String pid) {
		HashMap<String, Object> result = new HashMap<>();
		
		HashMap<String, String> param = new HashMap<>();
		param.put("pkind", "3");
		param.put("pid", pid);
		
		result = (HashMap<String, Object>) HttpUtil.callURL("http://smsmsgr.ione24.com/_support/mem_check_owra.ashx", null, param, "UTF-8");
		
		return (String) result.get("responseBody");
	}
	
	
	/**
	 * 셔틀버스명 조회
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/center/list/json/selectShuttleBusName")
	public @ResponseBody ReturnDataVO selectShuttleBusName(@RequestParam HashMap<String, Object> hashmapParam) {
		
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		ReturnDataVO result = new ReturnDataVO();
		
		try {
			
			resultList = mapper.selectShuttleBusName(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	/**
	 * 셔틀버스 기사 조회
	 * @param hashmapParam
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/center/list/json/selectDriverList")
	public @ResponseBody ReturnDataVO selectDriverList(@RequestParam HashMap<String, Object> hashmapParam){
		
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		ReturnDataVO result = new ReturnDataVO();		
		
		try {
			
			resultList = mapper.selectDriverList(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/center/list/json/selectDriverHelper")
	public @ResponseBody ReturnDataVO selectDriverHelper(@RequestParam HashMap<String, Object> hashmapParam) {
		
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		ReturnDataVO result = new ReturnDataVO();	
		
		try {
			
			resultList = mapper.selectHelperList(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**
	 * 셔틀버스 노선조회 select
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getSearchShuttleMst")
	public @ResponseBody ReturnDataVO getSearchShuttleMst(@RequestParam HashMap<String, Object> hashmapParam) {
		
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		
		
		try {
			
			resultList = mapper.getSearchShuttleMst(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");
			
		} catch(Exception e) {
			result.setResultCode("S999");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**
	 * 셔틀버스 정류장조회 select
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getSearchShuttleSta")
	public @ResponseBody ReturnDataVO getSearchShuttleSta(@RequestParam HashMap<String, Object> hashmapParam) {
		
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
		
		
		try {
			
			resultList = mapper.getSearchShuttleSta(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");
			
		} catch(Exception e) {
			result.setResultCode("S999");
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**
	 * 셔틀버스 도착시간 select
	 * @param hashmapParam
	 * @return
	 */
	@RequestMapping(value="/getSearchShuttleArrTm")
	public @ResponseBody ReturnDataVO getSearchShuttleArrTm(@RequestParam HashMap<String, Object> hashmapParam) {
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();		
		
		try {
			
			resultList = mapper.getSearchShuttleArrTm(hashmapParam);
			result.setData(resultList);
			result.setResultCode("S000");
			
		} catch(Exception e) {
			result.setResultCode("S999");
			e.printStackTrace();
		}
		
		return result;
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
		HashMap<String, Object> info = new HashMap<String, Object>();
		
		try {
			
			if(isLogon != null && isLogon.equals("Y") && loginUserVo != null && !("").equals(loginUserVo.getUser_id()))
			{				
				info = centerInfoMngMapper.getCenterInfoRetrieve(hashmapParam);
				
				loginUserVo.setAca_id(hashmapParam.get("aca_id").toString());
				loginUserVo.setAca_nm(hashmapParam.get("aca_nm").toString());	
				if(info.get("user_nm") != null) {
					loginUserVo.setUser_nm(info.get("user_nm").toString());
				} else {
					loginUserVo.setUser_nm("");
				}
				
				if(!info.isEmpty()) {
					loginUserVo.setLogo_use_yn((String) info.get("logo_use_yn"));
					loginUserVo.setLogo_img_path((String) info.get("logo_img_path"));
				}
			
				
				HttpSession session2 = request.getSession(true);
				session2.setAttribute("S_USER", loginUserVo);
			}
						
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
    	
    }
    
        
    @RequestMapping(value="/baseInfoClassEdtCreate")
    public @ResponseBody ReturnDataVO baseInfoClassEdtCreate(
    		@RequestParam HashMap<String, Object> hashmapParam
    		, @RequestParam(value="baseInfoWeekday",required=false) List<String> days
    		, HttpSession session
    		) {
    	
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	
    	try {
    		
    		/*
    		int daysCnt = 0;
			if(days != null){
				daysCnt = days.size();
			}else{
				daysCnt = 0;
			}			

			hashmapParam.put("checkDayCnt", daysCnt);
			
			
			String dayStr = "";
			for(String day : days) {
				dayStr += day;
				dayStr += ",";
			}
			dayStr = StringUtils.chop(dayStr);
			dayStr = "'" + dayStr + "'";
			
			hashmapParam.put("checkDay", dayStr);
			*/
    		hashmapParam.put("aca_id", member.getAca_id());
			String end_dt = mapper.setBaseInfoClassEdt(hashmapParam);
			
			if(end_dt != null) {
				result.setResultCode("S000");
				result.setData(end_dt);
			} else {
				result.setResultCode("S999");
				result.setResultCode("오류가 발생했습니다.");
			}
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		result.setResultCode("S999");
			result.setResultMsg("오류가 발생했습니다.");
    	}
    	
    	
    	return result;
    }
    
    /**
     * 청구서 클래스명 조회
     * (클래스명 못 찾을 경우 조회)
     * @param hashmap
     * @return
     */
    @RequestMapping(value="/getInvoiceClassNm")
    public @ResponseBody ReturnDataVO getInvoiceClassNm(@RequestParam HashMap<String, Object> hashmap) {
    	ReturnDataVO result = new ReturnDataVO();
    	String clsNm = "";
    	
    	
    	try {
    		
    		clsNm = mapper.getInvoiceClassNm(hashmap);
    		result.setResultCode("S000");
    		result.setData(clsNm);
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
	
    @RequestMapping(value="/testTrialView")
    public ModelAndView testTrialView(@RequestParam(value="std_id", required=true) String std_id) {
    	ModelAndView mv = new ModelAndView();
    	HashMap<String, Object> stdInfo = new HashMap<String, Object>();
    	List<HashMap<String, Object>> chartList = new ArrayList<HashMap<String, Object>>();
    	List<HashMap<String, Object>> examList = new ArrayList<HashMap<String, Object>>();
    	ObjectMapper jsonMapper = new ObjectMapper();
    	
    	String jsonStdInfo = null;
    	String jsonChartList = null;
    	String jsonExamList = null;
    	
    	try {
    		
    		byte[] decodedBytes = Base64.getDecoder().decode(std_id);
        	String stdId = new String(decodedBytes);
        	
        	stdInfo = mapper.getStdinfo(stdId);
        	chartList = mapper.getSelectStdTrialExamCharts(stdId);
        	examList = mapper.getSelectStdTrialExamList(stdId);
        	
        	jsonStdInfo = jsonMapper.writeValueAsString(stdInfo);
        	jsonChartList = jsonMapper.writeValueAsString(chartList);
        	jsonExamList = jsonMapper.writeValueAsString(examList);
        	
        	mv.addObject("jsonStdInfo", jsonStdInfo);
        	mv.addObject("jsonChartList", jsonChartList);
        	mv.addObject("jsonExamList", jsonExamList);
        	mv.setViewName("/common/common/testTrialView");
        	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return mv;
    }

	/**
 	 * 수업평가서
 	 * @param  rpt_seq_enc
 	 * @return ModelAndView
 	 */
	  @RequestMapping(value = "dayLearnReportView")
	  public ModelAndView dayLearnReportView(@RequestParam(required = true, value = "rpt_seq_enc") String rpt_seq_enc) {
		  
		  ModelAndView mv = new ModelAndView();
		  HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
		  
		  hashmapParam.put("rpt_seq_enc", rpt_seq_enc);
		  
		  /* 레포트 정보 */
		  HashMap<String, Object> info = new HashMap<>();
		  
		  try {
			  info = mapper.getStdReportInfoRetrieve(hashmapParam);
			  
			  String atdDt = (String) info.get("atd_dt");
			  String[] atdDtArr = atdDt.split("-");
			  
			  info.put("title", atdDtArr[0] +  "년 " + atdDtArr[1] + "월 수업평가서");
			  
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
  
		  mv.addObject("info", info);
		  mv.setViewName("/common/common/dayLearnReportView");
		  
		  return mv;
	  }

	/**
 	 * 독서진단 결과
 	 * @param  rpt_seq_enc
 	 * @return ModelAndView
 	 */
	  @RequestMapping(value = "testBookRsltReportView")
	  public ModelAndView testStdRsltReportView(@RequestParam(required = true, value = "unique_code") String unique_code) {
		  
		ModelAndView mv = new ModelAndView();
		HashMap<String, Object> hashmapParam = new HashMap<String, Object>();
		
		/* 레포트 정보 */
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> examInfo = new HashMap<>();
		HashMap<String, Object> statInfo = new HashMap<>();
		ObjectMapper jsonMapper = new ObjectMapper();

		String question = null;
		String chart = null;
		
		try {

			hashmapParam = mapper.getTestStdReportInfo(unique_code);

			examInfo = testBookRsltMngMapper.getExamDtlRetrieve(hashmapParam);
			
			resultList = testBookRsltMngMapper.getQuestionInfoRetrieve(hashmapParam);
			question = jsonMapper.writeValueAsString(resultList);

			resultList = testBookRsltMngMapper.getExamChartRsltRetrieve(hashmapParam);
			chart = jsonMapper.writeValueAsString(resultList);
			
			statInfo = testBookRsltMngMapper.getStatisticsInfoRetrieve(hashmapParam);

			mv.addObject("examInfo", examInfo);
			mv.addObject("questionInfo", question);
			mv.addObject("statisticsInfo", statInfo);
			mv.addObject("chartInfo", chart);

		} catch(Exception e) {
			e.printStackTrace();
		}

		mv.setViewName("/common/common/testBookRsltReportView");
		
		return mv;
	  }
	  
	@RequestMapping(value = "/getSelectAgeOfDate")
	public @ResponseBody ReturnDataVO getSelectAgeOfDate(@RequestParam HashMap<String, Object> hashmap) {
		ReturnDataVO result = new ReturnDataVO();
		String strAge = "";

		try {

			strAge = mapper.getSelectAgeOfDate(hashmap);
			result.setResultCode("S000");
			result.setData(strAge);

		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg(e.getMessage());
		}

		return result;
	}  
	
	
	 
	private String getDateDay(String date, String dateType) throws Exception {
		 
        String day = "";
 
        SimpleDateFormat df = new SimpleDateFormat(dateType); // 인스턴스 생성
        Date nDate = df.parse(date); // date 부분 잘라냄
 
        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);// nDate 기준으로 날짜 변경
 
        int dayNum = cal.get(Calendar.DAY_OF_WEEK); // Calendar로부터 요일상수 받음
 
        switch (dayNum) { // daynum 1=일요일 2=월요일 ...
        case 1:
            day = "일";
            break;
        case 2:
            day = "월";
            break;
        case 3:
            day = "화";
            break;
        case 4:
            day = "수";
            break;
        case 5:
            day = "목";
            break;
        case 6:
            day = "금";
            break;
        case 7:
            day = "토";
            break;
        }
 
        return day;
 
    }
	
}
