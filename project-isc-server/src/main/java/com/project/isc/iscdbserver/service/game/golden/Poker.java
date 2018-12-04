package com.project.isc.iscdbserver.service.game.golden;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poker implements Comparable<Poker> {

	public static String[] TYPES = { "♥", "♠", "♦", "♣" }; // 所有花se数组
	public static String[] POINTS = { "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "J", "Q", "K", "A" }; // 所有点数数组
	public static Map<String, Integer> TYPES_VALUES = new HashMap<String, Integer>(); // 花se权值
	public static Map<String, Integer> POINTS_VALUES = new HashMap<String, Integer>(); // 点数权值

	static {
		TYPES_VALUES.put("♥", 1);//黑桃
		TYPES_VALUES.put("♠", 2);//红桃
		TYPES_VALUES.put("♦", 3);//方块
		TYPES_VALUES.put("♣", 4);//米快
		
		

		POINTS_VALUES.put("2", 1);
		POINTS_VALUES.put("3", 2);
		POINTS_VALUES.put("4", 3);
		POINTS_VALUES.put("5", 4);
		POINTS_VALUES.put("6", 5);
		POINTS_VALUES.put("7", 6);
		POINTS_VALUES.put("8", 7);
		POINTS_VALUES.put("9", 8);
		POINTS_VALUES.put("10", 9);
		POINTS_VALUES.put("J", 10);
		POINTS_VALUES.put("Q", 11);
		POINTS_VALUES.put("K", 12);
		POINTS_VALUES.put("A", 13);
	}
	public Poker() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 花色
	 */
	private String type;
	/**
	 * 牌点
	 */
	private String point;

	/**
	 * ♠10  
	 * 
	 * @param tp
	 */
	public Poker(String tp) {
		this.type = tp.substring(0, 1); // 给花se赋值
		this.point = tp.substring(1); // 给点数赋值
	}
	
	/**
	 * 获取花色权值  
	 * 
	 * @return
	 */
	public int getTypeValue() {
		return TYPES_VALUES.get(this.type);
	}

	/**
	 * 获取点数权值  
	 * 
	 * @return
	 */
	public int getPointValue() {
		return POINTS_VALUES.get(this.point);
	}

	@Override
	public String toString() {
		String rs = this.getType() + this.getPoint();
		if (rs.length() == 2)
			rs = rs + " ";
		return rs;
	}

	public int compareTo(Poker o) {
		// 先比点数，点数权值大则，小则小，相同点数，比花se权值
		if (o.getPointValue() > this.getPointValue())
			return -1;
		if (o.getPointValue() < this.getPointValue())
			return 1;

		if (o.getTypeValue() > this.getTypeValue())
			return -1;
		if (o.getTypeValue() < this.getTypeValue())
			return 1;

		return 0;
	}

	/****************************
	 * * Getters and Setters
	 **************************************/
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public static void main(String[] args) {
		List<Poker> ps = new ArrayList<Poker>();

		for (int i = 0; i < 20; i++) {
			int j = (int) (Math.random() * 4);
			int k = (int) (Math.random() * 13);
			String tp = TYPES[j] + POINTS[k];
			ps.add(new Poker(tp));
		}
		// Collections.sort(ps);
		for (Poker p : ps) {
			System.out.println(p);
		}

	}
}
