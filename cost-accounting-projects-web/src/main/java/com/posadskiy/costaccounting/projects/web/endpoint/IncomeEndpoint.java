package com.posadskiy.costaccounting.projects.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.request.IncomeRequest;
import com.posadskiy.costaccounting.projects.core.controller.ProjectController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project/income")
public class IncomeEndpoint {

    private final ProjectController projectController;

    @Autowired
    public IncomeEndpoint(ProjectController incomeController) {
        this.projectController = incomeController;
    }

    @PostMapping("add")
    public void addIncome(@RequestBody final IncomeRequest incomeRequest) {
        projectController.saveIncome(incomeRequest.getUserId(), incomeRequest.getIncome());
    }

    @PostMapping("delete")
    public void deleteIncome(@RequestBody final IncomeRequest incomeRequest) {
        projectController.deleteIncome(incomeRequest.getUserId(), incomeRequest.getIncome());
    }
}
