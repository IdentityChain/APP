package com.project.isc.iscdbserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//相关比例
@Component
public class BonusPayPercentage {
	// 管理费比例
	private static double manageCostPercentage;
	// 奖金比例
	private static double bonusNumberPercentage;
	// 产品积分比例
	private static double productCoinNumberPercentage;
	// 报单消费单价
	private static double consumeCoinUnitPrice;
	// 重销产品单价
	private static double reconsumeCoinUnitPrice;
	// 提现手续费
	private static double drawCostPercentage;

	public static double getManageCostPercentage() {
		return manageCostPercentage;
	}

	public static double getReconsumeCoinUnitPrice() {
		return reconsumeCoinUnitPrice;
	}
	
	@Value("${bonus.percentage.reconsumeCoinUnitPrice}")
	public void setReconsumeCoinUnitPrice(double reconsumeCoinUnitPrice) {
		BonusPayPercentage.reconsumeCoinUnitPrice = reconsumeCoinUnitPrice;
	}

	@Value("${bonus.percentage.manageCostPercentage}")
	public void setManageCostPercentage(double manageCostPercentage) {
		BonusPayPercentage.manageCostPercentage = manageCostPercentage;
	}

	public static double getBonusNumberPercentage() {
		return bonusNumberPercentage;
	}

	@Value("${bonus.percentage.bonusNumberPercentage}")
	public void setBonusNumberPercentage(double bonusNumberPercentage) {
		BonusPayPercentage.bonusNumberPercentage = bonusNumberPercentage;
	}

	public static double getProductCoinNumberPercentage() {
		return productCoinNumberPercentage;
	}

	@Value("${bonus.percentage.productCoinNumberPercentage}")
	public void setProductCoinNumberPercentage(double productCoinNumberPercentage) {
		BonusPayPercentage.productCoinNumberPercentage = productCoinNumberPercentage;
	}

	public static double getConsumeCoinUnitPrice() {
		return consumeCoinUnitPrice;
	}

	@Value("${bonus.percentage.consumeCoinUnitPrice}")
	public void setConsumeCoinUnitPrice(double consumeCoinUnitPrice) {
		BonusPayPercentage.consumeCoinUnitPrice = consumeCoinUnitPrice;
	}

	public static double getDrawCostPercentage() {
		return drawCostPercentage;
	}

	@Value("${bonus.percentage.drawCostPercentage}")
	public void setDrawCostPercentage(double drawCostPercentage) {
		BonusPayPercentage.drawCostPercentage = drawCostPercentage;
	}

}
