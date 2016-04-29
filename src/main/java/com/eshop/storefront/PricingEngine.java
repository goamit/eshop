package com.eshop.storefront;

import com.eshop.inventory.Inventory;

/**
 * Represents business logic for all sort of pricing calculations.
 * Can be extended easily to fetch discount/tax from inventory and consider them in pricing.
 * @author goamit
 */
public class PricingEngine {

	@SuppressWarnings("unused") // placeholder to extend for discounts, taxes, etc
	private Inventory inventory;
	
	public PricingEngine(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void process(LineItem lineItem) {
		lineItem.setTotal(lineItem.getQty() * lineItem.getProduct().getPrice());
	}
	
	public void process(Basket basket) {
		double total = 0;
		for (LineItem lineItem : basket.getLineItems().values()) {
			process(lineItem);
			total += lineItem.getTotal(); 
		}
		basket.setTotal(total);
	}
	
}
