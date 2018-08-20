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
@Table(name = "tb_isc_log")
public class ISCLog implements Serializable{

	private static final long serialVersionUId = 1L;
	//id
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long isclId;
	//用户ID
	@Column
	private long userId;
	//活动ID
	@Column
	private long activityId;;
	//原始ISC
	@Column
	private double originalISC;
	//新增ISC
	@Column
	private double addISC;
	//最终ISC
	@Column
	private double finallyISC;
	//状态
	@Column
	private String status;
	
	//创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//确认时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date confirmTime;

	public long getIsclId() {
		return isclId;
	}

	public void setIsclId(long isclId) {
		this.isclId = isclId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public double getOriginalISC() {
		return originalISC;
	}

	public void setOriginalISC(double originalISC) {
		this.originalISC = originalISC;
	}

	public double getAddISC() {
		return addISC;
	}

	public void setAddISC(double addISC) {
		this.addISC = addISC;
	}

	public double getFinallyISC() {
		return finallyISC;
	}

	public void setFinallyISC(double finallyISC) {
		this.finallyISC = finallyISC;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	
}
