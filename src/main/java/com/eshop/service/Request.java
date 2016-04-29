package com.eshop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eshop.storefront.Basket;

/**
 * Represents model to hold all necessary inputs to service a request  
 * @author goamit
 */
public class Request {

	private static Map<String, Basket> sessions = new HashMap<String, Basket>();
	
	public enum ActionType {
		VIEW,
		ADD,
		REM;
	}
	
	private String userId;
	private ActionType action;
	private List<String> keys;
	private Map<String, Long> qtyMap;
	private Map<String, Object> responseMap;
	
	public Request(String userId, ActionType action) {
		this.userId = userId;
		this.action = action;
		this.keys = new ArrayList<String>();
		this.qtyMap = new HashMap<String, Long>();
		this.responseMap = new HashMap<String, Object>();
	}

	public String getUserId() {
		return userId;
	}

	public ActionType getAction() {
		return action;
	}

	public Collection<String> getKeys() {
		return keys;
	}
	
	public Map<String, Long> getQtyMap() {
		return qtyMap;
	}

	public Map<String, Object> getResponseMap() {
		return responseMap;
	}

	public void addItem(String key, long qty) {
		if(keys.contains(key)) {
			qtyMap.put(key, qtyMap.get(key) + qty);
		} else {
			keys.add(key);
			qtyMap.put(key, qty);
		}
	}
	
	public Basket getBasket() {
		Basket basket = sessions.get(userId);
		if(basket == null) {
			basket = new Basket();
			sessions.put(userId, basket);
		}
		return basket;
	}
	
	public static void clear(String userId) {
		sessions.remove(userId);
	}
	
	public void process() {
		RequestHandler.instance().process(this);
	}

}
