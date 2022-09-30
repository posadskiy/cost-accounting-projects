package com.posadskiy.costaccounting.projects.web.endpoint;

import com.posadskiy.costaccounting.moneyactions.api.request.PurchaseRequest;
import com.posadskiy.costaccounting.projects.core.controller.ProjectController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchase")
public class PurchaseEndpoint {

	private final ProjectController purchaseController;

	public PurchaseEndpoint(ProjectController purchaseController) {
		this.purchaseController = purchaseController;
	}

	@PostMapping("add")
	public void addPurchase(@RequestBody final PurchaseRequest purchaseRequest) {
		purchaseController.savePurchase(purchaseRequest.getUserId(), purchaseRequest.getPurchase());
	}
	
	@PostMapping("delete")
	public void deletePurchase(@RequestBody final PurchaseRequest purchaseRequest) {
		purchaseController.deletePurchase(purchaseRequest.getUserId(), purchaseRequest.getPurchase());
	}
}
