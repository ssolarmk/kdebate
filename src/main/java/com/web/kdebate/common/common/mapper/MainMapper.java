package com.web.kdebate.common.common.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {

	int getQueryTotalCnt();

	List<HashMap<String, Object>> getCenterStatusCntList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getBossPtListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getParentPtListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAnnualSalesTrendRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getUserStatusCntList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClassStdListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getNoticeListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getBoardDataFileListRetrieve(HashMap<String, Object> hashmapParam);

	int boardDataCntUpdate(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getAcaUseStatusRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> teacherList(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> smsPayInvCheck(HashMap<String, Object> hashmapParam);

	void smsPayReadCreate(HashMap<String, Object> hashmapParam);

	int smsPayReadCheck(HashMap<String, Object> hashmapParam);
}
