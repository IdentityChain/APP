package com.project.isc.iscdbserver.repository.coin;

import com.project.isc.iscdbserver.entity.coin.CoinETHAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinETHAddressRepository extends JpaSpecificationExecutor<CoinETHAddress>,JpaRepository<CoinETHAddress, String> {

}
