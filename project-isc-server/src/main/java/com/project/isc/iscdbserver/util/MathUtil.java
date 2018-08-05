package com.project.isc.iscdbserver.util;

import java.util.List;

public class MathUtil {

	public static List<Integer> getDateCycle() {
		return null;
	}

	public static void main(String[] args) {
		double d = 12.12321321;
		System.out.println((int)(Math.floor(d)));
		System.out.println(Math.ceil(d));
	}
	
	public static long floor(double value) {
		return (long)(Math.floor(value));
	}
	
	public static long ceil(double value) {
		return (long)(Math.ceil(value));
	}
	
}
