package com.project.isc.iscdbserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.isc.iscdbserver.viewentity.RetMsg;

@Service
public class TradingService {

	@Autowired
    private RestTemplate restTemplate;
    
	@Value("${app.tradingurl.host}")
	private String tradingurlHost;
	@Value("${app.tradingurl.port}")
	private String tradingurlPort;
	
	
    public RetMsg getIscNumber(String address) {
    		String getIscNumber ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/getiscbanvalue/";
//    		String getIscNumber ="";
    		RetMsg retMsg  = this.restTemplate.getForObject(getIscNumber+address,RetMsg.class);
    		return retMsg;
    }
    
    public RetMsg getCreatewallet() {
    		String getCreatewallet ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/createwallet";
//    		String getCreatewallet ="";
    		RetMsg retMsg  = this.restTemplate.getForObject(getCreatewallet,RetMsg.class);
    		return retMsg;
    }

    public RetMsg sendMassage(Object object){
		String getCreatewallet ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/createwallet";
//    		String getCreatewallet ="";
		RetMsg retMsg  = this.restTemplate.getForObject(getCreatewallet,RetMsg.class);
		return retMsg;
	}

	public RetMsg getMassage(String blockid){
		String getCreatewallet ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/createwallet";
//    		String getCreatewallet ="";
		RetMsg retMsg  = this.restTemplate.getForObject(getCreatewallet,RetMsg.class);
		return retMsg;
	}
}
