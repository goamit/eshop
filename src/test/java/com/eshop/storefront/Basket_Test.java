package com.eshop.storefront;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eshop.Assist;

public class Basket_Test {

	@Test
	public void testEmptyRequest() {
		Basket basket = new Basket();
		assertTrue(basket.isEmpty());
		
		basket.addLineItem(new LineItem(Assist.price1));
		assertFalse(basket.isEmpty());
	}
	
	@Test
	public void testBasketAddLineItemWithDiffProducts() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.price1));
		basket.addLineItem(new LineItem(Assist.price2));
		
		assertEquals(2, basket.getLineItems().size());
		assertEquals(1, basket.getLineItems().get(Assist.price1.getProduct().getName()).getQty());
		assertEquals(1, basket.getLineItems().get(Assist.price2.getProduct().getName()).getQty());
	}
	
	@Test
	public void testBasketAddLineItemWithSameProducts() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.price1));
		basket.addLineItem(new LineItem(Assist.price1));
		
		assertEquals(1, basket.getLineItems().size());
		assertEquals(2, basket.getLineItems().get(Assist.price1.getProduct().getName()).getQty());
	}

	@Test
	public void testBasketPricing() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.price1));
		basket.addLineItem(new LineItem(Assist.price2));
		basket.addLineItem(new LineItem(Assist.price3));

		basket.compute();

		assertEquals(3, basket.getLineItems().size());
		assertEquals(2.75, basket.getTotal(), 0.0);
	}
	
}
