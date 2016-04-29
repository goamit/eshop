package com.eshop.storefront;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eshop.util.Format;

/**
 * Represent model for shopping cart
 * @author goamit
 */
public class Basket {

	private List<String> keys;
	private Map<String, LineItem> lineItems;
	private List<String> notes;
	private double total;

	public Basket() {
		this.keys = new ArrayList<String>();
		this.lineItems = new HashMap<String, LineItem>();
		this.notes = new ArrayList<String>();
		this.total = 0;
	}
	
	public List<String> getKeys() {
		return keys;
	}

	public Map<String, LineItem> getLineItems() {
		return lineItems;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void addNote(String note) {
		this.notes.add(note);
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void addLineItem(LineItem lineItem) {
		String key = lineItem.getProduct().getCode();
		if(keys.contains(key)) {
			lineItems.get(key).add(lineItem.getQty());
		} else {
			keys.add(key);
			lineItems.put(key, lineItem);
		}
	}
	
	public void removeLineItem(LineItem lineItem) {
		String key = lineItem.getProduct().getCode();
		if(keys.contains(key)) {
			lineItems.get(key).remove(lineItem.getQty());
			if(lineItems.get(key).getQty() == 0) {
				keys.remove(key);
				lineItems.remove(key);
			}
		} else {
			throw new IllegalStateException(key + " does not exist in basket");
		}
	}
	
	private final static String COLSEP = "\t";
	private final static String BORDER_LINES = "================================================================";
	private final static String MIDDLE_LINES = "----------------------------------------------------------------";
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(BORDER_LINES).append("\n");
		if(keys.isEmpty()) {
			sb.append("Basket is empty !").append("\n");
		} else {
			sb.append("Basket").append("\n");
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i);
				sb.append(i + 1).append(")").append(COLSEP).append(lineItems.get(key)).append("\n");
			}
			sb.append(MIDDLE_LINES).append("\n");
			sb.append("SubTotal").append(COLSEP).append(Format.format(total)).append("\n");
			for (String note : notes) {
				sb.append(note).append("\n");
			}
		}
		sb.append(BORDER_LINES);
		return sb.toString();
	}
	
}
