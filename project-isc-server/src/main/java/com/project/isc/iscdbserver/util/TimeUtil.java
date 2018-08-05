package com.project.isc.iscdbserver.util;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	private final static long DAY_SECOND = 86400000;  //1000*60*60*24
	private final static int DATE_CYCLE = 30;
	/**
	 * 相差时间 已天为单位
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	public static int getDiscrepantDays(String dateStart,String dateEnd){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date beginDate = df.parse(dateStart);
			Date endDate = df.parse(dateEnd);
			return getDiscrepantDays(beginDate, endDate);
		} catch (ParseException e) {
			return -1;
		}
	}
	
	public static boolean getTimeSame(Date d1,Date d2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str1 = getDateFormat(d1);
		String str2 = getDateFormat(d2);
		try {
			Date beginDate = df.parse(str1);
			Date endDate = df.parse(str2);
			if ((beginDate.getTime()-endDate.getTime())==0) {
				return true;
			}
		} catch (ParseException e) {
			return false;
		}
		return false;
	}
	public static boolean getTimeSameDay(Date d1,Date d2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (d1!=null && d2!=null) {
			String str1 = getDateFormat(d1);
			String str2 = getDateFormat(d2);
			try {
				Date beginDate = df.parse(str1);
				Date endDate = df.parse(str2);
				if ((beginDate.getTime()-endDate.getTime())==0) {
					return true;
				}
			} catch (ParseException e) {
				return false;
			}
		}
		return false;
	}
	
	public static boolean getTimeSameDay(String str1,String str2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginDate = df.parse(str1);
			Date endDate = df.parse(str2);
//			endDate = addDay(endDate,-1);
			if ((beginDate.getTime()-endDate.getTime())==0) {
				return true;
			}
		} catch (ParseException e) {
			return false;
		}
		return false;
	}
	
	/**
	 * 将时间格式化
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static String getDateFormatDay(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	/**
	 * 将时间格式化 现在时间
	 * @return
	 */
	public static String getDateFormat() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static String getDateFormatDay() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	/**
	 * 相差时间 已天为单位 重载方法
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	public static int getDiscrepantDays(Date dateStart,Date dateEnd) {
		long betweenDate = (dateEnd.getTime()-dateStart.getTime())/DAY_SECOND;
		return (int)betweenDate;
	}

	/**
	 * 已当前时间未边界增加天数
	 * @param day
	 * @return
	 */
	public static Date addDay(int day) {
		Date nowdate = new Date();
		Date dateStart = new Date((nowdate.getTime()+(DAY_SECOND* day)));
		return dateStart;
	}
	
	public static Date addDay(Date nowdate,int day) {
		Date dateStart = new Date((nowdate.getTime()+(DAY_SECOND* day)));
		return dateStart;
	}
	
	public static int getDateCycle(int discrepantDays) {
		return discrepantDays/DATE_CYCLE;
	}
	
	public static int getDateCycleRemainder(int discrepantDays) {
		return discrepantDays%DATE_CYCLE;
	}
	
	/**
	 * 比较两个时间的大小
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean compareDate(Date d1,Date d2) {
		System.out.println(d1.before(d2));
		System.out.println(d1.after(d2));
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		String date1 = "2017-12-11 23:59:59";
		String date2 = "2017-12-12 00:00:01";

		//2018-01-30 10:46:46.0
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = addDay(df.parse(date1), 30);
		
		String d = "2018-01-30 10:46:46.0";
		Date date = df.parse(d);
		System.out.println(date.getTime());
		System.out.println(date.getTime()+(DAY_SECOND* 30));
		Date date3x = new Date(date.getTime());
		String d3x = df.format(date3x);
		Date date3x1 = df.parse(d3x);
		Date date30 = new  Date(date.getTime()+(DAY_SECOND* 30));
		System.out.println();
		
//		String date1 = "2017-12-11";
//		String date2 = "2017-12-12";
		System.out.println(getTimeSameDay(date1, date2));
//		compareDate(date1, date2);
		
		int days = getDiscrepantDays(date1,date2);
		System.out.println(days);
//		int cycle = getDateCycle(days);
//		int cycleR = getDateCycleRemainder(days);
//		System.out.println(cycle);
//		System.out.println(cycleR);
//		
//		Date date = new Date();
//		String nowDate = getDateFormat(date);
//		System.out.println(nowDate);
//		
//		Date datea  = addDay(100);
//		String nowDate1 = getDateFormat(datea);
//		System.out.println(nowDate1);
//		
//		compareDate(date, datea);
//		Date d1 =new Date();
//		Thread.sleep(2000);
//		Date d2 =new Date();
//		System.out.println(getTimeSame(d1, d2));
//		System.out.println(d1.before(d2));
		
	}

}
