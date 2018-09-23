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

	/**
	 * 判断String对象是否为空
	 * 默认为空，不为空是true
	 * @param str
	 * @return
	 */
	public static boolean getStringisNotNull(String str){
		if(str!=null && !"".equals(str)&& !"NULL".equalsIgnoreCase(str)){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		double total =10.0;
		int size =11;
		System.out.println(total/size);
	}
}
