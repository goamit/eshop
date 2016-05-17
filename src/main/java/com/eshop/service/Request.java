package com.eshop.service;

import java.util.ArrayList;
import java.util.List;

import com.eshop.storefront.Basket;
import com.eshop.storefront.ShoppingCartEngine;

/**
 * Represents model to hold all necessary inputs to service a request 
 * @author goamit
 */
public class Request {

	private List<String> items;
	private List<String> messages;
	private Basket basket;
	
	public Request() {
		this.items = new ArrayList<String>();
		this.messages = new ArrayList<String>();
	}

	public List<String> getItems() {
		return items;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public void addItem(String item) {
		items.add(item);
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
	public boolean isEmpty() {
		return items.size() == 0;
	}
	
	public void process() {
		ShoppingCartEngine.instance().process(this);
	}

}
