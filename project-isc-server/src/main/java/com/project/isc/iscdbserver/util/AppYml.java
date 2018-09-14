package com.project.isc.iscdbserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppYml {

private static String maxSize;
private static String minSize;
private static String uuid;
private static double addisc;
private static int iscMaxNumber;

private static String tradingurlHost;
private static String tradingurlPort;

    public static String getUuid() {
        return uuid;
    }
    @Value("${app.property.uuid}")
    public void setUuid(String uuid) {
        AppYml.uuid = uuid;
    }

    public static String getMaxSize() {
        return maxSize;
    }
    @Value("${app.property.maxSize}")
    private void setMaxSize(String maxSize) {
        AppYml.maxSize = maxSize;
    }

    public static String getMinSize() {
        return minSize;
    }
    @Value("${app.property.minSize}")
    private void setMinSize(String minSize) {
        AppYml.minSize = minSize;
    }
	public static double getAddisc() {
		return addisc;
	}
	@Value("${app.integral.addIsc}")
	public void setAddisc(double addisc) {
		AppYml.addisc = addisc;
	}
	public static int getIscMaxNumber() {
		return iscMaxNumber;
	}
	@Value("${app.integral.iscMaxNumber}")
	public void setIscMaxNumber(int iscMaxNumber) {
		AppYml.iscMaxNumber = iscMaxNumber;
	}
	//@Value("${app.tradingurl.getIscNumber}")
	public static String getTradingurlHost() {
		return tradingurlHost;
	}
	@Value("${app.tradingurl.host}")
	public static void setTradingurlHost(String tradingurlHost) {
		AppYml.tradingurlHost = tradingurlHost;
	}
	public static String getTradingurlPort() {
		return tradingurlPort;
	}
	@Value("${app.tradingurl.port}")
	public static void setTradingurlPort(String tradingurlPort) {
		AppYml.tradingurlPort = tradingurlPort;
	}
	
    
	
}
