package com.project.isc.iscdbserver.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateFormatUtil {
	private DateFormatUtil() {
	}

	public static String DateObjectToString(Date date) {
		if (null == date)
			return null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	public static String DateObjectToTimeString(Date date) {
		if (null == date)
			return null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	public static Date stringToDate(String dateStr) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException("时间格式转换失败");
		}
	}

	public static int compareDateObject(Date srcDate, Date destDate) {
		if ((null == srcDate) || (null == destDate))
			throw new RuntimeException("比较日期不能为null");

		long srcMillisecond = srcDate.getTime();
		long destMillsecond = destDate.getTime();
		long gap = destMillsecond - srcMillisecond;

		if (0 == gap)
			return 0;
		else if (gap > 0)
			return 1;
		else
			return -1;
	}

	// 获取当前月的开始和结束时间
	public static List<Date> getStartDateAndEndDateOfNowMonth() {
		Date startDate = null;
		Date endDate = null;

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int days = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(year, month, 1, 0, 0, 0);
		startDate = calendar.getTime();

		calendar.set(year, month, days, 23, 59, 59);
		endDate = calendar.getTime();

		List<Date> dateList = new ArrayList<Date>();
		dateList.add(startDate);
		dateList.add(endDate);

		System.out.println(startDate);
		System.out.println(endDate);

		return dateList;
	}

	// 获取上一周每天的开始和结束时间
	public static List<List<Date>> getStartDayAndEndDayOfLastWeek() {
		List<List<Date>> dateList = new ArrayList<List<Date>>();

		Calendar calendar = Calendar.getInstance();
		// 获取当前时间
		Date now = calendar.getTime();
		long nowTimes = now.getTime();

		// 获取本周第几天:国外周日为第一天
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		//国内星期
		int dayOfWeekInChina;
		if(dayOfWeek == 1) {
			dayOfWeekInChina = 7;
		}else {
			dayOfWeekInChina = dayOfWeek - 1;
		}

		/****** 获取上一周周日开始时间和结束时间 ********/
		// 获取当天时分秒
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		// 上周周日时间毫秒数
		long sundayOfLastWeek_end = nowTimes - (dayOfWeekInChina - 1) * 24 * 3600 * 1000 - h * 3600 * 1000 - m * 60 * 1000
				- s * 1000 - 1000;
		long sundayOflastWeek_start = sundayOfLastWeek_end - 24 * 3600 * 1000 + 1000;
		List<Date> sundayList = new ArrayList<Date>();
		sundayList.add(new Date(sundayOflastWeek_start));
		sundayList.add(new Date(sundayOfLastWeek_end));

		// 上周周六时间毫秒数
		long saturdayOfLastWeek_end = sundayOflastWeek_start - 1000;
		long saturdayOfLastWeek_start = saturdayOfLastWeek_end - 24 * 3600 * 1000 + 1000;
		List<Date> saturdayList = new ArrayList<Date>();
		saturdayList.add(new Date(saturdayOfLastWeek_start));
		saturdayList.add(new Date(saturdayOfLastWeek_end));

		// 上周周五时间毫秒数
		long fridayOfLastWeek_end = saturdayOfLastWeek_start - 1000;
		long fridayOfLastWeek_start = fridayOfLastWeek_end - 24 * 3600 * 1000 + 1000;
		List<Date> fridayList = new ArrayList<Date>();
		fridayList.add(new Date(fridayOfLastWeek_start));
		fridayList.add(new Date(fridayOfLastWeek_end));

		// 上周周四时间毫秒数
		long thursdayOfLastWeek_end = fridayOfLastWeek_start - 1000;
		long thursdayOfLastWeek_start = thursdayOfLastWeek_end - 24 * 3600 * 1000 + 1000;
		List<Date> thursdayList = new ArrayList<Date>();
		thursdayList.add(new Date(thursdayOfLastWeek_start));
		thursdayList.add(new Date(thursdayOfLastWeek_end));

		// 上周周三时间毫秒数
		long wednesdayOfLastWeek_end = thursdayOfLastWeek_start - 1000;
		long wednesdayOfLastWeek_start = wednesdayOfLastWeek_end - 24 * 3600 * 1000 + 1000;
		List<Date> wednesdayList = new ArrayList<Date>();
		wednesdayList.add(new Date(wednesdayOfLastWeek_start));
		wednesdayList.add(new Date(wednesdayOfLastWeek_end));

		// 上周周二时间毫秒数
		long tuesdayOfLastWeek_end = wednesdayOfLastWeek_start - 1000;
		long tuesdayOfLastWeek_start = tuesdayOfLastWeek_end - 24 * 3600 * 1000 + 1000;
		List<Date> tuesdayList = new ArrayList<Date>();
		tuesdayList.add(new Date(tuesdayOfLastWeek_start));
		tuesdayList.add(new Date(tuesdayOfLastWeek_end));

		// 上周周一时间毫秒数
		long mondayOfLastWeek_end = tuesdayOfLastWeek_start - 1000;
		long mondayOfLastWeek_start = mondayOfLastWeek_end - 24 * 3600 * 1000 + 1000;
		List<Date> mondayList = new ArrayList<Date>();
		mondayList.add(new Date(mondayOfLastWeek_start));
		mondayList.add(new Date(mondayOfLastWeek_end));

		dateList.add(sundayList);
		dateList.add(saturdayList);
		dateList.add(fridayList);
		dateList.add(thursdayList);
		dateList.add(wednesdayList);
		dateList.add(tuesdayList);
		dateList.add(mondayList);
		
		return dateList;
	}
	
	// 获取当天开始和结束日期
	public static List<Date> getStartDateAndEndDateOfNowDay() {
		Date startDate = null;
		Date endDate = null;
		List<Date> dateList = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int days = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(year, month, days, 0, 0, 0);
		startDate = calendar.getTime();

		calendar.set(year, month, days, 23, 59, 59);
		endDate = calendar.getTime();
		
		dateList.add(startDate);
		dateList.add(endDate);
		
		return dateList;
	}

	public static String secondTimeToString(long secondTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(secondTime);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(calendar.getTime());
	}

	public static void main(String[] args) {
		// getStartDateAndEndDateOfNowMonth();
		//getStartDayAndEndDayOfLastWeek();
		System.out.println(getStartDateAndEndDateOfNowDay());
	}
}
