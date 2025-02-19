package com.web.kdebate.common.common.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MnulPaymentVO {

	// 수기결제 항목
	private String STOREID;
	private String CRYPTO_KEY;
	private String TRAN_NO;
	private String PRODUCT_TYPE;
	private String TAX_FREE_CD;
	private String BILL_TYPE;
	private String GOODS_NAME;
	private String AMT;
	private String QUANTITY;
	private String SALE_DATE;
	private String ETC_DATA;
	private String TRAN_TYPE;
	private String KIND;
	private String CUSTOMER_TEL;
	private String CUSTOMER_EMAIL;

	private String ENC_DATA;

	private String VAT;
	private String CORP_CD;

	// 부분 취소시 사용 ============
	private String CANCEL_ID;
	private String CANCEL_CAUSE;
	private String TID;

	private String REP_AUTH_NO;

	@NotEmpty(message = "고객명")
	private String CUSTOMER_NAME;

	@NotEmpty(message = "카드번호")
	private String CARD_NO;

	@NotEmpty(message = "카드 유효기간")
	private String EXPIRATION_DATE;

	@NotEmpty(message = "주민/사업자번호")
	private String CARD_AUTH;

	@NotEmpty(message = "카드 비밀번호")
	private String PASSWD;

	@NotEmpty(message = "할부개월수")
	private String INSTALL;

	// LMS에서 사용
	private String SALES_TP;

	private String BOSS_ID;

	private String INVOICE_NO;

	private String STD_ID;

}
