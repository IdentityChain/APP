package com.project.isc.iscdbserver.viewentity;

import org.hibernate.validator.constraints.NotBlank;

public class UserLoginPasswordUpdateRequest {
	@NotBlank(message = "用户名不能为空")
	private String account;
	@NotBlank(message = "用户原密码不能为空")
	private String oldPassword;
	@NotBlank(message = "用户新密码不能为空")
	private String newPassword;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
