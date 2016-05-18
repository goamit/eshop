package com.eshop.storefront;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eshop.inventory.Offer;
import com.eshop.inventory.OfferType;
import com.eshop.inventory.Price;
import com.eshop.inventory.Product;
import com.eshop.util.Format;

/**
 * Represent model for shopping cart line item and holds logic for line item level computation
 * @author goamit
 */
public class LineItem {

	private Price price;
	private List<Offer> offers;
	private int qty;
	private double total;
	private List<String> notes;
	
	public LineItem(Price price) {
		this(price, null);
	}
	
	public LineItem(Price price, List<Offer> offers) {
		this.price = price;
		this.offers = offers;
		this.qty = 1;
		this.total = 0;
		this.notes = new ArrayList<String>();
	}
	
	public Product getProduct() {
		return price.getProduct();
	}

	public Price getPrice() {
		return price;
	}
	
	public int getQty() {
		return qty;
	}

	public void add(int qty) {
		this.qty = this.qty + qty;
	}
	
	public double getTotal() {
		return total;
	}
	
	public List<String> getNotes() {
		return notes;
	}

	public void addNote(String note) {
		this.notes.add(note);
	}
	
	public boolean hasOffers() {
		return offers != null && !offers.isEmpty();
	}

	public void compute() {
		notes.clear();
		total = 0;
		int targetQty = getQty();
		//check offers if any
		if(hasOffers()) {
			//sort offers highest qty first
			Collections.sort(offers, (o1, o2) -> o2.getQty() - o1.getQty());
			for (Offer offer : offers) {
				if(OfferType.MBP.equals(offer.getType())) {
					if(targetQty >= offer.getQty()) {
						//calculate savings
						int factor = targetQty / offer.getQty();
						int effectedQty = factor * offer.getQty();
						double effectedPrice = factor * offer.getPrice();
						targetQty -= effectedQty; 
						total += effectedPrice;
						//add saving note
						addNote(offer.getDesc() + " savings " + Format.format((effectedQty * price.getUnitPrice() - effectedPrice)));
					} else {
						break;
					}
				}
			}
		}
		//calculate price of remaining quantity if any 
		total += targetQty * price.getUnitPrice();
	}
	
}
