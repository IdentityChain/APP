package com.project.isc.iscdbserver.util;

public class StringUtils {

	public static boolean isBlank(String filePath) {
		if (filePath != null && filePath.length()>0) {
			return true;
		}
		return false;
	}
	//隐藏姓名
	public final static String HIDE_NAME="HIDE_NAME";
	//隐藏手机号
	public final static String HIDE_PHONE="HIDE_PHONE";
	//隐藏身份证
	public final static String HIDE_IDENTITY="HIDE_IDENTITY";
	//排行名称隐藏
	public final static String HIDE_TOP100="HIDE_TOP100";

	/**
	 * 隐藏部分文字
	 * @param str
	 * @return
	 */
	public static String getStringHide(String str,String type) {
		if(getStringisNotNull(str)) {
			if(HIDE_NAME.equals(type)) {
				if(str.length()==2){
					str = str.substring(0, 1)+"*";
				}else {
					str = str.substring(0, 1)+"*"+str.substring(str.length()-1,str.length());
				}

			}else if(HIDE_PHONE.equals(type)){
				str = str.substring(0, 3)+"****"+str.substring(str.length()-2,str.length());
			}else if(HIDE_IDENTITY.equals(type)){
				str = str.substring(0, 3)+"****"+str.substring(str.length()-2,str.length());
			}else if(HIDE_TOP100.equals(type)){
				if(str.length()==2){
					str = str.substring(0, 1)+"*";
				}else if(str.length()==3){
					str = str.substring(0, 1)+"*";
				}else if(str.length()>3){
					str = str.substring(0, 3)+"****"+str.substring(str.length()-2,str.length());
				}else {
					str = str.substring(0, 3)+"****"+str.substring(str.length()-2,str.length());
				}
			}else {
				return str;
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
		if(str!=null && !"".equals(str)&& !"NULL".equalsIgnoreCase(str) && str.length()>0){
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
