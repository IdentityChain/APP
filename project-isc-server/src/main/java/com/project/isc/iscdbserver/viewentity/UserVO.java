package com.project.isc.iscdbserver.viewentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.project.isc.iscdbserver.statusType.UserLevel;
import com.project.isc.iscdbserver.statusType.UserType;

public class UserVO {
	// 用户id
	private long userId;

	// 账号
	private String account;

	// 昵称
	private String nickName;

	// 用户级别
	private String userLevel = UserLevel.CONSUMER;

	// 用户类型
	private String userType = UserType.COMMON;
	
	//邀请码
	private String invitationCode;
	
	//邀请人-谁邀请的我
	private String pinvitationCode;

	// 用户状态(false:未激活(初始化默认值),true:激活)
	private boolean userStatus = true;

	// 创建时间
	private Date createTime;

	// isc数目
	private double iscCoin;

	// 用户积分
	private double productCoin;

	// 用户激活时间
	private Date activateTime;

	// 用户手机号码
	private String userPhone;

	// 密码重置标识
	private boolean passwordReset = false;




	public boolean isPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(boolean passwordReset) {
		this.passwordReset = passwordReset;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
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
}
