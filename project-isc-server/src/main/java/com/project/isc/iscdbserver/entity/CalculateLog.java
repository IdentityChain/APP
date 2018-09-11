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
@Table(name = "tb_calculate_log")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CalculateLog implements Serializable{

	private static final long serialVersionUId = 1L;
	
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
	// 成就日志ID
	private String cclId;
	// 用户ID
	@Column
	private String userId;
	// 成就ID
	@Column
	private String ccId;
	// 活动ID
	@Column
	private String activityId;
	// 成就值ID
	@Column
	private int calculateValue;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public String getCclId() {
		return cclId;
	}

	public void setCclId(String cclId) {
		this.cclId = cclId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCcId() {
		return ccId;
	}

	public void setCcId(String ccId) {
		this.ccId = ccId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
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
