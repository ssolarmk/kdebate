package com.web.kdebate.common.common.domain;

import lombok.Data;

@Data
public class TaxPointCashResponseVO {

	private String serviceCode;
	private String rejectCode;
	private String rejectMessageFirst;
	private String rejectMessageSecond;
	private String approveDayTime;
	private Long totalAmt;
	private Long imposeTaxAmt;
	private String approveNum;
	private String issuerName; // 발급사명
	private String cashReceiptType; // 현금영수증 type
	private String creditMaskedCardNo; // 신용카드 마스킹된 카드번호
	private String terminalProcessNum;
	private String affiliationCatId; // 가맹점 CATID
	private String cashReceiptNoticeMessage; // 현금영수증 알림메시지

}
