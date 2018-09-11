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

/**
 * 
 * 邀请表，保存用户生成的邀请码ID
 */
@Entity
@Table(name = "tb_invitation")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Invitation implements Serializable {
	
	private static final long serialVersionUId = 1L;
	
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
	// 唯一id
	private String invitaId;
	
	//邀请人ID
	@Column
	private String sourceUserId;
	
	//被邀请人ID
	@Column
	private String toUserId;
	
	// 创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public String getInvitaId() {
		return invitaId;
	}

	public void setInvitaId(String invitaId) {
		this.invitaId = invitaId;
	}

	public String getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
