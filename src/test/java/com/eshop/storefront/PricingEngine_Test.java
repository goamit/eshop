package com.eshop.storefront;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.eshop.Assist;
import com.eshop.inventory.Inventory;

public class PricingEngine_Test {

	@Test
	public void testProcessLineItemUpdatePricing() {
		Inventory inventory = mock(Inventory.class);
		
		LineItem lineItem = new LineItem(Assist.product1, 2);
		
		new PricingEngine(inventory).process(lineItem);
		
		assertEquals(1.0, lineItem.getTotal(), 0.0);
	}
	
	@Test
	public void testProcessBasketUpdatePricing() {
		Inventory inventory = mock(Inventory.class);
		
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.product1, 2));
		basket.addLineItem(new LineItem(Assist.product2, 3));
		
		new PricingEngine(inventory).process(basket);
		
		assertEquals(3.25, basket.getTotal(), 0.0);
	}
	
}
