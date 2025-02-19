package com.web.kdebate.common.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SplitTransDataError {

	@JsonProperty("code")
	private String code;

	@JsonProperty("message")
	private String msg;
}
