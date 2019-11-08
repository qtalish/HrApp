package com.kgate.controllers;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.kgate.entity.DailyReport;
import com.kgate.entity.HrCallingSheet;
import com.kgate.entity.HrDailyReport;
import com.kgate.entity.User;
import com.kgate.repository.DailyReportRepository;
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

	@Autowired
	DailyReportRepository dr;

	@InitBinder
	public void initConverter(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@GetMapping("/hrDailyReport")
	public ModelAndView hrDailyReport(@SessionAttribute("user") User user,
			@ModelAttribute("hdr") HrDailyReport dailyReport) {
		ModelAndView mav = new ModelAndView("hrDailyReport");
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}

		dailyReport.setDate(new Date());
		/*
		 * DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); Date today = new
		 * Date(); Date todayWithZeroTime = formatter.parse(formatter.format(today));
		 * List<HrCallingSheet> li =
		 * hrCallingSheetService.findByUpdatedDateAndEmpCode(todayWithZeroTime,
		 * user.getEmpCode()); HrDailyReport hrreport = new HrDailyReport(); String name
		 * = ""; for (HrCallingSheet sheet : li) { name += sheet.getCandidateName() +
		 * ", "; } name = name.replaceAll(", $", ""); hrreport.setResourcesCalled(name);
		 * hrreport.setDate(new Date());
		 * 
		 * mav.addObject("userName", user.getFname()); mav.addObject("hdr", hrreport);
		 */

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

		hdr.setDate(date);
		hdr.setEmpCode(user.getEmpCode());
		hdr.setFname(user.getFname());
		hdr.setLname(user.getLname());
		hdr.setMonth(month);
		hdr.setYear(year);
		hrDailyReportService.saveReport(hdr);
		mav.addObject("hdr", new HrDailyReport());

		return mav;

	}

	String getMonthForInt(int num) {
		String month = "";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	@GetMapping("/workReport")
	public ModelAndView dailyReport(@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
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
		Date date = new Date();
		String mon = new ReportController().getMonthForInt(date.getMonth());
		mav.addObject("mon", mon);
		int year = date.getYear() + 1900;
		mav.addObject("year", year);
		List<DailyReport> li = dailyReportService.getAllEmployee(mon, year);
		mav.addObject("li", li);
		return mav;
	}

	@GetMapping("/hrWorkReport")
	public ModelAndView hrWorkReport(@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}

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
		Date date = new Date();
		String mon = new ReportController().getMonthForInt(date.getMonth());
		mav.addObject("mon", mon);
		int year = date.getYear() + 1900;
		System.out.println("fdafjafls" + year);
		mav.addObject("year", year);
		List<HrDailyReport> list = hrDailyReportService.getAllHr(mon, year);

		mav.addObject("list", list);
		return mav;
	}

	@GetMapping("/searchWorkReport")
	public ModelAndView showDailyReport(@RequestParam("month") String month, @RequestParam("year") Integer year,
			@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
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
	public ModelAndView showHrDailyReport(@RequestParam("month") String month, @RequestParam("year") Integer year,
			@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
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
	public ModelAndView getDailyReport(@SessionAttribute("user") User user) throws ParseException {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("dailyReport");
		String type = user.getUserType();
		mav.addObject("type", type); // to check user type so that respective dashboard can be assign to the user
		mav.addObject("userName", user.getFname());
        //To get current Date with zero time
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String aaa = sdf.format(new Date());
		System.out.println("After formatting ......" + aaa);
		Date date = sdf.parse(aaa);
		System.out.println("After parsing....." + date);
		DailyReport dailyReport = dr.getTodaysTask(date, user.getEmpCode());
		
		//To get previous day with zero time
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);// subtracting a day
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String result = s.format(new Date(cal.getTimeInMillis()));
		Date date1 = s.parse(result);
		System.out.println("--------date---------"+date1);
		String yest = dr.getPreviousTask(date1, user.getEmpCode());
		System.out.println("........yest......." + yest);
		mav.addObject("yest",yest);
		if (dailyReport == null) {
			mav.addObject("dailyReport",new DailyReport());
		} else if (!(dailyReport == null)) {
			mav.addObject("dailyReport", dailyReport);
		}
		return mav;
	}

	@PostMapping("/submitDailyReport")
	public ModelAndView submitDailyReport(@ModelAttribute("dailyReport") DailyReport dailyReport,
			@SessionAttribute("user") User user) throws ParseException {
		ModelAndView mav = new ModelAndView("dailyReport");

		String type = user.getUserType();
		mav.addObject("type", type);
		String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		Date date = dailyReport.getDate();
		String month = months[date.getMonth()];
		int year = dailyReport.getDate().getYear() + 1900;
		dailyReport.setEmpCode(user.getEmpCode());
		dailyReport.setFirstName(user.getFname());
		dailyReport.setLastName(user.getLname());
		dailyReport.setMonth(month);
		dailyReport.setYear(year);
		// dailyReport.setDate(new Date());
		dailyReportService.saveReport(dailyReport);
		mav.addObject("dailyReport", new DailyReport());

		return mav;
	}

	@GetMapping("/hrCallingSheet")
	public ModelAndView getCallingSheet(@ModelAttribute("hcs") HrCallingSheet callingSheet,
			@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("hrCallingSheet");

		callingSheet.setDate(new Date());

		List<String> status = new ArrayList<String>();
		status.add("Ringing");
		status.add("Disconnected");
		status.add("Call Again");
		status.add("Busy");
		status.add("Location Issue");
		status.add("Not Relevant");
		status.add("Assessment Test Passed");
		status.add("Assessment Test Not Attempted");
		status.add("Telephonic Interview Scheduled");
		status.add("Didn't turn-up for the Interview");
		status.add("Rejected in Telephonic Interview ");
		status.add("Telephonic Interview Passed");
		status.add("Rejected in F2F Interview ");
		status.add("F2F Interview Passed");
		status.add("Offer Shared");
		status.add("Joined");
		status.add("Didn't Joined");
		status.add("F2F @ Client");
		status.add("Telphonic @ Client");
		status.add("Rejected by Client ");

		mav.addObject("status", status);

		List<HrCallingSheet> candidates = hrCallingSheetService.findAllCandidate(user.getEmpCode());
		mav.addObject("candidates", candidates);
		mav.addObject("hcs", callingSheet);
		return mav;
	}

	@PostMapping("/submitCallingSheet")
	public ModelAndView submitCallingSheet(@ModelAttribute("hcs") HrCallingSheet callingSheet,
			@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("hrCallingSheet");
		String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		Date date = callingSheet.getDate();
		String month = months[date.getMonth()];
		int year = callingSheet.getDate().getYear() + 1900;
		System.out.println("Month " + month + " Year " + year);

		List<String> status = new ArrayList<String>();
		status.add("Ringing");
		status.add("Disconnected");
		status.add("Call Again");
		status.add("Busy");
		status.add("Location Issue");
		status.add("Not Relevant");
		status.add("Assessment Test Passed");
		status.add("Assessment Test Not Attempted");
		status.add("Telephonic Interview Scheduled");
		status.add("Didn't turn-up for the Interview");
		status.add("Rejected in Telephonic Interview ");
		status.add("Telephonic Interview Passed");
		status.add("Rejected in F2F Interview ");
		status.add("F2F Interview Passed");
		status.add("Offer Shared");
		status.add("Joined");
		status.add("Didn't Joined");
		status.add("F2F @ Client");
		status.add("Telphonic @ Client");
		status.add("Rejected by Client ");

		mav.addObject("status", status);

		callingSheet.setFname(user.getFname());
		callingSheet.setLname(user.getLname());
		callingSheet.setEmpCode(user.getEmpCode());
		callingSheet.setMonth(month);
		callingSheet.setYear(year);
		callingSheet.setUpdateDate(new Date());
		hrCallingSheetService.saveCallingSheet(callingSheet);
		mav.addObject("hcs", new HrCallingSheet());

		List<HrCallingSheet> candidates = hrCallingSheetService.findAllCandidate(user.getEmpCode());
		mav.addObject("candidates", candidates);

		return mav;
	}

	@GetMapping("/candidateList")
	@ResponseBody
	public Map<String, Object> viewCandiadateList(@RequestParam(name = "page", required = false) String page,
			HttpServletRequest request, @SessionAttribute("user") User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		int initialPage = 0;

		try {
			initialPage = Integer.parseInt(page);
			initialPage = initialPage - 1;
		} catch (Exception e) {
		}
		Pageable pageable = PageRequest.of(initialPage, 10);
		Page<HrCallingSheet> candidateList = hrCallingSheetService.findEmployeePage(pageable, user.getEmpCode());

		map.put("candidate", candidateList.getContent());
		map.put("pageno", candidateList.getTotalPages());
		return map;
	}

	@RequestMapping(value = "/editCandidateAjax", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			HttpServletRequest request/* , @ModelAttribute("hcs") HrCallingSheet callingSheet */) {
		int userId = Integer.parseInt(request.getParameter("id"));
		HrCallingSheet hrcallingSheet = hrCallingSheetService.findCandidateById(userId);
		ModelAndView mav = new ModelAndView("hrCallingSheet");
		List<String> status = new ArrayList<String>();
		status.add("Ringing");
		status.add("Disconnected");
		status.add("Call Again");
		status.add("Busy");
		status.add("Location Issue");
		status.add("Not Relevant");
		status.add("Assessment Test Passed");
		status.add("Assessment Test Not Attempted");
		status.add("Telephonic Interview Scheduled");
		status.add("Didn't turn-up for the Interview");
		status.add("Rejected in Telephonic Interview ");
		status.add("Telephonic Interview Passed");
		status.add("Rejected in F2F Interview ");
		status.add("F2F Interview Passed");
		status.add("Offer Shared");
		status.add("Joined");
		status.add("Didn't Joined");
		status.add("F2F @ Client");
		status.add("Telphonic @ Client");
		status.add("Rejected by Client ");

		mav.addObject("status", status);
		mav.addObject("hcs", hrcallingSheet);
		return mav;
	}

	@RequestMapping(value = "/deleteCandidateAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteEmployee(@RequestBody HrCallingSheet hrCallingSheet) {
		Map<String, Object> map = new HashMap<>();
		hrCallingSheetService.deleteById(hrCallingSheet.getId());
		return map;
	}

	@RequestMapping(value = "/searchCandidate", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAll(HttpServletRequest request, @RequestParam("val") String st,
			HrCallingSheet hrCallingSheet) {
		Map<String, Object> map = new HashMap<>();
		int initialPage = 0;
		try {
			initialPage = Integer.parseInt(request.getParameter("page"));
			initialPage = initialPage - 1;
		} catch (Exception e) {
		}
		Pageable pageable = PageRequest.of(initialPage, 10);
		Page<HrCallingSheet> list = hrCallingSheetService.searchCandidate(pageable, st);
		System.out.println("aaaaas" + list);

		if (list != null) {
			map.put("status", "200");
			map.put("message", "Data found");
			map.put("candidate", list);
		} else {
			map.put("status", "404");
			map.put("message", "Data not found");

		}
		map.put("candidate", list.getContent());
		map.put("pageno", list.getTotalPages());
		return map;
	}

}
