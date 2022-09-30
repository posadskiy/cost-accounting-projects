package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import com.posadskiy.costaccounting.projects.core.controller.CategoryStatisticsController;
import com.posadskiy.costaccounting.projects.core.controller.ProjectController;
import com.posadskiy.costaccounting.projects.core.db.ProjectRepository;
import com.posadskiy.costaccounting.projects.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.projects.core.db.model.DbProject;
import com.posadskiy.costaccounting.projects.core.db.model.DbStatisticCategory;
import com.posadskiy.costaccounting.projects.core.exception.ProjectDoesNotExistException;
import com.posadskiy.costaccounting.projects.core.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ProjectControllerImpl implements ProjectController {

    private final ProjectRepository projectRepository;
    private final CategoryStatisticsController categoryStatisticsController;
    
    @Autowired
    public ProjectControllerImpl(ProjectRepository projectRepository, CategoryStatisticsController categoryStatisticsController) {
        this.projectRepository = projectRepository;
        this.categoryStatisticsController = categoryStatisticsController;
    }


    @Override
    public DbProject save(@NotNull DbProject project) {
        return projectRepository.save(project);
    }

    @Override
    public @NotNull DbProject getProject(@NotNull String id) {
        Optional<DbProject> foundProject = projectRepository.findById(id);
        if (!foundProject.isPresent()) throw new ProjectDoesNotExistException();

        return foundProject.get();
    }

	@Override
	public List<String> getMonths(String id) {
		DbProject project = getProject(id);
		final List<String> months = (List<String>) CollectionUtils.arrayToList(
			project.getStatistics().keySet().toArray()
		);
		Collections.reverse(months);
		return months;
	}

    @Override
    public void savePurchase(String projectId, Purchase purchase) {
        DbProject project = this.getProject(projectId);
		DbMonthStatistic monthStatistic = getDbMonthStatisticByProject(project, purchase.getDate());

		final DbStatisticCategory dbStatisticCategory = monthStatistic
			.getPurchaseCategories()
			.get(purchase.getCategory().getId());
		if (dbStatisticCategory == null) {
			log.error("Add Purchase to Project Statistic: Category not found " + purchase.getCategory());
			return;
		}

		categoryStatisticsController.addMoneyActionToStatisticCategory(dbStatisticCategory, purchase);

        this.save(project);
    }

    @Override
    public void saveIncome(String id, Income income) {
        DbProject project = this.getProject(id);
		DbMonthStatistic statisticMonth = getDbMonthStatisticByProject(project, income.getDate());

		final DbStatisticCategory dbStatisticCategory = statisticMonth
			.getIncomeCategories()
			.get(income.getCategory().getId());
		if (dbStatisticCategory == null) {
			log.error("Add Income to Statistic: Category not found " + income.getCategory());
			return;
		}
		categoryStatisticsController.addMoneyActionToStatisticCategory(dbStatisticCategory, income);

        this.save(project);
    }
	
	private DbMonthStatistic getDbMonthStatisticByProject(@NotNull final DbProject project, @NotNull final Date moneyActionDate) {
		final String monthAndYear = Utils.getMonthAndYear(moneyActionDate);
		final DbMonthStatistic statisticMonth = project.getStatistics().get(monthAndYear);
		if (statisticMonth != null) return statisticMonth;

		final DbMonthStatistic newMonthStatistic = categoryStatisticsController.createDefaultMonthStatistic(monthAndYear, project.getPurchaseCategories(), project.getIncomeCategories(), project.getLimits());
		project.getStatistics().put(monthAndYear, newMonthStatistic);
		return newMonthStatistic;
	}

    @Override
    public void deletePurchase(String id, Purchase purchase) {
        DbProject project = this.getProject(id);
		DbMonthStatistic statisticMonth = project.getStatistics().get(Utils.getMonthAndYear(purchase.getDate()));

		categoryStatisticsController.decreaseMoneyActionToStatisticCategory(statisticMonth.getPurchaseCategories().get(purchase.getCategory().getId()), purchase);

        this.save(project);
    }

    @Override
    public void deleteIncome(String id, Income income) {
        DbProject project = this.getProject(id);
		DbMonthStatistic statisticMonth = project.getStatistics().get(Utils.getMonthAndYear(income.getDate()));

		categoryStatisticsController.decreaseMoneyActionToStatisticCategory(statisticMonth.getIncomeCategories().get(income.getCategory().getId()), income);

        this.save(project);
    }
}

