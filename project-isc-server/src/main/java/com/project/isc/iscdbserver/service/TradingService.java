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

	/**
	 * 查询ISC数目
	 * @param address
	 * @return
	 */
    public RetMsg getIscNumber(String address) {
    		String getIscNumber ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/getiscbanvalue/";
    		RetMsg retMsg  = this.restTemplate.getForObject(getIscNumber+address,RetMsg.class);
    		return retMsg;
    }

	/**
	 * 创建ISC钱包
	 * @return
	 */
	public RetMsg getCreatewallet() {
    		String getCreatewallet ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/createwallet";
    		RetMsg retMsg  = this.restTemplate.getForObject(getCreatewallet,RetMsg.class);
    		return retMsg;
    }

	/**
	 * 发送信息到区块链
	 * @param object
	 * @return
	 */
	public RetMsg sendMassage(Object object){
		String sendMassagestr ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/setcontent/";
		RetMsg retMsg  = this.restTemplate.getForObject(sendMassagestr+object.toString(),RetMsg.class);
		return retMsg;
	}

	/**
	 * 查询区块链中哈希值的数据
	 * 参考：
	 * 0x2cf56048f2e075ed9380cb1a633e9e2c99cc856c6a04a84f85cccd30a819e24c
	 * @param blockid
	 * @return
	 */
	public RetMsg getSendMassage(String blockid){
		String getSendMassagestr ="http://"+tradingurlHost+":"+tradingurlPort+"/ethapi/getcontent/";
		RetMsg retMsg  = this.restTemplate.getForObject(getSendMassagestr+blockid,RetMsg.class);
		return retMsg;
	}
}
