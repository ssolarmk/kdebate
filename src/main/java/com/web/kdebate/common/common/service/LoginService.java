package com.web.kdebate.common.common.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.common.common.domain.SessionVO;
import com.web.kdebate.common.common.mapper.LoginMapper;

@Service
public class LoginService {

	@Autowired
	private LoginMapper mapper;

	public SessionVO getUserRetrieve(HashMap<String, String> param) {

		return mapper.getUserRetrieve(param);
	}

	public List<String> getUserGrpRetrieve(HashMap<String, String> param) {
		// TODO Auto-generated method stub
		return mapper.getUserGrpRetrieve(param);
	}

	public void createloginHist(HashMap<String, Object> loginHist) {
		mapper.createloginHist(loginHist);
	}

	public List<HashMap<String, Object>> getMenuRetrieve(SessionVO loginUserVo) {
		// TODO Auto-generated method stub
		return mapper.getMenuRetrieve(loginUserVo);
	}

	public List<HashMap<String, Object>> getRoomRetrieve(SessionVO loginUserVo) {
		// TODO Auto-generated method stub
		return mapper.getRoomRetrieve(loginUserVo);
	}

	public int getAcaAllianceCnt(SessionVO loginUserVo) {
		return mapper.getAcaAllianceCnt(loginUserVo);
	}

	public List<HashMap<String, Object>> getAcaAllianceList(SessionVO loginUserVo) {
		return mapper.getAcaAllianceList(loginUserVo);
	}

	public void lastLoginUpdate(SessionVO loginUserVo) {
		// TODO Auto-generated method stub
		mapper.lastLoginUpdate(loginUserVo);
	}

	public List<HashMap<String, Object>> getLinkList() {
		// TODO Auto-generated method stub
		return mapper.getLinkList();
	}

}
