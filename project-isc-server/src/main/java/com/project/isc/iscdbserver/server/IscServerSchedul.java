package com.project.isc.iscdbserver.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.ActivtyService;
import com.project.isc.iscdbserver.service.CalculateService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import com.project.isc.iscdbserver.util.TimeUtil;
import com.project.isc.iscdbserver.util.UserLoginSetting;

@Service
public class IscServerSchedul {
	@Autowired
	private UserService userService;
	@Autowired
	private CalculateService calculateService;
	@Autowired
	private ActivtyService activtyService;
	@Autowired
	private UserLoginSetting userLoginSetting;
	
	/**
	 * 统计top100
	 */
	@Transactional
	public void mainTop100() {
		List<User> users = userService.findAllOrderByCalculateValueTop100();
		List<CalculateStatistics> ccss = new ArrayList<CalculateStatistics>();
		if(users!=null && users.size()>0) {
			int i =1;
			for (User user : users) {
				CalculateStatistics ccs = new CalculateStatistics();
				ccs.setCalculateValue(user.getCalculateValue());
				ccs.setCreateTime(new Date());
				ccs.setName(user.getNickName());
				ccs.setRanking(i);
				i++;
				ccs.setUserId(user.getUserId());
				ccss.add(ccs);
				if(i>4) {
					break;
				}
			}
//			calculateService
			calculateService.deleteAllCalculateStatistics();
			calculateService.saveAllCalculateStatistics(ccss);
		}
	}
	//最多可剩余资源数目
	@Value("${app.integral.iscMaxNumber}")
	private int iscMaxNumber;
	//每个资源的大小
	@Value("${app.integral.addIsc}")
	private double addisc;
	/**
	 * 生成矿数据
	 */
	@Transactional
	public void mainISCcoin() {
		Iterable<User> users = userService.getAll();
		List<ISCLog> isclogs = new ArrayList<ISCLog>();
		for (User user : users) {
			List<ISCLog> isculogs = calculateService.getCalculateLogByUserIdAndStatus(user.getUserId());
			//最多出现30个
			if(isculogs!=null && isculogs.size()<iscMaxNumber) {
				ISCLog isclog = new ISCLog();
				isclog.setCreateTime(new Date());
				isclog.setStatus(ISCConstant.ISC_LOG_NEW);
				isclog.setOriginalISC(user.getIscCoin());
				isclog.setAddISC(addisc);
				isclog.setFinallyISC(user.getIscCoin()+addisc);
				isclog.setUserId(user.getUserId());
				isclogs.add(isclog);
			}else {
				//等于空或者大于30个不做处理
			}
		}
		calculateService.savaAllCalculateLog(isclogs);
	}
	
	/**
	 * 修改的矿数据
	 */
	@Transactional
	public void mainDeleteISCcoinlog() {
		Date nowDate = TimeUtil.addDay(new Date(),-2);
		
		List<ISCLog> isclogs = calculateService.getAllCalculateLogByCreateTime(nowDate,ISCConstant.ISC_LOG_NEW);
		if(isclogs!=null && isclogs.size()>0) {
			for (ISCLog iscLog : isclogs) {
				if(ISCConstant.ISC_LOG_NEW.equals(iscLog.getStatus())) {
					iscLog.setStatus(ISCConstant.ISC_LOG_OVER);
					iscLog.setConfirmTime(new Date());
				}
			}
			calculateService.savaAllCalculateLog(isclogs);
		}
	}
}
