package com.web.kdebate.student.students.mapper;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.student.students.domain.StudentRegistVO;

@Mapper
public interface StudentRegistMapper {
	
	String getUserId(@Valid StudentRegistVO studentVO);
	
	int userInfoCreate(@Valid StudentRegistVO studentVO);
	
	int studentInfoCreate(@Valid StudentRegistVO studentVO);
	
	int stdClassCreate(@Valid StudentRegistVO studentVO);
	
	int stdInfoChgLogCreate(StudentRegistVO studentVO);
	
	List<HashMap<String, Object>> stdBroList(StudentRegistVO studentVO);
	
	int stdBroCreate(StudentRegistVO studentVO);
	
	int broStdCreate(StudentRegistVO studentVO);
	
	String getStdParentId();
	
	int parentInfoCreate(StudentRegistVO studentVO);

	int parentCreate(StudentRegistVO studentVO);
	
	int getStudentIdConfirm(HashMap<String, Object> hashmapParam);
	
	int getParentHpNoConfirm(HashMap<String, Object> hashmapParam);

	HashMap<String, Object> getParentInfo(HashMap<String, Object> hashmapParam);
	
	int getStdAtdCdConfirm(HashMap<String, Object> hashmapParam);
}
