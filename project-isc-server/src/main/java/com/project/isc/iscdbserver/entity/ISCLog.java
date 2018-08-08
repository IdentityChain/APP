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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long isclId;
	@Column
	private long userId;
	@Column
	private long activityId;;
	@Column
	private double originalISC;
	@Column
	private double addISC;
	@Column
	private double finallyISC;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

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
	
}
