package com.eshop.inventory;

/**
 * Placeholder for immutable tax associations with products or group codes as key
 * @author goamit
 */
public final class Tax {

	private final String key;
	private final Operation operation;
	private final boolean active;
	
	public Tax(String key, Operation operation, boolean active) {
		this.key = key;
		this.operation = operation;
		this.active = active;
	}

	public String getKey() {
		return key;
	}

	public Operation getOperation() {
		return operation;
	}

	public boolean isActive() {
		return active;
	}

}
