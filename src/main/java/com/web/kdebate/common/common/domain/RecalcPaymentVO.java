package com.web.kdebate.common.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RecalcPaymentVO {

	private boolean self;
	private int cnt = 0;
	private List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

	public void addItems(Map<String, Object> item) {
		items.add(item);
	};
}
