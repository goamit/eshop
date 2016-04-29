package com.eshop.inventory;

/**
 * Placeholder for immutable operation definition, useful to define attributes to customise arithmetic calculations  
 * @author goamit
 */
public final class Operation {

	private final String code;
	private final String desc;
	private final String operator;
	private final double value;
	private final String criteria;
	
	public Operation(String code, String desc, String operator, double value, String criteria) {
		this.code = code;
		this.desc = desc;
		this.operator = operator;
		this.value = value;
		this.criteria = criteria;
	}

	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public String getOperator() {
		return operator;
	}

	public double getValue() {
		return value;
	}

	public String getCriteria() {
		return criteria;
	}

}
