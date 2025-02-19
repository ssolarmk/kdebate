package com.web.kdebate.learning.learn.service;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.learning.learn.mapper.TestBookRsltMngMapper;



@Service
public class TestBookRsltMngService {

	@Autowired
		private TestBookRsltMngMapper mapper;

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}
	
	public List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClassListRetrieve(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getStudentListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentListRetrieve(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getExamListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getExamListRetrieve(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getBookListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getBookListRetrieve(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getExamChartRsltRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getExamChartRsltRetrieve(hashmapParam);
	}
		
	public HashMap<String, Object> getExamDtlRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getExamDtlRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getQuestionInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getQuestionInfoRetrieve(hashmapParam);
	}
		
	public HashMap<String, Object> getStatisticsInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getStatisticsInfoRetrieve(hashmapParam);
	}
	
	public void reportSmsCreate(HashMap<String, Object> hashmapParam) {
		mapper.reportSmsCreate(hashmapParam);
	}

	public HashMap<String, Object> getSmsInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getSmsInfo(hashmapParam);
	}
		
}