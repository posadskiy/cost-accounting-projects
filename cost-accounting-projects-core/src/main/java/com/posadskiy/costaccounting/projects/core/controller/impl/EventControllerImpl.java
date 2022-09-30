package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.costaccounting.projects.core.controller.EventController;
import com.posadskiy.costaccounting.projects.core.controller.UserController;
import com.posadskiy.costaccounting.projects.core.db.model.DbUser;
import com.posadskiy.costaccounting.projects.core.db.model.MoneyAction;
import com.posadskiy.costaccounting.projects.core.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class EventControllerImpl implements EventController {
    private final UserController userController;

    @Autowired
    public EventControllerImpl(UserController userController) {
        this.userController = userController;
    }

    @Override
    public Map<Integer, List<MoneyAction>> monthEvents(@NotNull String userId, int year, int month) {
        final DbUser foundUser = userController.getById(userId);

        final LocalDateTime monthStart = LocalDateTime.of(year, month, 1, 0, 0);
        final LocalDateTime monthFinish = monthStart.plusMonths(1);
        final List<MoneyAction> monthPurchases = foundUser.getPurchases().stream().filter(dbPurchase -> {
            final LocalDateTime date = Utils.convertToLocalDateTimeViaInstant(dbPurchase.getDate());
            return date.isAfter(monthStart) && date.isBefore(monthFinish);
        }).collect(Collectors.toList());

        final List<MoneyAction> monthIncomes = foundUser.getIncomes().stream().filter(dbIncome -> {
            final LocalDateTime date = Utils.convertToLocalDateTimeViaInstant(dbIncome.getDate());
            return date.isAfter(monthStart) && date.isBefore(monthFinish);
        }).collect(Collectors.toList());

        final Map<Integer, List<MoneyAction>> mapByDays = Stream.of(monthPurchases, monthIncomes)
            .flatMap(Collection::stream)
            .collect(Collectors.groupingBy(event -> Utils.convertDateToDays(event.getDate())));

        mapByDays.forEach((integer, moneyActions) -> moneyActions.sort((o1, o2) -> o1.getDate().before(o2.getDate()) ? 1 : -1));

        return mapByDays;
    }

}
