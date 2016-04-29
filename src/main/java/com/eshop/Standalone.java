package com.eshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.eshop.service.Request;
import com.eshop.service.Request.ActionType;

/**
 * Main class to demonstrate shopping basket tool taking inputs through console.
 * 'exit' to quit program
 * 'clear' to refresh basket
 * 'view' search inventory for matching items, or display basket if no items provided
 * qty default to 1 if not provided
 * Follow usage options, displayed at runtime, to explore functionality. 
 * @author goamit
 */
public class Standalone {

	private static String USERID = "demo";
	
	public static void main( String[] args ) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("standalone eshop started..");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("* 'exit' to quit program");
		System.out.println("* 'clear' to refresh basket");
		System.out.println("* 'view' search inventory for matching items, or display basket if no items provided");
		System.out.println("*  qty default to 1 if not provided");
		System.out.println("------------------------------------------------------------------------------------");
		while(true) {
			System.out.print("view/add/rem item1[=qty1] [item2=qty2] ...");
			String input = scanner.nextLine().trim();
			if("exit".equalsIgnoreCase(input)) {
				break;
			} else if("clear".equalsIgnoreCase(input)) {
				Request.clear(USERID);
			} else {
				try {
					process(input);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		System.out.println("exiting.");
		scanner.close();
    }
	
	public static void process(String input) {
		//validate input
		List<String> items = new ArrayList<String>();
		String[] tokens = input.split(" ");
		for (String token : tokens) {
			if(!token.isEmpty()) {
				items.add(token);
			}
		}
		
		ActionType action;
		try {
			action = ActionType.valueOf(items.get(0).toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid action: " + items.get(0));
		}

		Request request = new Request(USERID, action);

		if(items.size() > 1) {
			for (int i = 1; i < items.size(); i++) {
				String item = items.get(i);
				try {
					String key = item;
					Long qty = 1l;
					if(item.contains("=")) {
						String[] keyval = item.split("=", 2);
						key = keyval[0];
						qty = Long.parseLong(keyval[1]);
					}
					if(qty <= 0) {
						throw new IllegalArgumentException("Invalid input " + i + " qty: " + item + " should be > 0");
					}
					request.addItem(key, qty);
				} catch (Exception e) {
					throw new IllegalArgumentException("Invalid input " + i + ": " + item);
				}
			}
	
			//process request
			request.process();
			
			//print output
			for (String key : request.getKeys()) {
				if(request.getResponseMap().containsKey(key)) {
					Object obj = request.getResponseMap().get(key);
					if(obj instanceof Collection) {
						@SuppressWarnings("rawtypes")
						Collection collection = (Collection) obj;
						for (Object item : collection) {
							System.out.println(item);
						}
					} else {
						System.out.println(obj);
					}
				}
			}
		} else if(!ActionType.VIEW.equals(action)) {
			throw new IllegalArgumentException("No input provided");
		}
		
		if(!ActionType.VIEW.equals(action) || items.size() == 1) {
			System.out.println(request.getBasket());
		}
	}
	
}
