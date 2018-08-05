package com.project.isc.iscdbserver.viewentity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:接收新增用户post请求参数
 *
 */
public class UserSaveRequest {
	@NotBlank(message = "新增用户名不能为空!")
	private String account;
	
	@NotBlank(message = "新增用户密码不能为空!")
	private String password;
	
	@NotBlank(message = "上级用户名不能为空")
	private String parentAccount;
	
	@NotBlank(message = "推荐人用户名不能为空")
	private String recommendAccount;
	
	@NotBlank(message = "用户所属部门不能为空")
	@Pattern(regexp = "[ABC]",message = "用户部门位置只能为A、B或C")
	private String position;

	public String getParentAccount() {
		return parentAccount;
	}

	public void setParentAccount(String parentAccount) {
		this.parentAccount = parentAccount;
	}

	public String getRecommendAccount() {
		return recommendAccount;
	}

	public void setRecommendAccount(String recommendAccount) {
		this.recommendAccount = recommendAccount;
	}

	public UserSaveRequest() {
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
