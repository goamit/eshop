package com.eshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eshop.inventory.Inventory;
import com.eshop.inventory.Product;
import com.eshop.inventory.SimpleInventory;
import com.eshop.service.Request.ActionType;
import com.eshop.storefront.Basket;
import com.eshop.storefront.LineItem;
import com.eshop.storefront.PricingEngine;

/**
 * Singleton class responsible to service requested actions, also a placeholder to implement service level requirements 
 * @author goamit
 */
public class RequestHandler {
	
	private static RequestHandler singleton;
	
	public static RequestHandler instance() {
		if(singleton == null) {
			synchronized(RequestHandler.class) {
				if(singleton == null) {
					singleton = new RequestHandler();
				}
			}
		}
		return singleton;
	}
	
	private Inventory inventory;
	private PricingEngine pricingEngine; 
	
	private RequestHandler() {
		inventory = new SimpleInventory();
		pricingEngine = new PricingEngine(inventory);
	}
	
	public void process(Request request) {
		if(ActionType.VIEW.equals(request.getAction())) {
			view(request);
		} else if(ActionType.ADD.equals(request.getAction())) {
			add(request);
		} else if(ActionType.REM.equals(request.getAction())) {
			remove(request);
		}
	}
	
	protected void view(Request request) {
		Map<String, List<Product>> productLists = inventory.searchProducts(request.getKeys());
		for (String key : request.getKeys()) {
			if(productLists.containsKey(key)) {
				List<LineItem> lineItems = new ArrayList<LineItem>();
				List<Product> products = productLists.get(key);
				for (Product product : products) {
					LineItem lineItem = new LineItem(product, request.getQtyMap().get(key));
					pricingEngine.process(lineItem);
					lineItems.add(lineItem);
				}
				request.getResponseMap().put(key, lineItems);
			} else {
				request.getResponseMap().put(key, "Not found");
			}
		}
	}
	
	protected void add(Request request) {
		Basket basket = request.getBasket();
		Map<String, Product> products = inventory.lookupProducts(request.getKeys());
		for (String key : request.getKeys()) {
			if(products.containsKey(key)) {
				basket.addLineItem(new LineItem(products.get(key), request.getQtyMap().get(key)));
			} else {
				request.getResponseMap().put(key, "Not found");
			}
		}
		pricingEngine.process(basket);
	}
	
	protected void remove(Request request) {
		Basket basket = request.getBasket();
		Map<String, Product> products = inventory.lookupProducts(request.getKeys());
		for (String key : request.getKeys()) {
			if(products.containsKey(key)) {
				try {
					basket.removeLineItem(new LineItem(products.get(key), request.getQtyMap().get(key)));
				} catch(RuntimeException e) {
					request.getResponseMap().put(key, e.getMessage());
				}
			} else {
				request.getResponseMap().put(key, "Not found");
			}
		}
		pricingEngine.process(basket);
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void setPricingEngine(PricingEngine pricingEngine) {
		this.pricingEngine = pricingEngine;
	}

}
