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

	//真实姓名
	@NotBlank(message = "姓名不能为空")
	@Size(min = 2,max = 10,message = "姓名的长度不合法!")
	private String realName;
	
	@NotBlank(message = "身份证号码不能为空")
	@Pattern(regexp = "(^[1-9]\\d{5}(19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)", message = "身份证号码格式错误")
	private String identityNo;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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
}
