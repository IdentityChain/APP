package com.project.isc.iscdbserver.controller;

import com.project.isc.iscdbserver.entity.FutureLetters;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.FutureLettersService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.util.StringUtils;
import com.project.isc.iscdbserver.viewentity.RetMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 *
 * @author ISC
 *
 */
@Api(value = "区块链操作", tags = "区块链操作")
@RestController
@RequestMapping("/futureLetters")
@CrossOrigin
public class FutureLettersController {

    @Autowired
    private FutureLettersService futureLettersService;

    @Autowired
    private UserService userService;

    //写入到区块链
    @ApiOperation(value="写入到区块链", notes="")
    @PostMapping("/sendmessage")
    @Transactional
    public RetMsg sendmessage(@RequestParam String userid,@RequestParam  String flMessage) {
        RetMsg retMsg = new RetMsg();
        String flId = "";
        if(StringUtils.getStringisNotNull(userid)){
            User user = userService.getUserById(userid);
            if(user!=null){
                flId = futureLettersService.sendMessage(userid,flMessage);
            }

        }



        retMsg = new RetMsg();
        retMsg.setCode(200);
        retMsg.setData(flId);
        retMsg.setMessage("写入到区块链成功");
        retMsg.setSuccess(true);
        return retMsg;
    }

    //得到写入区块链的信息
    @ApiOperation(value="得到写入区块链的信息", notes="")
    @PostMapping("/getmessage")
    @Transactional
    public RetMsg getmessage(@RequestParam String userid,@RequestParam  String flid) {
        RetMsg retMsg = new RetMsg();
        String flMessage = "";
        if(StringUtils.getStringisNotNull(userid)){
            User user = userService.getUserById(userid);
            if(user!=null){
                FutureLetters futureLetters = futureLettersService.getFutureLetters(flid);
                flMessage = futureLetters.getFlMessage();
            }

        }



        retMsg = new RetMsg();
        retMsg.setCode(200);
        retMsg.setData(flMessage);
        retMsg.setMessage("读取信息成功");
        retMsg.setSuccess(true);
        return retMsg;
    }
}
