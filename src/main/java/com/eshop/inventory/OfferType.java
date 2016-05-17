package com.eshop.inventory;

public enum OfferType {

	MBP("Multibuy price");
	
	private final String desc;

	private OfferType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
}
