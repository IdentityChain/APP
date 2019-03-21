package com.project.isc.iscdbserver.repository;

import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.entity.TradingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TradingLogRepository extends JpaRepository<TradingLog, String>, JpaSpecificationExecutor<TradingLog> {

	List<TradingLog> findByUserId(String userid);

}
