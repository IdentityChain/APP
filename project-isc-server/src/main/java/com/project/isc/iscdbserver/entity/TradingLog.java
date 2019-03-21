package com.project.isc.iscdbserver.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_trading_log")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class TradingLog implements Serializable {

    private static final long serialVersionUId = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    // 交易
    private String tlId;
    // 用户ID
    @Column
    private String userId;
    // 用户名称
    @Column
    private String userName;
    // 操作内容
    @Column
    private String tradingContent;
    // 操作详情
    @Column
    private String tradingTitle;
    // 操作结果
    @Column
    private String operationResult;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    //创建时间
    private Date createTime;

    public String getTlId() {
        return tlId;
    }

    public void setTlId(String tlId) {
        this.tlId = tlId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTradingContent() {
        return tradingContent;
    }

    public void setTradingContent(String tradingContent) {
        this.tradingContent = tradingContent;
    }

    public String getTradingTitle() {
        return tradingTitle;
    }

    public void setTradingTitle(String tradingTitle) {
        this.tradingTitle = tradingTitle;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

