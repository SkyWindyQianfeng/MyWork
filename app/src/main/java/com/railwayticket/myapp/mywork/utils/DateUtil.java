package com.railwayticket.myapp.mywork.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间日期处理类
 */
@SuppressLint("SimpleDateFormat")
public final class DateUtil {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATE_FORMAT2 = "yyyy年MM月dd日";
	public final static String DATE_FORMAT3 = "yyyyMMdd";
	public final static String DATE_FORMAT4 = "yyyyMM";
	public final static String DATE_FORMAT5 = "yyyy年MM月";
	public final static String DATE_FORMAT6 = "yyyy-MM";
	public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	public final static String FULL_DATE_TIME = "yyyy-MM-dd HH:mm:ss:SSS";
	public final static String TIME_FORMAT = "HH:mm";
	public final static String TIME_FORMAT1 = "HH:mm:ss";
	public final static String TIME_FORMAT2 = "HHmmss";
	public final static String MONTH_DAY_HOUR_MINUTE_FORMAT = "MM-dd HH:mm";
	public final static String FULL_DATE_TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
	public final static String FULL_DATE_TIME_FORMAT_2 = "yyyyMMddHHmmss";
	public final static String FULL_DATE_TIME_FORMAT_3 = "yyyy/MM/dd HH:mm";
	public final static String FULL_DATE_TIME_FORMAT_4 = "yyyyMMddHHmm";
	public final static String FULL_DATE_TIME_FORMAT_5 = "HHddmmss";

	private static final int DAYS_OF_A_WEEK = 7;

	private DateUtil() {
	}

