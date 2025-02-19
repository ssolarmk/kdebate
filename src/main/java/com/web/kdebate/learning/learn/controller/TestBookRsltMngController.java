package com.web.kdebate.learning.learn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.kdebate.common.common.domain.PageingVO;
import com.web.kdebate.common.common.domain.ReturnDataVO;
import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.learning.learn.service.TestBookRsltMngService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/learning/learn/testBookRsltMng")
public class TestBookRsltMngController {

	@Autowired
	private TestBookRsltMngService mapper;

	@RequestMapping("/view")
	public String testBookRsltMngView(HttpSession session) {
		return "pages/learning/learn/testBookRsltMngView";
	}

	/**
	 * 검색 조건 학급 검색
	 * 
	 * @param hashmapParam
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getClassListRetrieve", method = RequestMethod.POST)
	public @ResponseBody ReturnDataVO srchStdListRetrieve(@RequestParam HashMap<String, Object> hashmapParam, HttpSession session) {
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		SessionVO member = (SessionVO) session.getAttribute("S_USER");

		try {

			String liveYn = String.valueOf(hashmapParam.get("live_chk"));

			if ("Y".equals(liveYn)) {

				if (member.getP_aca_id() == null) {
					hashmapParam.put("aca_id", member.getAca_id());
				} else {
					hashmapParam.put("aca_id", member.getP_aca_id());
				}
			}

			list = mapper.getClassListRetrieve(hashmapParam);

			result.setData(list);
			result.setResultCode("S000");
		} catch (Exception e) {
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 원생목록 가져오기
	 * 
	 * @param hashmapParam
	 * @return json
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getStudentListRetrieve")
	public @ResponseBody HashMap<String, Object> getStudentListRetrieve(
			@RequestBody HashMap<String, Object> hashmapParam, HttpSession session) {

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("p_aca_id", member.getP_aca_id());
		hashmapParam.put("my_aca_id", member.getAca_id());

		try {
			List<String> srch_status = (List<String>) hashmapParam.get("srch_status");
			int srch_status_cnt = 0;
			if (srch_status != null) {
				srch_status_cnt = srch_status.size();
			} else {
				srch_status_cnt = 0;
			}
			hashmapParam.put("srch_status_cnt", srch_status_cnt);
			hashmapParam.put("srch_status_list", srch_status);
			PageingVO pageing = new PageingVO();
			pageing.setPageingVO(hashmapParam);

			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

			hashmapParam.put("start", pageing.getStart());
			hashmapParam.put("end", pageing.getLength());

			resultList = mapper.getStudentListRetrieve(hashmapParam);
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

	/**
	 * 시험결과조회 목록 리스트 조회
	 * 
	 * @param hashmapParam
	 * @param session
	 * @model paging
	 * @return json
	 */
	@RequestMapping(value = "/getExamListRetrieve")
	public @ResponseBody HashMap<String, Object> getExamListRetrieve(@RequestBody HashMap<String, Object> hashmapParam,
			HttpSession session) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		hashmapParam.put("aca_id", member.getAca_id());

		try {

			PageingVO pageing = new PageingVO();
			pageing.setPageingVO(hashmapParam);

			int ordCol = Integer.parseInt(String.valueOf(pageing.getOrder().get(0).get("column")));
			hashmapParam.put("sidx", pageing.getColumns().get(ordCol).get("data"));
			hashmapParam.put("sord", pageing.getOrder().get(0).get("dir"));

			list = mapper.getExamListRetrieve(hashmapParam);
			hashmapResult.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
	}

	/**
	 * 교재 목록 조회
	 * 
	 * @param hashmapParam
	 * @param session
	 * @return json
	 */
	@RequestMapping(value = "/getBookListRetrieve")
	public @ResponseBody ReturnDataVO getBookListRetrieve(@RequestParam HashMap<String, Object> hashmapParam) {
		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		try {
			list = mapper.getBookListRetrieve(hashmapParam);
			result.setData(list);
			result.setResultCode("S000");
		} catch (Exception e) {
			result.setResultCode("S999");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 시험결과 차트 조회
	 * 
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value = "/getExamChartRsltRetrieve")
	public @ResponseBody ReturnDataVO getExamChartRsltRetrieve(@RequestParam HashMap<String, Object> hashmapParam) {

		ReturnDataVO result = new ReturnDataVO();
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		try {
			resultList = mapper.getExamChartRsltRetrieve(hashmapParam);

			result.setResultCode("S000");
			result.setData(resultList);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
		}

		return result;
	}

	/**
	 * 시험 성적 상세 화면
	 * 
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value = "/getExamDtlRetrieve")
	public ModelAndView getExamDtlRetrieve(@RequestParam HashMap<String, Object> hashmapParam) {
		ModelAndView mv = new ModelAndView();

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> examInfo = new HashMap<>();
		HashMap<String, Object> statInfo = new HashMap<>();
		ObjectMapper jsonMapper = new ObjectMapper();

		String question = null;
		String chart = null;

		try {

			examInfo = mapper.getExamDtlRetrieve(hashmapParam);

			resultList = mapper.getQuestionInfoRetrieve(hashmapParam);
			question = jsonMapper.writeValueAsString(resultList);

			resultList = mapper.getExamChartRsltRetrieve(hashmapParam);
			chart = jsonMapper.writeValueAsString(resultList);

			statInfo = mapper.getStatisticsInfoRetrieve(hashmapParam);

			mv.addObject("examInfo", examInfo);
			mv.addObject("questionInfo", question);
			mv.addObject("statisticsInfo", statInfo);
			mv.addObject("chartInfo", chart);

		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("rptMap");
		}

		mv.setViewName("pages/learning/learn/testBookRsltReportView");

		return mv;
	}

	/**
	 * 평가서 문자 전송
	 * 
	 * @param HashMap
	 * @param HttpServletRequest
	 * @return json
	 */
	@Transactional
	@RequestMapping(value = "/reportSmsCreate")
	public @ResponseBody ReturnDataVO reportSmsCreate(@RequestParam HashMap<String, Object> hashmapParam,
			HttpServletRequest request) {

		ReturnDataVO result = new ReturnDataVO();

		try {

			HashMap<String, Object> smsInfo = new HashMap<String, Object>();

			String url = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			String url2 = "/common/common/testBookRsltReportView?unique_code="
					.concat(hashmapParam.get("unique_code").toString());
			url = url.concat(url2);

			hashmapParam.put("req_url", url);

			smsInfo = mapper.getSmsInfo(hashmapParam);

			if (smsInfo != null) {
				mapper.reportSmsCreate(smsInfo);

				result.setResultMsg("전송 완료");

			} else {
				result.setResultMsg("조회된 학부모 번호가 없습니다.");
			}

			result.setResultCode("S000");

		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			result.setResultCode("S999");
		}

		return result;
	}

}