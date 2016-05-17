package com.eshop.inventory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Represent methods expected in an inventory implementation 
 * @author goamit
 */
public interface Inventory {

	public Map<String, Price> lookupPrices(Collection<String> items);
	
	public Map<String, List<Offer>> lookupOffers(Collection<String> items);
	
}
