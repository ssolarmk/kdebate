package com.web.kdebate.student.students.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.kdebate.student.students.domain.AcaStdDataVo;
import com.web.kdebate.student.students.domain.AcaStdRequestVo;

@Mapper
public interface StudentTransMapper {

    List<AcaStdRequestVo> getAcaRequestInfo();

    void UpdateAcaStdData(HashMap<String, Object> map);

    List<AcaStdDataVo> getAcaStdData();

}
