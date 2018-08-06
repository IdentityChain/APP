package com.project.isc.iscdbserver.viewentity;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:接收新增用户post请求参数
 *
 */
public class UserSaveRequest {
	@NotBlank(message = "新增手机号不能为空!")
	private String phone;
	
	@NotBlank(message = "新增用户密码不能为空!")
	private String password;
	
	@NotBlank(message = "验证码不能为空!")
	private String smsCode;
	
	public UserSaveRequest() {
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSmsCode() {
		return smsCode;
	}


	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}


	

}
