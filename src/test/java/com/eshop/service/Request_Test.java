package com.eshop.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eshop.storefront.Basket;

public class Request_Test {

	@Test
	public void testAddUniqueItems() {
		Request request = new Request("TestUser", Request.ActionType.VIEW);
		
		assertEquals(0, request.getKeys().size());
		assertEquals(0, request.getQtyMap().size());
		assertEquals(0, request.getResponseMap().size());
		
		request.addItem("a", 1);
		request.addItem("b", 2);
		request.addItem("c", 3);
		
		assertEquals(3, request.getKeys().size());
		assertEquals(3, request.getQtyMap().size());
		assertEquals(0, request.getResponseMap().size());
		
		assertTrue(request.getKeys().contains("a"));
		assertTrue(request.getKeys().contains("b"));
		assertTrue(request.getKeys().contains("c"));
		
		assertEquals(1, request.getQtyMap().get("a").longValue());
		assertEquals(2, request.getQtyMap().get("b").longValue());
		assertEquals(3, request.getQtyMap().get("c").longValue());
	}

	@Test
	public void testAddRepeatedItems() {
		Request request = new Request("TestUser", Request.ActionType.VIEW);
		
		assertEquals(0, request.getKeys().size());
		assertEquals(0, request.getQtyMap().size());
		assertEquals(0, request.getResponseMap().size());
		
		request.addItem("a", 1);
		request.addItem("b", 2);
		request.addItem("a", 3);
		
		assertEquals(2, request.getKeys().size());
		assertEquals(2, request.getQtyMap().size());
		assertEquals(0, request.getResponseMap().size());
		
		assertTrue(request.getKeys().contains("a"));
		assertTrue(request.getKeys().contains("b"));
		
		assertEquals(4, request.getQtyMap().get("a").longValue());
		assertEquals(2, request.getQtyMap().get("b").longValue());
	}

	@Test
	public void testSession() {
		Request request1 = new Request("TestUser", Request.ActionType.VIEW);
		Request request2 = new Request("TestUser", Request.ActionType.VIEW);
		assertTrue(request1.getBasket() == request2.getBasket());

		Request request3 = new Request("OtherUser", Request.ActionType.VIEW);
		assertFalse(request1.getBasket() == request3.getBasket());
		
		Basket origBasket = request1.getBasket();
		
		Request.clear("TestUser");

		assertFalse(origBasket == request1.getBasket());
	}
	
}
