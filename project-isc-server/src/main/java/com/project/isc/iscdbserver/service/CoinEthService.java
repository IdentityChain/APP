package com.project.isc.iscdbserver.service;

import com.project.isc.iscdbserver.entity.coin.CoinETHAddress;
import com.project.isc.iscdbserver.entity.coin.CoinSearchLog;
import com.project.isc.iscdbserver.repository.coin.CoinETHAddressRepository;
import com.project.isc.iscdbserver.repository.coin.CoinSearchLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CoinEthService {

    @Autowired
    private CoinETHAddressRepository coinETHAddressRepository;

    @Autowired
    private CoinSearchLogRepository coinSearchLogRepository;


    public boolean recodeCreateWallet(String userid,String createInfo,String ethAddress,String key,String type){
        try{
            CoinETHAddress coinETHAddress = new CoinETHAddress();
            coinETHAddress.setAvailable(true);
            coinETHAddress.setCreateInfo(createInfo);
            coinETHAddress.setEthAddress(ethAddress);
            coinETHAddress.setKeyString(key);
            coinETHAddress.setCreateTime(new Date());
            coinETHAddress.setUserId(userid);
            coinETHAddress.setType(type);
            coinETHAddressRepository.save(coinETHAddress);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean recodeLog(String userid,String content,String contTotal,String type){
        try{
            CoinSearchLog coinSearchLog = new CoinSearchLog();
            coinSearchLog.setCreateTime(new Date());
            coinSearchLog.setLogContent(content);
            coinSearchLog.setType(type);
            coinSearchLog.setUserid(userid);
            coinSearchLogRepository.save(coinSearchLog);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
