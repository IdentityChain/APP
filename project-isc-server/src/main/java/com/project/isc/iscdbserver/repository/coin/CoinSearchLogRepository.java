package com.project.isc.iscdbserver.repository.coin;

import com.project.isc.iscdbserver.entity.coin.CoinSearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinSearchLogRepository extends JpaSpecificationExecutor<CoinSearchLog>,JpaRepository<CoinSearchLog, String> {

}
