package com.web.kdebate.common.common.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.common.common.mapper.MainMapper;

@Service
public class MainService {

	@Autowired
	private MainMapper mapper;

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}

	public List<HashMap<String, Object>> getCenterStatusCntList(HashMap<String, Object> hashmapParam) {
		return mapper.getCenterStatusCntList(hashmapParam);
	}

	public List<HashMap<String, Object>> getBossPtListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getBossPtListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getParentPtListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getParentPtListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getAnnualSalesTrendRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAnnualSalesTrendRetrieve(hashmapParam);
	}

	public HashMap<String, Object> getUserStatusCntList(HashMap<String, Object> hashmapParam) {
		return mapper.getUserStatusCntList(hashmapParam);
	}

	public List<HashMap<String, Object>> getClassListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClassListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getClassStdListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getClassStdListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getNoticeListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getNoticeListRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> getBoardDataFileListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getBoardDataFileListRetrieve(hashmapParam);
	}

	public int boardDataCntUpdate(HashMap<String, Object> hashmapParam) {
		return mapper.boardDataCntUpdate(hashmapParam);
	}

	public List<HashMap<String, Object>> getAcaUseStatusRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAcaUseStatusRetrieve(hashmapParam);
	}

	public List<HashMap<String, Object>> teacherList(HashMap<String, Object> hashmapParam) {
		return mapper.teacherList(hashmapParam);
	}

	public HashMap<String, Object> smsPayInvCheck(HashMap<String, Object> hashmapParam) {
		return mapper.smsPayInvCheck(hashmapParam);
	}

	public void smsPayReadCreate(HashMap<String, Object> hashmapParam) {
		mapper.smsPayReadCreate(hashmapParam);
	}

	public int smsPayReadCheck(HashMap<String, Object> hashmapParam) {
		return mapper.smsPayReadCheck(hashmapParam);
	}
}
