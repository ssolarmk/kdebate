package com.web.kdebate.center.user.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.center.user.domain.UserMngVO;
import com.web.kdebate.center.user.mapper.UserMapper;


@Service
public class UserService {

	@Autowired
	private UserMapper mapper;

	public List<HashMap<String, Object>> getUserListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getUserListRetrieve(hashmapParam);
	}

	public int getUserListRetrieveCnt(HashMap<String, Object> hashmapParam) {
		return mapper.getUserListRetrieveCnt(hashmapParam);
	}

	public Object getAuthGrpCdRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getAuthGrpCdRetrieve(hashmapParam);
	}

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}

	public List<HashMap<String, Object>> getUserCnctLogRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getUserCnctLogRetrieve(hashmapParam);
	}

	public int chkUserId(HashMap<String, Object> hashmapParam) {
		return mapper.chkUserId(hashmapParam);
	}

	public int userAuthGrpCreate(UserMngVO userMngVO) {
		return mapper.userAuthGrpCreate(userMngVO);
	}

	public int userCreate(UserMngVO userMngVO) {
		return mapper.userCreate(userMngVO);
	}

	public int userAuthGrpDelete(UserMngVO userMngVO) {
		return mapper.userAuthGrpDelete(userMngVO);
	}

	public int userUpdate(UserMngVO userMngVO) {
		return mapper.userUpdate(userMngVO);
	}

	public List<HashMap<String, Object>> getUserAllListRetrieve() {
		return mapper.getUserAllListRetrieve();
	}

	public void userpwdUpdate(HashMap<String, Object> temp) {
		mapper.userpwdUpdate(temp);
	}

}
