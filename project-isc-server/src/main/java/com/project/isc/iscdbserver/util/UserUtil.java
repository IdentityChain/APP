package com.project.isc.iscdbserver.util;

import java.util.Date;

import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.statusType.UserLevel;
import com.project.isc.iscdbserver.statusType.UserType;
import com.project.isc.iscdbserver.viewentity.UserVO;

public class UserUtil {
	private UserUtil() {
	}

	public static UserVO UserToUserVO(User user) {
		UserVO userVO = new UserVO();
		userVO.setAccount(user.getAccount());
		userVO.setActivateTime(DateFormatUtil.DateObjectToString(user.getActivateTime()));
		
		return userVO;
	}

	public static User getTestUser() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date createDate = new Date();
		User u = new User();
		String name = "zs" + createDate.getTime();
		u.setNickName(name);
		u.setAccount(name);
		u.setPassword(MD5Util.encrypeByMd5("123456"));
		u.setCreateTime(createDate);
		u.setUserLevel(UserLevel.CONSUMER);
		u.setUserType(UserType.COMMON);
		u.setUserStatus(true);
		u.setInvitationCode("xxxxxxxsdf");
		return u;
	}
}
