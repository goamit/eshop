package com.eshop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eshop.inventory.Group;
import com.eshop.inventory.Product;

public class Assist {

	public static Group group = new Group("FRU", "Fruit", false);
	
	public static Product product1 = new Product("APP", "Apple", group, 0.50);
	public static Product product2 = new Product("PEA", "Peach", group, 0.75);
	public static Product product3 = new Product("MAN", "Mango", group, 1.50);
	
	public static List<String> keys = Arrays.asList(product1.getDesc(), product2.getDesc(), product3.getDesc());
	public static Map<String, List<Product>> productListMap = new HashMap<String, List<Product>>();
	public static Map<String, Product> productMap = new HashMap<String, Product>();

	static {
		productListMap.put(product1.getDesc(), Arrays.asList(product1));
		productListMap.put(product2.getDesc(), Arrays.asList(product2));
		productListMap.put(product3.getDesc(), Arrays.asList(product3));
		
		productMap.put(product1.getDesc(), product1);
		productMap.put(product2.getDesc(), product2);
		productMap.put(product3.getDesc(), product3);
	}
	
}
