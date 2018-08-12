package com.project.isc.iscdbserver.viewentity;

import java.util.Date;


/**
 * 算力统计
 * @author isc
 *
 */
public class CalculateStatisticsVO{
	private long ccsId;
	private int ranking;
	private String name;
	private int calculateValue;
	private Date createTime;

	public long getCcsId() {
		return ccsId;
	}

	public void setCcsId(long ccsId) {
		this.ccsId = ccsId;
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
