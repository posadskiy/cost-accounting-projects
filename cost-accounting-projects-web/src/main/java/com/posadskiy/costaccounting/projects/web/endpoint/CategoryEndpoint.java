package com.posadskiy.costaccounting.projects.web.endpoint;

import com.posadskiy.costaccounting.projects.api.dto.Category;
import com.posadskiy.costaccounting.projects.core.controller.CategoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project/category")
public class CategoryEndpoint {
    
    private final CategoryController categoryController;
    
    @Autowired
    public CategoryEndpoint(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    @GetMapping("purchase-categories/{projectId}")
    public List<Category> getPurchaseCategories(@PathVariable final String projectId) {
        return categoryController.getPurchaseCategories(projectId);
    }

    @GetMapping("income-categories/{projectId}")
    public List<Category> getIncomeCategories(@PathVariable final String projectId) {
        return categoryController.getIncomeCategories(projectId);
    }
}
