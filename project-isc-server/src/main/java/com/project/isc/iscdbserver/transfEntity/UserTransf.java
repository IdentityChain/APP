package com.project.isc.iscdbserver.transfEntity;

import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.util.DateFormatUtil;
import com.project.isc.iscdbserver.util.StringUtils;
import com.project.isc.iscdbserver.viewentity.UserVO;
import com.project.isc.iscdbserver.viewentity.UserVOSimple;

import java.util.ArrayList;
import java.util.List;

public class UserTransf{
	public static UserVO transfToVO(User u) {
		UserVO uvo = new UserVO();
		uvo.setUserId(u.getUserId());
		uvo.setAccount(u.getAccount());
		uvo.setCreateTime(DateFormatUtil.DateObjectToString(u.getCreateTime()));
		uvo.setNickName(u.getNickName());
		uvo.setInvitationCode(u.getInvitationCode());
		uvo.setIscCoin(u.getIscCoin());
		uvo.setCalculateValue(u.getCalculateValue());
		uvo.setUserPhone(u.getUserPhone());
		//是否需要重置密码
		uvo.setPasswordReset(u.isPasswordReset());
		uvo.setEthAddress(u.getEthAddress());
		//这个表示是不是已经身份证新认证了
		uvo.setUserStatus(u.getUserStatus());
		uvo.setRealName(StringUtils.getStringHide(u.getRealName(),StringUtils.HIDE_NAME));
		uvo.setIdentityNo(StringUtils.getStringHide(u.getIdentityNo(),StringUtils.HIDE_IDENTITY));
		return uvo;
	}


	public static UserVOSimple transfToVOSimple(User u) {
		UserVOSimple uvo = new UserVOSimple();
		uvo.setUserId(u.getUserId());
		uvo.setCreateTime(DateFormatUtil.DateObjectToString(u.getCreateTime()));
		uvo.setNickName(StringUtils.getStringHide(u.getNickName(),StringUtils.HIDE_PHONE));
		uvo.setInvitationCode(u.getInvitationCode());
		uvo.setPinvitationCode(u.getPinvitationCode());
		return uvo;
	}

	public static List<UserVO> transfToVO(List<User> users){
		if(users!=null){
			List<UserVO> userVOS =  new ArrayList<>();
			for(User u:users){
				userVOS.add(transfToVO(u));
			}
			return userVOS;
		}
		return null;
	}

	public static List<UserVOSimple> transfToVOSimple(List<User> users){
		if(users!=null){
			List<UserVOSimple> userVOS =  new ArrayList<>();
			for(User u:users){
				userVOS.add(transfToVOSimple(u));
			}
			return userVOS;
		}
		return null;
	}
	
	public static User transfToPO(UserVO uvo) {
		return null;
	}
}
