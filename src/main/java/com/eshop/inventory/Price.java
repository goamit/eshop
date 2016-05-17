package com.eshop.inventory;

/**
 * Represent an immutable price model
 * @author goamit
 */
public final class Price {

	private final Product product;
	private final double unitPrice;
	
	public Price(Product product, double unitPrice) {
		this.product = product;
		this.unitPrice = unitPrice;
	}

	public Product getProduct() {
		return product;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Price other = (Price) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Price [product=" + product + ", unitPrice=" + unitPrice + "]";
	}

}
