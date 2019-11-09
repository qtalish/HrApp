package com.kgate.test;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


public class Test2 {
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException
	{

/*	String date = "2019-11-06";
	String date2 = "2019-12-10";
	
	
		
	//Parsing the date
	LocalDate dateBefore = LocalDate.parse(date);
	System.out.println(dateBefore);
	LocalDate dateAfter = LocalDate.parse(date2);
		
	//calculating number of days in between
	long noOfDaysBetween = ChronoUnit.MONTHS.between(dateBefore, dateAfter);
		
	//displaying the number of days
	System.out.println(noOfDaysBetween);
	*/
	
		
		String startDate = "2019-11-03";
		String promotionDate = "2019-12-08";

		LocalDate sdate = LocalDate.parse(startDate);
		LocalDate pdate = LocalDate.parse(promotionDate);

		LocalDate ssdate = LocalDate.of(sdate.getYear(), sdate.getMonth(), sdate.getDayOfMonth());
		LocalDate ppdate = LocalDate.of(pdate.getYear(), pdate.getMonth(), pdate.getDayOfMonth());

		Period period = Period.between(ssdate, ppdate);
		System.out.println("Difference: " + period.getYears() + " years " 
		                                  + period.getMonths() + " months "
		                                  + period.getDays() + " days ");
	
	
	
	
	
	
	
/*    Date date4 = sdf.parse(date);
	Date date3 =	sdf.parse(date2);
	
	System.out.println("..."+date3);
	 
	 long difference = date3.getTime() - date4.getTime();
     float daysBetween = (difference / (1000*60*60*24));
          You can also convert the milliseconds to days using this method
          * float daysBetween = 
          *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
          
     System.out.println("Number of Days between dates: "+daysBetween);*/
	

	}	
}
