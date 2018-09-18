package com.project.isc.iscdbserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.isc.iscdbserver.service.TradingService;
import com.project.isc.iscdbserver.viewentity.RetMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "交易管理", tags = "交易管理")
@RestController
@RequestMapping("/trading")
@CrossOrigin
public class TradingController {
	
	@Autowired
	private TradingService tradingService;
	
	@ApiOperation(value="测试", notes="")
	@GetMapping("/getTrading")
	public RetMsg getTrading() {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
		tradingService.getCreatewallet();
		tradingService.getIscNumber("0xf0048fa1d1c89ca60d6280addd535ff72d40e91d");
		
		retMsg.setCode(200);
		retMsg.setData("");
		retMsg.setMessage("交易管理测试");
		retMsg.setSuccess(true);

		return retMsg;
	}
	
	@ApiOperation(value="创建钱包", notes="")
	@GetMapping("/getTradingCreate")
	public RetMsg getTradingCreate(@RequestParam("userid") String id) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
		try {
			retMsg = tradingService.getCreatewallet();
			retMsg.getData();
		} catch (Exception e) {
			retMsg.setCode(400);
			retMsg.setData("创建钱包失败!");
			retMsg.setMessage("创建钱包失败，请联系管理员解决!");
			retMsg.setSuccess(true);

		}
		return retMsg;
	}
	
	@ApiOperation(value="钱包查询", notes="")
	@GetMapping("/getTradingFind")
	public RetMsg getTradingFind(@RequestParam("userid") String userid,@RequestParam("address") String address) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
		try {
			retMsg = tradingService.getIscNumber(address);
		} catch (Exception e) {
			retMsg.setCode(400);
			retMsg.setData("0");
			retMsg.setMessage("钱包查询异常");
			retMsg.setSuccess(true);

		}
		return retMsg;
	}
}
