package com.web.kdebate.student.students.domain;

import lombok.Data;

@Data
public class CreateStudentInfoVO{

	private String aca_id = null;
	private String ent_dttm = null;
	private String ent_user_id = null;
	private String upt_dttm = null;
	private String upt_user_id = null;
	
	/* 원생 정보 */
	private String info_std_id = null;
	private String info_std_pwd = null;
	private String info_std_gender = null;
	private String info_std_memo = null;

	private String std_url = null;
	private String info_nick_nm = null;
	private String std_nm = null;
	private String email = null;
	private String birth = null;
	private String hp_no = null;
	private String zip_code = null;
	private String addr = null;
	private String addr_dtl = null;
	
	private String info_std_status = null;
	private String std_gb_txt = null;
	private String std_sch_cls = null;
	private String sch_grade = null;
	private String sch_nm = null;
	private String sch_lvl = null;
	private String std_lvl_cd = null;

	private String mng_class_cd = null;
	private String std_mgt_cls = null;
	private String std_mgt_num = null;
	private String atd_chk_cd = null;
	
	private String cash_reg_no = null;
	
	/* 지정석 정보 */
	private String class_seat_yn = null;
	private String class_room = null;
	private String room_seat_no = null;
	
	/* 학부모 정보 */
	private String info_rep_parent_gb = null;
	private String rep_parent_nm = null;
	private String parent_hp_no = null;
	private String parent_hp_no_cnfm = null;
	private String c_parent_id = null;
	private String c_parent_hp_no = null;
	
	private String parent_id = null;
	private String std_bro_id = null;
	private String std_use_yn = null;
	
	/* 알림 정보 */
	private String atd_sms_yn = null;
	private String lev_sms_yn = null;
	private String exam_sms_yn = null;
	private String std_auto_pay = null;
	private String study_live_chk = null;
	private String study_care_chk = null;

	private String dc_yn = null;
	private String dc_cd = null;
	private String dc_tp = null;
	private String dc_amt = null;
	
}