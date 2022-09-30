package com.posadskiy.costaccounting.projects.core.db.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@ToString
public class MoneyAction {
	@Id
	private String id;
	private DbCategory category;
	private Double amount;
	private String name;
	private Date date;
	private Boolean isPrivate;
}
