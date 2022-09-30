package com.posadskiy.costaccounting.projects.core.controller;

import com.posadskiy.costaccounting.projects.core.db.model.MoneyAction;

import java.util.List;
import java.util.Map;

public interface EventController {
    Map<Integer, List<MoneyAction>> monthEvents(String userId, int year, int month);

}
