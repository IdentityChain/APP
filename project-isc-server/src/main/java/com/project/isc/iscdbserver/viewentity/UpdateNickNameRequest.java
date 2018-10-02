package com.project.isc.iscdbserver.viewentity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(value = "用户更新昵称参数对象",description="userUpdateNickName")
public class UpdateNickNameRequest {
	@NotBlank(message = "用户未登录")
	@ApiModelProperty(value = "用户id", required = true)
	private String userid;

	@NotBlank(message = "用户昵称不能为空")
	@ApiModelProperty(value = "用户旧昵称", required = true)
	private String oldNickName;

	@NotBlank(message = "新用户昵称不能为空")
	@ApiModelProperty(value = "用户新昵称", required = true)
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
