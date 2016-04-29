package com.eshop.storefront;

import java.util.ArrayList;
import java.util.List;

import com.eshop.inventory.Product;
import com.eshop.util.Format;

/**
 * Represent model for shopping cart line item
 * @author goamit
 */
public class LineItem {

	private Product product;
	private long qty;
	private List<String> notes;
	private double total;
	
	public LineItem(Product product, long qty) {
		this.product = product;
		this.qty = qty;
		this.notes = new ArrayList<String>();
		this.total = 0;
	}
	
	public Product getProduct() {
		return product;
	}

	public long getQty() {
		return qty;
	}

	public void add(long qty) {
		this.qty = this.qty + qty;
	}
	
	public void remove(long qty) {
		if(this.qty < qty) {
			throw new IllegalStateException("Qty to remove " + qty + " is more than available " + this.qty);
		}
		this.qty = this.qty - qty;
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

	private final static String COLSEP = "\t";
			
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(product.getCode()).append(COLSEP);
		sb.append(product.getDesc()).append(COLSEP);
		sb.append(qty).append(COLSEP);
		sb.append(Format.format(product.getPrice())).append(COLSEP);
		sb.append(Format.format(total)).append(COLSEP);
		for (String note : notes) {
			sb.append("\n").append(note);
		}
		return sb.toString();
	}
	
}
