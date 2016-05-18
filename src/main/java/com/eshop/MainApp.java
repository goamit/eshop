package com.eshop;

import java.util.Scanner;

import com.eshop.service.Request;

/**
 * Main class to demonstrate shopping cart taking inputs from console.
 * @author goamit
 */
public class MainApp {

	public static void main( String[] args ) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Shopping Cart Demo started..");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("* 'exit' to quit program");
		System.out.println("* 'item1 [item2] ...' to check out");
		System.out.println("------------------------------------------------------------------------------------");
		while(true) {
			String input = scanner.nextLine().trim();
			if(!input.isEmpty()) {
				if("exit".equalsIgnoreCase(input)) {
					break;
				} else {
					try {
						process(input);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				System.out.println("No input provided");
			}
		}
		System.out.println("exiting.");
		scanner.close();
    }
	
	public static void process(String input) {
		//prepare and submit request
		Request request = new Request();
		String[] items = input.split(" ");
		for (String item : items) {
			if(!item.isEmpty()) {
				request.addItem(item);
			}
		}
		request.process();
		Monitor.display(request);
	}
	
}
