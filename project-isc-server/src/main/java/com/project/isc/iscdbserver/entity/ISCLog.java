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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_isc_log")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ISCLog implements Serializable{

	private static final long serialVersionUId = 1L;
	//id
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
	private String isclId;
	//用户ID
	@Column
	private String userId;
	//活动ID
	@Column
	private String activityId;;
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

	public String getIsclId() {
		return isclId;
	}

	public void setIsclId(String isclId) {
		this.isclId = isclId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
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
