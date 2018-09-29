package com.project.isc.iscdbserver.viewentity;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateNickNameRequest {
	@NotBlank(message = "用户未登录")
	private String userid;
	@NotBlank(message = "用户昵称不能为空")
	private String oldNickName;
	@NotBlank(message = "新用户昵称不能为空")
	private String newNickName;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOldNickName() {
		return oldNickName;
	}

	public void setOldNickName(String oldNickName) {
		this.oldNickName = oldNickName;
	}

	public String getNewNickName() {
		return newNickName;
	}

	public void setNewNickName(String newNickName) {
		this.newNickName = newNickName;
	}
}
