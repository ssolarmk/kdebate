package com.web.kdebate.common.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SplitTransDtl {

	@JsonProperty("splitBizNo")
	private String splitBizNo;

	@JsonProperty("splitAmount")
	private Long splitAmount;

}
