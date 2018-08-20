package com.project.isc.iscdbserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.isc.iscdbserver.entity.CalculateStatistics;
import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.repository.CalculateLogRepository;
import com.project.isc.iscdbserver.repository.CalculateRepository;
import com.project.isc.iscdbserver.repository.CalculateStatisticsRepository;
import com.project.isc.iscdbserver.repository.ISCLogRepository;
import com.project.isc.iscdbserver.statusType.ISCConstant;

/**
 * 算力服务
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
	
	public List<ISCLog> getCalculateLogByUserId(Long userid) {
		//这里只出现新增的
		List<ISCLog> isclogs = iscLogRepository.findByUserId(userid);
		return isclogs;
	}
	
	public ISCLog getCalculateLog(Long logid) {
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
}
