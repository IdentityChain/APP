package com.project.isc.iscdbserver.util;

import com.project.isc.iscdbserver.statusType.UserLevel;

public class UserLevelUtil {
	
	private UserLevelUtil() {}
	
	public static String transfer(String userLevel) {
		String userLevelDesc;
		
		switch(userLevel) {
			case UserLevel.CONSUMER:
				userLevelDesc = UserLevel.CONSUMER_CH;
				break;
			case UserLevel.COMMON_SALEMAN:
				userLevelDesc = UserLevel.COMMON_SALEMAN_CH;
				break;
			case UserLevel.GROUP_LEADER:
				userLevelDesc = UserLevel.GROUP_LEADER_CH;
				break;
			case UserLevel.DIRECOTR:
				userLevelDesc = UserLevel.DIRECOTR_CH;
				break;
			case UserLevel.MANAGEAR:
				userLevelDesc = UserLevel.MANAGEAR_CH;
				break;
			case UserLevel.BRANCH_MANAGER:
				userLevelDesc = UserLevel.BRANCH_MANAGER_CH;
				break;
			case UserLevel.ADVANCE_MANAGER:
				userLevelDesc = UserLevel.ADVANCE_MANAGER_CH;
				break;
			case UserLevel.MARKET_DIRECTOR:
				userLevelDesc = UserLevel.MARKET_DIRECTOR_CH;
				break;
			case UserLevel.ADVANCED_DIRECTOR:
				userLevelDesc = UserLevel.ADVANCED_DIRECTOR_CH;
				break;
			case UserLevel.BOARD_DIRECOTR:
				userLevelDesc = UserLevel.BOARD_DIRECOTR_CH;
				break;
			case UserLevel.EXECUTIVE_PRESIDEANT:
				userLevelDesc = UserLevel.EXECUTIVE_PRESIDEANT_CH;
				break;
			case UserLevel.PRESIDEANT:
				userLevelDesc = UserLevel.PRESIDEANT_CH;
				break;
			case UserLevel.CROWN:
				userLevelDesc = UserLevel.CROWN_CH;
				break;
			case UserLevel.CROWN_AMBASSADOR:
				userLevelDesc = UserLevel.CROWN_AMBASSADOR_CH;
				break;
			default:
				throw new RuntimeException("级别不正确");
		}
		
		return userLevelDesc;
	}
	
	public static void main(String[] args) {
		System.out.println(transfer("NNN"));
	}
	
}
