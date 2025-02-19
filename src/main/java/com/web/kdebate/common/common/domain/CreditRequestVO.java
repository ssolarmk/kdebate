package com.web.kdebate.common.common.domain;

import lombok.Data;

@Data
public class CreditRequestVO {

	private String serviceCode;
	private String signDataRequest;
	private String nonCardCancelNum;
	private String installmentMonths; // 할부개월
	private Long dutyFreeAmt;
	private Long imposeTaxAmt;
	private Long totalSaleAmt;
	private String approveNum;
	private String origTradeDate;
	private String catId;
	private String signData;
}
