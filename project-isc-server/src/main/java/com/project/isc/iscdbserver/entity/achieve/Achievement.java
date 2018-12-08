package com.project.isc.iscdbserver.entity.achieve;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 成就任务类
 */
@Entity
@Table(name = "tb_achievement")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Achievement implements Serializable{

	private static final long serialVersionUId = 1L;
	
	// 活动ID
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
	private String id;		//成就ID
	@Column
	private double addiscValue;	//给ISC数目
	@Column
	private int calculateValue;	//给成就点数目
	@Column
	private String content;			//成就完成规则
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;		//创建时间
	@Column
	private String createUserId;	//创建用户ID
	@Column
	private String createUserName;	//创建用户名称
	@Column
	private String grayImgPath;		//成就图片-未完成
	@Column
	private String img_path;		//成就图片-完成
	@Column
	private int steps;				//完成条件-步骤，如100次，10000次
	@Column
	private String title;			//成就标题
	@Column
	private boolean available;	//是否可用
	@Column
	private String type;			//成就类型-每日成就，成就，签到成就，ISC
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;		//更新时间

	public static long getSerialVersionUId() {
		return serialVersionUId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public double getAddiscValue() {
		return addiscValue;
	}

	public void setAddiscValue(double addiscValue) {
		this.addiscValue = addiscValue;
	}

	public int getCalculateValue() {
		return calculateValue;
	}

	public void setCalculateValue(int calculateValue) {
		this.calculateValue = calculateValue;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getGrayImgPath() {
		return grayImgPath;
	}

	public void setGrayImgPath(String grayImgPath) {
		this.grayImgPath = grayImgPath;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
