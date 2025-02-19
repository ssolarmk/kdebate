package com.web.kdebate.student.students.domain;

import java.util.List;

import lombok.Data;

@Data
public class AcaStdResponseVo {
    private String cmd;
    private String StatusCode;
    private String ErrorMessage;   
    private List<AcaStdDataVo> stdInfoList;
}
