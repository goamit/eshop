package com.eshop.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eshop.storefront.Basket;

public class Request_Test {

	@Test
	public void testAddItemsOrResponses() {
		Request request = new Request();
		
		assertEquals(0, request.getItems().size());
		assertEquals(0, request.getMessages().size());
		
		request.addItem("a");
		request.addItem("b");
		request.addItem("a");
		
		assertEquals(3, request.getItems().size());
		assertEquals(0, request.getMessages().size());
		
		request.addMessage("r1");
		request.setBasket(new Basket());
		
		assertEquals(3, request.getItems().size());
		assertEquals(1, request.getMessages().size());
		assertNotNull(request.getBasket());
	}

}
