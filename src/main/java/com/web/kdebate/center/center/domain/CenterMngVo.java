package com.web.kdebate.center.center.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("CenterMngVo")
public class CenterMngVo {
	private String aca_id = null;
    private String aca_nm = null;
    private String corp_cd = null;
    private String boss_nm = null;
    private String boss_id = null;
    private String user_pwd = null;
    private String aca_tp = null;
    private String sales_grade = null;
    private String p_aca_id = null;
    private String jisa_cd = null;
    private String biz_no = null;
    private String tel_no = null;
    private String hp_no = null;
    private String fax_no = null;
    private String email = null;
    private String addr = null;
    private String addr_dtl = null;
    private String zip_no = null;
    private String open_tm = null;
    private String close_tm = null;
    private String hompage = null;
    private String cont_stat = null;
    private String use_yn = null;
    private String ent_dttm = null;
    private String ent_user_id = null;
    private String upt_dttm = null;
    private String upt_user_id = null;
    private String biz_nm = null;
    private String mig_a_code = null;
    private String mig_home_code = null;

    /* 계약정보 */
    private String cont_aca_id = null;
    private String cont_seq = null;
    private String cont_gb = null;
    private String cont_usr_nm = null;
    private String cont_tel_no = null;
    private String cont_amt = null;
    private String cont_dt = null;
    private String str_dt = null;
    private String end_dt = null;
    private String pay_way = null;
    private String pay_yn = null;
    private String pay_dt = null;
    private String cont_ent_dttm = null;
    private String cont_ent_user_id = null;

    /* 관리대장정보 */
    private String ledg_seq = null;
    private String ledg_aca_id = null;
    private String ledg_cd = null;
    private String ledg_nm = null;
    private String ledg_conts = null;
    private String writer_nm = null;
    private String write_dt = null;
    private String ledg_use_yn = null;

    /* 옵션관리 정보 */
    private String option_aca_id = null;
    private String logo_use_yn = null;
    private String logo_file_nm = null;
    private String logo_img_path = null;
    private String pcode_use_yn = null;
    private String level_use_yn = null;
    private String curri_use_yn = null;
    private String class_period_type = null;
    private String invoice_issue_auto = null;
    private String invoice_isssue_type = null;
    private String invoice_issue_day = null;
    private Long file_limit = null;
    private String homepage_use_yn = null;
    private String homepage_url = null;
    private String exam_sys_use_yn = null;
    private String rating_sys_use_yn = null;
    private String receipt_print_yn = null;
    private String receipt_print_std_info_yn = null;
    private String office_of_education = null;
    private String aca_oper_tp_camp = null;
    private String aca_oper_tp_lib = null;
    private String live_class_oper_yn = null;




    /* 상담정보 */
    private String cncl_aca_id = null;
    private String cncl_seq = null;
    private String cncl_tp = null;
    private String cncl_dt = null;
    private String cust_nm = null;
    private String cust_tel_no = null;
    private String cust_pos = null;
    private String cncl_conts = null;
    private String cncl_ent_dttm = null;
    private String cncl_ent_user_id = null;

    /* 수수료 정보 */
    private String lic_amt_mon = null;
    private String lic_amt_std = null;
    private String lic_cont_tp = null;
    private String sms_prepay_yn = null;
    private String sms_price = null;
    private String lms_price = null;
    private String mms_price = null;
    private String rcs_price = null;
    private String katalk_price = null;
    
    /* 자매학원정보 */
    private String alliance_aca_id = null;
    private String alliance_aca = null;
    private String alliance_gb = null;
    private String alliance_use_yn = null;
    
    /* 우수센터정보 */
    private String prize_aca_id = null;
    private String prize_gb = null;
    private String yyyymm = null;
    private String prize_yyyy = null;
    private String prize_mm = null;
    private String week = null;
    private String prize_reason = null;
    private String prize_benefit = null;
    private String prize_use_yn = null;

    /* 단말기정보 */
 	private String pos_corp_cd = null;
 	private String pos_seq = null;
 	private String pos_tp = null;
 	private String pos_nm = null;
 	private String pos_serial_no = null;
 	private String pos_van_cd = null;
 	private String pos_cat_id = null;
 	private String pos_memb_store_id = null;
 	private String pos_use_yn = null;
 	private String pos_ent_user_id = null;
 	private String pos_upt_user_id = null;

 	/* PG 결제정보 */
 	private String pg_store_id = null;
 	private String pg_user_id = null;
 	private String pg_crypto_key = null;
 	private String pg_upt_user_id = null;
 	private String pg_pay_rate = null;
 	private String pg_pay_day = null;
 	private String pg_sms_auth_code = null;
 	private String auth_yn = null;

}
