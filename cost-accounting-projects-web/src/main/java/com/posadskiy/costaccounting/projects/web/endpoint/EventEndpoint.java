package com.posadskiy.costaccounting.projects.web.endpoint;

import com.posadskiy.costaccounting.projects.core.controller.EventController;
import com.posadskiy.costaccounting.projects.core.db.model.MoneyAction;
import com.posadskiy.costaccounting.projects.web.endpoint.request.MoneyActionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("events")
public class EventEndpoint {
    private final EventController eventController;

    @Autowired
    public EventEndpoint(EventController eventController) {
        this.eventController = eventController;
    }

    @PostMapping("month")
    public Map<Integer, List<MoneyAction>> lastMonthEvents(@RequestBody final MoneyActionRequest moneyActionRequest) {
        return eventController.monthEvents(moneyActionRequest.getUserId(), moneyActionRequest.getYear(), moneyActionRequest.getMonth());
    }
}
