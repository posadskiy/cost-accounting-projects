package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.costaccounting.projects.api.dto.Category;
import com.posadskiy.costaccounting.projects.core.controller.CategoryController;
import com.posadskiy.costaccounting.projects.core.db.CategoriesRepository;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import com.posadskiy.costaccounting.projects.core.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryControllerImpl implements CategoryController {

    private final CategoriesRepository categoriesRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryControllerImpl(CategoriesRepository categoriesRepository, CategoryMapper categoryMapper) {
        this.categoriesRepository = categoriesRepository;
        this.categoryMapper = categoryMapper;
    }
    @Override
    public List<Category> getPurchaseCategories(String projectId) {
        List<Category> list = new ArrayList<>();
        for (DbCategory category : categoriesRepository.findAllByProjectId(projectId)) {
            if (category.getIsPurchase() == Boolean.TRUE) {
                list.add(
                    categoryMapper.mapToDto(category)
                );
            }
        }
        return list;
    }

    @Override
    public List<Category> getIncomeCategories(String projectId) {
        List<Category> list = new ArrayList<>();
        for (DbCategory category : categoriesRepository.findAllByProjectId(projectId)) {
            if (category.getIsIncome() == Boolean.TRUE) {
                list.add(
                    categoryMapper.mapToDto(category)
                );
            }
        }
        return list;
    }
}
