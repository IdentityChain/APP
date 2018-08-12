package com.project.isc.iscdbserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.repository.CalculateLogRepository;
import com.project.isc.iscdbserver.repository.CalculateRepository;
import com.project.isc.iscdbserver.repository.CalculateStatisticsRepository;
import com.project.isc.iscdbserver.repository.ISCLogRepository;

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
	
	public List<ISCLog> getCalculateLog(Long userid) {
		List<ISCLog> isclogs = iscLogRepository.findByUserId(userid);
		return isclogs;
	}
}
