package com.web.kdebate.center.user.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.center.user.domain.EmpMngVO;
import com.web.kdebate.center.user.mapper.EmpMapper;


@Service
public class EmpService {

	@Autowired
	private EmpMapper mapper;

	public int getQueryTotalCnt() {
		return mapper.getQueryTotalCnt();
	}

	public HashMap<String, Object> acaIdChk(HashMap<String, Object> hashmapParam) {
		return mapper.acaIdChk(hashmapParam);
	}

	public List<HashMap<String, Object>> getEmpListRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getEmpListRetrieve(hashmapParam);
	}

	public int userCreate(@Valid EmpMngVO empMngVO) {
		return mapper.userCreate(empMngVO);
	}

	public int userAuthGrpCreate(@Valid EmpMngVO empMngVO) {
		return mapper.userAuthGrpCreate(empMngVO);
	}

	public int empCreate(@Valid EmpMngVO empMngVO) {
		return mapper.empCreate(empMngVO);
	}
	
	public void subUserCreate(@Valid EmpMngVO empMngVO) {
		mapper.subUserCreate(empMngVO);
	}
	
	public void subEmpCreate(@Valid EmpMngVO empMngVO) {
		mapper.subEmpCreate(empMngVO);
	}
	
	public void subAuthGrpCreate(@Valid EmpMngVO empMngVO) {
		mapper.subAuthGrpCreate(empMngVO);
	}
	
	public void subEmpWorkCdCreate(@Valid EmpMngVO empMngVO) {
		mapper.subEmpWorkCdCreate(empMngVO);
	}

	public int empJisaGrpCreate(@Valid EmpMngVO empMngVO) {
		return mapper.empJisaGrpCreate(empMngVO);
	}

	public int userUpdate(@Valid EmpMngVO empMngVO) {
		return mapper.userUpdate(empMngVO);
	}

	public int userAuthGrpDelete(@Valid EmpMngVO empMngVO) {
		return mapper.userAuthGrpDelete(empMngVO);
	}

	public int empUpdate(@Valid EmpMngVO empMngVO) {
		return mapper.empUpdate(empMngVO);
	}

	public int empJisaGrpDelete(@Valid EmpMngVO empMngVO) {
		return mapper.empJisaGrpDelete(empMngVO);
	}

	public int empWorkCdCreate(@Valid EmpMngVO empMngVO) {
		return mapper.empWorkCdCreate(empMngVO);
	}

	public int empWorkCdDelete(@Valid EmpMngVO empMngVO) {
		return mapper.empWorkCdDelete(empMngVO);
	}

	public int cnctInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.cnctInfoCreate(hashmapParam);
	}

	public int loginChkInfoCreate(HashMap<String, Object> hashmapParam) {
		return mapper.loginChkInfoCreate(hashmapParam);
	}

	public List<HashMap<String, Object>> getEmpCnctLogRetrieve(HashMap<String, Object> hashmapParam) {
		return mapper.getEmpCnctLogRetrieve(hashmapParam);
	}

	public int getAtdChkCodeRetrieve(HashMap<String,Object> paramMap) {
		return mapper.getAtdChkCodeRetrieve(paramMap);
	}

}
