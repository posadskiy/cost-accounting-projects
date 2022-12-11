package com.posadskiy.costaccounting.projects.core.controller;

import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import com.posadskiy.costaccounting.projects.core.db.model.DbProject;

import java.util.List;

public interface ProjectController {
    DbProject save(DbProject project);

    DbProject getProject(String id);

    List<String> getMonths(String id);
    void savePurchase(String projectId, Purchase purchase);

    void saveIncome(String id, Income income);

    void deletePurchase(String projectId, Purchase purchase);

    void deleteIncome(String id, Income income);

    List<String> getAllUsersByProjectId(String projectId);
}
