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
@Table(name = "tb_announcement")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Announcement  implements Serializable{
	
	private static final long serialVersionUId = 1L;
	
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id",length = 32)
	private String id;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private String userid;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
