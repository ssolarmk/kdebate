package com.web.kdebate.center.center.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.center.center.domain.CenterMngVo;
import com.web.kdebate.center.center.mapper.CenterMngMapper;
import com.web.kdebate.common.common.domain.FileVo;
import com.web.kdebate.common.common.domain.SessionVO;


@Service
public class CenterMngService {

	@Autowired
	private CenterMngMapper mapper;

	public List<HashMap<String, Object>> getCenterMngListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCenterMngListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getContractListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getContractListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getLedgerListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getLedgerListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getCounselListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCounselListRetrieve(hashmapParam);
	}
	
	public List<HashMap<String, Object>> getAcaAllianceAcaList(HashMap<String, Object> hashmapParam) {
		return mapper.getAcaAllianceAcaList(hashmapParam);
	}
	
	public List<HashMap<String, String>> getAcaSrchList(HashMap<String, Object> hashmapParam) {
		return mapper.getAcaSrchList(hashmapParam);
	}
	
	public int allianceInfoCreate(CenterMngVo centerMngVo) {
		return mapper.allianceInfoCreate(centerMngVo);
	}
	
	public int allianceMatchingInfoCreate(CenterMngVo centerMngVo) {
		return mapper.allianceMatchingInfoCreate(centerMngVo);
	}

	public List<HashMap<String, Object>> getPrizeListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getPrizeListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getPosListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getPosListRetrieve(hashmapParam);
	}

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}

	public String getNewAcaID() {
		return mapper.getNewAcaID();
	}

	public int centerInfoCreate(CenterMngVo centerMngVo) {
		return mapper.centerInfoCreate(centerMngVo);
	}

	public int bossUserCreate(CenterMngVo centerMngVo) {
		return mapper.bossUserCreate(centerMngVo);
	}

	public int salesCorpCreate(CenterMngVo centerMngVo) {
		return mapper.salesCorpCreate(centerMngVo);
	}

	public int empUserCreate(CenterMngVo centerMngVo) {
		return mapper.empUserCreate(centerMngVo);
	}

	public int empAuthCreate(CenterMngVo centerMngVo) {
		return mapper.empAuthCreate(centerMngVo);
	}

	public int centerInfoUpdate(CenterMngVo centerMngVo) {
		return mapper.centerInfoUpdate(centerMngVo);
	}

	public int acaSalesCorpCreate(CenterMngVo centerMngVo) {
		return mapper.acaSalesCorpCreate(centerMngVo);
	}

	public int salesCorpUpdate(CenterMngVo centerMngVo) {
		return mapper.salesCorpUpdate(centerMngVo);
	}

	public int bossUserUpdate(CenterMngVo centerMngVo) {
		return mapper.bossUserUpdate(centerMngVo);
	}

	public int ledgerInfoCreate(CenterMngVo centerMngVo) {
		return mapper.ledgerInfoCreate(centerMngVo);
	}

	public int ledgerInfoUpdate(CenterMngVo centerMngVo) {
		return mapper.ledgerInfoUpdate(centerMngVo);
	}

	public int contractInfoCreate(CenterMngVo centerMngVo) {
		return mapper.contractInfoCreate(centerMngVo);
	}

	public int contractInfoUpdate(CenterMngVo centerMngVo) {
		return mapper.contractInfoUpdate(centerMngVo);
	}

	public int counselInfoCreate(CenterMngVo centerMngVo) {
		return mapper.counselInfoCreate(centerMngVo);
	}

	public int counselInfoUpdate(CenterMngVo centerMngVo) {
		return mapper.counselInfoUpdate(centerMngVo);
	}

	public int prizeInfoCreate(CenterMngVo centerMngVo) {
		return mapper.prizeInfoCreate(centerMngVo);
	}

	public int prizeInfoUpdate(CenterMngVo centerMngVo) {
		return mapper.prizeInfoUpdate(centerMngVo);
	}

	public int posCreate(@Valid CenterMngVo centerMngVo) {
		return mapper.posCreate(centerMngVo);
	}

	public int posUpdate(@Valid CenterMngVo centerMngVo) {
		return mapper.posUpdate(centerMngVo);
	}

	public int pgInfoCreate(@Valid CenterMngVo centerMngVo) {
		return mapper.pgInfoCreate(centerMngVo);
	}

	public int smsAuthInfoUpdate(@Valid CenterMngVo centerMngVo) {
		return mapper.smsAuthInfoUpdate(centerMngVo);
	}

	public int corpGoodsCreate(@Valid CenterMngVo centerMngVo) {
		return mapper.corpGoodsCreate(centerMngVo);
	}

	public int centerLogCreate(@Valid CenterMngVo centerMngVo) {
		return mapper.centerLogCreate(centerMngVo);
	}

	public int ledgerDataFileCreate(FileVo fileVo) {
		return mapper.ledgerDataFileCreate(fileVo);
	}

	public List<HashMap<String, Object>> getLedgerMultiFileList(HashMap<String, Object> hashmapParam) {
		return mapper.getLedgerMultiFileList(hashmapParam);
	}

	public int ledgerDataFileDelete(HashMap<String, Object> hashmapParam) {
		return mapper.ledgerDataFileDelete(hashmapParam);
	}

	public List<HashMap<String, Object>> getLedgerDataFileList(HashMap<String, Object> hashmapParam) {
		return mapper.getLedgerDataFileList(hashmapParam);
	}
	
	public int optionInfoUpdate(@Valid CenterMngVo centerMngVo) {
		return mapper.optionInfoUpdate(centerMngVo);
	}

	public int licInfoUpdate(@Valid CenterMngVo centerMngVo) {
		return mapper.licInfoUpdate(centerMngVo);
	}

	public void callPrcSyncAcaInfo(HashMap<String, Object> callPrcSyncAcaInfoParam) {
		mapper.callPrcSyncAcaInfo(callPrcSyncAcaInfoParam);
	}

	public int acaSmsUidInfoUpdate(HashMap<String, String> hashmapParam) {
		return mapper.acaSmsUidInfoUpdate(hashmapParam);
	}

	public SessionVO getUserRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getUserRetrieve(hashmapParam);
	}

	public List<String> getUserGrpRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getUserGrpRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getMenuRetrieve(SessionVO loginUserVo) {
		return mapper.getMenuRetrieve(loginUserVo);
	}
	
	public int centerCodeCopy(HashMap<String, Object> hashmapParam) {
		return mapper.centerCodeCopy(hashmapParam);
	}

	public int centerLvlCopy(HashMap<String, Object> hashmapParam) {
		return mapper.centerLvlCopy(hashmapParam);
	}
	
	public int centerCourseCopy(HashMap<String, Object> hashmapParam) {
		return mapper.centerCourseCopy(hashmapParam);
	}

	public int getCenterCourseCnt(HashMap<String, Object> hashmapParam) {
		return mapper.getCenterCourseCnt(hashmapParam);
	}
	
}
