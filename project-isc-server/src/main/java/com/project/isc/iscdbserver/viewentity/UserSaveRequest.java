package com.project.isc.iscdbserver.viewentity;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description:接收新增用户post请求参数
 *
 */
public class UserSaveRequest {
	@NotBlank(message = "新增手机号不能为空!")
	private String phone;
	
	@NotBlank(message = "验证码不能为空!")
	private String smsCode;
	
	//邀请码，选填
	private String invitationCode;
	
	public UserSaveRequest() {
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

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

}
