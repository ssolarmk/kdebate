package com.web.kdebate.center.user.domain;

import lombok.Data;

@Data
public class EmpMngVO {

	private String aca_id = null;
	private String user_id = null;
	private String emp_aca_id = null;
	
	// 직원
	private String emp_user_id = null;
	private String emp_user_nm = null;
	private String emp_user_pwd = null;
	private String emp_auth_grp_cd = null;
	private String emp_user_gb_cd = null;
	private String emp_tel_no = null;
	private String emp_hp_no = null;
	private String emp_email = null;
	private String emp_zip_code = null;
	private String emp_addr = null;
	private String emp_addr_dtl = null;
	private String emp_use_yn = null;

	private String emp_tp = null;
	private String emp_jisa_cd = null;
	private String emp_dept = null;
	private String emp_work_cd = null;
	private String emp_work_lvl = null;
	private String emp_join_dt = null;
	private String emp_leav_dt = null;
	private String emp_memo = null;
	private String emp_did_url = null;
	private String emp_did_text = null;
	private String live_teach_yn = null;
	private String atd_chk_code = null;
	
	// 조교
	private String emp_sub_user_id = null;
	private String emp_sub_user_nm = null;
	private String emp_sub_tp = null;
	private String emp_sub_user_gb_cd = null;
	private String emp_sub_auth_grp_cd = null;
	private String emp_sub_work_cd = null;
	private String emp_sub_memo = null;
	private String emp_sub_use_yn = null;

	private String sal_base_amt = null;
	private String sal_spc_amt = null;
	private String sal_insen_base_cnt = null;
	private String sal_insen_per_amt = null;
	private String img_url = null;
	
	
	private String kiosk_atd_chk_tp;
	private String kiosk_atd_chk_tp1 = "N";
	private String kiosk_atd_chk_tp2 = "N";
	private String kiosk_atd_room_no;
	private String kiosk_atd_gb = null;

}
