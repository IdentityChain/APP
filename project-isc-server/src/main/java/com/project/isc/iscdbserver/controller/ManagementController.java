package com.project.isc.iscdbserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.isc.iscdbserver.entity.Announcement;
import com.project.isc.iscdbserver.service.ManagementService;
import com.project.isc.iscdbserver.viewentity.RetMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author ISC
 *
 */
@Api(value = "系统管理", tags = "系统管理")
@RestController
@RequestMapping("/management")
@CrossOrigin
public class ManagementController {

	@Autowired
	private ManagementService managementService;
	
	@ApiOperation(value="获得最新公告", notes="")
	@GetMapping("/getNewAnnouncement")
	public RetMsg getNewAnnouncement() {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		Announcement announcement = managementService.getNewAnnouncement();
		retMsg.setCode(200);
		retMsg.setData(announcement);
		retMsg.setMessage("获得最新公告");
		retMsg.setSuccess(true);
		
		return retMsg;
	}
}
