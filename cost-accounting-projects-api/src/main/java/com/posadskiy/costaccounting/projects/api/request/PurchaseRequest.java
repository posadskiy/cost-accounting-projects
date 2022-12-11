package com.posadskiy.costaccounting.projects.api.request;

import com.posadskiy.costaccounting.moneyactions.api.dto.Purchase;
import lombok.Data;

@Data
public class PurchaseRequest {
	private String projectId;
	private String purchaseId;
	private Purchase purchase;
}
