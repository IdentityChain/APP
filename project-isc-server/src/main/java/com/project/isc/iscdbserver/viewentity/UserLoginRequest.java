package com.project.isc.iscdbserver.viewentity;

import org.hibernate.validator.constraints.NotBlank;

public class UserLoginRequest {
	@NotBlank(message="用户名不能为空")
	private String account;
	
	@NotBlank(message="用户密码不能为空")
	private String password;

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

}
