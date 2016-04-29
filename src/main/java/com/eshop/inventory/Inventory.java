package com.eshop.inventory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Represent methods expected in an inventory implementation 
 * @author goamit
 */
public interface Inventory {

	Map<String, List<Product>> searchProducts(Collection<String> descs);
	
	Map<String, Product> lookupProducts(Collection<String> descs);
	
	Collection<Discount> lookupDiscounts(Collection<Product> products);
	
	Collection<Tax> lookupTaxes(Collection<Product> products);
	
}
