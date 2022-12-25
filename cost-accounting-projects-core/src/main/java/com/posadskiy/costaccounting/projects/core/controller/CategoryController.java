package com.posadskiy.costaccounting.projects.core.controller;

import com.posadskiy.costaccounting.projects.api.dto.Category;

import java.util.List;

public interface CategoryController {
    List<Category> getPurchaseCategories(final String projectId);
    List<Category> getIncomeCategories(final String projectId);
}
