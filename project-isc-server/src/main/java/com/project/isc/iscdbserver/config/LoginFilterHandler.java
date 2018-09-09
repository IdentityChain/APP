package com.project.isc.iscdbserver.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.util.MD5Util;
import com.project.isc.iscdbserver.util.UserLoginSetting;
import com.project.isc.iscdbserver.entity.User;

import java.util.Enumeration;

@Component
@CrossOrigin
public class LoginFilterHandler implements HandlerInterceptor {
	@Autowired
	private UserLoginSetting userLoginSetting;

	@Autowired
	private UserService userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Cookie[] cookies = request.getCookies();
		if (null == cookies || 0 == cookies.length) {
			response.setHeader("loginStatus", "false");
			return false;
		}

		for (Cookie cookie : cookies) {
			if ("autoLogin".equals(cookie.getName())) {
				String value = cookie.getValue();
				String[] tArr = value.split(":");

				if (3 != tArr.length) {
					response.setHeader("loginStatus", "false");
					return false;
				}

				// 验证cookie是否过期
				long expireTime = Long.parseLong(tArr[1]);
				if (System.currentTimeMillis() > expireTime) {
					response.setHeader("loginStatus", "false");
					return false;
				}

				// 验证密码是否正确
				String account = tArr[0];
				User u = this.userService.findByAccount(account);
				if (null == u) {
					response.setHeader("loginStatus", "false");
					return false;
				}

				String data = MD5Util.encrypeByMd5(tArr[0] + ":" + tArr[1] + ":" + u.getPassword());
				if (!data.equals(tArr[2])) {
					response.setHeader("loginStatus", "false");
					return false;
				}

				// 设置cookie
				setUserLoginCookie(u, request, response);
				response.setHeader("loginStatus", "true");
				return true;
			}
		}

		response.setHeader("loginStatus", "false");
		response.setHeader( "Access-Control-Allow-Origin","http://localhost:8000");
		response.setHeader( "Access-Control-Allow-Methods","POST,GET" );
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader( "Access-Control-Expose-Headers", "loginStatus");
		return false;
	}

	private void setUserLoginCookie(User user, HttpServletRequest request, HttpServletResponse response) {
		long time = System.currentTimeMillis() + this.userLoginSetting.getExpireTime() * 60 * 10000;
		String account = user.getAccount();
		String password = user.getPassword();
		String data = MD5Util.encrypeByMd5(account + ":" + time + ":" + password);
		String cookieStr = account + ":" + time + ":" + data;

		Cookie cookie = new Cookie("autoLogin", cookieStr);
		cookie.setComment("自动登录cookie!");
//		cookie.setDomain("localhost:8080");
		cookie.setPath("/");
		cookie.setMaxAge((int) (this.userLoginSetting.getExpireTime() * 60));
		response.addCookie(cookie);
	}
}
