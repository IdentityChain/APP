package com.project.isc.iscdbserver.viewentity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
public class FutureLettersVO {

    private String flId;

    // 用户id
    private String userId;

    // 用户内容
    private String flMessage;

    //创建时间
    private String createTime;

    //写入时间
    private String writeTime;

    // 寄送到的邮箱
    private String flemail;

    //寄送时间
    private String sendTime;

    // 状态-是否已写入区块链
    private String flStatus;

    // 状态-是否公开
    private String flIsPublic;

    // 状态-是否已寄送
    private String flIsSendStatus;

    // 写入区块链ID
    private String blockChain;

    // 是否加密或者加密方式
    private String flEncryption;

    public String getFlId() {
        return flId;
    }

    public void setFlId(String flId) {
        this.flId = flId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlMessage() {
        return flMessage;
    }

    public void setFlMessage(String flMessage) {
        this.flMessage = flMessage;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    public String getFlemail() {
        return flemail;
    }

    public void setFlemail(String flemail) {
        this.flemail = flemail;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(String flStatus) {
        this.flStatus = flStatus;
    }

    public String getFlIsPublic() {
        return flIsPublic;
    }

    public void setFlIsPublic(String flIsPublic) {
        this.flIsPublic = flIsPublic;
    }

    public String getFlIsSendStatus() {
        return flIsSendStatus;
    }

    public void setFlIsSendStatus(String flIsSendStatus) {
        this.flIsSendStatus = flIsSendStatus;
    }

    public String getBlockChain() {
        return blockChain;
    }

    public void setBlockChain(String blockChain) {
        this.blockChain = blockChain;
    }

    public String getFlEncryption() {
        return flEncryption;
    }

    public void setFlEncryption(String flEncryption) {
        this.flEncryption = flEncryption;
    }
}
