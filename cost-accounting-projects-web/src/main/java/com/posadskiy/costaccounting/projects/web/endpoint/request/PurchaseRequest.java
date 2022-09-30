package com.posadskiy.costaccounting.projects.web.endpoint.request;

import com.posadskiy.costaccounting.projects.api.dto.Purchase;
import lombok.Data;

@Data
public class PurchaseRequest {
	private String userId;
	private String purchaseId;
	private Purchase purchase;
}
