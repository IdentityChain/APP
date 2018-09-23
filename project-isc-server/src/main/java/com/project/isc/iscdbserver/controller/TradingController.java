package com.project.isc.iscdbserver.controller;

import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.isc.iscdbserver.service.TradingService;
import com.project.isc.iscdbserver.viewentity.RetMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "交易管理", tags = "交易管理")
@RestController
@RequestMapping("/trading")
@CrossOrigin
public class TradingController {

    @Autowired
    private TradingService tradingService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "创建钱包", notes = "")
    @PostMapping("/getTradingCreate")
    public RetMsg getTradingCreate(@RequestParam("userid") String userid) {
        // 如果数据校验有误，则直接返回校验错误信息
        RetMsg retMsg = new RetMsg();

        try {
            if (StringUtils.getStringisNotNull(userid)) {
                User user = userService.getUserById(userid);
                if (user != null) {
                    retMsg = tradingService.getCreatewallet();
                    return retMsg;
                } else {
                    retMsg.setData("没有这个用户");
                }
            } else {
                retMsg.setData("ID为空");
            }
            retMsg.setCode(400);

            retMsg.setMessage("钱包查询异常");
            retMsg.setSuccess(true);

            return retMsg;
        } catch (Exception e) {
            retMsg.setCode(400);
            retMsg.setData("创建钱包失败!");
            retMsg.setMessage("创建钱包失败，请联系管理员解决!");
            retMsg.setSuccess(true);
            return retMsg;
        }
    }

    @ApiOperation(value = "钱包查询", notes = "")
    @PostMapping("/getTradingFind")
    public RetMsg getTradingFind(@RequestParam("userid") String userid, @RequestParam("address") String address) {
        // 如果数据校验有误，则直接返回校验错误信息
        RetMsg retMsg = new RetMsg();

        try {
            if (StringUtils.getStringisNotNull(userid)) {
                User user = userService.getUserById(userid);
                if (user != null) {
                    retMsg = tradingService.getIscNumber(address);
                } else {
                    retMsg.setData("没有这个用户");
                }
            } else {
                retMsg.setData("ID为空");
            }
            retMsg.setCode(400);

            retMsg.setMessage("钱包查询异常");
            retMsg.setSuccess(true);

            return retMsg;
        } catch (Exception e) {
            retMsg.setCode(400);
            retMsg.setData("0");
            retMsg.setMessage("钱包查询异常");
            retMsg.setSuccess(true);
            return retMsg;
        }
    }
}
