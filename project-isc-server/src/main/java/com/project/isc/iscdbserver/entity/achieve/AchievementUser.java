package com.project.isc.iscdbserver.entity.achieve;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 成就任务进度类
 */
@Entity
@Table(name = "tb_achievement_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class AchievementUser implements Serializable{

	private static final long serialVersionUId = 1L;
	
	//
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
	private String id;				//进度ID
	@Column(length = 64)
	private String achId;			//成就任务ID
	@Column(length = 64)
	private String userId;			//用户ID
	@Column
	private int userSteps;		//用户进度
	@Column
	private int completeRate;		//完成率
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;		//创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date finishTime;		//完成时间
	@Column
	private boolean is_create;		//是否领取
	@Column
	private boolean available;		//是否可用
	@Column
	private String type;			//成就类型-每日成就，成就，签到成就，ISC

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

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

	public String getAchId() {
		return achId;
	}

	public void setAchId(String achId) {
		this.achId = achId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserSteps() {
		return userSteps;
	}

	public void setUserSteps(int userSteps) {
		this.userSteps = userSteps;
	}

	public int getCompleteRate() {
		return completeRate;
	}

	public void setCompleteRate(int completeRate) {
		this.completeRate = completeRate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public boolean isIs_create() {
		return is_create;
	}

	public void setIs_create(boolean is_create) {
		this.is_create = is_create;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
