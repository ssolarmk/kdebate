package com.web.kdebate.center.user.domain;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserMngVO implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 6539632825634545282L;

	@NotEmpty(message="아이디")
	private String user_id = null;
	private String user_pswd_new = null;
	private String user_pswd_new_cnfm = null;
	private String user_ch_pswd_new = null;
	private String user_ch_pswd_new_cnfm = null;
	private String user_gb_cd = null;
	private String center_id = null;
	private String ent_dttm = null;
	private String upt_dttm = null;
	private String lst_cnct_dttm = null;
	private String lst_cnct_ip = null;
	private String auth_grp_cd = null;
	private String use_yn = null;
	private String dept_cd = null;
	private String aca_id = null;
	@NotEmpty(message="사용자명")
	private String user_nm = null;
	private String tel_no = null;
	private String ent_user_id = null;
	private String upt_user_id = null;
	private String email = null;
	private String ui_theme = null;
	private String pos = null;
	private String site_gb_cd = null;
}
