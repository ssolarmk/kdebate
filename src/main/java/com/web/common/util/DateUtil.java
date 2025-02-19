package com.web.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	/**
	 * �??��?�� ?��맷형?��?�� ?���? �?�?
	 * 
	 * <pre>
	 *  - Object ?��?��?�� ?���?, format?�� ?��?��값으�? 받음
	 *  - ?��짜는 String, Date ????���? ?��?��<br>
	 *  1. yyyy-MM-dd hh:mm:ss
	 *  2. yyyy?�� MM?�� dd?��
	 *  3. yyyy/MM/dd hh-mm-ss
	 *  4. ?��,�?,초는 ?��?���??��?�� 
	 *  5. ?��?�� ?���??���? ????��게로 ...<br>
	 *  ?��?�� : <p>
	 *  convertDate("20100712102251","yyyy-MM-dd HH:mm:ss");
	 *	convertDate("20100712102251","yyyy-MM-dd");
	 *	convertDate(new Date(),"yyyy?�� MM?�� dd?�� HH?�� mm�? ss�?");
	 *	convertDate(new Date(),"yyyy/MM/dd HH:mm:ss");
	 *	convertDate("2010-08-09","yyyy/MM/dd HH:mm:ss");
	 *	convertDate("2010-08-09","yyyyMMdd");
	 *	convertDate(new Date(),"yyyyMMdd");
	 * </pre>
	 * 
	 * @param b_date
	 * @param format
	 * @return string date
	 * @author Ryu Cheol Min
	 * @since 2010-07-12
	 */
	public static String convertDate(Object b_date, String format) {
		// 최종 리턴 ?���? ?���?
		String a_date = "";

		// 중간 ?���? ?���? ?���?
		Date m_date = null;

		String type = getB_dateType(b_date);

		if (type.equals("String")) // 1. String Type
		{
			String tmpTime = strFilter(b_date.toString());
			// System.out.println("tmpTime : "+tmpTime);
			GregorianCalendar gc = null;

			switch (tmpTime.length()) {
				case 8:
					gc = getGc(tmpTime, 1);
					break;
				case 12:
					gc = getGc(tmpTime, 2);
					break;
				case 14:
					gc = getGc(tmpTime, 3);
					break;
				default:
					gc = null;
			}
			if (gc != null)
				m_date = gc.getTime();
		} else if (type.equals("Date")) // 2. Date Type
		{
			m_date = (Date) b_date;
		} else
			return null;
		if (m_date == null)
			return null;

		a_date = setDateFormat(m_date, format);

		return a_date;
	}

	/**
	 * string?�� date�? �??��
	 * 
	 * @param strDate
	 * @param strFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDateConverte(String strDate, String strFormat) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(strFormat);
		return format.parse(strDate);
	}

	/**
	 * ?��?���? or ?���? ????�� ?���? ?��?��
	 * 
	 * @param b_date
	 * @return
	 */
	private static String getB_dateType(Object b_date) {
		if (b_date != null) {
			if (b_date.getClass().toString().equals("class java.lang.String")) {
				return "String";
			} else if (b_date.getClass().toString().equals("class java.util.Date")) {
				return "Date";
			}
		}
		return "";
	}

	/**
	 * ?��?��문자 ?���?
	 * 
	 * @param str
	 * @return
	 */
	public static String strFilter(String str) {
		String str_imsi = "";
		String[] filter_word = { "", "\\.", "\\?", "\\/", "\\~", "\\!", "\\@",
				"\\#", "\\$", "\\%", "\\^", "\\&", "\\*", "\\(", "\\)", "\\_",
				"\\+", "\\=", "\\|", "\\\\", "\\}", "\\]", "\\{", "\\[",
				"\\\"", "\\'", "\\:", "\\;", "\\<", "\\,", "\\>", "\\.", "\\?",
				"\\/", "\\-", "\\ " };
		for (int i = 0; i < filter_word.length; i++) {
			str_imsi = str.replaceAll(filter_word[i], "");
			str = str_imsi;
		}

		return str;
	}

	/**
	 * Format?��?���? �??��
	 * 
	 * @param m_date
	 * @param fm
	 * @return
	 */
	public static String setDateFormat(Date m_date, String fm) {

		if (fm == null)
			return fm;

		SimpleDateFormat sdf = new SimpleDateFormat(fm);

		try {
			// System.out.println("m_date : "+m_date);
			return sdf.format(m_date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * ?���? ?��?��?�� ?���? 처리
	 * 
	 * @param time
	 * @param type
	 * @return
	 */
	private static GregorianCalendar getGc(String time, int type) {
		int year = 0;
		int month = 0;
		int dayOfMonth = 0;
		int hourOfDay = 0;
		int minute = 0;
		int second = 0;
		GregorianCalendar gc = null;

		if (type == 1) {
			year = Integer.parseInt(time.substring(0, 4));
			month = Integer.parseInt(time.substring(4, 6));
			dayOfMonth = Integer.parseInt(time.substring(6, 8));
			gc = new GregorianCalendar(year, month - 1, dayOfMonth);
		} else if (type == 2) {
			year = Integer.parseInt(time.substring(0, 4));
			month = Integer.parseInt(time.substring(4, 6));
			dayOfMonth = Integer.parseInt(time.substring(6, 8));
			hourOfDay = Integer.parseInt(time.substring(8, 10));
			minute = Integer.parseInt(time.substring(10, 12));
			gc = new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute);

		} else if (type == 3) {
			year = Integer.parseInt(time.substring(0, 4));
			month = Integer.parseInt(time.substring(4, 6));
			dayOfMonth = Integer.parseInt(time.substring(6, 8));
			hourOfDay = Integer.parseInt(time.substring(8, 10));
			minute = Integer.parseInt(time.substring(10, 12));
			second = Integer.parseInt(time.substring(12, 14));
			gc = new GregorianCalendar(year, month - 1, dayOfMonth, hourOfDay, minute, second);
		}
		return gc;
	}

	/**
	 * yyyy-MM-dd ?��?��?�� ?��?��?���? ?��?��
	 * 
	 * @return
	 */
	public static String getTodateYmd() {
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");

		return sdformat.format(date);
	}

	/**
	 * ?���? ????�� �??�� ?��?��?���? ?��?��
	 * 
	 * @return
	 */
	public static String getTodayDateFormat(String format) {
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat(format);

		return sdformat.format(date);
	}

	/**
	 * ?��?�� ?���? �??��?���?
	 * yy-mm-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dtFormat = new SimpleDateFormat("yy-mm-dd HH:mm:ss");
		return dtFormat.format(cal.getTime());
	}

	/**
	 * ?���? ?�� ?���? �??��?���?
	 * 
	 * @return
	 */
	public static String getYesterTime() {

		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, -1);

		String yesterday = new java.text.SimpleDateFormat("yyyyMMdd").format(day.getTime());

		return yesterday;
	}

	/**
	 * return days between two date strings with user defined format.
	 * 
	 * @param String from date string
	 * @param String to date string
	 * @return int ?���? ?��?��?�� 맞고, 존재?��?�� ?��짜일 ?�� 2�? ?��?�� ?��?��?�� ?��?�� 리턴
	 *         -999: ?��?��?�� ?���? ?��?��거나 존재?���? ?��?�� ?���? ?��?�� 기간?�� ?��?��
	 */
	@SuppressWarnings("null")
	public static String daysBetween(String from, String to, String format) {
		String rimitStr = "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date d1 = null;
		java.util.Date d2 = null;
		long duration = 0;

		String rimitHour = "";
		String rimitMinute = "";

		try {
			d1 = formatter.parse(from);
			d2 = formatter.parse(to);
		} catch (java.text.ParseException e) {
			duration = -999;
		}

		if (!formatter.format(d1).equals(from))
			duration = -999;
		if (!formatter.format(d2).equals(to))
			duration = -999;

		duration = d2.getTime() - d1.getTime();

		if (duration < 0)
			duration = -999;

		int hour = (int) (duration / (1000 * 60 * 60));
		int minute = (int) (duration / (1000 * 60)) - (hour * 60);

		rimitHour = String.valueOf(hour);
		rimitMinute = String.valueOf(minute);

		rimitStr = rimitHour + "?���? " + rimitMinute + "�? ?��?��";

		if (duration == -999)
			rimitStr = "?��간이 �??��?��?��?��.";

		return rimitStr;
	}

	/*
	 * public static void main(String[] args)
	 * {
	 * System.out.println(daysBetween("2011-06-01 11:50:00", "2011-06-06 11:10:00",
	 * "yyyy-MM-dd HH:mm:ss"));
	 * }
	 */

	/**
	 * ?��짜계?��
	 * 
	 * <pre>
	 * -?��?��?��짜로�??��?�� 계산?��
	 * 1. Y : ?��?��계산
	 * 2. M : ?�� 계산
	 * 3. D : ?��짜계?��
	 * getCalculationDate("Y", 5)  : ?��?��로�???�� 5?��?���? 리턴
	 * getCalculationDate("D", -3) : ?��?��로�???�� 3?��?��?�� 리턴
	 * </pre>
	 * 
	 * @param gb
	 * @param num
	 * @return
	 */
	public static String getCalculationDate(String gb, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// Calendar c = Calendar.getInstance();
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));

		if (gb.equals("Y")) {
			c.add(Calendar.YEAR, num);
		} else if (gb.equals("M")) {
			c.add(Calendar.MONTH, num);
		} else if (gb.equals("D")) {
			c.add(Calendar.DATE, num);
		}

		String toDate = sdf.format(c.getTime());

		return toDate;
	}

	/**
	 * ?��?��?��?��리�???��?��?��?�� 고유값을 ?��?��?���? ?��?�� ?��?��?��?��?��17?��리의TIMESTAMP값을 구하?��
	 * 기능
	 *
	 * @param
	 * @return Timestamp �?
	 * @exception MyException
	 * @see
	 */
	public static String getTimeStamp() {

		String rtnStr = null;

		// 문자?���? �??��?���? ?��?�� ?��?�� ?��?��(?��?��-?��-?�� ?��:�?:�?:�?(?��?��?��?�� �?))
		String pattern = "yyyyMMddhhmmssSSS";

		SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		rtnStr = sdfCurrent.format(ts.getTime());

		return rtnStr;
	}

}
