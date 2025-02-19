package com.web.kdebate.student.students.domain;

import lombok.Data;

@Data
public class InvoiceMsgVO {
	
	private String std_id = null;
	private String std_nm = null;
	private String parent_tel = null;
	private String unpay_amt = null;
	private String unpay_cnt = null;
	
	private String reserved_date;
	private String mt_type;
	private String phone_number;
	private String callback;
	private String title;
	private String message;
	private String retunr_seq;
	private String request_date;
	private String response_date;
	private String response_code;
	private String report_type;
	private String report_date;
	private String report_code;
	private String arrival_date;
	private String aca_id;
	private String ent_user_id;
	private String status;
	private String sms_send_gb = null;
    private String sms_send_day = null;
    private String sms_send_tm = null;
    private String sms_cont_tp = null;
	
}
