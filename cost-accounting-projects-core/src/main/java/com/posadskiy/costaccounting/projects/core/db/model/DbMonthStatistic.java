package com.posadskiy.costaccounting.projects.core.db.model;

import lombok.Data;

import java.util.Map;

@Data
public class DbMonthStatistic {
	private Integer year;
	private String month;
	private Map<String, DbStatisticCategory> purchaseCategories;
	private Map<String, DbStatisticCategory> incomeCategories;
}
