package com.project.isc.iscdbserver.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.ActivtyService;
import com.project.isc.iscdbserver.service.CalculateService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import com.project.isc.iscdbserver.viewentity.CalculateStatisticsVO;
import com.project.isc.iscdbserver.viewentity.RetMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author ISC
 *
 */
@Api(value = "算力管理", tags = "算力管理")
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
	
	@ApiOperation(value="获得算力排行100", notes="")
	@GetMapping("/getCalculateStatistic100")
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
			ccsvos.add(vo);
		}
		
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(ccsvos);
		retMsg.setMessage("获得算力排行100");
		retMsg.setSuccess(true);

		return retMsg;
	}
	
	//获得可挖的数据
	@ApiOperation(value="获得可挖的数据", notes="")
	@GetMapping("/getCalculateLog/{userid}")
	@Transactional
	public RetMsg getCalculateLog(@PathVariable("userid") Long userid) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
//		User user = userService.getUserById(userid);
		List<ISCLog> isclogs = calculateService.getCalculateLogByUserId(userid);
		
		
		
		retMsg = new RetMsg();
		retMsg.setCode(200);
		retMsg.setData(isclogs);
		retMsg.setMessage("获得可挖的数据成功");
		retMsg.setSuccess(true);

		return retMsg;
	}
	
	//用户点击挖矿数据
	@ApiOperation(value="用户点击挖矿数据", notes="")
	@GetMapping("/checkCalculateLog/{logid}")
	@Transactional
	public RetMsg checkCalculateLog(@PathVariable("logid") Long logid) {
		// 如果数据校验有误，则直接返回校验错误信息
		RetMsg retMsg = new RetMsg();
		
//		User user = userService.getUserById(userid);
		String massage = "数据异常";
		ISCLog isclog = calculateService.getCalculateLog(logid);
		//状态是新增的处理
		if(isclog!=null && ISCConstant.ISC_LOG_NEW.equals(isclog.getStatus())) {
			isclog.setConfirmTime(new Date());
			
			Long userid = isclog.getUserId();
			User user = userService.getUserById(userid);
			user.setIscCoin(user.getIscCoin()+isclog.getAddISC());
			
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
	
}
