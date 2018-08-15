package com.project.isc.iscdbserver.transfEntity;

import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.viewentity.UserVO;

public class UserTransf{
	public static UserVO transfToVO(User u) {
		UserVO uvo = new UserVO();
		uvo.setAccount(u.getAccount());
		uvo.setCreateTime(u.getCreateTime());
		uvo.setNickName(u.getNickName());
		uvo.setInvitationCode(u.getInvitationCode());
		return uvo;
	}
	
	public static User transfToPO(UserVO uvo) {
		return null;
	}
}
