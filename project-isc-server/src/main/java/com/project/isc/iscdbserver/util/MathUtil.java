package com.project.isc.iscdbserver.util;

import java.util.List;

public class MathUtil {

	public static List<Integer> getDateCycle() {
		return null;
	}

	public static void main(String[] args) {
		double d = 12.12321321;
		for(int i = 0;i<100;i++){
			System.out.println(i+":"+redomZeroOrOne());
		}

		System.out.println((int)(Math.floor(d)));
		System.out.println(Math.ceil(d));
	}

	public static int redomZeroOrOne(){
		double a = Math.random();
		if(a<0.3){
			return 1;
		}
		return 0;
	}
	
	public static long floor(double value) {
		return (long)(Math.floor(value));
	}
	
	public static long ceil(double value) {
		return (long)(Math.ceil(value));
	}
	
}
