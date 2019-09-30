package com.kgate.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Abc {
	public static void main(String[] args) throws ParseException {
		String start_dt = "2011-01-31";

		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = (Date) parser.parse(start_dt);
		System.out.println(date);

		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); 
		System.out.println(formatter.format(date));

	} }
