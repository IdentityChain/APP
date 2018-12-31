package com.project.isc.iscdbserver.controller;

import com.project.isc.iscdbserver.annotation.Auth;
import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.entity.achieve.Achievement;
import com.project.isc.iscdbserver.entity.achieve.AchievementUser;
import com.project.isc.iscdbserver.server.IscServerSchedul;
import com.project.isc.iscdbserver.service.AchievementService;
import com.project.isc.iscdbserver.service.ActivtyService;
import com.project.isc.iscdbserver.service.CalculateService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.statusType.ConfigConstant;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import com.project.isc.iscdbserver.transfEntity.AchievementTransf;
import com.project.isc.iscdbserver.transfEntity.ISCLogTransf;
import com.project.isc.iscdbserver.viewentity.CalculateStatisticsVO;
import com.project.isc.iscdbserver.viewentity.ISCLogVO;
import com.project.isc.iscdbserver.viewentity.RetMsg;
import com.project.isc.iscdbserver.viewentity.achieve.AchievementVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author ISC
 *
 */
@Api(value = "成就管理", tags = "成就管理")
@RestController
@RequestMapping("/calculate")
@CrossOrigin
public class CalculateController {
	@Autowired
	private UserService userService;
	@Autowired
	private CalculateService calculateService;
	@Autowired
	private ActivtyService activtyService;
	@Autowired
	private AchievementService achievementService;

	@Autowired
	private IscServerSchedul iscServerSchedul;
	
	@ApiOperation(value="获得成就排行100", notes="")
	@GetMapping("/getCalculateStatistic100")
	@Auth
	public RetMsg getCalculateStatistic100() {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
//		calculateStatistics.get
		List<CalculateStatisticsVO> ccsvos = new ArrayList<CalculateStatisticsVO>();
//		for (int i = 1; i < 100; i++) {
//			CalculateStatisticsVO vo = new CalculateStatisticsVO();
//			vo.setName("zs"+i);
//			vo.setCcsId(i);
//			vo.setCreateTime(new Date());
//			vo.setCalculateValue(168-i);
//			vo.setRanking(i);
//			ccsvos.add(vo);
//		}
		List<CalculateStatistics> ccss =calculateService.getTop100();
		for (CalculateStatistics ccs : ccss) {
			CalculateStatisticsVO vo = new CalculateStatisticsVO();
			vo.setCreateTime(ccs.getCreateTime());
			vo.setCalculateValue(ccs.getCalculateValue());
			vo.setRanking(ccs.getRanking());
			vo.setName(ccs.getName());
			ccsvos.add(vo);
		}
		
		retMsg.setCode(200);
		retMsg.setData(ccsvos);
		retMsg.setMessage("获得成就排行100");
		retMsg.setSuccess(true);

		return retMsg;
	}
	
	//获得可挖的数据
	@ApiOperation(value="获得可挖的数据", notes="")
	@GetMapping("/getCalculateLog/{userid}")
	@Transactional
	@Auth
	public RetMsg getCalculateLog(@PathVariable("userid") String userid) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
//		User user = userService.getUserById(userid);
		List<ISCLog> isclogs = calculateService.getCalculateLogByUserIdAndStatus(userid);
		List<ISCLogVO> isclogvos = new ArrayList<ISCLogVO>();
		if (isclogs!=null && isclogs.size()>0) {
			for (ISCLog isclog : isclogs) {
				isclogvos.add(ISCLogTransf.transfToVO(isclog));
			}
		}		
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(isclogvos);
		retMsg.setMessage("获得可挖的数据成功");
		retMsg.setSuccess(true);

		return retMsg;
	}
	
	//用户点击挖矿数据
	@ApiOperation(value="用户点击挖矿数据", notes="")
	@GetMapping("/checkCalculateLog/{logid}")
	@Transactional
	@Auth
	public RetMsg checkCalculateLog(@PathVariable("logid") String logid) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
