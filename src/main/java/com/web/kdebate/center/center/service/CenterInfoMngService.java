package com.web.kdebate.center.center.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.center.center.domain.CenterInfoMngVO;
import com.web.kdebate.center.center.mapper.CenterInfoMngMapper;


@Service
public class CenterInfoMngService {

	@Autowired
	private CenterInfoMngMapper mapper;

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}

	public HashMap<String, Object> getCenterInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getCenterInfoRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClassListRetrieve(hashmapParam);
	}

	// 독서실 Room 정보
	public List<HashMap<String, Object>> getClsRoomListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClsRoomListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getContListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getContListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getGoodsListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getGoodsListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getPosListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getPosListRetrieve(hashmapParam);
	}

	public int centerBossMobileUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.centerBossMobileUpdate(centerInfoMngVO);
	}

	public int centerUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.centerUpdate(centerInfoMngVO);
	}

	public int centerLogCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.centerLogCreate(centerInfoMngVO);
	}

	public int classCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.classCreate(centerInfoMngVO);
	}

	public int classUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.classUpdate(centerInfoMngVO);
	}

	public int clsRoomCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.clsRoomCreate(centerInfoMngVO);
	}

	public int clsRoomUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.clsRoomUpdate(centerInfoMngVO);
	}

	public List<HashMap<String, Object>> getCorpCdList(HashMap<String, Object> hashmapParam) {
		return mapper.getCorpCdList(hashmapParam);
	}

	public int goodsCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.goodsCreate(centerInfoMngVO);
	}

	public int centerGoodsCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.centerGoodsCreate(centerInfoMngVO);
	}
	
	public HashMap<String, Object> getSelectCorpInfo(String gds_corp_cd) {
		return mapper.getSelectCorpInfo(gds_corp_cd);
	}
	
	public void bookInfoCreate(CenterInfoMngVO centerInfoMngVO) {
		mapper.bookInfoCreate(centerInfoMngVO);
	}
	
	public void bookInfoUpdate(CenterInfoMngVO centerInfoMngVO) {
		mapper.bookInfoUpdate(centerInfoMngVO);
	}
	
	public void bookStockInfoCreate(CenterInfoMngVO centerInfoMngVO) {
		mapper.bookStockInfoCreate(centerInfoMngVO);
	}

	public int goodsUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.goodsUpdate(centerInfoMngVO);
	}

	public int centerGoodsUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.centerGoodsUpdate(centerInfoMngVO);
	}

	public int centerGoodsInfoUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.centerGoodsInfoUpdate(centerInfoMngVO);
	}

	public List<HashMap<String, Object>> getSalesCompListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getSalesCompListRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getSalesCompInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getSalesCompInfoRetrieve(hashmapParam);
	}

	public String getSalesCorpCd() {
		return mapper.getSalesCorpCd();
	}
	
	public int getCorpBizNoDuplChk(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.getCorpBizNoDuplChk(centerInfoMngVO);
	}

	public int salesCorpCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.salesCorpCreate(centerInfoMngVO);
	}

	public int acaSalesCorpCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.acaSalesCorpCreate(centerInfoMngVO);
	}

	public int userInfoCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.userInfoCreate(centerInfoMngVO);
	}

	public int userAuthCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.userAuthCreate(centerInfoMngVO);
	}

	public int salesCorpUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.salesCorpUpdate(centerInfoMngVO);
	}

	public int acaSalesCorpUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.acaSalesCorpUpdate(centerInfoMngVO);
	}

	public int clsSeatPoUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.clsSeatPoUpdate(hashmapParam);
	}

	public List<HashMap<String, Object>> getMngClassTeacherList(HashMap<String, Object> hashmapParam) {
		return mapper.getMngClassTeacherList(hashmapParam);
	}

	public List<HashMap<String, Object>> getMngClassListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getMngClassListRetrieve(hashmapParam);
	}

	public int mngClassCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.mngClassCreate(centerInfoMngVO);
	}

	public int mngClassUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.mngClassUpdate(centerInfoMngVO);
	}

	public String getMaxClsTtime(HashMap<String, Object> hashmapParam) {
		return mapper.getMaxClsTtime(hashmapParam);
	}

	public String getMaxClsWdayTtime(HashMap<String, Object> hashmapParam) {
		return mapper.getMaxClsWdayTtime(hashmapParam);
	}

	public List<HashMap<String, Object>> getClassTimeListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClassTimeListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getClassTeacherInfoRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClassTeacherInfoRetrieve(hashmapParam);
	}

	public int classTimeCreate(HashMap<String, Object> map) {
		return mapper.classTimeCreate(map);
	}

	public int classTimeDelete(HashMap<String, Object> hashMap) {
		return mapper.classTimeDelete(hashMap);
	}

	public void callPrcSyncAcaInfo(HashMap<String, Object> callPrcSyncAcaInfoParam) {
		mapper.callPrcSyncAcaInfo(callPrcSyncAcaInfoParam);
	}

	public void callPrcSyncRoomTimeTable(HashMap<String, Object> callPrcSyncRoomTimeTableParam) {
		mapper.callPrcSyncRoomTimeTable(callPrcSyncRoomTimeTableParam);
	}

	public String getCorpCd(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.getCorpCd(centerInfoMngVO);
	}

	public void deleteStdClsTimeTable(HashMap<String, Object> map) {
		mapper.deleteStdClsTimeTable(map);
	}

	public void deleteStdAtdTimeTable(HashMap<String, Object> map) {
		mapper.deleteStdAtdTimeTable(map);
	}

	public void updateStdAtdTimeTable(HashMap<String, Object> map) {
		mapper.updateStdAtdTimeTable(map);
	}

	public List<HashMap<String, Object>> getGroupCodeRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getGroupCodeRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getLowerCodeRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getLowerCodeRetrieve(hashmapParam);
	}

	public int codeAcaCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.codeAcaCreate(centerInfoMngVO);
	}
	public int codeAcaUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.codeAcaUpdate(centerInfoMngVO);
	}

	public int optionInfoUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.optionInfoUpdate(centerInfoMngVO);
	}

	// SMS 잔여포인트 조회
	public HashMap<String,Object> getCheckUserPoint(HashMap<String, Object> hashmapParam) {
		return mapper.getCheckUserPoint(hashmapParam);
	}

	// 일자별 SMS 내역
	public List<HashMap<String, Object>> getSelectSmsHistoryList(HashMap<String, Object> hashmapParam) {
		return mapper.getSelectSmsHistoryList(hashmapParam);
	}

	public List<HashMap<String, Object>> getCenterOptionRetrieve(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.getCenterOptionRetrieve(centerInfoMngVO);
	}
	//기존항목 호출
	public void deleteTbAcaSchMgt(@Valid CenterInfoMngVO centerInfoMngVO) {
		 mapper.deleteTbAcaSchMgt(centerInfoMngVO);

	}

	public void insertTbAcaSchMgt(@Valid CenterInfoMngVO centerInfoMngVO) {
		mapper.insertTbAcaSchMgt(centerInfoMngVO);

	}

	public int getOptionInfoCnt(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.getOptionInfoCnt(centerInfoMngVO);
	}

	public int optionInfoInsert(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.optionInfoInsert(centerInfoMngVO);
	}

	public List<HashMap<String, Object>> getLowerCodeRetrieve2(HashMap<String, Object> hashmapParam) {
		return mapper.getLowerCodeRetrieve2(hashmapParam);
	}

	public int codeUpdate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.codeUpdate(centerInfoMngVO);
	}

	public int codeCreate(@Valid CenterInfoMngVO centerInfoMngVO) {
		return mapper.codeCreate(centerInfoMngVO);
	}
}
