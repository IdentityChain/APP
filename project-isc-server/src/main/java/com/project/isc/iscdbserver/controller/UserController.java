package com.project.isc.iscdbserver.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.project.isc.iscdbserver.annotation.Auth;
import com.project.isc.iscdbserver.util.*;
import com.project.isc.iscdbserver.viewentity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.project.isc.iscdbserver.entity.SmsCode;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.RedisService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.statusType.SmsType;
import com.project.isc.iscdbserver.transfEntity.UserTransf;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

	@Autowired
	private Md5TokenGenerator tokenGenerator;
	
	/**
	 * @Description：新增用户
	 */
	@ApiOperation(value="新增用户", notes="新增用户")
	@PostMapping("/firstsave")
	@Transactional
	public RetMsg firstsave(@RequestBody @Validated UserSaveRequest userSavePostParams, BindingResult bindingResult) {
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
//			try {
//				SmsCode smsCode = (SmsCode) redisService.getObj(phone);
//				if (null != smsCode && smsCode.getOperation().equals(SmsType.INIT_USER_INFO)){
//					if (!smsCode.getCode().equals(smsCodeString)){
//						retMsg = new RetMsg();
//						retMsg.setMessage("验证码不正确");
//						retMsg.setCode(400);
//						retMsg.setSuccess(false);
//						return retMsg;
//					}
//				}else {
//					retMsg = new RetMsg();
//					retMsg.setMessage("请先获取验证码");
//					retMsg.setCode(400);
//					retMsg.setSuccess(false);
//					return retMsg;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new RuntimeException("系统异常");
//			}
			User user = new User();
			user.setUserPhone(phone);
			user.setAccount(phone);
			user.setNickName(phone);
			user.setCreateTime(new Date());
			user.setPinvitationCode(invitationCode);//设置邀请码
			user.setPassword(MD5Util.encrypeByMd5(password));
			//默认支付密码和登录密码相同
			user.setPaymentPassword(MD5Util.encrypeByMd5(password));
			user.setCalculateValue(100);
			userService.save(user);
			//返回用户的邀请码
			String myInvitationCode = ShareCodeUtil.getMyInvitationCode(user.getUserId());
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
	@ApiOperation(value="使用手机号登录", notes="使用手机号登录")
	@PostMapping("/loginbyphone")
	@Transactional
	public RetMsg loginByPhone(@RequestBody @Validated UserSaveRequest userSavePostParams, BindingResult bindingResult) {
		String phone = userSavePostParams.getPhone();
		String smsCodeString = userSavePostParams.getSmsCode();
		String invitationCode = userSavePostParams.getInvitationCode();

		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;


			
			// 验证短信验证码是否正确
			try {
				//使用手机号登录或注册验证码
				String smsCodeType = phone+SmsType.SMS_CODE_TYPE_PHONELOGIN;
				SmsCode smsCode = (SmsCode) redisService.getObj(smsCodeType);
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
				String myInvitationCode = ShareCodeUtil.getMyInvitationCode(user.getUserId());
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
	 * @Description：修改用户密码-短信
	 */
	@ApiOperation(value="修改用户密码-短信", notes="修改用户密码-短信")
	@PostMapping("/updateLoginPasswordBySms")
	@Transactional
	public RetMsg updateLoginPasswordBySms(@RequestBody @Validated UpdateUserPasswordBySmsRequest updateUserPasswordBySmsRequest, BindingResult bindingResult) {
		String phone = updateUserPasswordBySmsRequest.getPhone();
		String smsCodeString = updateUserPasswordBySmsRequest.getSmsCode();
		String password  = updateUserPasswordBySmsRequest.getPassword();


		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;



		// 验证短信验证码是否正确
//		try {
//			//修改密码短信验证码
//			String smsCodeType = phone+SmsType.SMS_CODE_TYPE_PHONECHANGEPWD;
//			SmsCode smsCode = (SmsCode) redisService.getObj(smsCodeType);
//			if (null != smsCode && smsCode.getOperation().equals(SmsType.INIT_USER_INFO)){
//				if (!smsCode.getCode().equals(smsCodeString)){
//					retMsg = new RetMsg();
//					retMsg.setMessage("验证码不正确");
//					retMsg.setCode(400);
//					retMsg.setSuccess(false);
//					return retMsg;
//				}
//			}else {
//				retMsg = new RetMsg();
//				retMsg.setMessage("请先获取验证码");
//				retMsg.setCode(400);
//				retMsg.setSuccess(false);
//				return retMsg;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("系统异常");
//		}
		User user = this.userService.findByUserPhone(phone);
		//如果得到用户信息允许登录否则直接注册登录
		// phone不允许重复
		if (null != user) {
			user.setPassword(MD5Util.encrypeByMd5(password));
			this.userService.save(user);
		}else {
			throw new RuntimeException("系统异常");
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
	@ApiOperation(value="更新用户登录密码", notes="更新用户登录密码")
	@PostMapping("/updateLoginPassword")
	@Transactional
	@Auth
	public RetMsg updatePassword(@RequestBody @Validated UserLoginPasswordUpdateRequest userPasswordUpdateRequest,
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
	 * @Description：更新用户账号
	 * @return：RetMsg
	 */
	@ApiOperation(value="更新用户账号", notes="更新用户账号")
	@PostMapping("/updateAccount")
	@Transactional
	@Auth
	public RetMsg updateAccount(@RequestBody @Validated UpdateAccountRequest updateAccountRequest,
								 BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;


		String userID = updateAccountRequest.getUserid();
		String oldAccount = updateAccountRequest.getOldAccount();
		String newAccount = updateAccountRequest.getNewAccount();

		// 验证用户是否存在
		User user = this.userService.findByUserId(userID);
		if (null == user)
			throw new RuntimeException("用户不存在");

		// 判断原用户名是否正确
		if (oldAccount.equals(user.getAccount())) {
			throw new RuntimeException("不存在这个用户");
		}
		User user1 = this.userService.findByAccount(newAccount);
		if(user1!=null){
			throw new RuntimeException("用户名已存在");
		}

		user.setAccount(newAccount);
		this.userService.save(user);

		setUserLoginCookie(user, request, response);
		response.setHeader("loginStatus", "true");

		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(UserTransf.transfToVO(user));
		retMsg.setMessage("用户用户名更新成功");
		retMsg.setSuccess(true);

		return retMsg;

	}

	/**
	 * @Description：更新昵称
	 * @return：RetMsg
	 */
	@ApiOperation(value="更新昵称", notes="更新昵称")
	@PostMapping("/updateNickName")
	@Transactional
	@Auth
	public RetMsg updateNickName(@RequestBody @Validated UpdateNickNameRequest updateNickNameRequest,
								BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;


		String userID = updateNickNameRequest.getUserid();
		String oldNickName = updateNickNameRequest.getOldNickName();
		String newNickName = updateNickNameRequest.getNewNickName();

		// 验证用户是否存在
		User user = this.userService.findByUserId(userID);
		if (null == user)
			throw new RuntimeException("用户不存在");

		// 判断原用户名是否正确
		if (!oldNickName.equals(user.getNickName())) {
			throw new RuntimeException("不存在这个昵称的用户");
		}
		List<User> userList = this.userService.findByNickName(newNickName);
		if(userList!=null && userList.size()>0){
			throw new RuntimeException("昵称已存在");
		}

		user.setNickName(newNickName);
		this.userService.save(user);

		setUserLoginCookie(user, request, response);
		response.setHeader("loginStatus", "true");

		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(UserTransf.transfToVO(user));
		retMsg.setMessage("用户昵称更新成功");
		retMsg.setSuccess(true);

		return retMsg;

	}

	/**
	 * @Description：用户登录,passwordReset：密码重置标识，true：密码已重置；false：密码未重置
	 * @return：retMsg
	 */
	@ApiOperation(value="用户登录", notes="用户登录")
	@PostMapping("/login")
	public RetMsg login(@RequestBody @Validated UserLoginRequest userLoginRequest, BindingResult bindingResult,
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
//		setUserLoginCookie(user, request, response);
//		response.setHeader("loginStatus", "true");

		String token = tokenGenerator.generate(account, password);
//		redisService.setStrWithTime(token, account, this.userLoginSetting.getExpireTime() *60);
		redisService.setObj(token,account, this.userLoginSetting.getExpireTime() * 60);
		response.setHeader("Authorization", token);
		retMsg = new RetMsg();
		// 验证用户是否需要重置密码

		retMsg.setCode(200);
		retMsg.setSuccess(true);
		retMsg.setMessage("登陆成功");
		retMsg.setData(UserTransf.transfToVO(user));

		return retMsg;
	}

	private void setUserLoginCookie(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
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
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
	}

	/**
	 * @Description：完善信息
	 * @return：RetMsg
	 */
	@ApiOperation(value="完善信息-身份证", notes="完善信息-身份证")
	@PostMapping("/initUserInfo")
	@Transactional
	@Auth
	public RetMsg initUserInfo(@RequestBody @Validated UserInitSettingRequest userInitSettingRequest, BindingResult bindingResult,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = userInitSettingRequest.getUserid();
		String realName = userInitSettingRequest.getRealName();
		String identityNo = userInitSettingRequest.getIdentityNo();
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;

		// 验证用户是否存在
		User user = this.userService.findByUserId(userId);
		if (null == user)
			throw new RuntimeException("用户账号不存在");

		// 设置用户登录新密码、支付密码和手机号码
		user.setUserStatus(true);
		user.setRealName(realName);
		user.setIdentityNo(identityNo);
		//用户已完善资料
		user.setUserStatus(true);

		// 更新用户信息
		this.userService.save(user);

		// 设置cookie
		setUserLoginCookie(user, request, response);
		response.setHeader("loginStatus", "true");

		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(UserTransf.transfToVO(user));
		retMsg.setSuccess(true);
		retMsg.setMessage("用户资料完善成功成功");

		return retMsg;
	}

	/************************** 用户查询方法 *******************************/
	// 根据用户名查找用户信息
	@ApiOperation(value="用户名查找用户信息", notes="用户名查找用户信息")
	@GetMapping("/findByAccount")
	@Auth
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
	
	// 根据用户ID查找用户信息
	@ApiOperation(value="用户ID查找用户信息", notes="用户ID查找用户信息")
	@GetMapping("/findByUserId")
	@Auth
	public RetMsg findByUserId(@RequestParam("userid") String userid) {
		if (null == userid)
			throw new RuntimeException("用户账户名不能为null");

		try {
			User u = this.userService.findByUserId(userid);
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


	/**
	 * @Description：修改用户交易密码-短信
	 */
	@ApiOperation(value="修改用户交易密码-短信", notes="修改用户交易密码-短信")
	@PostMapping("/updatePaymentPasswordBySms")
	@Transactional
	@Auth
	public RetMsg updatePaymentPasswordBySms(@RequestBody @Validated UpdateUserPayPasswordBySmsRequest updateUserPayPasswordBySmsRequest, BindingResult bindingResult) {
		String phone = updateUserPayPasswordBySmsRequest.getPhone();
		String smsCodeString = updateUserPayPasswordBySmsRequest.getSmsCode();
		String payPassword  = updateUserPayPasswordBySmsRequest.getPayPassword();


		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;



		// 验证短信验证码是否正确
//		try {
//			//修改密码短信验证码
//			String smsCodeType = phone+SmsType.SMS_CODE_TYPE_PHONECHANGEPWD;
//			SmsCode smsCode = (SmsCode) redisService.getObj(smsCodeType);
//			if (null != smsCode && smsCode.getOperation().equals(SmsType.INIT_USER_INFO)){
//				if (!smsCode.getCode().equals(smsCodeString)){
//					retMsg = new RetMsg();
//					retMsg.setMessage("验证码不正确");
//					retMsg.setCode(400);
//					retMsg.setSuccess(false);
//					return retMsg;
//				}
//			}else {
//				retMsg = new RetMsg();
//				retMsg.setMessage("请先获取验证码");
//				retMsg.setCode(400);
//				retMsg.setSuccess(false);
//				return retMsg;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("系统异常");
//		}
		User user = this.userService.findByUserPhone(phone);
		//如果得到用户信息允许登录否则直接注册登录
		// phone不允许重复
		if (null != user) {
			user.setPaymentPassword(MD5Util.encrypeByMd5(payPassword));
			user.setPasswordReset(true);
			this.userService.save(user);
		}else {
			throw new RuntimeException("系统异常");
		}
		// 返回新增用户信息
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(UserTransf.transfToVO(user));
		retMsg.setMessage("用户登录成功");
		retMsg.setSuccess(true);
		return retMsg;
	}

	// 修改用户的交易密码
	@ApiOperation(value="修改用户的交易密码", notes="修改用户的交易密码")
	@PostMapping("/updatePaymentPassword")
	@Auth
	public RetMsg updatePaymentPassword(@RequestBody @Validated UpdatePaymentPasswordRequest updatePaymentPasswordRequest,
			BindingResult bindingResult) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = ValidateErrorUtil.getInstance().errorList(bindingResult);
		if (null != retMsg)
			return retMsg;

		String account = updatePaymentPasswordRequest.getAccount();
		String loginPassword = updatePaymentPasswordRequest.getLoginPassword();
		String oldPaymentPassword = updatePaymentPasswordRequest.getOldPaymentPassword();
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
			this.userService.save(user);

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

	// 根据用户ID查找用户信息
	@ApiOperation(value="获得用户数目", notes="获得用户数目")
	@GetMapping("/findUserCount")
	public RetMsg findUserCount() {
		long userCount  = this.userService.findUserCount();
		RetMsg retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(userCount);
		retMsg.setMessage("获得用户数目");
		retMsg.setSuccess(true);
		return retMsg;
	}

}
