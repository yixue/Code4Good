package com.chase.code4good;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateParser {
	
	public static Calendar parse(String str) {
		String delims_space = "[ ]";
		String[] tokens_space = str.split(delims_space);
		String date = tokens_space[0];
		
		String delims_dash = "[-]";
		String[] tokens_dash = date.split(delims_dash);
		
		String year = tokens_dash[0];
		int y = Integer.parseInt(year);
		
		String month = tokens_dash[1];
		int m = Integer.parseInt(month);
		
		String day = tokens_dash[2];
		int d = Integer.parseInt(day);
		
		Calendar returnDate = new GregorianCalendar();
		returnDate.set(y, m, d);
		
		return returnDate;
	}
}
