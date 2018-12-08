package com.project.isc.iscdbserver.service;

import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.achieve.Achievement;
import com.project.isc.iscdbserver.entity.achieve.AchievementUser;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<AchievementUser> getAchievementUsers(String userid,String type,int page,int pagesize){
		Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "createTime");
		List<AchievementUser> achievementUserList = achievementUserRepository.findAchievementUsersByUserIdAndType(userid,type,pageable);
		return achievementUserList;
	}

	public Map<String,Achievement> getAchievementsBy(List<String> ids){
		List<Achievement> achievements = achievementRepository.findAchievementsByIdIn(ids);
		Map<String,Achievement> map = null;
		if(achievements!=null&& achievements.size()>0){
			map = new HashMap<>();
			for (Achievement am:achievements
				 ) {
				map.put(am.getId(),am);
			}
		}
		return map;
	}

	public Map<String,AchievementUser> getAchievementUsersBy(List<String> ids){
		List<AchievementUser> achievementUserList = achievementUserRepository.findAchievementUsersByAchIdIn(ids);
		Map<String,AchievementUser> map = null;
		if(achievementUserList!=null&& achievementUserList.size()>0){
			map = new HashMap<>();
			for (AchievementUser amu:achievementUserList
					) {
				map.put(amu.getId(),amu);
			}
		}
		return map;
	}

	public Achievement findAchievementOne(String id){
		return achievementRepository.findOne(id);
	}

	public AchievementUser findAchievementUserOne(String id){
		return achievementUserRepository.findOne(id);
	}

	public boolean insertAchievement(Achievement achievement){
		try {

			achievementRepository.save(achievement);
			return  true;
		}catch (Exception e){
			return false;
		}
	}

	public boolean insertAchievementUser(AchievementUser achievementUser){
		try {
			achievementUserRepository.save(achievementUser);
			return  true;
		}catch (Exception e){
			return false;
		}
	}

	public AchievementUser findAchievementUserByUserIdAndAchId(String userid,String achid){
		return achievementUserRepository.findAchievementUserByUserIdAndAchId(userid,achid);
	}

	public List<Achievement> findAllAchievement(){
		return achievementRepository.findAll();
	}
}
