package com.project.isc.iscdbserver.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_calculate_log")
public class CalculateLog implements Serializable{

	private static final long serialVersionUId = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 成就日志ID
	private long cclId;
	// 用户ID
	@Column
	private long userId;
	// 成就ID
	@Column
	private long ccId;
	// 活动ID
	@Column
	private long activityId;
	// 成就值ID
	@Column
	private int calculateValue;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public long getCclId() {
		return cclId;
	}

	public void setCclId(long cclId) {
		this.cclId = cclId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCcId() {
		return ccId;
	}

	public void setCcId(long ccId) {
		this.ccId = ccId;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public int getCalculateValue() {
		return calculateValue;
	}

	public void setCalculateValue(int calculateValue) {
		this.calculateValue = calculateValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
