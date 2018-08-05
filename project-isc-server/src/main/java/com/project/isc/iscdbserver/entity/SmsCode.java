package com.project.isc.iscdbserver.entity;

import java.io.Serializable;

public class SmsCode implements Serializable{

	private static final long serialVersionUId = 1L;
	
	private String account;
	private String code;
	private String operation;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}
