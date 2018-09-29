package com.project.isc.iscdbserver.viewentity;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateAccountRequest {
	@NotBlank(message = "用户未登录")
	private String userid;
	@NotBlank(message = "用户名不能为空")
	private String oldAccount;
	@NotBlank(message = "新用户名不能为空")
	private String newAccount;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOldAccount() {
		return oldAccount;
	}

	public void setOldAccount(String oldAccount) {
		this.oldAccount = oldAccount;
	}

	public String getNewAccount() {
		return newAccount;
	}

	public void setNewAccount(String newAccount) {
		this.newAccount = newAccount;
	}
}
