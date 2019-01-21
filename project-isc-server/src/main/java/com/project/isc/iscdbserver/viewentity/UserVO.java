package com.project.isc.iscdbserver.viewentity;

import java.util.Date;

public class UserVO {
	// 用户id
	private String userId;

	// 账号
	private String account;

	// 昵称
	private String nickName;

	// 用户级别
	private String userLevel;

	// 用户类型
	private String userType;
	
	//邀请码
	private String invitationCode;
	
	//邀请人-谁邀请的我
	private String pinvitationCode;

	// 用户状态(false:未激活(初始化默认值),true:激活)
	private boolean userStatus = true;

	// 创建时间
	private String createTime;

	// isc数目
	private double iscCoin;
	
	//成就点
	private int calculateValue;

	// 用户积分
	private double productCoin;

	// 用户激活时间
	private Date activateTime;

	// 用户手机号码
	private String userPhone;

	// 密码重置标识
	private boolean passwordReset;

	//真实姓名
	private String realName;
	// 用户身份证号码
	private String identityNo;

	//用户ETH地址
	private String ethAddress;

	public String getEthAddress() {
		return ethAddress;
	}

	public void setEthAddress(String ethAddress) {
		this.ethAddress = ethAddress;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public boolean isPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(boolean passwordReset) {
		this.passwordReset = passwordReset;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public double getIscCoin() {
		return iscCoin;
	}

	public void setIscCoin(double iscCoin) {
		this.iscCoin = iscCoin;
	}

	public double getProductCoin() {
		return productCoin;
	}

	public void setProductCoin(double productCoin) {
		this.productCoin = productCoin;
	}

	public Date getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(Date activateTime) {
		this.activateTime = activateTime;
	}
		
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getPinvitationCode() {
		return pinvitationCode;
	}

	public void setPinvitationCode(String pinvitationCode) {
		this.pinvitationCode = pinvitationCode;
	}

	public int getCalculateValue() {
		return calculateValue;
	}

	public void setCalculateValue(int calculateValue) {
		this.calculateValue = calculateValue;
	}
	
}
