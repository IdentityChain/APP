package com.project.isc.iscdbserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {

	/**
	 * 去除字符串未尾字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimEnd(String str, String endStr) {

		return str.substring(0, str.length() - endStr.length());
  
	}

	/**
	 * 获取UUID
	 */
	public static String getUUID() {

		UUID Str = UUID.randomUUID();

		String id = Str.toString().replace("-", "");

		return id.trim();

	}

	/**
	 * 非空判断
	 * @param Object
	 * @return 如果是空返回true，否则返回false
	 */
	public static boolean isNullOrEmpty(Object Object) {

		if (null == Object)
			return true;

		if (Object instanceof String) {

			if (((String) Object).trim().length() == 0)
				return true;
		}

		return false;
	}

	/**
	 * 字符串转为long 型
	 */
	public static long toLong(Object s) {

		long result = 0;

		if (isNullOrEmpty(s))
			return result;

		String tmp = String.valueOf(s);

		if (isNumeric(tmp)) {
			result = Long.parseLong(tmp);
		}

		return result;

	}

	/**
	 * 字符串转为INT32 型
	 * 
	 * @param s
	 * @return
	 */
	public static int toInt32(Object s) {

		int result = 0;

		if (isNullOrEmpty(s))
			return result;
		
		
		
		String tmp = String.valueOf(s);

		if (isNumeric(tmp)) {
			result = Integer.parseInt(tmp);
		}

		return result;

	}
	
	/**
	 * boolean 转 int true——1 false——0
	 * 
	 * @param s
	 * @return
	 */
	public static int booleanTOint(boolean s){
		if (s) {
			return 1;
		}
		return 0;
	}

	/**
	 * 字符串转为Boll 型
	 */
	public static boolean toBoolean(Object s) {

		boolean result = false;
		if (isNullOrEmpty(s))
			result = false;

		if (s != null) {
			if (s.toString().toLowerCase().equals("true"))
				result = true;
			else if (s.toString().toLowerCase().equals("false"))
				result = false;
			else if (s.toString().equals("1"))
				result = true;
			else if (s.toString().equals("0"))
				result = false;
		}
		return result;

	}

	/**
	 * 字符串转为日期类型
	 * 
	 * @param s
	 * @param format
	 *            :例：yyyy-MM-dd
	 * @return
	 */
	public static Date toDateTime(Object s, String format) {

		Date result = null;

		if (isNullOrEmpty(s)) {
			return result;
		}

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			result = dateFormat.parse((String) s);

		} catch (Exception e) {
			result = null;
		}

		return result;
	}

	/**
	 * 把日期转换为字符串
	 * 
	 * @param s
	 * @param format
	 *            :例：yyyy-MM-dd
	 * @return
	 */
	public static String toDateStr(Object s, String format) {

		String result = "";

		Date d = null;
		if (isNullOrEmpty(s)) {
			return "";
		}
		try {

			d = (Date) s;

			SimpleDateFormat dateFormat = new SimpleDateFormat(format);

			result = dateFormat.format(d);

		} catch (Exception e) {
			result = "";
		}

		return result;
	}

	/* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException{
    	System.out.println(s);
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
	/**
	 * 字符串转为float 型
	 */
	public static float toFloat(Object s) {

		float result = 0;

		if (isNullOrEmpty(s))
			return result;

		String tmp = String.valueOf(s);

		if (isNumeric(tmp)) {
			result = Float.parseFloat(tmp);
		}

		return result;
	}

	/**
	 * 字符串转为double 型
	 */
	public static double toDouble(Object s) {

		double result = 0;

		if (isNullOrEmpty(s))
			return result;

		String tmp = (String) s;

		if (isNumeric(tmp)) {
			result = Double.parseDouble(tmp);
		}

		return result;
	}

	/**
	   * 获取现在时间
	   * 
	   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 * @throws ParseException 
	   */
	public static Date getNowDate() throws ParseException {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	 
	   return formatter.parse(dateString);
	}
	/**
	 * 对象转为String类型
	 */
	public static String toString(Object s) {

		if (isNullOrEmpty(s))
			return "";
		else
			return String.valueOf(s);
	}

	/**
	 * 去除文本中的html标签信息
	 */
	public static String ReplaceHtmlTag(String html) {

		// ???
		return "";
	}

	/** 
     * 匹配数字正则
     * @param s 
     * @return 
     */  
    public static boolean isNumeric(String s){  
        String regex = "^[0-9][0-9]*\\.[0-9]+$|^[0-9][0-9]*$|^0+\\.[0-9]+$";  
        if(isNullOrEmpty(s)) return false;
        char c = s.charAt(0);  
        boolean bool;  
        if(c=='+'|c=='-'){  
            bool = s.substring(1).matches(regex);  
        }else{  
            bool = s.matches(regex);  
        }  
        return bool;
    }
    //判断传入字符串是否是包装类型或者基础类型
    public static boolean isBaseAndPackageType(String dataType){
    	boolean flag = false;
    	if (dataType.equals("String")||dataType.equalsIgnoreCase("byte")||dataType.equals("int")||dataType.equalsIgnoreCase("short")
    			||dataType.equalsIgnoreCase("long")||dataType.equalsIgnoreCase("boolean")||dataType.equals("char")||
    			dataType.equalsIgnoreCase("float")||dataType.equalsIgnoreCase("double")||dataType.equals("Integer")||
    			dataType.equals("Character")) {
			flag = true;
		}
    	return flag;
    }
	
    /** 
     * 字符转码 
     *  
     * @param theString 
     * @return 
     */  
    public static String decodeUnicode(String theString) {  
  
        char aChar;  
  
        int len = theString.length();  
  
        StringBuffer buffer = new StringBuffer(len);  
  
        for (int i = 0; i < len;) {  
  
            aChar = theString.charAt(i++);  
  
            if (aChar == '\\') {  
  
                aChar = theString.charAt(i++);  
  
                if (aChar == 'u') {  
  
                    int val = 0;  
  
                    for (int j = 0; j < 4; j++) {  
  
                        aChar = theString.charAt(i++);  
  
                        switch (aChar) {  
  
                        case '0':  
  
                        case '1':  
  
                        case '2':  
  
                        case '3':  
  
                        case '4':  
  
                        case '5':  
  
                        case '6':  
  
                        case '7':  
  
                        case '8':  
  
                        case '9':  
  
                            val = (val << 4) + aChar - '0';  
  
                            break;  
  
                        case 'a':  
  
                        case 'b':  
  
                        case 'c':  
  
                        case 'd':  
  
                        case 'e':  
  
                        case 'f':  
  
                            val = (val << 4) + 10 + aChar - 'a';  
  
                            break;  
  
                        case 'A':  
  
                        case 'B':  
  
                        case 'C':  
  
                        case 'D':  
  
                        case 'E':  
  
                        case 'F':  
  
                            val = (val << 4) + 10 + aChar - 'A';  
  
                            break;  
  
                        default:  
  
                            throw new IllegalArgumentException(  
  
                            "Malformed      encoding.");  
                        }  
  
                    }  
  
                    buffer.append((char) val);  
  
                } else {  
  
                    if (aChar == 't') {  
  
                        aChar = '\t';  
                    }  
  
                    if (aChar == 'r') {  
  
                        aChar = '\r';  
                    }  
  
                    if (aChar == 'n') {  
  
                        aChar = '\n';  
                    }  
  
                    if (aChar == 'f') {  
  
                        aChar = '\f';  
  
                    }  
  
                    buffer.append(aChar);  
                }  
  
            } else {  
  
                buffer.append(aChar);  
  
            }  
  
        }  
  
        return buffer.toString();  
  
    }  
}
