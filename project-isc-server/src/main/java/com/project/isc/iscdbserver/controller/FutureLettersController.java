package com.project.isc.iscdbserver.controller;

import com.project.isc.iscdbserver.entity.FutureLetters;
import com.project.isc.iscdbserver.entity.User;
import com.project.isc.iscdbserver.service.FutureLettersService;
import com.project.isc.iscdbserver.service.TradingService;
import com.project.isc.iscdbserver.service.UserService;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import com.project.isc.iscdbserver.transfEntity.FutureLettersTransf;
import com.project.isc.iscdbserver.util.StringUtils;
import com.project.isc.iscdbserver.viewentity.FuturLettersRequest;
import com.project.isc.iscdbserver.viewentity.FutureLettersVO;
import com.project.isc.iscdbserver.viewentity.FutureLettersVOSimple;
import com.project.isc.iscdbserver.viewentity.RetMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ISC
 *
 */
@Api(value = "给未来的一封信", tags = "给未来的一封信")
@RestController
@RequestMapping("/futureLetters")
@CrossOrigin
public class FutureLettersController {

    @Autowired
    private FutureLettersService futureLettersService;

    @Autowired
    private TradingService tradingService;

    @Autowired
    private UserService userService;

    //写入到区块链
    @ApiOperation(value="提交信件", notes="")
    @PostMapping("/sendmessage")
    @Transactional
    public RetMsg sendmessage(@RequestBody @Validated FuturLettersRequest futurLettersRequest) {
        RetMsg retMsg = new RetMsg();
        String flId = "";
        if(futurLettersRequest!=null && StringUtils.getStringisNotNull(futurLettersRequest.getUserid())){
            User user = userService.getUserById(futurLettersRequest.getUserid());
            if(user!=null){
                //写入到数据库
                flId = futureLettersService.sendMessage(futurLettersRequest.getUserid(),
                        futurLettersRequest.getFlMessage(),
                        futurLettersRequest.getFlemail(),
                        futurLettersRequest.getSendTime(),
                        futurLettersRequest.getFlIsPublic(),
                        futurLettersRequest.getFlEncryption());
                //如果是公开邮件，写入到区块链

            }

        }
        retMsg.setCode(200);
        retMsg.setData(flId);
        retMsg.setMessage("您的未来邮件已寄出成功，等待写入到区块链");
        retMsg.setSuccess(true);
        return retMsg;
    }

    //将已写入到数据库的信件写入到区块链
    @ApiOperation(value="将已写入到数据库的信件写入到区块链", notes="")
    @PostMapping("/sendmessageblock")
    @Transactional
    public RetMsg sendmessageblock(@RequestParam String userid,@RequestParam  String flid) {
        RetMsg retMsg = new RetMsg();
        String flMessage = "";
        if(StringUtils.getStringisNotNull(userid)){
            User user = userService.getUserById(userid);
            if(user!=null){
                FutureLetters futureLetters = futureLettersService.getFutureLetters(flid);
                //如果有这封信而且这封信是属于这个用户的，基础这封信
                //这里以后可以扩展很多功能的
                if(futureLetters!=null && futureLetters.getFlId().equals(flid)){
                    RetMsg retMsgBlock  = tradingService.getSendMassage(futureLetters.getFlMessage());
                    if(retMsg.isSuccess()){
                        futureLetters.setBlockChain(retMsgBlock.getMessage());
                        futureLetters.setWriteTime(new Date());
                        //这里表示已写入到区块链，带区块链确认
                        futureLetters.setFlStatus(ISCConstant.ISC_FL_STATUS_ONGOING);
                        futureLettersService.updateFutureLetters(futureLetters);
                        flMessage = retMsgBlock.getMessage();
                    }else{
                        futureLetters.setWriteTime(new Date());
                        //这里表示已执行写入到区块链接口，但是未写入成功
                        futureLetters.setFlStatus(ISCConstant.ISC_FL_STATUS_FAILURE);
                        futureLettersService.updateFutureLetters(futureLetters);
                    }
                }
            }

        }
        retMsg.setCode(200);
        retMsg.setData(flMessage);
        retMsg.setMessage("将已写入到数据库的信件写入到区块链成功");
        retMsg.setSuccess(true);
        return retMsg;
    }

    //得到写入区块链的信息
    @ApiOperation(value="得到写入区块链的信息", notes="")
    @GetMapping("/getmessage")
    @Transactional
    public RetMsg getmessage(@RequestParam String userid,@RequestParam  String flid) {
        RetMsg retMsg = new RetMsg();
        String flMessage = "";
        if(StringUtils.getStringisNotNull(userid)) {
            User user = userService.getUserById(userid);
            if (user != null) {
                FutureLetters futureLetters = futureLettersService.getFutureLetters(flid);
                flMessage = futureLetters.getFlMessage();
            }
        }
        retMsg.setCode(200);
        retMsg.setData(flMessage);
        retMsg.setMessage("读取信息成功");
        retMsg.setSuccess(true);
        return retMsg;
    }

    //得到写入区块链的详细信息
    @ApiOperation(value="得到写入区块链的详细信息", notes="")
    @GetMapping("/getmessagedetail")
    @Transactional
    public RetMsg getmessagedetail(@RequestParam String userid,@RequestParam  String flid) {
        RetMsg retMsg = new RetMsg();
        String flMessage = "";
        if(StringUtils.getStringisNotNull(userid)) {
            User user = userService.getUserById(userid);
            if (user != null) {
                FutureLetters futureLetters = futureLettersService.getFutureLetters(flid);
                retMsg.setData(FutureLettersTransf.transfToVO(futureLetters));
            }
        }
        retMsg.setCode(200);
        retMsg.setMessage("读取信息成功");
        retMsg.setSuccess(true);
        return retMsg;
    }

    //得到有多少封信
    @ApiOperation(value="得到有多少封信", notes="")
    @GetMapping("/getLetterCount")
    @Transactional
    public RetMsg getLetterCount(){
        RetMsg retMsg = new RetMsg();

        long count  = futureLettersService.getFutureLettersCount();
        retMsg.setCode(200);
        retMsg.setData(count);
        retMsg.setMessage("得到有多少封信成功");
        retMsg.setSuccess(true);
        return retMsg;
    }

    //得到最新的10条公开信
    @ApiOperation(value="得到最新的10条公开信", notes="")
    @GetMapping("/getLetterTop10Public")
    @Transactional
    public RetMsg getLetterTop10Public(){
        RetMsg retMsg = new RetMsg();

        List<FutureLetters>  lettersList= futureLettersService.getFutureLetterTop10Public();
        List<FutureLettersVOSimple> lvo = new ArrayList<FutureLettersVOSimple>();
        if(lettersList!=null && lettersList.size()>0){
            for (FutureLetters ll:lettersList
                 ) {
                lvo.add(FutureLettersTransf.transfToVOSimple(ll));
            }
        }
        retMsg.setCode(200);
        retMsg.setData(lvo);
        retMsg.setMessage("得到有多少封信成功");
        retMsg.setSuccess(true);
        return retMsg;
    }
}
