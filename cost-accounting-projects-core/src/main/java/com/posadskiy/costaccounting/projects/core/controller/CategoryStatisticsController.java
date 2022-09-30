package com.posadskiy.costaccounting.projects.core.controller;

import com.posadskiy.costaccounting.moneyactions.api.dto.MoneyAction;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategoryCurrentLimit;
import com.posadskiy.costaccounting.projects.core.db.model.DbMonthStatistic;
import com.posadskiy.costaccounting.projects.core.db.model.DbStatisticCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface CategoryStatisticsController {
    void decreaseMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction);

    void addMoneyActionToStatisticCategory(@NotNull DbStatisticCategory dbStatisticCategory, @NotNull MoneyAction moneyAction);

    @NotNull DbMonthStatistic createDefaultMonthStatistic(@NotNull String monthAndYear, @NotNull List<DbCategory> purchaseCategories, @NotNull List<DbCategory> incomeCategories, @NotNull Map<String, DbCategoryCurrentLimit> limits);
}
