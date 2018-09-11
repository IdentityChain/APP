package com.project.isc.iscdbserver.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.project.isc.iscdbserver.entity.ISCLog;

@Repository
public interface ISCLogRepository extends JpaRepository<ISCLog, Long>, JpaSpecificationExecutor<ISCLog> {

	List<ISCLog> findByUserId(Long userid);

	List<ISCLog> findByCreateTimeLessThanAndStatus(Date date,String status);

	List<ISCLog> findByUserIdAndStatus(Long userid, String status);

}
