package com.posadskiy.costaccounting.projects.api.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.Income;
import lombok.Data;

@Data
public class IncomeRequest {
	private String projectId;
	private String incomeId;
	private Income income;
}
