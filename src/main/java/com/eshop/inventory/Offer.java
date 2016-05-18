package com.eshop.inventory;

import com.eshop.util.Format;

/**
 * Represent an immutable offer/discount model
 * @author goamit
 */
public final class Offer {

	private final Product product;
	private final OfferType type;
	private final int qty;
	private final double price;
	
	public Offer(Product product, OfferType type) {
		this(product, type, 0, 0);
	}
	
	public Offer(Product product, OfferType type, int qty, double price) {
		super();
		this.product = product;
		this.type = type;
		this.qty = qty;
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public OfferType getType() {
		return type;
	}

	public int getQty() {
		return qty;
	}

	public double getPrice() {
		return price;
	}

	public String getDesc() {
		return type.getDesc() + " " + qty + " for " + Format.format(price);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + qty;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (qty != other.qty)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [product=" + product + ", type=" + type + ", qty=" + qty
				+ ", price=" + price + "]";
	}

}
