package com.posadskiy.costaccounting.projects.core.controller;

import com.posadskiy.costaccounting.projects.core.db.model.*;

import java.util.List;

public interface ProjectController {
	DbProject save(DbProject project);
	DbProject getProject(String id);
	//List<String> getMonths(String id);
	void savePurchase(String id, DbPurchase purchase);
	void saveIncome(String id, DbIncome income);
	void deletePurchase(String id, DbPurchase purchase);
	void deleteIncome(String id, DbIncome income);
}
