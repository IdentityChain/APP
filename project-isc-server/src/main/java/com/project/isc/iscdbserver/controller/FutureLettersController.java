package com.project.isc.iscdbserver.controller;

import com.project.isc.iscdbserver.service.FutureLettersService;
import com.project.isc.iscdbserver.transfEntity.UserTransf;
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
@Api(value = "写入到区块链", tags = "写入到区块链")
@RestController
@RequestMapping("/futureLetters")
@CrossOrigin
public class FutureLettersController {

    @Autowired
    private FutureLettersService futureLettersService;

    //写入到区块链
    @ApiOperation(value="写入到区块链", notes="")
    @PostMapping("/sendmessage")
    @Transactional
    public RetMsg sendmessage(@RequestParam String userid,@RequestParam  String flMessage) {

        String flId = futureLettersService.sendMessage(userid,flMessage);
        RetMsg retMsg = new RetMsg();
        retMsg = new RetMsg();
        retMsg.setCode(200);
        retMsg.setData(flId);
        retMsg.setMessage("写入到区块链成功");
        retMsg.setSuccess(true);
        return retMsg;
    }
}
