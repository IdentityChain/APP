package com.project.isc.iscdbserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserLoginSetting {
	private long expireTime;
	private boolean useInterceptor;

	public long getExpireTime() {
		return expireTime;
	}

	@Value("${login.expireTime}")
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	public boolean isUseInterceptor() {
		return useInterceptor;
	}
	
	@Value("${login.useInterceptor}")
	public void setUseInterceptor(boolean useInterceptor) {
		this.useInterceptor = useInterceptor;
	}

}
