package com.jkapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

	private TimeUtils() {
		throw new UnsupportedOperationException("can't instantiate class TimeUtils");
	}
	
	public static Date timestamp2Date(long timestamp) {
		return new Date(timestamp);
	}
	
	public static long date2timestamp(Date d) {
		if(d == null) {
			return 0;
		}
		return d.getTime();
	}
	
	public static String timestamp2DateString(long timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
		return sdf.format(timestamp2Date(timestamp));
	}
	
	public static String timestamp2DesriptionString(long timestamp) {
		long time = System.currentTimeMillis();
		
		long minute = 60 * 1000;
		long hour = 60 * minute;
		
		long value = timestamp - time;
		//非法
		if(value < 0) {
			return "";
		//1分钟内
		} else if(value < minute) {
			return "刚刚";
		//1小时内
		} else if(value < hour) {
			return value / hour + "分钟前";
		} else if(value < 24 * hour) {
			return value / hour + "小时前";
		} else {
			return timestamp2DateString(timestamp);
		}
	}

	public static long getTimestampDelta(Date d1, Date d2) {
		long timestamp1 = date2timestamp(d1);
		long timestamp2 = date2timestamp(d2);
		return timestamp1 - timestamp2;
	}
	
	public static int getYear(Date d) {
		return getYear(date2timestamp(d));
	}
	
	public static int getYear(long timestamp) {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	
	public static int getMonth(Date d) {
		return getYear(date2timestamp(d));
	}

	public static int getMonth(long timestamp) {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		return calendar.get(Calendar.MONTH);
	}
	
	public static int getDayOfWeek(Date d) {
		return getYear(date2timestamp(d));
	}
	
	public static int getDayOfWeek(long timestamp) {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getDayOfMonth(Date d) {
		return getYear(date2timestamp(d));
	}
	
	public static int getDayOfMonth(long timestamp) {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getDayOfYear(Date d) {
		return getYear(date2timestamp(d));
	}
	
	public static int getDayOfYear(long timestamp) {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
}
