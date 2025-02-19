package com.web.kdebate.common.common.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SplitTransData {

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

	@JsonProperty("split")
	private List<SplitTransDtl> dtlList = new ArrayList<SplitTransDtl>();

	public void addSplitTransDtl(SplitTransDtl dtl) {
		dtlList.add(dtl);
	}

}
