package com.project.isc.iscdbserver.service.game.golden;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.iscapp.common.util.CommonUtil;

public class FgfCardHand extends Poker {
	public static Map<Integer, String> HAND_TYPES = new HashMap<Integer, String>();
	/**
	 * 单散牌
	 */
	public static int HAND_TYPE_VALUE_DSP = 1;
	/**
	 * 对子牌
	 */
	public static int HAND_TYPE_VALUE_DZP = 2;
	/**
	 * 乱顺牌
	 */
	public static int HAND_TYPE_VALUE_LSP = 3;
	/**
	 * 乱金牌
	 */
	public static int HAND_TYPE_VALUE_LJP = 4;
	/**
	 * 顺金牌
	 */
	public static int HAND_TYPE_VALUE_SJP = 5;
	/**
	 * 豹子牌
	 */
	public static int HAND_TYPE_VALUE_BZP = 6;

	static {
		HAND_TYPES.put(1, "单散牌");
		HAND_TYPES.put(2, "对子牌");
		HAND_TYPES.put(3, "乱顺牌");
		HAND_TYPES.put(4, "乱金牌");
		HAND_TYPES.put(5, "顺金牌");
		HAND_TYPES.put(6, "豹子牌");
	}
	private Poker poker1;
	private Poker poker2;
	private Poker poker3;
	// 通过在构造器中调用getCardsTypeFun赋值，暴露getCardsType，删除setCardsType
	private int cardsType; // 牌型 比如豹子、金花等等

	// 构造器：将三种随机发的牌进行排序后赋给当前类的三个牌实例变量
	public FgfCardHand(Poker poker1, Poker poker2, Poker poker3) {

		List<Poker> pokers = new ArrayList<Poker>();
		pokers.add(poker1);
		pokers.add(poker2);
		pokers.add(poker3);
		Collections.sort(pokers);
		
		this.poker1 = pokers.get(0);
		this.poker2 = pokers.get(1);
		this.poker3 = pokers.get(2);

		this.cardsType = getCardsTypeFun();

	}

	
	
	/**
	 * 获取当前对象牌型的方法  
	 * 
	 * @return
	 */
	public int getCardsTypeFun() {
		// 1:单张牌 2:对子牌3:乱顺牌4:乱金牌5:顺金牌6:豹子牌
		// 判断是否是豹子牌型
		if (this.poker1.getPointValue() == this.poker2.getPointValue()
				&& this.poker2.getPointValue() == this.poker3.getPointValue()) {

			return HAND_TYPE_VALUE_BZP;
		}
		// 判断是否是金牌
		if (this.poker1.getTypeValue() == this.poker2.getTypeValue()
				&& this.poker2.getTypeValue() == this.poker3.getTypeValue()) {
			// 是否是顺金
			if (this.poker3.getPointValue() - this.poker2.getPointValue() == 1
					&& this.poker2.getPointValue()
							- this.poker1.getPointValue() == 1) {
				return HAND_TYPE_VALUE_SJP;
			}
			// 乱金牌
			else {
				return HAND_TYPE_VALUE_LJP;
			}
		} else {
			// 是否是乱顺牌
			if (this.poker3.getPointValue() - this.poker2.getPointValue() == 1
					&& this.poker2.getPointValue()
							- this.poker1.getPointValue() == 1) {
				return HAND_TYPE_VALUE_LSP;
			} else {
				// 是否是对子牌
				if (this.poker1.getPointValue() == this.poker2.getPointValue()
						|| this.poker2.getPointValue() == this.poker3
								.getPointValue()
						|| this.poker1.getPointValue() == this.poker3
								.getPointValue()) {
					return HAND_TYPE_VALUE_DZP;
				}
				// 其他的都是单张牌
				else {
					return HAND_TYPE_VALUE_DSP;
				}
			}
		}
	}

	public Poker getPoker1() {
		return poker1;
	}

	public void setPoker1(Poker poker1) {
		this.poker1 = poker1;
	}

	public Poker getPoker2() {
		return poker2;
	}

	public void setPoker2(Poker poker2) {
		this.poker2 = poker2;
	}

	public Poker getPoker3() {
		return poker3;
	}

	public void setPoker3(Poker poker3) {
		this.poker3 = poker3;
	}

	public int getCardsType() {
		return cardsType;
	}

