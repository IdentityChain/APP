package com.project.isc.iscdbserver.controller;


import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.isc.iscdbserver.entity.SmsCode;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.RedisService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.statusType.SmsType;
import com.project.isc.iscdbserver.transfEntity.UserTransf;
import com.project.isc.iscdbserver.util.ValidateErrorUtil;
import com.project.isc.iscdbserver.util.MD5Util;
import com.project.isc.iscdbserver.util.ShareCodeUtil;
import com.project.isc.iscdbserver.util.UserLoginSetting;
import com.project.isc.iscdbserver.viewentity.RetMsg;
import com.project.isc.iscdbserver.viewentity.UpdatePaymentPasswordRequest;
import com.project.isc.iscdbserver.viewentity.UserLoginPasswordUpdateRequest;
import com.project.isc.iscdbserver.viewentity.UserLoginRequest;
import com.project.isc.iscdbserver.viewentity.UserLoginSettingRequest;
import com.project.isc.iscdbserver.viewentity.UserSaveRequest;

import io.swagger.annotations.Api;

@Api(value = "用户管理", tags = "用户管理")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserLoginSetting userLoginSetting;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * @Description：新增用户
	 */
	@PostMapping("/firstsave")
	@Transactional
	public RetMsg firstsave(@Validated UserSaveRequest userSavePostParams, BindingResult bindingResult) {
		String phone = userSavePostParams.getPhone();
		String smsCodeString = userSavePostParams.getSmsCode();
		String invitationCode = userSavePostParams.getInvitationCode();
		String password = userSavePostParams.getPassword();

		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;

		// account不允许重复
		if (null != this.userService.findByUserPhone(phone))
			throw new RuntimeException("此手机号已注册");
			
			// 验证短信验证码是否正确
			try {
				SmsCode smsCode = (SmsCode) redisService.getObj(phone);
				if (null != smsCode && smsCode.getOperation().equals(SmsType.INIT_USER_INFO)){
					if (!smsCode.getCode().equals(smsCodeString)){
						retMsg = new RetMsg();
						retMsg.setMessage("验证码不正确");
						retMsg.setCode(400);
						retMsg.setSuccess(false);
						return retMsg;
					}
				}else {
					retMsg = new RetMsg();
					retMsg.setMessage("请先获取验证码");
					retMsg.setCode(400);
					retMsg.setSuccess(false);
					return retMsg;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("系统异常");
			}
			User user = new User();
			user.setUserPhone(phone);
			user.setAccount(phone);
			user.setCreateTime(new Date());
			user.setPinvitationCode(invitationCode);//设置邀请码
			user.setPassword(MD5Util.encrypeByMd5(password));
			user.setCalculateValue(100);
			userService.save(user);
			//返回用户的邀请码
			String myInvitationCode = ShareCodeUtil.toSerialCode(user.getUserId());
			user.setInvitationCode(myInvitationCode);
			userService.save(user);
			// 返回新增用户信息
			retMsg = new RetMsg();
			retMsg.setCode(200);
			retMsg.setData(UserTransf.transfToVO(user));
			retMsg.setMessage("用户注册成功");
			retMsg.setSuccess(true);
			return retMsg;
	}

	/**
	 * @Description：使用手机号登录
	 */
	@PostMapping("/loginbyphone")
	@Transactional
	public RetMsg loginByPhone(@Validated UserSaveRequest userSavePostParams, BindingResult bindingResult) {
		String phone = userSavePostParams.getPhone();
		String smsCodeString = userSavePostParams.getSmsCode();
		String invitationCode = userSavePostParams.getInvitationCode();

		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;


			
			// 验证短信验证码是否正确
			try {
				SmsCode smsCode = (SmsCode) redisService.getObj(phone);
				if (null != smsCode && smsCode.getOperation().equals(SmsType.INIT_USER_INFO)){
					if (!smsCode.getCode().equals(smsCodeString)){
						retMsg = new RetMsg();
						retMsg.setMessage("验证码不正确");
						retMsg.setCode(400);
						retMsg.setSuccess(false);
						return retMsg;
					}
				}else {
					retMsg = new RetMsg();
					retMsg.setMessage("请先获取验证码");
					retMsg.setCode(400);
					retMsg.setSuccess(false);
					return retMsg;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("系统异常");
			}
			User user = this.userService.findByUserPhone(phone);
			//如果得到用户信息允许登录否则直接注册登录
			// phone不允许重复
			if (null != user) {
					
			}else {
				user = new User();
				user.setUserPhone(phone);
				user.setCreateTime(new Date());
				user.setPinvitationCode(invitationCode);
				userService.save(user);
				//返回用户的邀请码
				String myInvitationCode = ShareCodeUtil.toSerialCode(user.getUserId());
				user.setInvitationCode(myInvitationCode);
				userService.save(user);
			}
			// 返回新增用户信息
			retMsg = new RetMsg();
			retMsg.setCode(200);
			retMsg.setData(UserTransf.transfToVO(user));
			retMsg.setMessage("用户登录成功");
			retMsg.setSuccess(true);
			return retMsg;
	}
	
	/**
	 * @Description：更新用户登录密码
	 * @return：RetMsg
	 */
	@PostMapping("/updateLoginPassword")
	@Transactional
	public RetMsg updatePassword(@Validated UserLoginPasswordUpdateRequest userPasswordUpdateRequest,
			BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;

		String account = userPasswordUpdateRequest.getAccount();
		String oldPassword = userPasswordUpdateRequest.getOldPassword();
		String newPassword = userPasswordUpdateRequest.getNewPassword();

		// 验证用户是否存在
		User user = this.userService.findByAccount(account);
		if (null == user)
			throw new RuntimeException("用户不存在");

		// 判断原密码是否正确
		if (!MD5Util.encrypeByMd5(oldPassword).equals(user.getPassword())) {
			throw new RuntimeException("原密码不正确");
		}

		user.setPassword(MD5Util.encrypeByMd5(newPassword));
		this.userService.save(user);

		setUserLoginCookie(user, request, response);
		response.setHeader("loginStatus", "true");

		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(UserTransf.transfToVO(user));
		retMsg.setMessage("用户登录密码更新成功");
		retMsg.setSuccess(true);

		return retMsg;

	}

	/**
	 * @Description：用户登录,passwordReset：密码重置标识，true：密码已重置；false：密码未重置
	 * @return：retMsg
	 */
	@PostMapping("/login")
	public RetMsg login(@Validated UserLoginRequest userLoginRequest, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		String account = userLoginRequest.getAccount();
		String password = userLoginRequest.getPassword();

		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;

		// 验证账户是否存在
		User user = this.userService.findByAccount(account);
		if (null == user) {
			throw new RuntimeException("当前用户不存在");
		}

		// 验证用户的密码是否正确
		if (!(MD5Util.encrypeByMd5(password).equals(user.getPassword()))) {
			throw new RuntimeException("用户密码输入有误");
		}

		// 设置cookie
		setUserLoginCookie(user, request, response);
		response.setHeader("loginStatus", "true");

		retMsg = new RetMsg();
		// 验证用户是否需要重置密码

		retMsg.setCode(200);
		retMsg.setSuccess(true);
		retMsg.setMessage("登陆成功");
		retMsg.setData(UserTransf.transfToVO(user));

		return retMsg;
	}

	private void setUserLoginCookie(User user, HttpServletRequest request, HttpServletResponse response) {
		long time = System.currentTimeMillis() + this.userLoginSetting.getExpireTime() * 60 * 10000;
		String account = user.getAccount();
		String password = user.getPassword();
		String data = MD5Util.encrypeByMd5(account + ":" + time + ":" + password);
		String cookieStr = account + ":" + time + ":" + data;

		Cookie cookie = new Cookie("autoLogin", cookieStr);
		cookie.setComment("自动登录cookie!");
		cookie.setPath("/");
		cookie.setMaxAge((int) (this.userLoginSetting.getExpireTime() * 60));
		response.addCookie(cookie);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
	}

	/**
	 * @Description：重置登录密码，设置支付密码和用户电话号码
	 * @return：RetMsg
	 */
	@PostMapping("/initUserInfo")
	@Transactional
	public RetMsg initUserInfo(@Validated UserLoginSettingRequest userLoginSettingRequest, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		String account = userLoginSettingRequest.getAccount();
		String newPassword = userLoginSettingRequest.getNewPassword();
		String paymentPassword = userLoginSettingRequest.getPaymentPassword();
		String userPhone = userLoginSettingRequest.getUserPhone();
		String nickName = userLoginSettingRequest.getNickName();

		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;

		// 验证用户是否存在
		User user = this.userService.findByAccount(account);
		if (null == user)
			throw new RuntimeException("用户账号不存在");

		// 设置用户登录新密码、支付密码和手机号码
		user.setPassword(MD5Util.encrypeByMd5(newPassword));
		user.setPaymentPassword(MD5Util.encrypeByMd5(paymentPassword));
		user.setUserPhone(userPhone);
		user.setPasswordReset(true);
		user.setNickName(nickName);

		// 更新用户信息
		this.userService.save(user);

		// 设置cookie
		setUserLoginCookie(user, request, response);
		response.setHeader("loginStatus", "true");

		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(UserTransf.transfToVO(user));
		retMsg.setSuccess(true);
		retMsg.setMessage("用户设置成功");

		return retMsg;
	}


	// 删除用户
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional
	private RetMsg deleteUser(User user) {
		String account = user.getAccount();
		// account值为空或者account用户不存在，抛出异常
		if ((account == null) || (this.userService.findByAccount(account) == null)) {
			throw new RuntimeException("当前用户不存在");
		}

		try {
			this.userService.delete(user);
			RetMsg retMsg = new RetMsg();
			retMsg.setCode(200);
			retMsg.setData(account);
			retMsg.setMessage("用户删除成功");
			retMsg.setSuccess(true);
			return retMsg;
		} catch (Exception e) {
			throw new RuntimeException("用户删除失败");
		}
	}

	/************************** 用户查询方法 *******************************/
	// 根据用户名查找用户信息
	@GetMapping("/findByAccount")
	public RetMsg findUserByAccount(@RequestParam("account") String account) {
		if (null == account)
			throw new RuntimeException("用户账户名不能为null");

		try {
			User u = this.userService.findByAccount(account);
			RetMsg retMsg = new RetMsg();
			retMsg.setCode(200);
			retMsg.setData(UserTransf.transfToVO(u));
			retMsg.setMessage("用户查询成功");
			retMsg.setSuccess(true);
			return retMsg;
		} catch (Exception e) {
			throw new RuntimeException("用户账户不存在！");
		}
	}

	// 修改用户的交易密码
	@PostMapping("/updatePaymentPassword")
	public RetMsg updatePaymentPassword(@Validated UpdatePaymentPasswordRequest updatePaymentPasswordRequest,
			BindingResult bindingResult) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;

		String account = updatePaymentPasswordRequest.getAccount();
		String loginPassword = updatePaymentPasswordRequest.getLoginPassword();
		String oldPaymentPassword = updatePaymentPasswordRequest.getOldPaymentpassword();
		String newPaymentPassword = updatePaymentPasswordRequest.getNewPaymentPassword();
		String phone = updatePaymentPasswordRequest.getPhone();

		User user = this.userService.findByAccount(account);
		if (null == user)
			throw new RuntimeException("用户不存在");

		if (!(MD5Util.encrypeByMd5(loginPassword)).equals(user.getPassword()))
			throw new RuntimeException("登录密码输入有误");

		if (!(MD5Util.encrypeByMd5(oldPaymentPassword)).equals(user.getPaymentPassword()))
			throw new RuntimeException("原支付密码输入错误");

		if (!phone.equals(user.getUserPhone()))
			throw new RuntimeException("电话号码输入有误");

		try {
			user.setPaymentPassword(MD5Util.encrypeByMd5(newPaymentPassword));

			retMsg = new RetMsg();
			retMsg.setCode(200);
			retMsg.setData(UserTransf.transfToVO(user));
			retMsg.setMessage("支付密码修改成功");
			retMsg.setSuccess(true);

			return retMsg;
		} catch (Exception e) {
			throw new RuntimeException("支付密码修改失败");
		}
	}

}
