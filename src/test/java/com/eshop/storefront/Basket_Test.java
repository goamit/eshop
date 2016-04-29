package com.eshop.storefront;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eshop.Assist;

public class Basket_Test {

	@Test
	public void testBasketAddLineItemWithDiffProducts() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.product1, 2));
		basket.addLineItem(new LineItem(Assist.product2, 3));
		
		assertEquals(2, basket.getKeys().size());
		assertEquals(2, basket.getLineItems().size());
		assertEquals(2, basket.getLineItems().get(Assist.product1.getCode()).getQty());
		assertEquals(3, basket.getLineItems().get(Assist.product2.getCode()).getQty());
	}
	
	@Test
	public void testBasketAddLineItemWithSameProducts() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.product1, 2));
		basket.addLineItem(new LineItem(Assist.product1, 3));
		
		assertEquals(1, basket.getKeys().size());
		assertEquals(1, basket.getLineItems().size());
		assertEquals(5, basket.getLineItems().get(Assist.product1.getCode()).getQty());
	}

	@Test
	public void testBasketRemoveLineItemWithLesserQty() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.product1, 10));
		basket.addLineItem(new LineItem(Assist.product2, 10));
		
		basket.removeLineItem(new LineItem(Assist.product1, 9));
		
		assertEquals(2, basket.getKeys().size());
		assertEquals(2, basket.getLineItems().size());
		assertEquals(1, basket.getLineItems().get(Assist.product1.getCode()).getQty());
		assertEquals(10, basket.getLineItems().get(Assist.product2.getCode()).getQty());
	}
	
	@Test
	public void testBasketRemoveLineItemWithSameQty() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.product1, 10));
		basket.addLineItem(new LineItem(Assist.product2, 10));
		
		basket.removeLineItem(new LineItem(Assist.product1, 10));
		
		assertEquals(1, basket.getKeys().size());
		assertEquals(1, basket.getLineItems().size());
		assertEquals(10, basket.getLineItems().get(Assist.product2.getCode()).getQty());
	}
	
	@Test
	public void testBasketRemoveLineItemWithMoreQty() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.product1, 10));
		basket.addLineItem(new LineItem(Assist.product2, 10));

		try {
			basket.removeLineItem(new LineItem(Assist.product1, 11));
			fail("IllegalStateException is expected");
		} catch (IllegalStateException e) {}
		
		assertEquals(2, basket.getKeys().size());
		assertEquals(2, basket.getLineItems().size());
		assertEquals(10, basket.getLineItems().get(Assist.product1.getCode()).getQty());
		assertEquals(10, basket.getLineItems().get(Assist.product2.getCode()).getQty());
	}

	@Test
	public void testBasketRemoveLineItemWhichDoesNotExist() {
		Basket basket = new Basket();
		basket.addLineItem(new LineItem(Assist.product1, 10));
		basket.addLineItem(new LineItem(Assist.product2, 10));
		
		try {
			basket.removeLineItem(new LineItem(Assist.product3, 1));
			fail("IllegalStateException is expected");
		} catch (IllegalStateException e) {}
		
		assertEquals(2, basket.getKeys().size());
		assertEquals(2, basket.getLineItems().size());
		assertEquals(10, basket.getLineItems().get(Assist.product1.getCode()).getQty());
		assertEquals(10, basket.getLineItems().get(Assist.product2.getCode()).getQty());
	}
	
}
