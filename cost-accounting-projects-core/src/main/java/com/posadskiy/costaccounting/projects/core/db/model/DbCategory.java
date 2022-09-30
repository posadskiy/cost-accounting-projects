package com.posadskiy.costaccounting.projects.core.db.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "category")
public class DbCategory {
	@Id
	private String id;
	private String name;
	private String emoji;
	private String projectId;
	private Boolean isPurchase;
	private Boolean isIncome;
}
