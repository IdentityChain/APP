package com.project.isc.iscdbserver.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.project.isc.iscdbserver.statusType.UserLevel;
import com.project.isc.iscdbserver.statusType.UserType;

/**
 * 用户类，映射用户表 注解@Entity一定要带，这个是数据库映射的注解
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	@Column
	private String salt;// 加密密码的盐

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 用户id
	private long userId;

	// 账号
	@Column(nullable = false, unique = true, length = 100)
	private String account;

	// 昵称
	@Column
	private String nickName;

	// 用户密码
	@Column(nullable = false, length = 50)
	private String password;

	// 用户级别
	@Column(nullable = false)
	private String userLevel = UserLevel.CONSUMER;

	// 用户类型
	@Column(nullable = false)
	private String userType = UserType.COMMON;
	
	//邀请码
	@Column
	private String invitationCode;
	
	//邀请人-谁邀请的我
	@Column
	private String pinvitationCode;

	// 用户状态(false:未激活(初始化默认值),true:激活)
	@Column(nullable = false)
	private boolean userStatus = true;

	// 创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	// isc数目
	@Column
	private double iscCoin;

	// 用户积分
	@Column
	private double productCoin;

	// 用户激活时间
	@Column
	private Date activateTime;

	// 用户手机号码
	@Column
	private String userPhone;
	
	//真实姓名
	@Column
	private String realName;
	// 用户身份证号码
	@Column
	private String identityNo;

	// 密码重置标识
	@Column
	private boolean passwordReset = false;

	// 用户交易密码
	@Column
	private String paymentPassword;

	// 省区
	@Column
	private String province;

	// 市区
	@Column
	private String city;
	
	// 真实地址
	@Column
	private String address;

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

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	/**
	 * 密码盐.
	 * 
	 * @return
	 */
	public String getCredentialsSalt() {
		return this.account + this.salt;
	}
	// 重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}
