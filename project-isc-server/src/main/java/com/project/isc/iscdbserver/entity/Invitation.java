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

/**
 * 
 * 邀请表，保存用户生成的邀请码ID
 */
@Entity
@Table(name = "tb_invitation")
public class Invitation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 唯一id
	private long invitaId;
	
	//邀请人ID
	@Column
	private long sourceUserId;
	
	//被邀请人ID
	@Column
	private long toUserId;
	
	// 创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public long getInvitaId() {
		return invitaId;
	}

	public void setInvitaId(long invitaId) {
		this.invitaId = invitaId;
	}

	public long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public long getToUserId() {
		return toUserId;
	}

	public void setToUserId(long toUserId) {
		this.toUserId = toUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
