package com.eshop.storefront;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.eshop.Assist;

public class LineItem_Test {

	@Test
	public void testLineItemAddQty() {
		LineItem lineItem = new LineItem(Assist.price1);

		assertEquals(1, lineItem.getQty());
		
		lineItem.add(3);
		
		assertEquals(4, lineItem.getQty());
	}
	
	@Test
	public void testLineItemPricing() {
		LineItem lineItem = new LineItem(Assist.price1);
		assertFalse(lineItem.hasOffers());

		lineItem.compute();

		assertEquals(1, lineItem.getQty());
		assertEquals(0.50, lineItem.getTotal(), 0.0);
	}
	
	@Test
	public void testLineItemPricingWithOffer() {
		LineItem lineItem = new LineItem(Assist.price1, Arrays.asList(Assist.offer1));
		assertTrue(lineItem.hasOffers());
		
		lineItem.compute();

		assertEquals(1, lineItem.getQty());
		assertEquals(0.50, lineItem.getTotal(), 0.0);
		
		lineItem.add(2);

		lineItem.compute();

		assertEquals(3, lineItem.getQty());
		assertEquals(1.40, lineItem.getTotal(), 0.0);
	}
	
	@Test
	public void testLineItemPricingWithOfferApplicableMultipleTimes() {
		LineItem lineItem = new LineItem(Assist.price1, Arrays.asList(Assist.offer1));

		lineItem.compute();

		assertEquals(1, lineItem.getQty());
		assertEquals(0.50, lineItem.getTotal(), 0.0);
		
		lineItem.add(3);

		lineItem.compute();

		assertEquals(4, lineItem.getQty());
		assertEquals(1.80, lineItem.getTotal(), 0.0);
	}
	
	@Test
	public void testLineItemPricingWithMultipleOffers() {
		LineItem lineItem = new LineItem(Assist.price2, Arrays.asList(Assist.offer2, Assist.offer3));

		lineItem.compute();

		assertEquals(1, lineItem.getQty());
		assertEquals(0.75, lineItem.getTotal(), 0.0);
		
		lineItem.add(4);

		lineItem.compute();

		assertEquals(5, lineItem.getQty());
		assertEquals(2.95, lineItem.getTotal(), 0.0);
	}
	
}
