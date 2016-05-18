package com.eshop.storefront;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent model for shopping basket and holds logic for basket level computation
 * @author goamit
 */
public class Basket {

	private Map<String, LineItem> lineItems;
	private double total;

	public Basket() {
		this.lineItems = new HashMap<String, LineItem>();
		this.total = 0;
	}
	
	public Map<String, LineItem> getLineItems() {
		return lineItems;
	}

	public double getTotal() {
		return total;
	}

	public void addLineItem(LineItem lineItem) {
		String key = lineItem.getProduct().getName();
		if(lineItems.containsKey(key)) {
			lineItems.get(key).add(lineItem.getQty());
		} else {
			lineItems.put(key, lineItem);
		}
	}
	
	public boolean isEmpty() {
		return lineItems.size() == 0;
	}
	
	public void compute() {
		total = 0;
		for (LineItem lineItem : getLineItems().values()) {
			lineItem.compute();
			total += lineItem.getTotal(); 
		}
	}
	
}
