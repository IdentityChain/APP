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
                if(StringUtils.getStringisNotNull(futurLettersRequest.getFlIsPublic())&& ISCConstant.ISC_FL_IS_PUBLIC_FALSE.equals(futurLettersRequest.getFlIsPublic())){
                    //如果是不公开的就不公开，其他全部是公开
                }else{
                    futurLettersRequest.setFlIsPublic(ISCConstant.ISC_FL_IS_PUBLIC_TRUE);
                }
                if(StringUtils.getStringisNotNull(futurLettersRequest.getFlEncryption()) && ISCConstant.ISC_FL_ENCRYPTION_MD5.equals(futurLettersRequest.getFlEncryption())){
                    //如果是MD5加密就加密，其他全部是不加密
                }else {
                    futurLettersRequest.setFlEncryption(ISCConstant.ISC_FL_ENCRYPTION_NOT);
                }
                //写入到数据库
                FutureLetters fl = futureLettersService.sendMessage(futurLettersRequest.getUserid(),
                        futurLettersRequest.getFlMessage(),
                        futurLettersRequest.getFlemail(),
                        futurLettersRequest.getSendTime(),
                        futurLettersRequest.getFlIsPublic(),
                        futurLettersRequest.getFlEncryption());
                //如果是公开邮件，写入到区块链
                if(ISCConstant.ISC_FL_IS_PUBLIC_FALSE.equals(futurLettersRequest.getFlIsPublic())){
                    //发送信息到区块链上，并返回哈希值
                    String blockId = sendToBlock(fl.getFlMessage());
                    //如果哈希值没有问题
                    if(StringUtils.getStringisNotNull(blockId)){
                        //设置哈希值
                        fl.setBlockChain(blockId);
                        fl.setWriteTime(new Date());
                        //设置写入区块链状态 完成写入
                        fl.setFlStatus(ISCConstant.ISC_FL_STATUS_COMPLETE);
                    }else{
                        fl.setWriteTime(new Date());
                        //设置写入区块链状态 写入失败
                        fl.setFlStatus(ISCConstant.ISC_FL_STATUS_FAILURE);
                    }
                    futureLettersService.updateFutureLetters(fl);
                }
            }

        }
        retMsg.setCode(200);
        retMsg.setData(flId);
        retMsg.setMessage("您的未来邮件已寄出成功，等待写入到区块链");
        retMsg.setSuccess(true);
        return retMsg;
    }

    /**
     * 发送信息到区块链上，并返回哈希值
     * @param sendMassage
     * @return
     */
    public String sendToBlock(String sendMassage){
        try{
            RetMsg retMsg = tradingService.sendMassage(sendMassage);
            //如果有返回数据
            if(retMsg!=null && retMsg.isSuccess()){
                //如果返回数据不是空
                if(retMsg.getData()!=null){
                    //得到哈希值
                    return retMsg.getData().toString();
                }
            }
            return "";
        }catch (Exception e){
            return "";
        }

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
                FutureLetters fl = futureLettersService.getFutureLetters(flid);
                //如果有这封信而且这封信是属于这个用户的，基础这封信
                //这里以后可以扩展很多功能的
                if(fl!=null && fl.getFlId().equals(flid)){
                    //发送信息到区块链上，并返回哈希值
                    String blockId = sendToBlock(fl.getFlMessage());
                    //如果哈希值没有问题
                    if(StringUtils.getStringisNotNull(blockId)){
                        //设置哈希值
                        fl.setBlockChain(blockId);
                        fl.setWriteTime(new Date());
                        //设置写入区块链状态 完成写入
                        fl.setFlStatus(ISCConstant.ISC_FL_STATUS_COMPLETE);
                    }else{
                        fl.setWriteTime(new Date());
                        //设置写入区块链状态 写入失败
                        fl.setFlStatus(ISCConstant.ISC_FL_STATUS_FAILURE);
                    }
                    futureLettersService.updateFutureLetters(fl);
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
    @ApiOperation(value="得到写入区块链的信息-数据库", notes="")
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

    //得到写入区块链的信息
    @ApiOperation(value="得到写入区块链的信息-哈希值", notes="")
    @GetMapping("/getmessageByhash")
    @Transactional
    public RetMsg getmessageByhash(@RequestParam String userid,@RequestParam  String hashid) {
        RetMsg retMsg = new RetMsg();
        String flMessage = "没有读取到信息";
        if(StringUtils.getStringisNotNull(userid)) {
            User user = userService.getUserById(userid);
            if (user != null) {
                FutureLetters fl = futureLettersService.getFutureLettersByBlockId(hashid);
                if(fl!=null && userid.equals(fl.getUserId())){
                    flMessage = getSendMassageStr(hashid);
                }
            }
        }
        retMsg.setCode(200);
        retMsg.setData(flMessage);
        retMsg.setMessage("接口返回成功");
        retMsg.setSuccess(true);
        return retMsg;
    }

    /**
     * 根据哈希值得到内容
     * @param hashid
     * @return
     */
    private String getSendMassageStr(String hashid){
        try{
            RetMsg retMsg = tradingService.getSendMassage(hashid);
            //如果有返回数据
            if(retMsg!=null && retMsg.isSuccess()){
                //如果返回数据不是空
                if(retMsg.getData()!=null){
                    //得到哈希值
                    return retMsg.getData().toString();
                }
            }
            return "没有读取到信息";
        }catch (Exception e){
            return "没有读取到信息";
        }
    }

    //得到写入区块链的详细信息
    @ApiOperation(value="得到写入区块链的详细信息-数据库", notes="")
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

    //分页查看公开信息
    @ApiOperation(value="分页查看公开信息", notes="")
    @GetMapping("/getPublicLetterList")
    @Transactional
    public RetMsg getPublicLetterList(@RequestParam int page,@RequestParam int pagesize){
        RetMsg retMsg = new RetMsg();

        List<FutureLetters>  lettersList= futureLettersService.getFutureLetterList(page,pagesize);
        List<FutureLettersVOSimple> lvo = new ArrayList<FutureLettersVOSimple>();
        if(lettersList!=null && lettersList.size()>0){
            for (FutureLetters ll:lettersList
                    ) {
                lvo.add(FutureLettersTransf.transfToVOSimple(ll));
            }
        }
        retMsg.setCode(200);
        retMsg.setData(lvo);
        retMsg.setMessage("分页查看公开信息");
        retMsg.setSuccess(true);
        return retMsg;
    }
}
