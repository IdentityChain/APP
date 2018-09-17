package com.project.isc.iscdbserver.viewentity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class UserInitSettingRequest {

	/**
	 * 隐藏传递
	 */
	@NotNull
	private String userid;
	@NotNull
	private String account;
	
	// 昵称
	@NotBlank(message = "昵称不能为空")
	@Size(min = 2,max = 10,message = "昵称的长度不合法!")
	private String nickName;

	@NotBlank(message = "用户交易密码不能为空")
	@Pattern(regexp = "[0-9]{6}", message = "交易密码只能是6位数字")
	private String paymentPassword;
	
	//真实姓名
	@NotBlank(message = "姓名不能为空")
	@Size(min = 2,max = 10,message = "姓名的长度不合法!")
	private String realName;
	
	@NotBlank(message = "身份证号码不能为空")
	@Pattern(regexp = "(^[1-9]\\d{5}(19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)", message = "身份证号码格式错误")
	private String identityNo;

	//省
	private String province;
	//市
	private String city;
	//详细地址
	private String address;
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
