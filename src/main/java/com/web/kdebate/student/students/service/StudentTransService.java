package com.web.kdebate.student.students.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.kdebate.student.students.domain.AcaStdDataVo;
import com.web.kdebate.student.students.domain.AcaStdRequestVo;
import com.web.kdebate.student.students.mapper.StudentTransMapper;

@Service
public class StudentTransService {

    @Autowired
    private StudentTransMapper studentTransMapper;

    public List<AcaStdRequestVo> getAcaRequestInfo() {
        return studentTransMapper.getAcaRequestInfo();
    }

    public void UpdateAcaStdData(HashMap<String, Object> map) {
        studentTransMapper.UpdateAcaStdData(map);
    }

    public List<AcaStdDataVo> getAcaStdData() {
        return studentTransMapper.getAcaStdData();
    }
}
