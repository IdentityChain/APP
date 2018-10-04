package com.project.isc.iscdbserver.viewentity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class FuturLettersRequest {
	@NotBlank(message = "用户未登录")
	private String userid;

	// 用户内容
	private String flMessage;

	// 寄送到的邮箱
	private String flemail;

	//寄送时间
	private Date sendTime;

	// 状态-是否公开
	private String flIsPublic;

	// 是否加密或者加密方式
	private String flEncryption;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFlMessage() {
		return flMessage;
	}

	public void setFlMessage(String flMessage) {
		this.flMessage = flMessage;
	}

	public String getFlemail() {
		return flemail;
	}

	public void setFlemail(String flemail) {
		this.flemail = flemail;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getFlIsPublic() {
		return flIsPublic;
	}

	public void setFlIsPublic(String flIsPublic) {
		this.flIsPublic = flIsPublic;
	}

	public String getFlEncryption() {
		return flEncryption;
	}

	public void setFlEncryption(String flEncryption) {
		this.flEncryption = flEncryption;
	}
}
