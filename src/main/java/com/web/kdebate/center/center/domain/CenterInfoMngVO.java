package com.web.kdebate.center.center.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("CenterInfoMngVO")
public class CenterInfoMngVO{

	private String user_id;
	private String aca_id;
	private String p_aca_id;
	private String upt_dttm = null;
	private String upt_user_id = null;

	// 학원정보
	private String aca_boss_id;
	private String aca_tp;
	private String aca_tel_no;
	private String aca_mobile;
	private String aca_open_tm;
	private String aca_close_tm;
	private String aca_email;
	private String aca_zip_no;
	private String aca_addr;
	private String aca_addr_dtl;
	private String aca_hompage;
	private String aca_boss_hp_chg;

	// 강의실정보
	private String class_room_no;
	private String class_room_nm;
	private String class_seat_cnt;
	private String class_use_yn;
	private String class_seat_start_num;
	private String color;

	// 독서실정보
	private String cls_room_no;
	private String cls_room_tp;
	private String cls_room_nm;
	private String cls_seat_cnt;
	private String cls_seat_start_num;
	private String cls_use_yn;

	// 청구항목
	private String gds_corp_cd;
	private String gds_goods_cd;
	private String gds_goods_nm;
	private String gds_goods_tp;
	private String gds_tax_tp;
	private String gds_price;
	private String gds_amt;
	private String gds_vat_amt;
	private String gds_goods_memo;
	private String gds_use_yn;
	private String gds_set_item_chk;
	private String gds_unit_tp;
	private String gds_unit_value;
	private String gds_pay_div_chk;
	private String gds_pay_calc_tp;
	private String gds_pay_calc_val;
	private String org_pay_div_chk;
	
	// 청구항목 - 교재정보
	private String item_tp;
	private String item_nm;
	private String lvl1;
	private String lvl2;
	private String vendor_nm;
	private String vendor_tel;

	// 판매처정보
	private String sal_crp_corp_cd;
	private String sal_crp_corp_nm;
	private String sal_crp_biz_no;
	private String sal_crp_tel_no;
	private String sal_crp_use_yn;
	private String sal_crp_zip_code;
	private String sal_crp_addr;
	private String sal_crp_addr_dtl;
	private String sal_boss_nm;

	// 관리그룹 정보
	private String mng_class_cd;
	private String mng_class_nm;
	private String mng_aca_id;
	private String mng_subject_cd;
	private String mng_main_teacher_id;
	private String mng_sub_teacher_id;
	private String mng_class_memo;
	private String mng_use_yn;

	// 코드관리 정보
	private String low_gr_code;
	private String low_gr_nm;
	private String low_code;
	private String low_code_nm;
	private String low_use_yn;
	private String low_sq;
	private String low_rmks;
	private String chk_cd1;

	//옵션관리 정보
	private String option_aca_id = null;
    private String logo_use_yn = null;
    private String logo_img_path = null;
    private String logo_file_nm = null;
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
    private String receipt_print_std_info_yn = null;
    private String receipt_print_text = null;
    private String receipt_print_yn = null;
    private String aca_oper_tp = null;
    private String aca_oper_tp_camp = null;
    private String aca_oper_tp_lib = null;
    private String live_class_oper_yn = null;
    private String office_of_education = null;


}
