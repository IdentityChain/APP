package com.project.isc.iscdbserver.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.ActivtyService;
import com.project.isc.iscdbserver.service.CalculateService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.util.UserLoginSetting;

@Service
public class IscServerSchedul {
	@Autowired
	private UserService userService;
	@Autowired
	private CalculateService calculateService;
	@Autowired
	private ActivtyService activtyService;
	@Autowired
	private UserLoginSetting userLoginSetting;
	
	/**
	 * 统计top100
	 */
	public void mainTop100() {
		
	}
	
	/**
	 * 生成矿数据
	 */
	public void mainISCcoin() {
		Iterable<User> users = userService.getAll();
		
		for (User user : users) {
			
		}
	}
	
	/**
	 * 修改的矿数据
	 */
	public void mainDeleteISCcoinlog() {
		
	}
}
