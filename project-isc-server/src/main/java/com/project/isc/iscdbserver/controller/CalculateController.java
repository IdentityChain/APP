package com.project.isc.iscdbserver.controller;

import com.project.isc.iscdbserver.annotation.Auth;
import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.entity.achieve.Achievement;
import com.project.isc.iscdbserver.entity.achieve.AchievementUser;
import com.project.isc.iscdbserver.service.AchievementService;
import com.project.isc.iscdbserver.service.ActivtyService;
import com.project.isc.iscdbserver.service.CalculateService;
import com.project.isc.iscdbserver.service.UserService;
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
				achievementVOS.add(AchievementTransf.transfToVO(aa,au));
			}
			retMsg.setData(achievementVOS);
		}
		return retMsg;
	}

	@ApiOperation(value="根据任务ID获得此任务的具体描述情况", notes="")
	@GetMapping("/getAchievement/{achievementid}")
	@Transactional
//	@Auth
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
//	@Auth
	public RetMsg getAchievementUser(@PathVariable("achievementUserid") String achievementUserid){
		RetMsg retMsg = new RetMsg();
		retMsg.setCode(200);
		AchievementUser achievementUser = calculateService.findAchievementUserOne(achievementUserid);
		retMsg.setData(achievementUser);
		retMsg.setMessage("成功");
		retMsg.setSuccess(true);
		return retMsg;
	}


	@ApiOperation(value="获得用户每日任务情况", notes="")
	@GetMapping("/test")
	@Transactional
	public void   test(){
		int[] steps = {1,30,100,1000,10000};
		for (int step:steps
			 ) {
			Achievement aa = new Achievement();
			aa.setAvailable(true);
			aa.setTitle("标题：每日任务-签到-连续签到："+step+"天");
			aa.setAddiscValue(0);//每日签到不给ISC
			aa.setContent("内容：每日签到获得的成就");
			aa.setCreateTime(new Date());
			aa.setGrayImgPath("这个图片路径需要提供，界面的相对路径");
			aa.setImg_path("这个图片路径需要提供，界面的相对路径");
			aa.setType("type");
			aa.setCalculateValue(10);
			aa.setSteps(step);
			calculateService.insertAchievement(aa);
		}
	}
}
