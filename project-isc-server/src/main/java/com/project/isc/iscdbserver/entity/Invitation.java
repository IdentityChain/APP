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
    @Column(length = 64)
	// 唯一id
	private String invitaId;
	
	//邀请人ID
	@Column
	private String sourceUserId;
	
	//邀请码
	@Column
	private String sourceCode;
	
	// 创建时间
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	//一级邀请
	@Column
	private int invitaFirstNumber;

	//二级邀请
	@Column
	private int invitaSecoundNumber;

	//三级邀请
	@Column
	private int invitaThreeNumber;

	//四级邀请
	@Column
	private int invitaFourNumber;

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

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getInvitaFirstNumber() {
		return invitaFirstNumber;
	}

	public void setInvitaFirstNumber(int invitaFirstNumber) {
		this.invitaFirstNumber = invitaFirstNumber;
	}

	public int getInvitaSecoundNumber() {
		return invitaSecoundNumber;
	}

	public void setInvitaSecoundNumber(int invitaSecoundNumber) {
		this.invitaSecoundNumber = invitaSecoundNumber;
	}

	public int getInvitaThreeNumber() {
		return invitaThreeNumber;
	}

	public void setInvitaThreeNumber(int invitaThreeNumber) {
		this.invitaThreeNumber = invitaThreeNumber;
	}

	public int getInvitaFourNumber() {
		return invitaFourNumber;
	}

	public void setInvitaFourNumber(int invitaFourNumber) {
		this.invitaFourNumber = invitaFourNumber;
	}
}
