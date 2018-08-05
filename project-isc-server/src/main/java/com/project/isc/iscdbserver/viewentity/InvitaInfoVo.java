package com.project.isc.iscdbserver.viewentity;

public class InvitaInfoVo {
	
	public InvitaInfoVo() {
		super();
	}
	
	public InvitaInfoVo(int number) {
		super();
		this.setNumber(number);
		this.setIscnumber(number);
	}

	private int number;
	
	private double iscnumber;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getIscnumber() {
		return iscnumber;
	}

	public void setIscnumber(double iscnumber) {
		this.iscnumber = iscnumber*22;
	}
	
	
}