//		User user = userService.getUserById(userid);
		String massage = "数据异常";
		ISCLog isclog = calculateService.getCalculateLog(logid);
		//状态是新增的处理
		if(isclog!=null && ISCConstant.ISC_LOG_NEW.equals(isclog.getStatus())) {
			isclog.setConfirmTime(new Date());
			
			String userid = isclog.getUserId();
			User user = userService.getUserById(userid);
			user.setIscCoin(user.getIscCoin()+isclog.getAddISC());
			isclog.setStatus(ISCConstant.ISC_LOG_CHECK);
			
			calculateService.saveCalculateLog(isclog);
			userService.save(user);
			massage = "挖矿成功";
		}else {
			massage = "挖矿已成功";
		}
		
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(massage);
		retMsg.setMessage("成功");
		retMsg.setSuccess(true);

		return retMsg;
	}

	/**
	 * 获得用户每日任务情况
	 *
	 * @param userid
	 * @return
	 */
	@ApiOperation(value="获得用户每日任务情况", notes="")
	@GetMapping("/getAchievementDay/{type}/{userid}/{page}/{pagesize}")
	@Transactional
//	@Auth
	public RetMsg getAchievement(@PathVariable("type") String type,@PathVariable("userid") String userid,@PathVariable("page") int page,@PathVariable("pagesize") int pagesize) {
		RetMsg retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData("");
		retMsg.setMessage("成功");
		retMsg.setSuccess(true);
		List<AchievementUser> listau =calculateService.getAchievementUsers(userid,type,page,pagesize);
		if(listau!=null && listau.size()>0){
			List<AchievementVO> achievementVOS = new ArrayList<>();
			for (AchievementUser au: listau
				 ) {
				Achievement aa = calculateService.findAchievementOne(au.getAchId());
				AchievementVO avo = AchievementTransf.transfToVO(aa,au);
				if(ConfigConstant.TASK_TYPE_EVERYDAY.equals(type)){
					avo.setToolText("+"+aa.getCalculateValue());
					avo.setToolType("Text");
					if(aa.getSteps()==1){
						avo.setToolText("签到");
						avo.setToolType("button");
						avo.setToolEvent("checkin");
					}
				}
				achievementVOS.add(avo);
			}
			retMsg.setData(achievementVOS);
		}
		return retMsg;
	}

	@ApiOperation(value="根据任务ID获得此任务的具体描述情况", notes="")
	@GetMapping("/getAchievement/{achievementid}")
	@Transactional
	@Auth
	public RetMsg getAchievement(@PathVariable("achievementid") String achievementid){
		RetMsg retMsg = new RetMsg();
		retMsg.setCode(200);
		Achievement achievement = calculateService.findAchievementOne(achievementid);
		retMsg.setData(achievement);
		retMsg.setMessage("成功");
		retMsg.setSuccess(true);
		return retMsg;
	}

	@ApiOperation(value="根据任务ID获得此任务的具体描述情况", notes="")
	@GetMapping("/getAchievementUser/{achievementUserid}")
	@Transactional
	@Auth
	public RetMsg getAchievementUser(@PathVariable("achievementUserid") String achievementUserid){
		RetMsg retMsg = new RetMsg();
		retMsg.setCode(200);
		AchievementUser achievementUser = calculateService.findAchievementUserOne(achievementUserid);
		retMsg.setData(achievementUser);
		retMsg.setMessage("成功");
		retMsg.setSuccess(true);
		return retMsg;
	}

	@ApiOperation(value="获得任务成就奖励，对满足100条件的", notes="")
	@GetMapping("/getAchievementUser/{userid}/{achievementUserid}/{achievementid}")
	@Transactional
	@Auth
	public RetMsg getAchievementUser(@PathVariable("userid") String userid,@PathVariable("achievementUserid") String achievementUserid,@PathVariable("achievementid") String achievementid){
		RetMsg retMsg = new RetMsg();
		retMsg.setCode(200);
		AchievementUser achievementUser = calculateService.findAchievementUserOne(achievementUserid);
		//!achievementUser.isIs_create()  是否已领取
		if(achievementUser!=null && achievementUser.getUserId().equals(userid) && !achievementUser.isIs_create()){
			Achievement achievement = calculateService.findAchievementOne(achievementid);
			User user = userService.getUserById(userid);
			user.setCalculateValue(user.getCalculateValue()+achievement.getCalculateValue());
			achievementUser.setIs_create(true);
			//更新用户成就表和成就表
			calculateService.insertAchievementUser(achievementUser);
			userService.save(user);
		}
		retMsg.setData("");
		retMsg.setMessage("成功");
		retMsg.setSuccess(true);
		return retMsg;
	}


	@ApiOperation(value="获得用户每日任务情况", notes="")
	@GetMapping("/test")
	@Transactional
	public void   test(){
		int[] steps = {1};
		for (int step:steps
			 ) {
			Achievement aa = new Achievement();
			aa.setAvailable(true);
			aa.setTitle("每日任务-签到");
			aa.setAddiscValue(0);//每日签到不给ISC
			aa.setContent("每日签到获得的成就");
			aa.setCreateTime(new Date());
			aa.setGrayImgPath("images/taskimg/daily/login-done.png");
			aa.setImg_path("images/taskimg/daily/login.png");
			aa.setType(ConfigConstant.TASK_TYPE_EVERYDAY);
			aa.setCalculateValue(10);
			aa.setSteps(step);
			calculateService.insertAchievement(aa);
		}

		Achievement aa = new Achievement();
		aa.setAvailable(true);
		aa.setTitle("每日任务-签到-连续签到：30天");
		aa.setAddiscValue(0);//每日签到不给ISC
		aa.setContent("每日签到获得的成就");
		aa.setCreateTime(new Date());
		aa.setGrayImgPath("images/taskimg/daily/login-done.png");
		aa.setImg_path("images/taskimg/daily/login.png");
		aa.setType(ConfigConstant.TASK_TYPE_ACHIEVE);
		aa.setCalculateValue(10);
		aa.setSteps(30);
		calculateService.insertAchievement(aa);

		Achievement aa1 = new Achievement(0.0,10, "每日签到获得的成就", new Date(), null, null,"images/taskimg/daily/login-done.png", "images/taskimg/daily/login.png",100, "每日任务-签到-连续签到：100天", true,ConfigConstant.TASK_TYPE_ACHIEVE, null);
		Achievement aa2 = new Achievement(0.0,20, "每日签到获得的成就", new Date(), null, null,"images/taskimg/daily/login-done.png", "images/taskimg/daily/login.png",365, "每日任务-签到-连续签到：365天", true,ConfigConstant.TASK_TYPE_ACHIEVE, null);
		Achievement aa3 = new Achievement(0.0,10, "拥有ISC获得的成就", new Date(), null, null,"images/taskimg/daily/login-done.png", "images/taskimg/daily/login.png",100, "拥有100个ISC", true,ConfigConstant.TASK_TYPE_ACHIEVE, null);
		Achievement aa4 = new Achievement(0.0,20, "拥有ISC获得的成就", new Date(), null, null,"images/taskimg/daily/login-done.png", "images/taskimg/daily/login.png",1000, "拥有1000个ISC", true,ConfigConstant.TASK_TYPE_ACHIEVE, null);
		Achievement aa5 = new Achievement(0.0,30, "拥有ISC获得的成就", new Date(), null, null,"images/taskimg/daily/login-done.png", "images/taskimg/daily/login.png",10000, "拥有10000个ISC", true,ConfigConstant.TASK_TYPE_ACHIEVE, null);
		Achievement aa6 = new Achievement(0.0,50, "拥有ISC获得的成就", new Date(), null, null,"images/taskimg/daily/login-done.png", "images/taskimg/daily/login.png",100000, "拥有100000个ISC", true,ConfigConstant.TASK_TYPE_ACHIEVE, null);
		calculateService.insertAchievement(aa1);
		calculateService.insertAchievement(aa2);
		calculateService.insertAchievement(aa3);
		calculateService.insertAchievement(aa4);
		calculateService.insertAchievement(aa5);
		calculateService.insertAchievement(aa6);
	}

	@ApiOperation(value="测试testAddAchievementUser", notes="")
	@GetMapping("/testAddAchievementUser")
	@Transactional
	public void   testAddAchievementUser(){
		iscServerSchedul.createAchievementUserList();
	}
}
