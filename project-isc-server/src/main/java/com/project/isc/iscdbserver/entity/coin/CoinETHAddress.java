package com.project.isc.iscdbserver.entity.coin;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * eth表地址
 */
@Entity
@Table(name = "tb_coin_eth_address")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CoinETHAddress implements Serializable{

	private static final long serialVersionUId = 1L;
	
	// 活动ID
	@Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 64)
	private String id;		//ETH地址ID
	@Column(length = 64)
	private String userId;		//用户ID
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;		//创建时间
	@Column
	private String createInfo;		//创建信息-新建用户创建
	@Column
	private String ethAddress;		//ETH地址
	@Column
	private String keyString;
	@Column
	private boolean available;		//是否可用
	@Column
	private String type;			//类型 -ETH
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;		//更新时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateInfo() {
		return createInfo;
	}

	public void setCreateInfo(String createInfo) {
		this.createInfo = createInfo;
	}

	public String getEthAddress() {
		return ethAddress;
	}

	public void setEthAddress(String ethAddress) {
		this.ethAddress = ethAddress;
	}

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
