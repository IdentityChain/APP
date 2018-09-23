package com.project.isc.iscdbserver.service;

import com.project.isc.iscdbserver.entity.FutureLetters;
import com.project.isc.iscdbserver.repository.FutureLettersRepository;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FutureLettersService {

    @Autowired
    private FutureLettersRepository futureLettersRepository;

    public String sendMessage(String userid,String flMessage){
        //保存到数据库
        FutureLetters futureLetters = new FutureLetters();
        futureLetters.setUserId(userid);
        futureLetters.setCreateTime(new Date());
        futureLetters.setFlEncryption("MD5");
        futureLetters.setFlStatus(ISCConstant.ISC_FL_STATUS_NEW);
        futureLetters.setFlMessage(flMessage);
        futureLettersRepository.save(futureLetters);
        //保存到区块链
        getBlockChainID(futureLetters);
        return futureLetters.getFlId();
    }

    public String getBlockChainID(FutureLetters futureLetters){
        //保存到区块链
        return null;
    }
}
