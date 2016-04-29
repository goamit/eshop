package com.eshop.inventory;

/**
 * Placeholder for immutable group model , useful to hold any feature at a product group level e.g. to make all fruits tax exempted. 
 * @author goamit
 */
public final class Group {

	private final String code;
	private final String desc;
	private final boolean taxExempt;
	
	public Group(String code, String desc, boolean taxExempt) {
		this.code = code;
		this.desc = desc;
		this.taxExempt = taxExempt;
	}

	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public boolean isTaxExempt() {
		return taxExempt;
	}

	@Override
	public String toString() {
		return "Group [code=" + code + ", desc=" + desc + ", taxExempt="
				+ taxExempt + "]";
	}

}