	@SuppressLint("SimpleDateFormat")
	public static Date parseDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT3);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static Date parseDate(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 剩余 天 时 分 秒
	 */
	public static String remainTime(Date startDate, String endDateStr) {

		Date endDate = DateUtil.parseDate(endDateStr,
				DateUtil.FULL_DATE_TIME_FORMAT_1);
		// Date endDate = DateUtil.parseDate(endDateStr,
		// DateUtil.FULL_DATE_TIME);

		long remain = endDate.getTime() - startDate.getTime();
		if (remain <= 0) {
			return " 0 天 0 小时 0 分 0 秒";
		} else {
			// 计算出相差天数
			int days = (int) (Math.floor(remain / (24 * 3600 * 1000)));
			// 计算出小时数
			double hour = remain % (24 * 3600 * 1000); // 计算天数后剩余的毫秒数
			int hours = (int) Math.floor(hour / (3600 * 1000));
			// 计算相差分钟数
			double minute = hour % (3600 * 1000); // 计算小时数后剩余的毫秒数
			int minutes = (int) Math.floor(minute / (60 * 1000));

			// 计算相差秒数
			double second = minute % (60 * 1000); // 计算分钟数后剩余的毫秒数
			int seconds = (int) Math.round(second / 1000);

			return days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
		}
	}

	/**
	 * 获取增加小时后的 Date
	 */
	public static Date addHour(Date date, int i) {
		Calendar calendar = getDefaultCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, i);
		return calendar.getTime();
	}

	@SuppressLint("SimpleDateFormat")
	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(date);
	}

	@SuppressLint("SimpleDateFormat")
	public static String formatTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
		return format.format(date);
	}

	/**
	 * 默认格式转化 把20120804格式化成2012-08-04格式
	 */
	public static String formatDate(String dateStr) {
		try {
			return formatDate(parseDate(dateStr, DATE_FORMAT3), DATE_FORMAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// 默认格式转化
	public static String fomatDateTime(String dateStr) {
		try {
			return formatDate(parseDate(dateStr, FULL_DATE_TIME_FORMAT_4),
					DATE_TIME_FORMAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 格式转化 把20120606162431格式化成2012-06-06 16:24:31格式
	 */
	public static String formatDateWithTime(String dateStr) {
		try {
			return formatDate(parseDate(dateStr, FULL_DATE_TIME_FORMAT_2),
					FULL_DATE_TIME_FORMAT_1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@SuppressLint("SimpleDateFormat")
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 格式化，去掉年头两位，去掉日期，例：返回1208(表示2012年8月)
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatDateNoTop(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT4);
			String formatStr = format.format(date);
			return formatStr.substring(2, formatStr.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化，去掉年头两位，去掉日期，例：返回201208(表示2012年8月)
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatDateWithTop(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT4);
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化，把2012-08-04格式化成20120804格式
	 */
	public static String formatDateNoSeparated(String dateStr) {
		try {
			Date date = parseDate(dateStr, DATE_FORMAT);
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT3);
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 格式化，把20120804格式化成格式2012-08-04
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatDateForSeparated(String dateStr) {
		try {
			Date date = parseDate(dateStr, DATE_FORMAT3);
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getCurrentDateFormat() {
		return formatDate(new Date());
	}

	public static int getDateNum(Date fromDate, Date endDate) {
		long days = (endDate.getTime() - fromDate.getTime())
				/ (1000 * 60 * 60 * 24);
		return (int) days;
	}

	public static Date addDate(Date date, int number) {
		Calendar calendar = getDefaultCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, number);
		return calendar.getTime();
	}

	public static Date addMonth(Date date, int number) {
		Calendar calendar = getDefaultCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, number);
		return calendar.getTime();
	}

	public static Date subMonth(Date date, int number) {
		Calendar calendar = getDefaultCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, number);
		return calendar.getTime();
	}

	public static Calendar getDefaultCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		return calendar;
	}

	public static String getStringDate(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return year + "-" + getNiceString(month) + "-" + getNiceString(day);
	}

	public static String getNiceString(int value) {
		String str = "00" + value;
		return str.substring(str.length() - 2, str.length());
	}

	public static Calendar getCalendarFromDate(Date date) {
		Calendar calendar = getDefaultCalendar();
		calendar.setTime(date);
		return calendar;
	}

	public static String getInterval(Date startDate, Date endDate) {
		long intervalTime = endDate.getTime() - startDate.getTime();
		return getInterval(intervalTime);
	}

	public static int getIntervalMinute(Date startDate, Date endDate) {
		long intervalTime = endDate.getTime() - startDate.getTime();
		return (int) (intervalTime / (1000 * 60));
	}

	public static int getIntervalHour(Date startDate, Date endDate) {
		long intervalTime = endDate.getTime() - startDate.getTime();
		return (int) ((intervalTime / (1000 * 60)) / 60);
	}

	public static String getInterval(long intervalTime) {
		int hour = (int) (intervalTime / (1000 * 60 * 60));
		int minute = (int) (intervalTime / (1000 * 60) - hour * 60);
		int second = (int) ((intervalTime / 1000) - hour * 60 * 60 - minute * 60);
		if (hour > 0) {
			return hour + "小时 " + minute + "分 " + second + "秒";
		} else if (minute > 0) {
			return minute + "分 " + second + "秒";
		} else {
			return second + "秒";
		}
	}

	public static int getIntervalDay(String sDateStr, String eDateStr) {
		int day = 0;
		try {
			Date sDate = DateUtil.parseDate(sDateStr, DateUtil.DATE_FORMAT);
			Date eDate = DateUtil.parseDate(eDateStr, DateUtil.DATE_FORMAT);
			long intervalTime = eDate.getTime() - sDate.getTime();
			day = (int) (intervalTime / 1000 / 60 / 60 / 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	public static String getDateStr(Date date) {
		return getYear(date) + "年" + getMonth(date) + "月" + getDayOfMonth(date)
				+ "日";
	}

	public static int getYear(Date date) {
		Calendar calendar = getCalendarFromDate(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar calendar = getCalendarFromDate(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDayOfMonth(Date date) {
		Calendar calendar = getCalendarFromDate(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date now) {
		Calendar calendar = getCalendarFromDate(now);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getWeekOfYear(Date date) {
		Calendar calendar = getCalendarFromDate(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		return calendar.get(Calendar.WEEK_OF_YEAR) - 1;
	}

	public static Date getCurrentDate() {
		Calendar calendar = getCalendarFromDate(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getNextDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentDate());
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 一周的日期
	 * 
	 * @param date
	 * @return
	 */
	public static List<Date> getWeekDayOfYear(Date date) {
		Calendar calendar = getCalendarFromDate(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		int year = calendar.get(Calendar.YEAR);

		List<Date> result = new ArrayList<Date>();
		result.add(getDateOfYearWeek(year, week, Calendar.MONDAY));
		result.add(getDateOfYearWeek(year, week, Calendar.TUESDAY));
		result.add(getDateOfYearWeek(year, week, Calendar.WEDNESDAY));
		result.add(getDateOfYearWeek(year, week, Calendar.THURSDAY));
		result.add(getDateOfYearWeek(year, week, Calendar.FRIDAY));
		result.add(getDateOfYearWeek(year, week, Calendar.SATURDAY));
		result.add(getDateOfYearWeek(year, week, Calendar.SUNDAY));
		return result;
	}

	/**
	 * 获取一年中某周,星期几的日期
	 * 
	 * @param yearNum
	 * @param weekNum
	 * @param dayOfWeek
	 * @return
	 */
	private static Date getDateOfYearWeek(int yearNum, int weekNum,
			int dayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		cal.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		/*
		 * cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0);
		 * cal.set(Calendar.SECOND, 0);
		 */
		return cal.getTime();
	}

	/**
	 * 获取指定日期是一周的第几天,星期日是第一天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = getCalendarFromDate(date);
		calendar.setMinimalDaysInFirstWeek(DAYS_OF_A_WEEK);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static boolean beyondDate(Date beginDate, Date endDate, int i) {
		long inter = endDate.getTime() - beginDate.getTime();
		return inter - (i * 1000 * 60 * 60 * 24) >= 0;
	}

	public static boolean beyondMinute(Date beginDate, Date endDate, int i) {
		long inter = endDate.getTime() - beginDate.getTime();
		return Math.abs(inter) - (i * 1000 * 60) >= 0;
	}

	public static boolean beyondSecond(Date beginDate, Date endDate, int i) {
		long inter = endDate.getTime() - beginDate.getTime();
		return Math.abs(inter) - (i * 1000) >= 0;
	}

	public static boolean isEndGtStart(Date StartDate, Date endDate) {
		long inter = endDate.getTime() - StartDate.getTime();
		return inter > 0;
	}

	// 判断是否同一天
	public static boolean isSameDay(Date dateOne, Date dateTwo) {
		boolean flag = true;
		if (getYear(dateOne) != getYear(dateTwo)) {
			flag = false;
		} else if (getMonth(dateOne) != getMonth(dateTwo)) {
			flag = false;
		} else if (getDayOfMonth(dateOne) != getDayOfMonth(dateTwo)) {
			flag = false;
		}
		return flag;
	}

	@SuppressLint("SimpleDateFormat")
	public static String convertLicai(long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	@SuppressLint("SimpleDateFormat")
	public static String convert(long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	public static String convertSecond(long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(FULL_DATE_TIME_FORMAT_1);
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	public static long toLong(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date b = null;
		long mill = 0;
		try {
			b = sdf.parse(str);
			mill = b.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mill;
	}

	public static String long2Minute(long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	public static long getLong(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d2 = null;
		try {
			d2 = sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long t3 = d2.getTime();
		return t3;
	}

	/**
	 * @Description: long类型转换成日期
	 *
	 * @param lo 毫秒数
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String longToDate(long lo){
		Date date = new Date(lo);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		return sd.format(date);
	}

	public static String longToHour(long lo){
		int allM= (int) (lo/1000);
		int h=allM/60/60;
		int m=allM/60%60;
		int s=allM%60;
		String hh="";
		String mm="";
		String ss="";
		if(h<10){
			hh="0"+h;
		}else{
			hh=h+"";
		}

		if(m<10){
			mm="0"+m;
		}else{
			mm=m+"";
		}
		if(s<10){
			ss="0"+s;
		}else{
			ss=""+s;
		}


		return hh+":"+mm+":"+ss;
	}

	public static long getNowTime()
	{
		return System.currentTimeMillis();
	}
	
	
}