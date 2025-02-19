package com.web.kdebate.learning.learn.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TestBookRsltMngMapper {

	int getQueryTotalCnt();
	
	List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, Object>> getStudentListRetrieve(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, Object>> getExamListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getBookListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getExamChartRsltRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getExamDtlRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getQuestionInfoRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getStatisticsInfoRetrieve(HashMap<String, Object> hashmapParam);

	void reportSmsCreate(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getSmsInfo(HashMap<String, Object> hashmapParam);

}