package com.eshop.util;

import java.text.DecimalFormat;

/**
 * Utility to hold consistent formating across module 
 * @author goamit
 */
public class Format {

	private static final DecimalFormat AMTFORMAT = new DecimalFormat("£###,##0.00");
	
	public static String format(double amt) {
		return AMTFORMAT.format(amt);
	}
	
}
