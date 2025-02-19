package com.web.kdebate.student.students.domain;

import lombok.Data;

@Data
public class StudentInfoMngVO{

	private String aca_id = null;
	private String ent_dttm = null;
	private String ent_user_id = null;
	private String upt_dttm = null;
	private String upt_user_id = null;

	private String std_url = null;

	/* 원생 정보 */
	private String std_id = null;
	private String std_nm = null;
	private String std_pwd = null;
	private String std_tel_no = null;
	private String std_hp_no = null;
	private String std_email = null;
	private String std_birth = null;
	private String std_status = null;
	private String std_lvl_3 = null;
	private String std_zip_code = null;
	private String std_addr = null;
	private String std_addr_dtl = null;
	private String std_sch_nm = null;
	private String std_sch_lvl = null;
	private String std_sch_cls = null;
	private String std_nick_nm = null;
	private String std_use_yn = null;
	private String std_lvl_cd = null;

	/* 상담 정보 */
	private String cnsl_dt = null;
	private String cnsl_user_id = null;
	private String cnsl_gb_cd = null;
	private String cnsl_target_cd = null;
	private String cnsl_cnts = null;
	private String cnsl_std_id = null;
	private String cnsl_seq = null;
	private String cnsl_sms_yn = null;
	private String cnsl_std_nm = null;

	/* 학부모 찾기 연결 정보 */
	private String parents_find_user_id = null;
	private String parents_find_info_std_id = null;
	private String parents_find_gb_cd = null;

	/* 학부모 신규 등록 */
	private String parent_id = null;
	private String parent_reg_std_id = null;
	private String parent_reg_gb_cd = null;
	private String parent_reg_user_nm = null;
	private String parent_reg_user_id = null;
	private String parent_prev_user_id = null;
	private String parent_reg_user_pswd = null;
	private String parent_reg_tel_no = null;
	private String parent_reg_hp_no = null;
	private String parent_reg_email = null;
	private String parent_reg_zip_code = null;
	private String parent_reg_addr = null;
	private String parent_reg_addr_dtl = null;
	private String parent_yn = null;
	private String parent_reg_hp_no_cnfm = null;
	private String parent_sms_cont = null;

	/* 형제 찾기 연결 정보 */
	private String bro_find_user_id = null;
	private String bro_find_info_std_id = null;
	private String bro_find_resp_type_cd = null;
	
	private String bus_schd_no = null;
	private String bus_line_no = null;
	private String bus_sta_id = null;
	

}
