package com.web.kdebate.center.center.mapper;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.center.center.domain.CenterInfoMngVO;



@Mapper
public interface CenterInfoMngMapper {

	int getQueryTotalCnt();

	HashMap<String, Object> getCenterInfoRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClsRoomListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getContListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getGoodsListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getPosListRetrieve(HashMap<String, Object> hashmapParam);

	int centerBossMobileUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int centerUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int centerLogCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int classCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int classUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int clsRoomCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int clsRoomUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	List<HashMap<String, Object>> getCorpCdList(HashMap<String, Object> hashmapParam);

	int goodsCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int centerGoodsCreate(@Valid CenterInfoMngVO centerInfoMngVO);
	
	HashMap<String, Object> getSelectCorpInfo(String gds_corp_cd);
	
	void bookInfoCreate(CenterInfoMngVO centerInfoMngVO);
	
	void bookInfoUpdate(CenterInfoMngVO centerInfoMngVO);
	
	void bookStockInfoCreate(CenterInfoMngVO centerInfoMngVO);

	int goodsUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int centerGoodsUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int centerGoodsInfoUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	List<HashMap<String, Object>> getSalesCompListRetrieve(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getSalesCompInfoRetrieve(HashMap<String, Object> hashmapParam);

	String getSalesCorpCd();
	
	int getCorpBizNoDuplChk(@Valid CenterInfoMngVO centerInfoMngVO);

	int salesCorpCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int acaSalesCorpCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int userInfoCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int userAuthCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int salesCorpUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int acaSalesCorpUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int clsSeatPoUpdate(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getMngClassTeacherList(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getMngClassListRetrieve(HashMap<String, Object> hashmapParam);

	int mngClassCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int mngClassUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	String getMaxClsTtime(HashMap<String, Object> hashmapParam);

	String getMaxClsWdayTtime(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getClassTimeListRetrieve(HashMap<String, Object> hashmapParam);

	int classTimeCreate(HashMap<String, Object> map);

	int classTimeDelete(HashMap<String, Object> hashMap);

	List<HashMap<String, Object>> getClassTeacherInfoRetrieve(HashMap<String, Object> hashmapParam);

	void callPrcSyncAcaInfo(HashMap<String, Object> callPrcSyncAcaInfoParam);

	void callPrcSyncRoomTimeTable(HashMap<String, Object> callPrcSyncRoomTimeTableParam);

	String getCorpCd(@Valid CenterInfoMngVO centerInfoMngVO);

	void deleteStdClsTimeTable(HashMap<String, Object> map);

	void deleteStdAtdTimeTable(HashMap<String, Object> map);

	void updateStdAtdTimeTable(HashMap<String, Object> map);

	List<HashMap<String, Object>> getGroupCodeRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getLowerCodeRetrieve(HashMap<String, Object> hashmapParam);

	int codeAcaCreate(@Valid CenterInfoMngVO centerInfoMngVO);

	int codeAcaUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int optionInfoUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	// SMS 잔여포인트 조회
	HashMap<String, Object> getCheckUserPoint(HashMap<String, Object> hashmapParam);

	// 일자별 SMS 내역
	List<HashMap<String, Object>> getSelectSmsHistoryList(HashMap<String, Object> hashmapParam);
	//옵션 호출
	List<HashMap<String, Object>> getCenterOptionRetrieve(@Valid CenterInfoMngVO centerInfoMngVO);

	void deleteTbAcaSchMgt(@Valid CenterInfoMngVO centerInfoMngVO);

	void insertTbAcaSchMgt(@Valid CenterInfoMngVO centerInfoMngVO);

	int getOptionInfoCnt(@Valid CenterInfoMngVO centerInfoMngVO);

	int optionInfoInsert(@Valid CenterInfoMngVO centerInfoMngVO);

	List<HashMap<String, Object>> getLowerCodeRetrieve2(HashMap<String, Object> hashmapParam);

	int codeUpdate(@Valid CenterInfoMngVO centerInfoMngVO);

	int codeCreate(@Valid CenterInfoMngVO centerInfoMngVO);
}
