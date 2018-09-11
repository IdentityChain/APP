package com.project.isc.iscdbserver.viewentity;

/**
 * 资源展现类
 * 
 * @author isc
 *
 */
public class ISCLogVO {
	private String isclId;
	// 新增ISC
	private double addISC;
	// 状态
	private String status;
	// 创建时间
	private String createTime;

	public String getIsclId() {
		return isclId;
	}

	public void setIsclId(String isclId) {
		this.isclId = isclId;
	}

	public double getAddISC() {
		return addISC;
	}

	public void setAddISC(double addISC) {
		this.addISC = addISC;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
