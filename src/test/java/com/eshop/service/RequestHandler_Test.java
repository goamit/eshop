package com.eshop.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.eshop.Assist;
import com.eshop.inventory.Inventory;
import com.eshop.inventory.Product;
import com.eshop.storefront.LineItem;

public class RequestHandler_Test {

	@Test
	public void testProcessView() {
		Inventory inventory = mock(Inventory.class);
		when(inventory.searchProducts(Assist.keys)).thenReturn(Assist.productListMap);

		RequestHandler requestHandler = RequestHandler.instance();
		requestHandler.setInventory(inventory);
		
		Request request = new Request("TestUser", Request.ActionType.VIEW);
		for (String key : Assist.keys) {
			request.addItem(key, 1);
		}
				
		assertEquals(0, request.getResponseMap().size());
		assertEquals(0, request.getBasket().getLineItems().size());
		
		requestHandler.process(request);
		
		assertEquals(3, request.getResponseMap().size());
		assertEquals(0, request.getBasket().getLineItems().size());
		
		assertLineItem(request, Assist.product1);
		assertLineItem(request, Assist.product2);
		assertLineItem(request, Assist.product3);
	}
	
	@Test
	public void testProcessAddRemove() {
		Inventory inventory = mock(Inventory.class);
		when(inventory.lookupProducts(Assist.keys)).thenReturn(Assist.productMap);

		RequestHandler requestHandler = RequestHandler.instance();
		requestHandler.setInventory(inventory);
		
		Request request = new Request("TestUser", Request.ActionType.ADD);
		for (String key : Assist.keys) {
			request.addItem(key, 1);
		}
				
		assertEquals(0, request.getResponseMap().size());
		assertEquals(0, request.getBasket().getLineItems().size());
		
		requestHandler.process(request);
		
		assertEquals(0, request.getResponseMap().size());
		assertEquals(3, request.getBasket().getLineItems().size());
		
		assertBasket(request, Assist.product1, Assist.product2, Assist.product3);
		
		request = new Request("TestUser", Request.ActionType.REM);
		for (String key : Assist.keys) {
			request.addItem(key, 1);
		}
		
		requestHandler.process(request);
		
		assertEquals(0, request.getResponseMap().size());
		assertEquals(0, request.getBasket().getLineItems().size());
	}

	@Test
	public void testInvalidRemove() {
		Inventory inventory = mock(Inventory.class);
		when(inventory.lookupProducts(Assist.keys)).thenReturn(Assist.productMap);

		RequestHandler requestHandler = RequestHandler.instance();
		requestHandler.setInventory(inventory);
		
		Request request = new Request("TestUser", Request.ActionType.REM);
		for (String key : Assist.keys) {
			request.addItem(key, 1);
		}
				
		assertEquals(0, request.getResponseMap().size());
		assertEquals(0, request.getBasket().getLineItems().size());
		
		requestHandler.process(request);
		
		assertEquals(3, request.getResponseMap().size());
		assertEquals(0, request.getBasket().getLineItems().size());
	}
	
	@SuppressWarnings("unchecked")
	private void assertLineItem(Request request, Product product) {
		List<LineItem> lineItemList = (List<LineItem>) request.getResponseMap().get(product.getDesc());
		assertEquals(1, lineItemList.size());
		assertEquals(product, lineItemList.get(0).getProduct());
	}
	
	private void assertBasket(Request request, Product... products) {
		for (Product product : products) {
			assertEquals(product, request.getBasket().getLineItems().get(product.getCode()).getProduct());
		}
	}
	
}
