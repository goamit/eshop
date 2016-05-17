package com.eshop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eshop.inventory.Offer;
import com.eshop.inventory.OfferType;
import com.eshop.inventory.Price;
import com.eshop.inventory.Product;

public class Assist {

	public static Product product1 = new Product("A");
	public static Product product2 = new Product("B");
	public static Product product3 = new Product("C");
	
	public static Price price1 = new Price(product1, 0.50);
	public static Price price2 = new Price(product2, 0.75);
	public static Price price3 = new Price(product3, 1.50);

	public static Offer offer1 = new Offer(product1, OfferType.MBP, 2, 0.90);
	public static Offer offer2 = new Offer(product2, OfferType.MBP, 3, 1.65);
	public static Offer offer3 = new Offer(product2, OfferType.MBP, 2, 1.30);
	
	
	public static List<String> keys = Arrays.asList(product1.getName(), product2.getName(), product3.getName());
	public static Map<String, Price> priceMap = new HashMap<String, Price>();
	public static Map<String, List<Offer>> offersMap = new HashMap<String, List<Offer>>();

	static {
		priceMap.put(product1.getName(), price1);
		priceMap.put(product2.getName(), price2);
		priceMap.put(product3.getName(), price3);

		offersMap.put(product1.getName(), Arrays.asList(offer1));
		offersMap.put(product2.getName(), Arrays.asList(offer2));
		offersMap.put(product3.getName(), Arrays.asList(offer3));
	}
		
}
