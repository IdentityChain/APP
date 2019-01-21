package com.project.isc.iscdbserver.controller;


import java.util.Date;
import java.util.List;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.transaction.Transactional;

import com.project.isc.iscdbserver.transfEntity.UserTransf;
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
	public RetMsg getUserInvitaCode(@PathVariable("userid") String userid) {
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
	@ApiOperation(value="获得本用户邀请的人数", notes="")
	@GetMapping("/getUserInvitaInfo/{userid}")
	public RetMsg getUserInvitaInfo(@PathVariable("userid") String userid) {
		int size = invitationService.getInvitationCountByUser(userid);
		RetMsg retMsg = new RetMsg();
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(size);
		retMsg.setMessage("获得本用户邀请的人数成功");
		retMsg.setSuccess(true);
		return retMsg;
	}

	/**
	 * 获得本用户邀请的人数和总共获得的ISC数量
	 * @param userid
	 * @return
	 */
	@ApiOperation(value="获得本用户邀请的人数对象", notes="")
	@GetMapping("/getUserInvitaInfoObject/{userid}")
	public RetMsg getUserInvitaInfoObject(@PathVariable("userid") String userid) {
		Invitation invitation = invitationService.getInvitationByUserId(userid);
		RetMsg retMsg = new RetMsg();
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(invitation);
		retMsg.setMessage("获得本用户邀请的人数对象成功");
		retMsg.setSuccess(true);
		return retMsg;
	}

	/**
	 *
	 * @param userid
	 * @return
	 */
	@ApiOperation(value="获得本用户邀请的人数列表", notes="")
	@GetMapping("/getUserInvitaInfo/{userid}/{page}/{pagesize}")
	public RetMsg getInvitaUserList(@PathVariable("userid") String userid,@PathVariable("page") int page,@PathVariable("pagesize") int pagesize) {
		User user = userService.getUserById(userid);
		List<User> users = userService.findUserListByInvite(user.getInvitationCode(),page,pagesize);
		RetMsg retMsg = new RetMsg();
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(UserTransf.transfToVOSimple(users));
		retMsg.setMessage("获得本用户邀请的人数列表成功");
		retMsg.setSuccess(true);
		return retMsg;
	}

	/**
	 * 生成邀请对象
	 * @param userid
	 */
	public void createInvitationByUserId(String userid) {
		User user = userService.getUserById(userid);
		if(user!=null){
			String invataCode = user.getInvitationCode();
			int first = userService.countByPinvitationCode(invataCode);
			int second = userService.countByPinvitationCodeSecond(invataCode);
			Invitation invitation = new Invitation();
			invitation.setCreateTime(new Date());
			invitation.setSourceCode(invataCode);
			invitation.setSourceUserId(userid);
			invitation.setInvitaFirstNumber(first);
			invitation.setInvitaSecoundNumber(second);
			invitation.setInvitaThreeNumber(0);
			invitation.setInvitaFourNumber(0);
			invitationService.save(invitation);
		}
	}

	@GetMapping("/test")
	public RetMsg testaddInvita() {
		List<User> users = userService.findAll();
		if(users!=null){
			for (User user:users){
				createInvitationByUserId(user.getUserId());
			}
		}
		return null;
	}
}
