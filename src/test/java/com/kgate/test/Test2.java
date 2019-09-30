package com.kgate.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.jexl2.UnifiedJEXL.Exception;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.xml.sax.SAXException;

import com.kgate.entity.Attendance;
import com.kgate.entity.AttendanceImport;

public class Test2 {
	
	/*
	 * @InitBinder public void initConverter(WebDataBinder binder) {
	 * CustomDateEditor dateEditor = new CustomDateEditor(new
	 * SimpleDateFormat("yyyy-MM-dd"), true);
	 * binder.registerCustomEditor(Date.class, dateEditor); }
	 */
	public static void main(String[] args) throws java.lang.Exception {
		try {
		File file2 = ResourceUtils.getFile("classpath:Book2.xlsx");
		System.out.println("1111111111111");
		InputStream stream = new FileInputStream(file2.getPath());
		System.out.println("1111111111111");
		System.out.println("stream :	" + stream);
		String path = "classpath:attendance.xml";
		File file =  ResourceUtils.getFile(path);
		List<AttendanceImport> att = Test2.parseExcelFileToBeans(stream, file);
		System.out.println("Person List: " + att);
		for (AttendanceImport a : att) {
			int daysInMonth = Test2.getMonthDays(a.getMonth(), a.getYear());
			int month = Test2.monthNumber(a.getMonth());
			System.out.println("month number is "+month);
			for (int i=1; i<=daysInMonth;i++) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String year =Integer.toString(a.getYear());
				Date date = format.parse((year+"-"+month+"-"+i ));
				System.out.println("!@!@!@!"+date);
				String input = format.format(date); 
				System.out.println("input "+input);
				
				/*
					 * Calendar cal = Calendar.getInstance(); cal.setTime(date); String formatedDate
					 * = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
					 * cal.get(Calendar.DATE); System.out.println("formatedDate : " + formatedDate);
					 * Date date1 = format.parse(formatedDate); System.out.println("ass"+date1);
					 */
				Attendance at = new Attendance();
				at.setAttDate(date);
				at.setEmpCode(a.getEmpCode());
				at.setFirstName(a.getFirstName());
				at.setLastName(a.getLastName());
				System.out.println("First Name: "+a.getLastName());
				at.setMonth(a.getMonth());
				Class class1 = a.getClass();
				String chng2 = "getD" + i;
				Method gs1Method = class1.getMethod(chng2, new Class[] {});
				String str1 = (String) gs1Method.invoke(a, new Object[] {});
				System.out.println("getString1 returned: " + str1);
			//	at.setStatus(a.getD1());
				at.setYear(a.getYear());
				at.setStatus(str1);
				
				System.out.println(at);
			}
		}
		
	} catch (Exception e) {

		e.printStackTrace();
		throw new java.lang.Exception("problem in uploading qs", e);
	}
}
	private static int monthNumber(String month) {
		int monthNumber = 0;
		if(month.equalsIgnoreCase("January"))
			monthNumber= 1;
		if(month.equalsIgnoreCase("February"))
			monthNumber= 2;
		if(month.equalsIgnoreCase("March"))
			monthNumber= 3;
		if(month.equalsIgnoreCase("April"))
			monthNumber= 4;
		if(month.equalsIgnoreCase("May"))
			monthNumber= 5;
		if(month.equalsIgnoreCase("June"))
			monthNumber= 6;
		if(month.equalsIgnoreCase("July"))
			monthNumber= 7;
		if(month.equalsIgnoreCase("August"))
			monthNumber= 8;
		if(month.equalsIgnoreCase("September"))
			monthNumber= 9;
		if(month.equalsIgnoreCase("October"))
			monthNumber= 10;
		if(month.equalsIgnoreCase("November"))
			monthNumber= 11;
		if(month.equalsIgnoreCase("December"))
			monthNumber= 12;
		return monthNumber;
		
	}
	public static int getMonthDays(String month, Integer year) {
	    int daysInMonth ;
	    if (month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")) {
	        daysInMonth = 30;
	    }
	    else {
	        if (month.equals("February")) {
	            daysInMonth = (year % 4 == 0) ? 29 : 28;
	        } else {
	            daysInMonth = 31;
	        }
	    }
	    return daysInMonth;
	}
	public static <T> List<T> parseExcelFileToBeans(final InputStream xlsFile, final File jxlsConfigFile)
			throws Exception, IOException, SAXException, InvalidFormatException {
		System.out.println("test");
		final XLSReader xlsReader = ReaderBuilder.buildFromXML(jxlsConfigFile);
		System.out.println("test 2");
		final List<T> result = new ArrayList<>();

		System.out.println("test 2");
		final Map<String, Object> beans = new HashMap<>();

		System.out.println("test 3");
		beans.put("result", result);
		try {
			System.out.println("test 3");
			xlsReader.read(xlsFile, beans);
			System.out.println("test 3");
		} catch (Exception r) {
			r.printStackTrace();
//			throw new AssessmentGenericException("Problems in converting excel into java beans");
		}
		return result;
	}

}
