package com.kgate.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.jexl2.UnifiedJEXL.Exception;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.kgate.entity.Attendance;
import com.kgate.entity.AttendanceImport;
import com.kgate.entity.User;
import com.kgate.repository.AttendanceRepository;
import com.kgate.repository.UserRepository;

@Controller
public class ImportController {

	@Autowired
	UserRepository urepo;
	
	@Autowired
	AttendanceRepository arepo;
	
	static int workload = 12;
	
	public static <T> List<T> parseExcelFileToBeans(final InputStream xlsFile, final File jxlsConfigFile)
			throws java.lang.Exception {
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
			throw new java.lang.Exception("Problems in converting excel into java beans");
		}
		return result;
	}
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void uploadAttendance(HttpServletResponse response, MultipartHttpServletRequest request) throws java.lang.Exception {
		try {

			
			MultipartFile multipartFile = request.getFile("fileAttendance");
			Long size = multipartFile.getSize();
			String fileName = multipartFile.getName();
			String contentType = multipartFile.getContentType();
			InputStream stream = multipartFile.getInputStream();
				
			System.out.println("1111111111111");
			System.out.println("stream :	" + stream);
			String path = "classpath:attendance.xml";
			File file =  ResourceUtils.getFile(path);
			List<AttendanceImport> att = ImportController.parseExcelFileToBeans(stream, file);
			
			if (att.size() == 0) {
				throw new java.lang.Exception("NO_DATA_IN_EXCEL");
			}			 
		
			System.out.println("Person List: " + att);
			for (AttendanceImport a : att) {
				System.out.println(a.getFirstName());
				System.out.println((a.getLastName()));
				int daysInMonth = ImportController.getMonthDays(a.getMonth(), a.getYear());
				int month = ImportController.monthNumber(a.getMonth());
				System.out.println("month number is "+month);
				for (int i=1; i<=daysInMonth;i++) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String year =Integer.toString(a.getYear());
					Date date = format.parse((year+"-"+month+"-"+i));
//					System.out.println("!@!@!@!"+date);
   
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
				System.out.println("kuuu"+at.getAttDate());
				System.out.println(at);
				arepo.save(at);
				
			}
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new java.lang.Exception("problem in uploading attendance", e);
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
		
		@RequestMapping(value = "/uploadEmp", method = RequestMethod.POST)
		public ModelAndView uploadEmp(HttpServletResponse response, MultipartHttpServletRequest request) throws java.lang.Exception {
			ModelAndView mav = new ModelAndView("redirect:/viewEmployees");
			try {
				MultipartFile multipartFile = request.getFile("fileEmp");
				Long size = multipartFile.getSize();
				String fileName = multipartFile.getName();
				String contentType = multipartFile.getContentType();
				InputStream stream = multipartFile.getInputStream();
					
				System.out.println("1111111111111");
				System.out.println("stream :	" + stream);
				String path = "classpath:employee.xml";
				File file =  ResourceUtils.getFile(path);
				List<User> user = ImportController.parseExcelFileToBeans(stream, file);
				
				if (user.size() == 0) {
					throw new java.lang.Exception("NO_DATA_IN_EXCEL");
				}			 
			
				System.out.println("Person List: " + user);
				for (User u : user) {
					System.out.println(u.getFname());
					System.out.println((u.getLname()));
					System.out.println(u.getUserType());
					String salt = BCrypt.gensalt(workload);
					String hspwd = BCrypt.hashpw(u.getPassword(), salt);
					u.setPassword(hspwd);
					urepo.save(u);
					UserController uc = new UserController();
					uc.sendMail(u.getEmail(),
							"<font color=\"red\">Hello </font> " + u.getFname() + ",<br>"
									+ "<font color=\"red\">Your Username is: </font> " + u.getEmail() + "<br>"
									+ "<font color=\"red\">Your Password is: </font>" + u.getPassword(),
							"Login Details");
				}
			} catch (Exception e) {

				e.printStackTrace();
				throw new java.lang.Exception("problem in uploading users", e);
			}
			return mav;
		}

}
