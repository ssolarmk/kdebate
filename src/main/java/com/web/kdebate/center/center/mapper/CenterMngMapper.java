package com.web.kdebate.center.center.mapper;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.center.center.domain.CenterMngVo;
import com.web.kdebate.common.common.domain.FileVo;
import com.web.kdebate.common.common.domain.SessionVO;


@Mapper
public interface CenterMngMapper {

	int getQueryTotalCnt();

	String getNewAcaID();

	List<HashMap<String, Object>> getCenterMngListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getContractListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getLedgerListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getCounselListRetrieve(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, Object>> getAcaAllianceAcaList(HashMap<String, Object> hashmapParam);
	
	List<HashMap<String, String>> getAcaSrchList(HashMap<String, Object> hashmapParam);
		
	int allianceInfoCreate(CenterMngVo centerMngVo);
	
	int allianceMatchingInfoCreate(CenterMngVo centerMngVo);

	List<HashMap<String, Object>> getPrizeListRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getPosListRetrieve(HashMap<String, Object> hashmapParam);

	int centerInfoCreate(CenterMngVo centerMngVo);

	int bossUserCreate(CenterMngVo centerMngVo);

	int acaSalesCorpCreate(CenterMngVo centerMngVo);

	int salesCorpCreate(CenterMngVo centerMngVo);

	int empUserCreate(CenterMngVo centerMngVo);

	int empAuthCreate(CenterMngVo centerMngVo);

	int centerInfoUpdate(CenterMngVo centerMngVo);

	int salesCorpUpdate(CenterMngVo centerMngVo);

	int bossUserUpdate(CenterMngVo centerMngVo);

	int ledgerInfoCreate(CenterMngVo centerMngVo);

	int ledgerInfoUpdate(CenterMngVo centerMngVo);

	int contractInfoCreate(CenterMngVo centerMngVo);

	int contractInfoUpdate(CenterMngVo centerMngVo);

	int counselInfoCreate(CenterMngVo centerMngVo);

	int counselInfoUpdate(CenterMngVo centerMngVo);

	int prizeInfoCreate(CenterMngVo centerMngVo);

	int prizeInfoUpdate(CenterMngVo centerMngVo);

	int posCreate(@Valid CenterMngVo centerMngVo);

	int posUpdate(@Valid CenterMngVo centerMngVo);

	int pgInfoCreate(@Valid CenterMngVo centerMngVo);

	int smsAuthInfoUpdate(@Valid CenterMngVo centerMngVo);

	int corpGoodsCreate(@Valid CenterMngVo centerMngVo);

	int centerLogCreate(@Valid CenterMngVo centerMngVo);

	int ledgerDataFileCreate(FileVo fileVo);

	List<HashMap<String, Object>> getLedgerMultiFileList(HashMap<String, Object> hashmapParam);

	int ledgerDataFileDelete(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getLedgerDataFileList(HashMap<String, Object> hashmapParam);

	int optionInfoUpdate(@Valid CenterMngVo centerMngVo);
	
	int licInfoUpdate(@Valid CenterMngVo centerMngVo);

	void callPrcSyncAcaInfo(HashMap<String, Object> callPrcSyncAcaInfoParam);

	int acaSmsUidInfoUpdate(HashMap<String, String> hashmapParam);

	SessionVO getUserRetrieve(HashMap<String, Object> hashmapParam);

	List<String> getUserGrpRetrieve(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getMenuRetrieve(SessionVO loginUserVo);
	
	int centerCodeCopy(HashMap<String, Object> hashmapParam);

	int centerLvlCopy(HashMap<String, Object> hashmapParam);
	
	int centerCourseCopy(HashMap<String, Object> hashmapParam);

	int getCenterCourseCnt(HashMap<String, Object> hashmapParam);

}
