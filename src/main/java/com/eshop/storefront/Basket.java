package com.eshop.storefront;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent model for shopping basket and holds logic for basket level computation
 * @author goamit
 */
public class Basket {

	private List<String> keys;
	private Map<String, LineItem> lineItems;
	private double total;

	public Basket() {
		this.keys = new ArrayList<String>();
		this.lineItems = new HashMap<String, LineItem>();
		this.total = 0;
	}
	
	public List<String> getKeys() {
		return keys;
	}

	public Map<String, LineItem> getLineItems() {
		return lineItems;
	}

	public double getTotal() {
		return total;
	}

	public void addLineItem(LineItem lineItem) {
		String key = lineItem.getProduct().getName();
		if(keys.contains(key)) {
			lineItems.get(key).add(lineItem.getQty());
		} else {
			keys.add(key);
			lineItems.put(key, lineItem);
		}
	}
	
	public boolean isEmpty() {
		return keys.size() == 0;
	}
	
	public void compute() {
		total = 0;
		for (LineItem lineItem : getLineItems().values()) {
			lineItem.compute();
			total += lineItem.getTotal(); 
		}
	}
	
}
