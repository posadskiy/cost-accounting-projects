package com.posadskiy.costaccounting.projects.api.dto;

import lombok.Data;

@Data
public class Category {
	private String id;
	private String name;
	private String emoji;
	private String projectId;
	private Boolean isPurchase;
	private Boolean isIncome;
}
