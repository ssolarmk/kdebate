package com.web.kdebate.common.common.domain;

import lombok.Data;

@Data
public class MsgVO {
	private String seat_no;
	private String std_id;
	private String std_nm;
	private String atd_chk_dttm;
	private String in_tm;
	private String dur_mm;
	private String dur_tm;
	private String curr_io_stat;
	private String curr_io_rsn_nm;
	private String atd_rsn_cd;
	private String last_io_tm;
	private String study_mm;
	private String study_tm;
	private String study_start_tms;
	private String aca_id;
	private String room_no;
}
