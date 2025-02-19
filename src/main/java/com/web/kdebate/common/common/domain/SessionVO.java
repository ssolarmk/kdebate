package com.web.kdebate.common.common.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("sessionVo")
@Data
public class SessionVO implements Serializable {

	private static final long serialVersionUID = 6301450078844122257L;
	private String user_id;
	private String user_nm;
	private String mbr_no;
	private String group_code;
	private List<String> user_group;
	private String user_grp_cd;
	private String site_gb_cd;
	private String aca_id;
	private String aca_nm;
	private String p_aca_id;
	private String mig_a_code;
	private String str_dt;
	private String end_dt;
	private String hompage;
	private String corp_cd;
	private String home_aes_key;
	private String homepage;
	private String homepage_url;
	private String home_url;
	private String lab_url;
	private String lab_aes_key;
	private String user_pwd;
	private String emp_tp;
	private String pcode_use_yn;
	private String logo_use_yn;
	private String logo_img_path;
	private String level_use_yn;
	private String curri_use_yn;
	private String sms_uid;
	private String profiles;
	private String receipt_print_text;

	private List<HashMap<String, Object>> menu;
	private List<HashMap<String, Object>> topMenu;
	private List<HashMap<String, Object>> room;

}
