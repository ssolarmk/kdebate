package com.web.kdebate.common.common.domain;

import lombok.Data;

@Data
public class CreditRequestVcatVO {

	private String tradeCode;
	private String tradeType;
	private String methodType;
	private Long tradeAmt;
	private Long vatAmt;
	private Long serviceAmt;
	private String installMents;
	private String approveNum; // 승인번호
	private String origTradeDate; // 원거래일자
	private String catId;
	private String cashReceiptIdNum; // 현금영수증식별번호
	private Long dutyFreeAmt; // 면세금액
	private String taxPayerFieldIdentifier;
	private String taxPayerFieldData;
	private String telegramMngNum;
	private String filter;
	private String signPadDisplayAmt;

}
