package com.posadskiy.costaccounting.projects.core.db.model;

import lombok.Data;

@Data
public class DbCategoryCurrentLimit {
	private DbCategory category;
	private Double limit;
}
