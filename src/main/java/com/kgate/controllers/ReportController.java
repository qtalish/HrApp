package com.kgate.controllers;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.DailyReport;
import com.kgate.entity.HrCallingSheet;
import com.kgate.entity.HrDailyReport;
import com.kgate.entity.User;
import com.kgate.repository.MessageRepository;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.repository.UserRepository;
import com.kgate.service.DailyReportService;
import com.kgate.service.HrCallingSheetService;
import com.kgate.service.HrDailyReportService;
import com.kgate.service.UserDocumentService;
import com.kgate.service.UserService;

@Controller
public class ReportController {

	@Autowired
	HrDailyReportService hrDailyReportService;

	@Autowired
	UserService userService;

	@Autowired
	MessageRepository msgrepo;

	@Autowired
	DailyReportService dailyReportService;

	@Autowired
	UserDocumentService userDocumentService;
	@Autowired
	UserDocumentRepository docRepo;

	@Autowired
	HrCallingSheetService hrCallingSheetService;
	@Autowired
	UserRepository userRepo;

	@InitBinder
	public void initConverter(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@GetMapping("/hrDailyReport")
	public ModelAndView hrDailyReport(@SessionAttribute("user") User user) throws ParseException {
		ModelAndView mav = new ModelAndView("hrDailyReport");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		List<HrCallingSheet> li = hrCallingSheetService.findByUpdatedDateAndEmpCode(todayWithZeroTime,
				user.getEmpCode());
		HrDailyReport hrreport = new HrDailyReport();
		String name = "";
		for (HrCallingSheet sheet : li) {
			name += sheet.getCandidateName() + ", ";
		}
		name = name.replaceAll(", $", "");
		hrreport.setResourcesCalled(name);
		hrreport.setDate(new Date());

		mav.addObject("userName", user.getFname());
		mav.addObject("hdr", hrreport);
		return mav;
	}

	@PostMapping("/submitHrDailyReport")
	public ModelAndView submitHrDailyReport(@ModelAttribute("hdr") HrDailyReport hdr,
			@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("hrDailyReport");
		String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		Date date = hdr.getDate();
		System.out.println("date----------------" + date);
		String month = months[date.getMonth()];
		int year = hdr.getDate().getYear() + 1900;
		System.out.println("Month " + month + " Year " + year);

		hdr.setEmpCode(user.getEmpCode());
		hdr.setFname(user.getFname());
		hdr.setLname(user.getLname());
		hdr.setMonth(month);
		hdr.setYear(year);
		hrDailyReportService.saveReport(hdr);
		mav.addObject("hdr", new HrDailyReport());

		return mav;

	}

	@GetMapping("/hrCallingSheet")
	public ModelAndView getCallingSheet(HttpServletRequest request, @SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("hrCallingSheet");
		HrCallingSheet hr = new HrCallingSheet();
		mav.addObject("userName", user.getFname());
		System.out.println("User Name: " + user.getFname());

		List<String> status = new ArrayList<String>();
		status.add("Ringing");
		status.add("Call Later");
		status.add("Busy");
		status.add("Disconnected");
		status.add("Location Issue");
		status.add("Relevent");
		status.add("Not Relevent");
		status.add("Not Reachable");
		status.add("Not Interested");
		status.add("Relocate");
		status.add("Recently Joined");

		mav.addObject("status", status);

		hr.setDate(new Date());
		mav.addObject("hcs", hr);
		return mav;
	}

	@PostMapping("/submitCallingSheet")
	public ModelAndView submitCallingSheet(@ModelAttribute("hcs") HrCallingSheet callingSheet,
			@SessionAttribute("user") User user) throws ParseException {
		ModelAndView mav = new ModelAndView("hrCallingSheet");
		mav.addObject("userName", user.getFname());
		System.out.println("User Name: " + user.getFname());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		callingSheet.setUpdatedDate(todayWithZeroTime);
		callingSheet.setMonth(new SimpleDateFormat("MMM").format(cal.getTime()));
		callingSheet.setYear(cal.get(Calendar.YEAR));
		callingSheet.setEmpCode(user.getEmpCode());
		callingSheet.setRecruiter(user.getFname());
		hrCallingSheetService.saveReport(callingSheet);
		HrCallingSheet hr = new HrCallingSheet();
		hr.setDate(new Date());
		mav.addObject("hcs", hr);
		return mav;
	}

	@GetMapping("/workReport")
	public ModelAndView dailyReport(@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("workReport");
		DailyReport dailyReport = new DailyReport();
		mav.addObject("userName", user.getFname());
		mav.addObject("dailyReport", dailyReport);
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] arr = dfs.getMonths();
		List<String> months = new ArrayList<>();
		for (int i = 0; i < arr.length - 1; i++) {
			months.add(arr[i]);
		}
		mav.addObject("months", months);

		List<Integer> years = new ArrayList<>();
		for (int i = 2018; i <= 2028; i++) {
			years.add(i);
		}
		mav.addObject("years", years);
		return mav;
	}

