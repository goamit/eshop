package com.eshop.storefront;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

import com.eshop.Assist;
import com.eshop.inventory.Inventory;
import com.eshop.inventory.Price;
import com.eshop.inventory.Product;
import com.eshop.service.Request;
import com.eshop.storefront.LineItem;
import com.eshop.storefront.ShoppingCartEngine;

public class ShoppingCartEngine_Test {

	@Test
	public void testProcessInvalidRequest() {
		try {
			ShoppingCartEngine.instance().process(new Request());
			fail("IllegalArgumentException is expected");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testProcessCheckOutWhenNoProductFound() {
		Inventory inventory = mock(Inventory.class);
		when(inventory.lookupPrices(Assist.keys)).thenReturn(new HashMap<String, Price>());
		
		ShoppingCartEngine requestHandler = ShoppingCartEngine.instance();
		requestHandler.setInventory(inventory);
		
		Request request = new Request();
		for (String key : Assist.keys) {
			request.addItem(key);
		}
				
		assertEquals(0, request.getMessages().size());
		assertNull(request.getBasket());
		
		requestHandler.process(request);
		
		assertEquals(3, request.getMessages().size());
		assertNull(request.getBasket());
	}
	
	@Test
	public void testProcessCheckOutWithOutOffers() {
		Inventory inventory = mock(Inventory.class);
		when(inventory.lookupPrices(Assist.keys)).thenReturn(Assist.priceMap);

		ShoppingCartEngine requestHandler = ShoppingCartEngine.instance();
		requestHandler.setInventory(inventory);
		
		Request request = new Request();
		for (String key : Assist.keys) {
			request.addItem(key);
		}
				
		assertEquals(0, request.getMessages().size());
		
		requestHandler.process(request);
		
		assertEquals(0, request.getMessages().size());
		assertEquals(3, request.getBasket().getLineItems().size());
		assertBasket(request.getBasket(), false, Assist.product1, Assist.product2, Assist.product3);
	}

	@Test
	public void testProcessCheckOutWithOffers() {
		Inventory inventory = mock(Inventory.class);
		when(inventory.lookupPrices(Assist.keys)).thenReturn(Assist.priceMap);
		when(inventory.lookupOffers(Assist.keys)).thenReturn(Assist.offersMap);
		
		ShoppingCartEngine requestHandler = ShoppingCartEngine.instance();
		requestHandler.setInventory(inventory);
		
		Request request = new Request();
		for (String key : Assist.keys) {
			request.addItem(key);
		}
				
		assertEquals(0, request.getMessages().size());
		
		requestHandler.process(request);
		
		assertEquals(0, request.getMessages().size());
		assertEquals(3, request.getBasket().getLineItems().size());
		assertBasket(request.getBasket(), true, Assist.product1, Assist.product2, Assist.product3);
	}

	private void assertBasket(Basket basket, boolean offers, Product... products) {
		for (Product product : products) {
			LineItem lineItem = basket.getLineItems().get(product.getName());
			assertEquals(product, lineItem.getProduct());
			assertEquals(offers, lineItem.hasOffers());
		}
	}
	
}
