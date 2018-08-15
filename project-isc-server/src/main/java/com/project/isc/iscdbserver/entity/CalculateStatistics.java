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
 * 算力统计
 * @author isc
 *
 */
@Entity
@Table(name = "tb_calculate_statistics")
public class CalculateStatistics implements Serializable{

	private static final long serialVersionUId = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ccsId;
	@Column
	private long userId;
	@Column
	private int ranking;
	@Column
	private String name;
	@Column
	private int calculateValue;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public long getCcsId() {
		return ccsId;
	}

	public void setCcsId(long ccsId) {
		this.ccsId = ccsId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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