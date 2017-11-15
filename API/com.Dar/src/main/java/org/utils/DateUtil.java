package org.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public static Date addSecs(Date date, int sec) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.SECOND, sec);
				
		return cal.getTime();
	}
}
