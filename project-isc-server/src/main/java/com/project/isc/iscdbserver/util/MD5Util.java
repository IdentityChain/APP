package com.project.isc.iscdbserver.util;

import java.security.MessageDigest;

import org.springframework.util.Base64Utils;

public class MD5Util {
	private MD5Util() {
	}

	// 加密
	public static String encrypeByMd5(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String encrypeStr = Base64Utils.encodeToString(md5.digest(str.getBytes("utf-8")));
			return encrypeStr;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("密码加密失败");
		}

	}

	public static void main(String[] args) throws Exception {
		System.out.println(encrypeByMd5("admin"));
	}

}
