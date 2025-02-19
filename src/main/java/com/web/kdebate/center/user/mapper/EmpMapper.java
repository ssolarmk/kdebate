package com.web.kdebate.center.user.mapper;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.center.user.domain.EmpMngVO;


@Mapper
public interface EmpMapper {

	int getQueryTotalCnt();

	HashMap<String, Object> acaIdChk(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getEmpListRetrieve(HashMap<String, Object> hashmapParam);

	int userCreate(@Valid EmpMngVO empMngVO);

	int userAuthGrpCreate(@Valid EmpMngVO empMngVO);

	int empCreate(@Valid EmpMngVO empMngVO);
	
	void subUserCreate(@Valid EmpMngVO empMngVO);
	
	void subEmpCreate(@Valid EmpMngVO empMngVO);
	
	void subAuthGrpCreate(@Valid EmpMngVO empMngVO);
	
	void subEmpWorkCdCreate(@Valid EmpMngVO empMngVO);

	int empJisaGrpCreate(@Valid EmpMngVO empMngVO);

	int userUpdate(@Valid EmpMngVO empMngVO);

	int userAuthGrpDelete(@Valid EmpMngVO empMngVO);

	int empUpdate(@Valid EmpMngVO empMngVO);

	int empJisaGrpDelete(@Valid EmpMngVO empMngVO);

	int empWorkCdCreate(@Valid EmpMngVO empMngVO);

	int empWorkCdDelete(@Valid EmpMngVO empMngVO);

	int cnctInfoCreate(HashMap<String, Object> hashmapParam);

	int loginChkInfoCreate(HashMap<String, Object> hashmapParam);

	List<HashMap<String, Object>> getEmpCnctLogRetrieve(HashMap<String, Object> hashmapParam);

	int getAtdChkCodeRetrieve(HashMap<String, Object> paramMap);


}
