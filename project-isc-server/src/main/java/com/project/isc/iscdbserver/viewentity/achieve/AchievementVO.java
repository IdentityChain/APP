package com.project.isc.iscdbserver.viewentity.achieve;

import java.util.Date;

public class AchievementVO {
    private static final long serialVersionUId = 1L;
    private String aaid;		//成就ID
    private double aaaddiscValue;	//给ISC数目
    private int aacalculateValue;	//给成就点数目
    private String aacontent;			//成就完成规则
    private Date caareateTime;		//创建时间
    private String aacreateUserId;	//创建用户ID
    private String aacreateUserName;	//创建用户名称
    private String aagrayImgPath;		//成就图片-未完成
    private String aaimg_path;		//成就图片-完成
    private int aasteps;				//完成条件-步骤，如100次，10000次
    private String aatitle;			//成就标题
    private boolean aavailable;	    //是否可用
    private String aatype;			//成就类型-每日成就，成就
    private Date aaupdateTime;		//更新时间

    private String auid;				//进度ID
    private String auachId;			//成就任务ID
    private String auuserId;			//用户ID
    private int auuserSteps;		//用户进度
    private int aucompleteRate;		//完成率
    private Date auaucreateTime;		//创建时间
    private Date aufinishTime;		//完成时间
    private boolean auis_create;		//是否领取
    private boolean auavailable;		//是否可用
    //toolType: 'button',
    //            toolEvent: 'checkin',
    //            toolText: '签到'
    public String toolType; //button  text
    public String toolEvent; //checkin
    public String toolText; //'签到'  +10

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolEvent() {
        return toolEvent;
    }

    public void setToolEvent(String toolEvent) {
        this.toolEvent = toolEvent;
    }

    public String getToolText() {
        return toolText;
    }

    public void setToolText(String toolText) {
        this.toolText = toolText;
    }

    public static long getSerialVersionUId() {
        return serialVersionUId;
    }

    public String getAaid() {
        return aaid;
    }

    public void setAaid(String aaid) {
        this.aaid = aaid;
    }

    public double getAaaddiscValue() {
        return aaaddiscValue;
    }

    public void setAaaddiscValue(double aaaddiscValue) {
        this.aaaddiscValue = aaaddiscValue;
    }

    public int getAacalculateValue() {
        return aacalculateValue;
    }

    public void setAacalculateValue(int aacalculateValue) {
        this.aacalculateValue = aacalculateValue;
    }

    public String getAacontent() {
        return aacontent;
    }

    public void setAacontent(String aacontent) {
        this.aacontent = aacontent;
    }

    public Date getCaareateTime() {
        return caareateTime;
    }

    public void setCaareateTime(Date caareateTime) {
        this.caareateTime = caareateTime;
    }

    public String getAacreateUserId() {
        return aacreateUserId;
    }

    public void setAacreateUserId(String aacreateUserId) {
        this.aacreateUserId = aacreateUserId;
    }

    public String getAacreateUserName() {
        return aacreateUserName;
    }

    public void setAacreateUserName(String aacreateUserName) {
        this.aacreateUserName = aacreateUserName;
    }

    public String getAagrayImgPath() {
        return aagrayImgPath;
    }

    public void setAagrayImgPath(String aagrayImgPath) {
        this.aagrayImgPath = aagrayImgPath;
    }

    public String getAaimg_path() {
        return aaimg_path;
    }

    public void setAaimg_path(String aaimg_path) {
        this.aaimg_path = aaimg_path;
    }

    public int getAasteps() {
        return aasteps;
    }

    public void setAasteps(int aasteps) {
        this.aasteps = aasteps;
    }

    public String getAatitle() {
        return aatitle;
    }

    public void setAatitle(String aatitle) {
        this.aatitle = aatitle;
    }

    public boolean isAavailable() {
        return aavailable;
    }

    public void setAavailable(boolean aavailable) {
        this.aavailable = aavailable;
    }

    public String getAatype() {
        return aatype;
    }

    public void setAatype(String aatype) {
        this.aatype = aatype;
    }

    public Date getAaupdateTime() {
        return aaupdateTime;
    }

    public void setAaupdateTime(Date aaupdateTime) {
        this.aaupdateTime = aaupdateTime;
    }

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    public String getAuachId() {
        return auachId;
    }

    public void setAuachId(String auachId) {
        this.auachId = auachId;
    }

    public String getAuuserId() {
        return auuserId;
    }

    public void setAuuserId(String auuserId) {
        this.auuserId = auuserId;
    }

    public int getAuuserSteps() {
        return auuserSteps;
    }

    public void setAuuserSteps(int auuserSteps) {
        this.auuserSteps = auuserSteps;
    }

    public int getAucompleteRate() {
        return aucompleteRate;
    }

    public void setAucompleteRate(int aucompleteRate) {
        this.aucompleteRate = aucompleteRate;
    }

    public Date getAuaucreateTime() {
        return auaucreateTime;
    }

    public void setAuaucreateTime(Date auaucreateTime) {
        this.auaucreateTime = auaucreateTime;
    }

    public Date getAufinishTime() {
        return aufinishTime;
    }

    public void setAufinishTime(Date aufinishTime) {
        this.aufinishTime = aufinishTime;
    }

    public boolean isAuis_create() {
        return auis_create;
    }

    public void setAuis_create(boolean auis_create) {
        this.auis_create = auis_create;
    }

    public boolean isAuavailable() {
        return auavailable;
    }

    public void setAuavailable(boolean auavailable) {
        this.auavailable = auavailable;
    }
}
