package com.eshop.inventory;

/**
 * Represent an immutable product model available for shoppings
 * @author goamit
 */
public final class Product {

	private final String code;
	private final String desc;
	private final Group group;
	private final double price;
	
	public Product(String code, String desc, Group group, double price) {
		this.code = code;
		this.desc = desc;
		this.group = group;
		this.price = price;
	}

	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public Group getGroup() {
		return group;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", desc=" + desc + ", group=" + group
				+ ", price=" + price + "]";
	}
	
}
