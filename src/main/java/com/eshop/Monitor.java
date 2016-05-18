package com.eshop;

import com.eshop.service.Request;
import com.eshop.storefront.Basket;
import com.eshop.storefront.LineItem;
import com.eshop.util.Format;

/**
 * Represents view to display results to console.
 * @author goamit
 */
public class Monitor {

	public static void display(Request request) { 
		//print output
		for (String message : request.getMessages()) {
			System.out.println(message);
		}
		if(request.getBasket() != null) {
			System.out.println(prepare(request.getBasket()));
		}
	}
	
	private final static String COLSEP = "\t";
	private final static String BORDER_LINES = "================================================================";
	private final static String MIDDLE_LINES = "----------------------------------------------------------------";
	
	public static String prepare(Basket basket) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(BORDER_LINES).append("\n");
		if(basket.isEmpty()) {
			sb.append("Basket is empty !").append("\n");
		} else {
			sb.append("Basket").append("\n");
			int i = 1;
			for (String key : basket.getLineItems().keySet()) {
				sb.append(i++).append(")").append(COLSEP).append(prepare(basket.getLineItems().get(key))).append("\n");
			}
			sb.append(MIDDLE_LINES).append("\n");
			sb.append("Total").append(COLSEP).append(Format.format(basket.getTotal())).append("\n");
		}
		sb.append(BORDER_LINES);
		
		return sb.toString();
	}
	
	public static String prepare(LineItem lineItem) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(lineItem.getProduct().getName()).append(COLSEP);
		sb.append(lineItem.getQty()).append(COLSEP);
		sb.append(Format.format(lineItem.getPrice().getUnitPrice())).append(COLSEP);
		sb.append(Format.format(lineItem.getTotal())).append(COLSEP);
		for (String note : lineItem.getNotes()) {
			sb.append("\n").append(note);
		}
		
		return sb.toString();
	}

}
