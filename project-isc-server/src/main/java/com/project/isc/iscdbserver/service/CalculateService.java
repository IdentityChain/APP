package com.project.isc.iscdbserver.service;

import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.achieve.Achievement;
import com.project.isc.iscdbserver.repository.CalculateLogRepository;
import com.project.isc.iscdbserver.repository.CalculateRepository;
import com.project.isc.iscdbserver.repository.CalculateStatisticsRepository;
import com.project.isc.iscdbserver.repository.ISCLogRepository;
import com.project.isc.iscdbserver.repository.achieve.AchievementRepository;
import com.project.isc.iscdbserver.repository.achieve.AchievementUserRepository;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 成就服务
 * @author ISC
 *
 */
@Service
public class CalculateService {
	@Autowired
	private CalculateRepository calculateRepository;
	@Autowired
	private CalculateLogRepository calculateLogRepository;
	@Autowired
	private CalculateStatisticsRepository calculateStatisticsRepository;
	@Autowired
	private ISCLogRepository iscLogRepository;
	@Autowired
	private AchievementRepository achievementRepository;
	@Autowired
	private AchievementUserRepository achievementUserRepository;
	
	/**
	 * 这个用户的所有数据
	 * @param userid
	 * @return
	 */
	public List<ISCLog> getCalculateLogByUserId(String userid) {
		//这里只出现新增的
		List<ISCLog> isclogs = iscLogRepository.findByUserId(userid);
		return isclogs;
	}
	
	public List<ISCLog> getCalculateLogByUserIdAndStatus(String userid) {
		//这里只出现新增的
		List<ISCLog> isclogs = iscLogRepository.findByUserIdAndStatus(userid,ISCConstant.ISC_LOG_NEW);
		return isclogs;
	}
	
	public ISCLog getCalculateLog(String logid) {
		ISCLog isclog = iscLogRepository.findOne(logid);
		return isclog;
	}

	public List<CalculateStatistics> getTop100(){
		List<CalculateStatistics> ccss = calculateStatisticsRepository.findAll();
		return ccss;
	}
	
	public ISCLog saveCalculateLog(ISCLog isclog) {
		isclog = iscLogRepository.save(isclog);
		return isclog;
	}
	
	public List<ISCLog> getAllCalculateLog() {
		//这里只出现新增的
		List<ISCLog> isclogs = iscLogRepository.findAll();
		return isclogs;
	}
	
	/**
	 * 保存所有矿数据
	 * @param isclogs
	 */
	public void savaAllCalculateLog(List<ISCLog> isclogs) {
		iscLogRepository.save(isclogs);
	}
	

	/**
	 * 保存所有排名信息
	 * @param ccss
	 */
	public void saveAllCalculateStatistics(List<CalculateStatistics> ccss) {
		calculateStatisticsRepository.save(ccss);
	}
	
	public void deleteAllCalculateStatistics() {
		calculateStatisticsRepository.deleteAll();
	}
	
	
	public List<ISCLog> getAllCalculateLogByCreateTime(Date date,String status) {
		//这里只出现新增的
		List<ISCLog> isclogs = iscLogRepository.findByCreateTimeLessThanAndStatus(date,status);
		return isclogs;
	}

	/**
	 * 获得用户可用的
	 * @param userid
	 * @param type
	 * @return
	 */
	public List<Achievement> getAchievements(String userid,String type,int page,int pagesize){
		Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "createTime");
		List<Achievement> achievements = achievementRepository.findAchievementsByAvailableAndType(true,type,pageable);

		return achievements;
	}
}
