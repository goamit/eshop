package com.eshop.storefront;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.eshop.inventory.Inventory;
import com.eshop.inventory.Offer;
import com.eshop.inventory.Price;
import com.eshop.inventory.SimpleInventory;
import com.eshop.service.Request;

/**
 * Singleton class responsible to service request. 
 * Holds the business logic for inventory lookup and shopping cart check out.
 * @author goamit
 */
public class ShoppingCartEngine {
	
	private static ShoppingCartEngine singleton;
	
	public static ShoppingCartEngine instance() {
		if(singleton == null) {
			synchronized(ShoppingCartEngine.class) {
				if(singleton == null) {
					singleton = new ShoppingCartEngine();
				}
			}
		}
		return singleton;
	}
	
	private Inventory inventory;
	
	private ShoppingCartEngine() {
		inventory = new SimpleInventory();
	}
	
	public void process(Request request) {
		//validate request
		if(request.isEmpty()) {
			throw new IllegalArgumentException("Request is empty");
		}
		
		//search prices/offers from inventory
		List<String> searchItems = request.getItems().stream().distinct().collect(Collectors.toList());
		Map<String, Price> prices = inventory.lookupPrices(searchItems);
		Map<String, List<Offer>> offers = inventory.lookupOffers(searchItems);
		
		//prepare basket
		Basket basket = new Basket();
		for (String item : request.getItems()) {
			if(prices.containsKey(item)) {
				basket.addLineItem(new LineItem(prices.get(item), offers.get(item)));
			} else {
				request.addMessage(item + " not found");
			}
		}
		
		//compute basket
		if(!basket.isEmpty()) {
			basket.compute();
			request.setBasket(basket);
		}
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
