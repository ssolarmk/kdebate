package com.web.kdebate.common.common.domain;

import lombok.Data;

@Data
public class CreditResponseVcatVO {

	private String tradeCode;
	private String tradeType;
	private String responseCode;
	private Long tradeAmt;
	private Long vatAmt;
	private Long serviceAmt;
	private String installMents;
	private String approveNum; // 승인번호
	private String approveDate; // 승인일시
	private String issuerCode;
	private String issuerNm;
	private String purchaserCode;
	private String purchaserName;
	private String affiliationCode;
	private String approveCatId;
	private Long remainAmt; // 잔액
	private String responseMsg; // 응답메시지
	private String cardBin; //
	private String cardFlag; // 카드구분
	private String telegramMngNum;
	private String tradeSerialNum;
}
