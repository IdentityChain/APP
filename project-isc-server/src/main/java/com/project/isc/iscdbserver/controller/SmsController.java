package com.project.isc.iscdbserver.controller;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.project.isc.iscdbserver.entity.SmsCode;
import com.project.isc.iscdbserver.service.RedisService;
import com.project.isc.iscdbserver.statusType.SmsType;
import com.project.isc.iscdbserver.util.SmsUtil;
import com.project.isc.iscdbserver.viewentity.RetMsg;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "短信管理", tags = "短信管理")
@RestController
@CrossOrigin
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private RedisService redisService;

	@GetMapping("/getCodeByPhone")
	public RetMsg getSmsCodeByAccount(String phoneNumber) {
		RetMsg retMsg = new RetMsg();

		// 创建验证码
		SmsCode code = new SmsCode();
		code.setOperation(SmsType.INIT_USER_INFO);
		code.setAccount(phoneNumber);
		String codeStr = SmsUtil.getSmsCode();
		code.setCode(codeStr);

		// 判断该用户是否在分钟内发送过验证码,如果没有就调用阿里短信服务,发送短信验证码
		try {
			if (redisService.getFreeTime(phoneNumber) > 180) {
				retMsg.setCode(400);
				retMsg.setSuccess(false);
				retMsg.setMessage("已经发送验证码,请在2分钟之后再试");
			} else {
				SendSmsResponse sendSmsResponse = SmsUtil.sendSms(phoneNumber, codeStr);
				QuerySendDetailsResponse querySendDetailsResponse = SmsUtil.querySendDetails(sendSmsResponse.getBizId(),
						phoneNumber);
				if (querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")) {
					retMsg.setSuccess(true);
					retMsg.setCode(200);
					retMsg.setMessage("发送成功");
					// 保存到redis
					redisService.setObj(phoneNumber, code);
				}
			}
		} catch (Exception e) {
			retMsg.setCode(400);
			retMsg.setSuccess(false);
			retMsg.setMessage("发送短信验证码失败");
		}
		return retMsg;
	}

}
