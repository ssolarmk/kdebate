package com.web.kdebate.common.common.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.common.common.domain.SessionVO;

@Mapper
public interface LoginMapper {

	SessionVO getUserRetrieve(HashMap<String, String> param);

	List<String> getUserGrpRetrieve(HashMap<String, String> param);

	void createloginHist(HashMap<String, Object> loginHist);

	List<HashMap<String, Object>> getMenuRetrieve(SessionVO loginUserVo);

	List<HashMap<String, Object>> getRoomRetrieve(SessionVO loginUserVo);

	int getAcaAllianceCnt(SessionVO loginUserVo);

	List<HashMap<String, Object>> getAcaAllianceList(SessionVO loginUserVo);

	void lastLoginUpdate(SessionVO loginUserVo);

	List<HashMap<String, Object>> getLinkList();

}
