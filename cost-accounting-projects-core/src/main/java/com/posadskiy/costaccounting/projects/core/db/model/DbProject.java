package com.posadskiy.costaccounting.projects.core.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "project")
public class DbProject {
	@Id
	private String id;
	private List<DbCategory> purchaseCategories;
	private List<DbCategory> incomeCategories;
	private Map<String, DbCategoryCurrentLimit> limits;
    private Map<String, DbMonthStatistic> statistics;
}
