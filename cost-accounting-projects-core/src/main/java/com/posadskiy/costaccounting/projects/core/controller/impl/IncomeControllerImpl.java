package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.costaccounting.projects.core.controller.*;
import com.posadskiy.costaccounting.projects.core.db.model.DbIncome;
import com.posadskiy.costaccounting.projects.core.db.model.DbUser;
import com.posadskiy.costaccounting.projects.api.dto.Category;
import com.posadskiy.costaccounting.projects.api.dto.Income;
import com.posadskiy.costaccounting.projects.core.exception.IncomeDoesNotExistOrTooOldException;
import com.posadskiy.costaccounting.projects.core.mapper.IncomeMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IncomeControllerImpl implements IncomeController {

	private final UserController userController;
	private final ProjectController projectController;
	private final IncomeMapper incomeMapper;
	private final CurrencyConverter currencyConverter;
	private final CategoryController categoryController;

	@Autowired
	public IncomeControllerImpl(UserController userController, ProjectController projectController, IncomeMapper incomeMapper, CurrencyConverter currencyConverter, CategoryController categoryController) {
		this.userController = userController;
		this.projectController = projectController;
		this.incomeMapper = incomeMapper;
		this.currencyConverter = currencyConverter;
		this.categoryController = categoryController;
	}

	private DbIncome getIncomeById(@NotNull List<DbIncome> incomes, @NotNull String incomeId) {
		final Optional<DbIncome> possibleIncomeForDeleting = incomes
			.stream()
			.filter(dbIncome -> StringUtils.equals(dbIncome.getId(), incomeId))
			.findFirst();
		if (!possibleIncomeForDeleting.isPresent()) throw new IncomeDoesNotExistOrTooOldException();
		return possibleIncomeForDeleting.get();
	}

	@Override
	public void addIncome(@NotNull String userId, @NotNull Income income) {
		final DbUser foundUser = userController.getById(userId);
		final DbIncome dbIncome = incomeMapper.mapFromDto(income, currencyConverter);

		userController.saveIncome(foundUser.getId(), dbIncome);
		projectController.saveIncome(foundUser.getProjectId(), dbIncome);
	}

	@Override
	public void deleteIncome(@NotNull String userId, @NotNull String incomeId) {
		final DbUser foundUser = userController.getById(userId);

		userController.deleteIncome(foundUser.getId(), incomeId);
		final DbIncome incomeForDeleting = getIncomeById(foundUser.getIncomes(), incomeId);
		projectController.deleteIncome(foundUser.getProjectId(), incomeForDeleting);
	}

	@Override
	public List<Category> getCategories(@NotNull String userId) {
		return categoryController.getIncomeCategories();
	}
}
