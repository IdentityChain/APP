package com.project.isc.iscdbserver.entity.coin;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_coin_search_log")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CoinSearchLog {


    private static final long serialVersionUId = 1L;

    // 活动ID
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
    private String id;		//ETH地址ID

    @Column(length = 64)
    private String userid;		//ETH地址ID
    @Column
    private String logContent;  //简述

    @Column

    private String logTotal;    //详情
    //public static final String LOG_TYPE_INFO ="INFO";
    //	public static final String LOG_TYPE_ERROR ="ERROR";
    @Column
    private String type;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;		//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLogTotal() {
        return logTotal;
    }

    public void setLogTotal(String logTotal) {
        this.logTotal = logTotal;
    }
}
