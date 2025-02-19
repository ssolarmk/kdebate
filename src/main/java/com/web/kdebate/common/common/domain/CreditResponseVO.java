package com.web.kdebate.common.common.domain;

import lombok.Data;

@Data
public class CreditResponseVO {

	private String serviceCode;
	private String rejectCode;
	private String rejectMessageFirst;
	private String rejectMessageSecond;
	private String approveDayTime;
	private Long totalAmt;
	private Long imposeTaxAmt;
	private String approveNum;
	private String issuerCode;
	private String purchaserCode;
	private String issuerName;
	private String purchaserName;
	private String affiliationCode;
	private String creditCardNo;
	private String installmentMonths;
	private String terminalProcessNum;
	private String approveCatId;
	private String signData;
}
