package com.web.kdebate.common.common.domain;

import lombok.Data;

@Data
public class TaxPointCashRequestVO {

	private String serviceCode;
	private String taxPointType;
	private String wcc;
	private String cashIdenticationNum; // 현금고객 식별번호
	private Long dutyFreeAmt;
	private Long imposeTaxAmt;
	private Long totalSaleAmt;
	private String resonForCancel; // 취소사유
	private String origTradeDate;
	private String approveNum;
	private String catId;

}
