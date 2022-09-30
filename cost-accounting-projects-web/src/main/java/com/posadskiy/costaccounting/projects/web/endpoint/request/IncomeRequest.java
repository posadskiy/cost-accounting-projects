package com.posadskiy.costaccounting.projects.web.endpoint.request;

import com.posadskiy.costaccounting.projects.api.dto.Income;
import lombok.Data;

@Data
public class IncomeRequest {
	private String userId;
	private String incomeId;
	private Income income;
}
