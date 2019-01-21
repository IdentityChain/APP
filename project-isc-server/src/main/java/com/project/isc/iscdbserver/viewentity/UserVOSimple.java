package com.project.isc.iscdbserver.viewentity;

public class UserVOSimple {
	// 用户id
	private String userId;

	// 昵称
	private String nickName;

	//邀请码
	private String invitationCode;

	//邀请人-谁邀请的我
	private String pinvitationCode;

	// 创建时间
	private String createTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPinvitationCode() {
		return pinvitationCode;
	}

	public void setPinvitationCode(String pinvitationCode) {
		this.pinvitationCode = pinvitationCode;
	}
}
