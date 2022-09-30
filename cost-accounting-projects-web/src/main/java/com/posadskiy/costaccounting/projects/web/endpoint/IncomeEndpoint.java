package com.posadskiy.costaccounting.projects.web.endpoint;

import com.posadskiy.costaccounting.projects.core.controller.IncomeController;
import com.posadskiy.costaccounting.projects.api.dto.Category;
import com.posadskiy.costaccounting.projects.web.endpoint.request.IncomeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("income")
public class IncomeEndpoint {

	private final IncomeController incomeController;

	@Autowired
	public IncomeEndpoint(IncomeController incomeController) {
		this.incomeController = incomeController;
	}

	@PostMapping("add")
	public void addIncome(@RequestBody final IncomeRequest incomeRequest) {
		incomeController.addIncome(incomeRequest.getUserId(), incomeRequest.getIncome());
	}

	@PostMapping("delete")
	public void deleteIncome(@RequestBody final IncomeRequest incomeRequest) {
		incomeController.deleteIncome(incomeRequest.getUserId(), incomeRequest.getIncomeId());
	}

	@PostMapping("categories")
	public List<Category> categories(@RequestBody final IncomeRequest incomeRequest) {
		return incomeController.getCategories(incomeRequest.getUserId());
	}
}
