package com.project.isc.iscdbserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.isc.iscdbserver.util.AppYml;
import com.project.isc.iscdbserver.viewentity.RetMsg;

@Service
public class TradingService {

	@Autowired
    private RestTemplate restTemplate;
    
    public RetMsg getIscNumber(String address) {
    		String getIscNumber ="http://"+AppYml.getTradingurlHost()+":"+AppYml.getTradingurlPort()+"/ethapi/getiscbanvalue/";
    		RetMsg retMsg  = this.restTemplate.getForObject(getIscNumber+address,RetMsg.class);
    		return retMsg;
    }
    
    public RetMsg getCreatewallet() {
    		String getCreatewallet ="http://"+AppYml.getTradingurlHost()+":"+AppYml.getTradingurlPort()+"/ethapi/createwallet";
    		RetMsg retMsg  = this.restTemplate.getForObject(getCreatewallet,RetMsg.class);
    		return retMsg;
    }
}
