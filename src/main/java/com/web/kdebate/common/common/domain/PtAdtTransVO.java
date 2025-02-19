package com.web.kdebate.common.common.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PtAdtTransVO {

	@NotEmpty(message = "설명회코드")
	@NotNull(message = "설명회코드")
	private String pt_seq;
	@NotEmpty(message = "학원코드")
	@NotNull(message = "학원코드")
	private String aca_id;
	@NotEmpty(message = "연락처")
	@NotNull(message = "연락처")
	private String resv_tel;
	@NotEmpty(message = "학생명")
	@NotNull(message = "학생명")
	private String std_nm;
	@NotEmpty(message = "학교구분")
	@NotNull(message = "학교구분")
	private String std_sch_lvl;
	@NotEmpty(message = "학교명")
	@NotNull(message = "학교명")
	private String std_sch_nm;
	@NotEmpty(message = "학년")
	@NotNull(message = "학년")
	private String std_sch_year;
	private String ent_dttm;
}
