package com.project.isc.iscdbserver.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_calculate")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Calculate implements Serializable{

	private static final long serialVersionUId = 1L;
	
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
	// 成就id
	private String ccId;
	// 用户id
	@Column
	private String userId;
	
	@Column
	private int calculateValue;
	@Column
	private String status;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public String getCcId() {
		return ccId;
	}

	public void setCcId(String ccId) {
		this.ccId = ccId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCalculateValue() {
		return calculateValue;
	}

	public void setCalculateValue(int calculateValue) {
		this.calculateValue = calculateValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
