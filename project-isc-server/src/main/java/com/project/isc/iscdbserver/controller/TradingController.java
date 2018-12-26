package com.project.isc.iscdbserver.controller;

import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.CoinEthService;
import com.project.isc.iscdbserver.service.TradingService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.util.StringUtils;
import com.project.isc.iscdbserver.viewentity.ISCInfoVo;
import com.project.isc.iscdbserver.viewentity.RetMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "交易管理", tags = "交易管理")
@RestController
@RequestMapping("/trading")
@CrossOrigin
public class TradingController {

    private static final String typeCreate="create";
    private static final String typeSelect="Select";

    @Autowired
    private TradingService tradingService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoinEthService coinEthService;

    @ApiOperation(value = "创建钱包", notes = "")
    @PostMapping("/getTradingCreate")
    public RetMsg getTradingCreate(@RequestParam("userid") String userid) {
        // 如果数据校验有误，则直接返回校验错误信息
        RetMsg retMsg = new RetMsg();
        String logs ="";
        String logstotal = "用户创建："+userid+"\t";
        try {
            if (StringUtils.getStringisNotNull(userid)) {
                User user = userService.getUserById(userid);
                if (user != null && (user.getEthAddress()==null || "".equals(user.getEthAddress()))) {
                    retMsg = tradingService.getCreatewallet();
                    if(retMsg!=null && retMsg.getData()!=null){
                        Map<String,String> map = (Map<String,String> )retMsg.getData();
                        String privatekey = map.get("privatekey");
                        String address = map.get("address");
                        logs ="创建的地址为："+address;
                        coinEthService.recodeCreateWallet(userid,typeCreate,address,privatekey,typeCreate);
                        user.setEthAddress(address);
                        userService.save(user);
                    }else {
                        logs ="创建接口调用返回为空";
                    }
                    savelog(userid,logs,logstotal+logs,typeCreate);
                    retMsg.setData(logs);
                    return retMsg;
                } else {
                    logs="没有这个用户或者此用户已分配ISC钱包地址";
                    retMsg.setData(logs);
                }
            } else {
                logs="ID为空";
                retMsg.setData(logs);
            }
            retMsg.setCode(400);
            logs="创建钱包异常";
            retMsg.setMessage(logs);
            retMsg.setSuccess(true);

            return retMsg;
        } catch (Exception e) {
            retMsg.setCode(400);
            logs="创建钱包失败";
            retMsg.setData(logs);
            retMsg.setMessage("创建钱包失败，请联系管理员解决!");
            retMsg.setSuccess(true);
            savelog(userid,logs,logstotal+logs,typeCreate);
            return retMsg;
        }
    }

    @ApiOperation(value = "钱包查询", notes = "")
    @GetMapping("/getTradingFind/{userid}/{address}")
    public RetMsg getTradingFind(@PathVariable("userid") String userid, @PathVariable("address") String address) {
        // 如果数据校验有误，则直接返回校验错误信息
        RetMsg retMsg = new RetMsg();
        String logstotal = "用户"+userid+"调用查询接口，查询地址："+address+"\t";
        String logs = "";
        try {
            if (StringUtils.getStringisNotNull(userid)) {
                User user = userService.getUserById(userid);
                if (user != null) {
                    retMsg = tradingService.getIscNumber(address);
                    logs="值为：";
                    if(retMsg!=null && retMsg.getData()!=null){
                        logs = "结果为："+retMsg.getData().toString();
                    }
                } else {
                    logs ="没有这个用户";
                    retMsg.setData(logs);
                }
            } else {
                logs="ID为空";
                retMsg.setData(logs);
            }
            retMsg.setCode(200);
            retMsg.setMessage(logs);
            retMsg.setSuccess(true);
            savelog(userid,logs,logstotal+logs,typeSelect);
            return retMsg;
        } catch (Exception e) {
            retMsg.setCode(400);
            retMsg.setData("0");
            logs="钱包查询异常";
            retMsg.setMessage(logs);
            retMsg.setSuccess(true);
            savelog(userid,logs,logstotal+logs,typeSelect);
            return retMsg;
        }
    }

    private void savelog(String userid,String content,String contTotal,String type){
        try{
            coinEthService.recodeLog(userid,content,contTotal,type);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "钱包监控", notes = "")
    @GetMapping("/getTradingetFindALL")
    public RetMsg getTradingFind() {
        // 如果数据校验有误，则直接返回校验错误信息
        RetMsg retMsg = new RetMsg();
        List<ISCInfoVo> list = getISCInfoVo();
        for (ISCInfoVo ii:list
                ) {
            try {
                retMsg = tradingService.getIscNumber(ii.getAddress());
                changeISC(ii,Long.getLong(retMsg.getData().toString()));
            } catch (Exception e) {
                ii.setName("【无法验证】"+ii.getName()+"[接口调用失败]");
            }
            System.out.println(ii.toString());
        }

        return retMsg;
    }

    private List<ISCInfoVo> getISCInfoVo(){
        List<ISCInfoVo> lisc = new ArrayList<ISCInfoVo>();
        ISCInfoVo iizzl1 =new ISCInfoVo("zzl1","朱自立","10.1日福利","ISC","0x361ab63A1539dF92dAa3A5E59EC6E55F3cE22Dcb",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl2 =new ISCInfoVo("zzl2","朱自立","BI交易所ISC","ISC","0xa308de0567e0d07ad0c686caabf91435930ad627",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl3 =new ISCInfoVo("zzl3","朱自立","BI交易所USDT","USDT","1BiWdJrWWuDg95LtmmQeazQkyAu3KFHBhR",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl4 =new ISCInfoVo("zzl3","朱自立","BI交易所ETH[无]","ETH","",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl5 =new ISCInfoVo("zzl4","朱自立","zg交易所ISC","ISC","0xc7b9b787350a8c6fc22288946811c9b01c4a0aea",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl6 =new ISCInfoVo("zzl5","朱自立","zg交易所USDT","USDT","1FzFCxyBzomgqmCW3P4vSgTzXav8NEXrLH",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl7 =new ISCInfoVo("zzl5","朱自立","zg交易所ETH","ETH","",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl8 =new ISCInfoVo("zzl6","朱自立","币乐交易所ISC","ISC","",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl9 =new ISCInfoVo("zzl6","朱自立","币乐交易所USDT","USDT","",0,50000,-1,"2018年11月2日","2018年11月2日");
        ISCInfoVo iizzl10 =new ISCInfoVo("zzl6","朱自立","币乐交易所ETH","ETH","",0,50000,-1,"2018年11月2日","2018年11月2日");
        return lisc;

    }

    private void changeISC(ISCInfoVo ii,long number){
        ii.setValidationNumber(number);
        ii.setDifferenceNumber(ii.getValidationNumber()-ii.getReportNumber());
    }
}
