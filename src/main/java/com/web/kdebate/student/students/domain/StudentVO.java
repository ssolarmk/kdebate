package com.web.kdebate.student.students.domain;

import lombok.Data;

@Data
public class StudentVO {

	private String aca_id = null;
	private String aca_nm = null;
	private String ent_dttm = null;
	private String ent_user_id = null;
	private String upt_dttm = null;
	private String upt_user_id = null;

	private String std_url = null;

	private String students_no = null;

	/* 원생 정보 */
	private String std_id = null;
	private String std_nm = null;
	private String std_mbr_tp = null;
	private String std_sch_major = null;
	private String std_lvl_cd = null;
	private String std_pwd = null;
	private String std_tel_no = null;
	private String std_hp_no = null;
	private String std_email = null;
	private String std_birth = null;
	private String std_status = null;
	private String std_zip_code = null;
	private String std_addr = null;
	private String std_addr_dtl = null;
	private String std_sch_nm = null;
	private String std_sch_lvl = null;
	private String std_sch_cls = null;
	private String std_nick_nm = null;
	private String std_gender= null;
	private String std_parent_nm = null;
	private String std_parent_hp_no = null;
	private String std_parent_gb_cd = null;
	private String std_parent_id = null;
	private String std_use_yn = null;
	private String std_valid = null;
	private String atd_chk_cd = null;
	private String atd_sms_yn = null;
	private String lev_sms_yn = null;
	private String exam_sms_yn = null;
	private String cash_reg_no = null;
	private String mng_class_cd = null;
	private String parent_gb_cd = null;
	private String parent_nm = null;
	private String parent_hp_no = null;
	private String std_aca_nm = null;
	private String parent_hp_no_cnfm = null;
	private String std_bro_id = null;
	private String pcode_use_yn = null;
	private String std_sch_grade = null;
	private String sch_grade_inp = null;
	private String sch_lvl_inp = null;
	private String sch_grade = null;
	private String sch_lvl = null;
	private String sch_nm = null;
	private String study_live_chk = null;
	private String study_care_chk = null;
	private String std_memo = null;
	private String aca_sch_mgt_cd = null;
	private String create_tp = null;
	private String std_mgt_cls = null;
	private String std_mgt_num = null;


	/* 청구서 정보 */
	private String inv_no = null;
	private String inv_enc = null;
	private String inv_nm = null;
	private String inv_tp = null;
	private String inv_learn_fdt = null;
	private String inv_learn_tdt = null;
	private String inv_issue_dt = null;
	private String inv_recv_limit_dt = null;
	private String inv_amt = null;
	private String inv_tot_amt = null;
	private String inv_memo_aca = null;
	private String inv_memo_parent = null;
	private String inv_pay_yn = null;
	private String inv_noti_yn = null;
	private String inv_recv_chk_yn = null;

	private String inv_corp_cd = null;
	private String inv_goods_cd = null;
	private String inv_price = null;
	private String inv_tot_price = null;
	private String inv_cnt = null;
	private String inv_dc_cd = null;
	private String inv_dc_tp = null;
	private String inv_dc_amt = null;

	/* 알람 정보 */
	private String noti_seq = null;
	private String noti_tp = null;
	private String send_tp = null;
	private String title = null;
	private String cnts = null;
	private String status = null;

	private String reserve = null;
	private String reserved_date = null;
	private String mt_type = null;
	private String c_parent_id = null;
	private String parent_id = null;

	private String sms_cont_tp = null;
	private String rep_parent_yn = null;

}
