package com.web.kdebate.common.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SplitTransDataCancel {

	@JsonProperty("storeId")
	private String storeId;

	@JsonProperty("svcType")
	private String svcType;

	@JsonProperty("tranNo")
	private String tranNo;

	@JsonProperty("tranType")
	private String tranType;

	@JsonProperty("amount")
	private Long amount;

}
