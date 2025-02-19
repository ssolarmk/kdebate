package com.web.kdebate.common.common.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SplitTransDataResult {

	@JsonProperty("@type")
	private String msgType;

	@JsonProperty("@service")
	private String serviceName;

	@JsonProperty("@version")
	private String version;

	@JsonProperty("result")
	private SplitTransData result;

	@JsonProperty("error")
	private SplitTransDataError error;
}
