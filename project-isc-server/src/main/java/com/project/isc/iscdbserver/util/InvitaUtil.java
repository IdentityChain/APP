package com.project.isc.iscdbserver.util;

public class InvitaUtil {
	public static String invitaUrlCode="";

	public static String getInvitaUrlCode(String invitaCode) {
		return invitaUrlCode+"?"+invitaCode;
	}
}
