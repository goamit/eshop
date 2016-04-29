package com.eshop.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Sample implementation of inventory for demonstration purpose, e.g. DAO to substitute this 
 * @author goamit
 */
public class SimpleInventory implements Inventory {
	
	private final static Map<String, Group> groups = new HashMap<String, Group>(); 
	private final static Map<String, Product> products = new HashMap<String, Product>();
	
	//placeholders for discounts, taxes
	private final static Map<String, Operation> operations = new HashMap<String, Operation>();
	private final static List<Discount> discounts = new ArrayList<Discount>();
	private final static List<Tax> taxes = new ArrayList<Tax>();
			
	static {
		addGroup(new Group("FRU", "Fruit", false));
		
		addProduct(new Product("LEM", "Lemon", groups.get("FRU"), 0.10));
		addProduct(new Product("BAN", "Banana", groups.get("FRU"), 0.20));
		addProduct(new Product("ORA", "Orange", groups.get("FRU"), 0.30));
		addProduct(new Product("APP", "Apple", groups.get("FRU"), 0.40));
		addProduct(new Product("PEA", "Peach", groups.get("FRU"), 0.50));
		
		//placeholders for discounts, taxes 
		addOperation(new Operation("MB2F1", "2 for 1", "PER", 50, "MULTIBUY-2"));
		addOperation(new Operation("FL10P", "10% off", "PER", 10, null));
		addOperation(new Operation("TO500A", "Total above 500", "AMT", 5, "TOTAL-500"));
		addOperation(new Operation("VAT", "VAT 20%", "PER", 20, null));
		
		discounts.add(new Discount("FRU", operations.get("FL10P"), true));
		discounts.add(new Discount("BAN", operations.get("MB2F1"), true));
		discounts.add(new Discount("ALL", operations.get("TO500A"), true));
		
		taxes.add(new Tax("ALL", operations.get("VAT"), true));
	}
	
	private static void addGroup(Group item) {
		groups.put(item.getCode(), item);
	}
	
	private static void addProduct(Product item) {
		products.put(item.getCode(), item);
	}

	private static void addOperation(Operation item) {
		operations.put(item.getCode(), item);
	}

	/**
	 * Return all matching products having given desc/s
	 */
	public Map<String, List<Product>> searchProducts(Collection<String> descs) {
		Map<String, List<Product>> result = new HashMap<String, List<Product>>();
		for (String desc : descs) {
			Supplier<Stream<Product>> filteredProducts = () -> products.values().stream().filter(p -> p.getDesc().toUpperCase().matches(".*" + desc.toUpperCase() + ".*"));
			if(filteredProducts.get().count() > 0) {
				result.put(desc, filteredProducts.get().collect(Collectors.toList()));
			}
		}
		return result;
	}
	
	/**
	 * Return products exactly match to given desc/s
	 */
	public Map<String, Product> lookupProducts(Collection<String> descs) {
		Map<String, Product> result = new HashMap<String, Product>();
		for (String desc : descs) {
			Supplier<Stream<Product>> filteredProducts = () -> products.values().stream().filter(p -> p.getDesc().toUpperCase().equals(desc.toUpperCase()));
			if(filteredProducts.get().count() > 0) {
				result.put(desc, filteredProducts.get().findFirst().get());
			}
		}
		return result;
	}

	public Collection<Discount> lookupDiscounts(Collection<Product> products) {
		// placeholder to retrieve discounts
		return null;
	}

	public Collection<Tax> lookupTaxes(Collection<Product> products) {
		// placeholder to retrieve taxes
		return null;
	}
	
}
