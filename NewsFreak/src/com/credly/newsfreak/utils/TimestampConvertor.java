/**
 * 
 */
package com.credly.newsfreak.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Karthy
 * 
 */
public class TimestampConvertor
{

	public static String usingDateAndCalendar(long input)
	{
		Date date = new Date(input * 1000);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return (cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-"
				+ cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + (cal
				.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

	}

	public static String usingDateAndCalendarWithTimeZone(long input)
	{
		Date date = new Date(input * 1000);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		return (cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-"
				+ cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + (cal
				.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

	}

	public static String usingDateFormatter(long input)
	{
		Date date = new Date(input * 1000);
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss z");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);

	}

	public static String usingDateFormatterWithTimeZone(long input)
	{
		Date date = new Date(input * 1000);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);

	}

}
