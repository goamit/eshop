package com.eshop.storefront;

import static org.junit.Assert.*;

import org.junit.Test;
import com.eshop.Assist;

public class LineItem_Test {

	@Test
	public void testLineItemAddQty() {
		LineItem lineItem = new LineItem(Assist.product1, 2);

		lineItem.add(3);
		
		assertEquals(5, lineItem.getQty());
	}
	
	@Test
	public void testLineItemRemoveLesserOrSameQty() {
		LineItem lineItem = new LineItem(Assist.product1, 10);

		lineItem.remove(9);
		
		assertEquals(1, lineItem.getQty());
		
		lineItem.remove(1);
		
		assertEquals(0, lineItem.getQty());
	}

	@Test
	public void testLineItemRemoveMoreQty() {
		LineItem lineItem = new LineItem(Assist.product1, 10);

		try {
			lineItem.remove(11);
		} catch (IllegalStateException e) {}

		assertEquals(10, lineItem.getQty());
	}
	
}
