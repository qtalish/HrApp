package com.kgate.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Abc {
	public static void main(String[] args) throws ParseException {
		
		Calendar cal  = Calendar.getInstance();
	    //subtracting a day
	    cal.add(Calendar.DATE, -1);

	    SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
	    String result = s.format(new Date(cal.getTimeInMillis()));
	    System.out.println("..."+result);
	    
	System.out.println(s.parse(result));
	} }
