package com.web.kdebate.common.common.domain;

import java.util.Map;

import lombok.Data;

@Data
public class MnulPaymentCallResultVO {

	private Map<String, String> resultMap;
	private boolean systemResultCd;
	private boolean networkResultCd;

}