	@GetMapping("/hrWorkReport")
	public ModelAndView hrWorkReport(@SessionAttribute("user") User user) {

		ModelAndView mav = new ModelAndView("hrWorkReport");
		mav.addObject("userName", user.getFname());
		System.out.println("user Name: " + user.getFname());
		HrDailyReport hrDailyReport = new HrDailyReport();
		mav.addObject("hrDailyReport", hrDailyReport);
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] arr = dfs.getMonths();
		List<String> months = new ArrayList<>();
		for (int i = 0; i < arr.length - 1; i++) {
			months.add(arr[i]);
		}
		mav.addObject("months", months);

		List<Integer> years = new ArrayList<>();
		for (int i = 2018; i <= 2028; i++) {
			years.add(i);
		}
		mav.addObject("years", years);
		return mav;
	}

	@GetMapping("/searchWorkReport")
	public ModelAndView showDailyReport(@RequestParam("month") String month, @RequestParam("year") Integer year) {
		ModelAndView mav = new ModelAndView("workReport");
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] arr = dfs.getMonths();
		List<String> months = new ArrayList<>();
		for (int i = 0; i < arr.length - 1; i++) {
			months.add(arr[i]);
		}
		mav.addObject("months", months);

		List<Integer> years = new ArrayList<>();
		for (int i = 2018; i <= 2028; i++) {
			years.add(i);
		}
		mav.addObject("years", years);
		List<DailyReport> li = dailyReportService.getAllEmployee(month, year);
		DailyReport dailyReport = new DailyReport();
		mav.addObject("dailyReport", dailyReport);
		mav.addObject("li", li);
		return mav;
	}

	@GetMapping("/searchHrWorkReport")
	public ModelAndView showHrDailyReport(@RequestParam("month") String month, @RequestParam("year") Integer year) {
		ModelAndView mav = new ModelAndView("hrWorkReport");
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] arr = dfs.getMonths();
		List<String> months = new ArrayList<>();
		for (int i = 0; i < arr.length - 1; i++) {
			months.add(arr[i]);
		}
		mav.addObject("months", months);

		List<Integer> years = new ArrayList<>();
		for (int i = 2018; i <= 2028; i++) {
			years.add(i);
		}
		mav.addObject("years", years);
		List<HrDailyReport> list = hrDailyReportService.getAllHr(month, year);
		HrDailyReport HrDailyReport = new HrDailyReport();
		mav.addObject("HrDailyReport", HrDailyReport);
		mav.addObject("list", list);
		return mav;
	}

	@GetMapping("/dailyReport")
	public ModelAndView getDailyReport(@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("dailyReport");
		mav.addObject("userName", user.getFname());
		DailyReport dailyReport = new DailyReport();
		dailyReport.setDate(new Date());
		mav.addObject("dailyReport", dailyReport);
		return mav;
	}

	@PostMapping("/submitDailyReport")
	public ModelAndView submitDailyReport(@ModelAttribute("dailyReport") DailyReport dailyReport,
			@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("dailyReport");
		String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		Date date = dailyReport.getDate();
		String month = months[date.getMonth()];
		int year = dailyReport.getDate().getYear() + 1900;
		System.out.println("Month " + month + " Year " + year);

		dailyReport.setEmpCode(user.getEmpCode());
		dailyReport.setFirstName(user.getFname());
		dailyReport.setLastName(user.getLname());
		dailyReport.setMonth(month);
		dailyReport.setYear(year);
		dailyReport.setDate(new Date());
		dailyReportService.saveReport(dailyReport);
		mav.addObject("dailyReport", new DailyReport());

		return mav;
	}

}