	/*
	 * 
	 * 开始比赛  传入三张牌  返回1 玩家1 胜利
	 * 
	 */
	public static int playGames(String poker001,String poker002) {
		FgfCardHand fgf1 = FgfCardHand.GetCardHand(poker001);
		System.out.println("玩家1的牌型:"+HAND_TYPES.get(fgf1.getCardsType()));
		FgfCardHand fgf2 = FgfCardHand.GetCardHand(poker002);
		System.out.println("玩家2的牌型:"+HAND_TYPES.get(fgf2.getCardsType()));
		// 首先判断牌型
		if (fgf1.getCardsType() > fgf2.getCardsType())
			return 1;
		if (fgf1.getCardsType() < fgf2.getCardsType())
			return -1;

		// 如果牌型相同

		// 比较豹子牌：只要看任意一张牌的点数就可以
		if (fgf1.getCardsType() == FgfCardHand.HAND_TYPE_VALUE_BZP) {
			if (fgf1.getPoker1().getPointValue() > fgf2.getPoker1()
					.getPointValue())
				return 1;
			if (fgf1.getPoker1().getPointValue() < fgf2.getPoker1()
					.getPointValue())
				return -1;
		}

		// 比较顺金牌：比较最大的那种牌的点数
		if (fgf1.getCardsType() == FgfCardHand.HAND_TYPE_VALUE_SJP) {
			if (fgf1.getPoker3().getPointValue() > fgf2.getPoker3()
					.getPointValue())
				return 1;
			if (fgf1.getPoker3().getPointValue() < fgf2.getPoker3()
					.getPointValue())
				return -1;
		}

		// 比较乱金牌：从3-1分别比较
		if (fgf1.getCardsType() == FgfCardHand.HAND_TYPE_VALUE_LJP) {
			if (fgf1.getPoker3().getPointValue() > fgf2.getPoker3()
					.getPointValue())
				return 1;
			if (fgf1.getPoker3().getPointValue() < fgf2.getPoker3()
					.getPointValue())
				return -1;

			if (fgf1.getPoker2().getPointValue() > fgf2.getPoker2()
					.getPointValue())
				return 1;
			if (fgf1.getPoker2().getPointValue() < fgf2.getPoker2()
					.getPointValue())
				return -1;

			if (fgf1.getPoker1().getPointValue() > fgf2.getPoker1()
					.getPointValue())
				return 1;
			if (fgf1.getPoker1().getPointValue() < fgf2.getPoker1()
					.getPointValue())
				return -1;
		}

		// 比较乱顺牌：比较最大的那种牌的点数
		if (fgf1.getCardsType() == FgfCardHand.HAND_TYPE_VALUE_LSP) {
			if (fgf1.getPoker3().getPointValue() > fgf2.getPoker3()
					.getPointValue())
				return 1;
			if (fgf1.getPoker3().getPointValue() < fgf2.getPoker3()
					.getPointValue())
				return -1;
		}

		// 比较对子牌：获得对子的牌点，然后如果对子牌点一样，要比较单张牌点
		if (fgf1.getCardsType() == FgfCardHand.HAND_TYPE_VALUE_DZP) {
			if (fgf1.getPoker2().getPointValue() > fgf2.getPoker2()
					.getPointValue())
				return 1;
			if (fgf1.getPoker2().getPointValue() < fgf2.getPoker2()
					.getPointValue())
				return -1;
			int fgf1Dp = 0;
			int fgf2Dp = 0;

			if (fgf1.getPoker1().getPointValue() == fgf1.getPoker2()
					.getPointValue()) {
				fgf1Dp = fgf1.getPoker3().getPointValue();
			} else {
				fgf1Dp = fgf1.getPoker1().getPointValue();
			}

			if (fgf2.getPoker1().getPointValue() == fgf2.getPoker2()
					.getPointValue()) {
				fgf2Dp = fgf2.getPoker3().getPointValue();
			} else {
				fgf2Dp = fgf2.getPoker1().getPointValue();
			}

			if (fgf1Dp > fgf2Dp)
				return 1;
			if (fgf1Dp < fgf2Dp)
				return -1;
		}

		// 比较单顺牌：从3-1分别比较
		if (fgf1.getCardsType() == FgfCardHand.HAND_TYPE_VALUE_DSP) {
			if (fgf1.getPoker3().getPointValue() > fgf2.getPoker3()
					.getPointValue())
				return 1;
			if (fgf1.getPoker3().getPointValue() < fgf2.getPoker3()
					.getPointValue())
				return -1;

			if (fgf1.getPoker2().getPointValue() > fgf2.getPoker2()
					.getPointValue())
				return 1;
			if (fgf1.getPoker2().getPointValue() < fgf2.getPoker2()
					.getPointValue())
				return -1;

			if (fgf1.getPoker1().getPointValue() > fgf2.getPoker1()
					.getPointValue())
				return 1;
			if (fgf1.getPoker1().getPointValue() < fgf2.getPoker1()
					.getPointValue())
				return -1;
		}

		return 0;
	}

