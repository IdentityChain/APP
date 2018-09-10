package com.project.isc.iscdbserver.util;

public class StringUtils {

	public static boolean isBlank(String filePath) {
		if (filePath != null && filePath.length()>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 隐藏部分文字
	 * @param str
	 * @return
	 */
	public static String getStringHide(String str) {
		if(str!=null && str.length()>0) {
			if(str.length()<5) {
				return str;
			}else {
				str = str.substring(0, 3)+"****"+str.substring(str.length()-2,str.length());
			}
		}
		return str;
	}

	
	public static void main(String[] args) throws Exception{
	}
}
