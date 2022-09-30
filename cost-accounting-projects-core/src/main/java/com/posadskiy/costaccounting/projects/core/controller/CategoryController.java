package com.posadskiy.costaccounting.projects.core.controller;

import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import com.posadskiy.costaccounting.projects.api.dto.Category;

import java.util.List;

public interface CategoryController {
	List<Category> getIncomeCategories();
	List<Category> getPurchaseCategories();
	DbCategory getById(String id);
	DbCategory getByName(String name);
	//String getNameWithEmoji(DbCategory category);
}