	/*
	 * 
	 * 发牌给与顾客3张手牌
	 * 
	 */
	public static String getPoker(){
		// 获取第一张手牌
		//--获取花色
		int j = (int) Math.ceil(Math.random()*4);
		//--获取牌数
		int k = (int) Math.ceil(Math.random()*13);
		String tp1 = j +""+ k;

		// 获取第二张手牌
		j = (int) Math.ceil(Math.random()*4);
	    k = (int) Math.ceil(Math.random()*13);
	    String tp2 = j +""+ k;
		// 获取第三张手牌
	    j = (int) Math.ceil(Math.random()*4);
	    k = (int) Math.ceil(Math.random()*13);
		String tp3 = j +""+ k;
		return tp1+"|"+tp2+"|"+tp3;
	}
	
	/*
	 * 
	 * 解析顾客手中的牌  并且服务对象FgfCardHand 上，方便比较
	 * 
	 */
	public static FgfCardHand GetCardHand(String Pokers){
		
		String[] poker=Pokers.split("\\|");
		int typepoint=CommonUtil.toInt32(poker[0].substring(0, 1))-1;
		int pointint=CommonUtil.toInt32(poker[0].substring(1))-1;
		String tp1 = TYPES[typepoint] + POINTS[pointint];
		
		 typepoint=CommonUtil.toInt32(poker[1].substring(0, 1))-1;
		 pointint=CommonUtil.toInt32(poker[1].substring(1))-1;
		String tp2 = TYPES[typepoint] + POINTS[pointint];
		
		typepoint=CommonUtil.toInt32(poker[2].substring(0, 1))-1;
		 pointint=CommonUtil.toInt32(poker[2].substring(1))-1;
		String tp3 = TYPES[typepoint] + POINTS[pointint];
		
		FgfCardHand fgf1 = new FgfCardHand(new Poker(tp1), new Poker(tp2), new Poker(tp3));
		
		return fgf1;
	}
	
	
	/*
	 * 
	 * 解析顾客手中的牌  并且服务对象FgfCardHand 上，方便比较
	 * 
	 */
	public static String QueyCardHand(String Pokers){
		
		String[] poker=Pokers.split("\\|");
		int typepoint=CommonUtil.toInt32(poker[0].substring(0, 1))-1;
		int pointint=CommonUtil.toInt32(poker[0].substring(1))-1;
		String tp1 = TYPES[typepoint] + POINTS[pointint];
		
		 typepoint=CommonUtil.toInt32(poker[1].substring(0, 1))-1;
		 pointint=CommonUtil.toInt32(poker[1].substring(1))-1;
		String tp2 = TYPES[typepoint] + POINTS[pointint];
		
		typepoint=CommonUtil.toInt32(poker[2].substring(0, 1))-1;
		 pointint=CommonUtil.toInt32(poker[2].substring(1))-1;
		String tp3 = TYPES[typepoint] + POINTS[pointint];				
		
		return tp1+"|"+tp2+"|"+tp3;
	}
	
	public static void main(String[] args) {
		
		
		for (int i = 0; i < 20; i++) {
			String play1poler=FgfCardHand.getPoker();
			String play2poler=FgfCardHand.getPoker();
			System.out.println("玩家1的扑克点数:"+play1poler);
			System.out.println("玩家2的扑克点数:"+play2poler);
			System.out.println("玩家1的扑克牌:"+FgfCardHand.QueyCardHand(play1poler));
			System.out.println("玩家2的扑克牌:"+FgfCardHand.QueyCardHand(play2poler));
			
			int result=FgfCardHand.playGames(play1poler, play2poler);
			if(result==1){
				System.out.println("玩家1赢了");
			}else{
				System.out.println("玩家2赢了");
			}
		}
		
	
	}

}
