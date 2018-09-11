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
    
}
