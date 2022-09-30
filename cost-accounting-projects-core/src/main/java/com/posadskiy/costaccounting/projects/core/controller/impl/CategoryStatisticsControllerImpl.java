package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.costaccounting.moneyactions.api.dto.MoneyAction;
import com.posadskiy.costaccounting.projects.core.controller.CategoryStatisticsController;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategoryCurrentLimit;
import com.posadskiy.costaccounting.projects.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.projects.core.db.model.DbStatisticCategory;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CategoryStatisticsControllerImpl implements CategoryStatisticsController {

    @Override
    public void decreaseMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction) {
        dbStatisticCategory.setAmount(dbStatisticCategory.getAmount() - moneyAction.getAmount());
        dbStatisticCategory.setEventCount(dbStatisticCategory.getEventCount() - 1);
        if (Objects.equals(dbStatisticCategory.getMaxAmount(), moneyAction.getAmount())) {
            dbStatisticCategory.setMaxAmount(dbStatisticCategory.getEventCount() != 0 ? dbStatisticCategory.getAmount() / dbStatisticCategory.getEventCount() : 0.0);
        }
    }


    @Override
    public void addMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction) {
        dbStatisticCategory.setAmount(dbStatisticCategory.getAmount() + moneyAction.getAmount());
        dbStatisticCategory.setEventCount(dbStatisticCategory.getEventCount() + 1);
        if (dbStatisticCategory.getMaxAmount() < moneyAction.getAmount()) {
            dbStatisticCategory.setMaxAmount(moneyAction.getAmount());
        }
    }

    @Override
    public @NotNull DbMonthStatistic createDefaultMonthStatistic(@NotNull String monthAndYear, @NotNull List<DbCategory> purchaseCategories, @NotNull List<DbCategory> incomeCategories, @NotNull Map<String, DbCategoryCurrentLimit> limits) {
        DbMonthStatistic statisticMonth = new DbMonthStatistic();
        //TODO: shit below should be refactored
        final String month = monthAndYear.split(" ")[0];
        final Integer year = Integer.valueOf(monthAndYear.split(" ")[1]);
        statisticMonth.setMonth(month);
        statisticMonth.setYear(year);

        final Map<String, DbStatisticCategory> defaultMonthStatisticCategory = purchaseCategories
            .stream()
            .map(dbCategory -> createDefaultStatisticCategoryWithLimit(dbCategory, getCategoryLimit(limits, dbCategory.getId())))
            .collect(Collectors.toMap(dbStatisticCategory -> dbStatisticCategory.getCategory().getId(), dbStatisticCategory -> dbStatisticCategory));
        statisticMonth.setPurchaseCategories(defaultMonthStatisticCategory);

        final Map<String, DbStatisticCategory> incomesStatistic = incomeCategories
            .stream()
            .map(this::createDefaultStatisticCategory)
            .collect(Collectors.toMap(dbStatisticCategory -> dbStatisticCategory.getCategory().getId(), dbStatisticCategory -> dbStatisticCategory));
        statisticMonth.setIncomeCategories(incomesStatistic);
        return statisticMonth;
    }

    //TODO: StatisticCategory fields should not be null
    private DbStatisticCategory createDefaultStatisticCategory(DbCategory category) {
        final DbStatisticCategory dbStatisticCategory = new DbStatisticCategory();
        dbStatisticCategory.setCategory(category);
        dbStatisticCategory.setAmount(0.0);
        dbStatisticCategory.setLimit(0.0);
        dbStatisticCategory.setMaxAmount(0.0);
        dbStatisticCategory.setEventCount(0);
        return dbStatisticCategory;
    }

    private DbStatisticCategory createDefaultStatisticCategoryWithLimit(DbCategory category, Double limit) {
        final DbStatisticCategory defaultStatisticCategory = createDefaultStatisticCategory(category);
        defaultStatisticCategory.setLimit(limit);
        return defaultStatisticCategory;
    }

    private Double getCategoryLimit(Map<String, DbCategoryCurrentLimit> limits, String categoryId) {
        return limits.get(categoryId).getLimit();
    }
}
