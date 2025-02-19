package com.web.kdebate.center.user.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.center.user.domain.UserMngVO;


@Mapper
public interface UserMapper {

	List<HashMap<String, Object>> getUserListRetrieve(HashMap<String, Object> hashmapParam);

	int getUserListRetrieveCnt(HashMap<String, Object> hashmapParam);

	Object getAuthGrpCdRetrieve(HashMap<String, Object> hashmapParam);

	int getQueryTotalCnt();

	List<HashMap<String, Object>> getUserCnctLogRetrieve(HashMap<String, Object> hashmapParam);

	int chkUserId(HashMap<String, Object> hashmapParam);

	int userAuthGrpCreate(UserMngVO userMngVO);

	int userCreate(UserMngVO userMngVO);

	int userAuthGrpDelete(UserMngVO userMngVO);

	int userUpdate(UserMngVO userMngVO);

	List<HashMap<String, Object>> getUserAllListRetrieve();

	void userpwdUpdate(HashMap<String, Object> temp);
	

}
