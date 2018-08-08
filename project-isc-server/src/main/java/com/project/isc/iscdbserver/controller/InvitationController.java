package com.project.isc.iscdbserver.controller;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.isc.iscdbserver.entity.Invitation;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.InvitationService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.viewentity.InvitaInfoVo;
import com.project.isc.iscdbserver.viewentity.RetMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author ISC
 *
 */
@Api(value = "邀请管理", tags = "邀请管理")
@RestController
@RequestMapping("/invita")
@CrossOrigin
public class InvitationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InvitationService invitationService;
	
	/**
	 * 获得用户邀请链接
	 * @param userid
	 * @return
	 */
	@ApiOperation(value="获得用户邀请码", notes="")
	@GetMapping("/getUserInvitaCode/{userid}")
	@Transactional
	public RetMsg getUserInvitaCode(@PathVariable("userid") Long userid) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
		User user = userService.getUserById(userid);
		
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(user.getInvitationCode());
		retMsg.setMessage("用户获得邀请码成功");
		retMsg.setSuccess(true);

		return retMsg;
	}
	
	/**
	 * 获得本用户邀请的人数和总共获得的ISC数量
	 * @param userid
	 * @return
	 */
	@ApiOperation(value="获得本用户邀请的人数和总共获得的ISC数量", notes="")
	@GetMapping("/getUserInvitaInfo/{userid}")
	public RetMsg getUserInvitaInfo(@PathVariable("userid") Long userid) {
		int size = invitationService.getInvitationCountByUser(userid);
		InvitaInfoVo ivo = new InvitaInfoVo(size);
		RetMsg retMsg = new RetMsg();
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(ivo);
		retMsg.setMessage("用户获得邀请码成功");
		retMsg.setSuccess(true);

		return retMsg;
	}
	
	@GetMapping("/test")
	public RetMsg testaddInvita() {
		Invitation iv = new Invitation();
		iv.setSourceUserId(3);
		iv.setToUserId(15);
		iv.setCreateTime(new Date());
		
		this.invitationService.save(iv);
		RetMsg retMsg = new RetMsg();
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData("xx");
		retMsg.setMessage("成功");
		retMsg.setSuccess(true);
		return retMsg;
	}
}
