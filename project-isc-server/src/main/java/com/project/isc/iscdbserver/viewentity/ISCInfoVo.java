package com.project.isc.iscdbserver.viewentity;

import java.util.Date;

public class ISCInfoVo {
    private String no;
    private String name;
    private String title;
    private String type;  //ISC  ETH  USDT
    private String address;
    private long validationNumber;  //验证数目
    private long reportNumber;      //上报数目
    private long differenceNumber;  //区别
    private String  validationDate;    //验证时间
    private String reportDate;      //上报时间

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getValidationNumber() {
        return validationNumber;
    }

    public void setValidationNumber(long validationNumber) {
        this.validationNumber = validationNumber;
    }

    public long getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(long reportNumber) {
        this.reportNumber = reportNumber;
    }

    public long getDifferenceNumber() {
        return differenceNumber;
    }

    public void setDifferenceNumber(long differenceNumber) {
        this.differenceNumber = differenceNumber;
    }

    public String getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(String validationDate) {
        this.validationDate = validationDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public ISCInfoVo(String no, String name, String title, String type, String address, long validationNumber, long reportNumber, long differenceNumber, String validationDate, String reportDate) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.type = type;
        this.address = address;
        this.validationNumber = validationNumber;
        this.reportNumber = reportNumber;
        this.differenceNumber = differenceNumber;
        this.validationDate = validationDate;
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "ISCInfoVo{" +
                "编号=" + no +"\t"+
                ", 姓名='" + name + '\'' +"\t"+
                ", 描述='" + title + '\'' +"\t"+
                ", 类型='" + type + '\'' +"\t"+
                ", 地址='" + address + '\'' +"\t"+
                ", 验证数目=" + validationNumber +"\t"+
                ", 提交数目=" + reportNumber +"\t"+
                ", 相差数目=" + differenceNumber +"\t"+
                ", 验证时间=" + validationDate +"\t"+
                ", 提交时间='" + reportDate + '\'' +
                '}';
    }
}
