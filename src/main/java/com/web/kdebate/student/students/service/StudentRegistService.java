package com.web.kdebate.student.students.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.student.students.domain.StudentRegistVO;
import com.web.kdebate.student.students.mapper.StudentRegistMapper;


@Service
public class StudentRegistService {
	
	@Autowired
	private StudentRegistMapper mapper;
	
	public String getUserId(@Valid StudentRegistVO studentVO) {
		return mapper.getUserId(studentVO);
	}
	
	public int userInfoCreate(@Valid StudentRegistVO studentVO) {
		return mapper.userInfoCreate(studentVO);
	}
	
	public int studentInfoCreate(@Valid StudentRegistVO studentVO) {
		return mapper.studentInfoCreate(studentVO);
	}
	
	public int stdClassCreate(@Valid StudentRegistVO studentVO) {
		return mapper.stdClassCreate(studentVO);
	}
	
	public int stdInfoChgLogCreate(StudentRegistVO studentVO) {
		return mapper.stdInfoChgLogCreate(studentVO);
	}
	
	public List<HashMap<String, Object>> stdBroList(StudentRegistVO studentVO) {
		return mapper.stdBroList(studentVO);
	}
	
	public int stdBroCreate(StudentRegistVO studentVO) {
		return mapper.stdBroCreate(studentVO);
	}

	public int broStdCreate(StudentRegistVO studentVO) {
		return mapper.broStdCreate(studentVO);
	}
	
	public String getStdParentId() {
		return mapper.getStdParentId();
	}
	
	public int parentInfoCreate(StudentRegistVO studentVO) {
		return mapper.parentInfoCreate(studentVO);
	}

	public int parentCreate(StudentRegistVO studentVO) {
		return mapper.parentCreate(studentVO);
	}
	
	public int getStudentIdConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getStudentIdConfirm(hashmapParam);
	}
	
	public int getParentHpNoConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getParentHpNoConfirm(hashmapParam);
	}

	public HashMap<String, Object> getParentInfo(HashMap<String, Object> hashmapParam) {
		return mapper.getParentInfo(hashmapParam);
	}
	
	public int getStdAtdCdConfirm(HashMap<String, Object> hashmapParam) {
		return mapper.getStdAtdCdConfirm(hashmapParam);
	}

}
