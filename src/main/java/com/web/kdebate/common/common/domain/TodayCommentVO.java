package com.web.kdebate.common.common.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TodayCommentVO {
	private String seq;
	@NotEmpty(message = "학생아이디")
	@NotNull(message = "학생아이디")
	private String std_id;
	@NotEmpty(message = "학생명")
	@NotNull(message = "학생명")
	private String std_nm;
	private String aca_id;
	private String aca_nm;
	@NotEmpty(message = "코멘트")
	@NotNull(message = "코멘트")
	private String comment;
	private String view_yn;
	private String use_yn;
	private String ent_dttm;
	private String ent_user;
	private String upt_user;
	private String upt_dttm;
}
