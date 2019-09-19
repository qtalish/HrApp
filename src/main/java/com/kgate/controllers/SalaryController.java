package com.kgate.controllers;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.Attendance;
import com.kgate.entity.Leave;
import com.kgate.entity.Salary;
import com.kgate.entity.User;
import com.kgate.entity.UserLeaves;
import com.kgate.repository.LeavesRepository;
import com.kgate.service.LeavesService;
import com.kgate.service.SalaryService;
import com.kgate.service.UserService;

@Controller
@SessionAttributes("user")
public class SalaryController {

	@Autowired
	UserService userService;

	@Autowired
	LeavesService leavesService;

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	LeavesRepository repo;

	@GetMapping("/viewSalary")
	public ModelAndView viewSalary() {
		ModelAndView mav = new ModelAndView("displaySalary");
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] arr = dfs.getMonths();

		/*
		 * List<User> listuser = userService.findEmployee();
		 * System.out.println("List of User........" + listuser);
		 * mav.addObject("listuser", listuser);
		 */

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

	@GetMapping("/searchSalary")
	public ModelAndView searchSalary(@RequestParam("month") String month, @RequestParam("year") Integer year,
			@RequestParam("empCode") String empCode) {
		ModelAndView mav = new ModelAndView("viewLeaves");
		System.out.println(",......" + month);
		System.out.println("...." + year);
		System.out.println("...." + empCode);
		System.out.println(leavesService.getLeavesDetails(empCode, month, 2019));
		UserLeaves userLeaves = leavesService.getLeavesDetails(empCode, month, year);
//	new changes
		Salary salary = new Salary();
		salary.setMonth(month);
		salary.setYear(year);
		salary.setEmpCode(empCode);
		Salary sal2 = salaryService.findByEmpCode(empCode, month, year);
		if (sal2 == null) {
			salaryService.saveSalary(salary);
		}
//		
//		salaryService.findByEmpCode(empCode,month,year);
		Salary sal = salaryService.findByEmpCode(empCode, month, year);
		if (sal == null) {
//			mav.addObject("salary",sal);
			Salary sala = new Salary();
			mav.addObject("salary", sala);
		} else {
			mav.addObject("salary", sal);
		}
		System.out.println("..........................." + sal);
//		Salary salary = new Salary();
//		mav.addObject("salary",salary);
		mav.addObject("userLeaves", userLeaves);
		return mav;
	}

	@PostMapping("/saveSalary")
	public ModelAndView saveSalary(@RequestParam("empCode") String empCode, @ModelAttribute("salary") Salary salary) {
		ModelAndView mav = new ModelAndView("adminDash");
		salary = salaryService.saveSalary(salary);

		return mav;
	}

	@GetMapping("/calculate")
	public ModelAndView calculcate() {
		ModelAndView mav = new ModelAndView("calculcate");
		// return mav;
		// balance leave claculationby awes
		String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		Date d = new Date();
		String month = months[d.getMonth() - 1];//get previous month
		String monthLeaves=months[d.getMonth() - 2];//to get previous bal leave
		Integer year = (Integer) Calendar.getInstance().get(Calendar.YEAR);//current year

		List<String> list = salaryService.getEmployeeList();//to fetch unique emp_id
		List<Attendance> user = salaryService.getAllEmployee(month, year);//to fetch all employee attendance
		//List<UserLeaves> userLeaves=leavesService.getPreviousLeaves
		 System.out.println(list);
		 System.out.println(user);
		for (int i = 0; i < list.size(); i++)
		{
		
			int workingdays = 0;
			Attendance atd;
			String empc = (String) list.get(i);
			UserLeaves userLeaves=leavesService.getPreviousLeaves(empc,monthLeaves,year);
			System.out.println(userLeaves);
			System.out.println("list: "+list.get(i).toString());
			float total_leaves = 0, halfday = 0,total_days_worked=0,unpaid_leaves=0;
			for (int j = 0; j < user.size(); j++) {
				atd = new Attendance();

				atd = user.get(j);
				
				
				System.out.println("test::: "+empc);
				if (empc.equals(atd.getEmpCode())) {
					if (atd.getStatus().equals("Present") || atd.getStatus().equals("Absent")
							|| atd.getStatus().equals("Half-Day"))
						workingdays = workingdays + 1; //total working days
					if (atd.getStatus().equals("Present"))
						total_days_worked = total_days_worked + 1;

					if (atd.getStatus().equals("Half-Day"))
						halfday = halfday + 1;
				}

			}
			total_leaves=(float) (workingdays-total_days_worked-0.5*halfday);
			float bal_leaves=(float) (userLeaves.getBalanceLeaves()+1.75-total_leaves);
			if(bal_leaves<0) {
				unpaid_leaves=Math.abs(bal_leaves);
				bal_leaves=0;
			}
			float paid_leaves=(float) (total_leaves-unpaid_leaves);
			System.out.println("empCode" + ":" + workingdays + " " + halfday + " " + total_leaves);
			float tdw=(float)workingdays-total_leaves;
			UserLeaves ul=new UserLeaves();
			ul.setBalanceLeaves(bal_leaves);
			ul.setEmpCode(empc);
			ul.setMonth(month);
			ul.setYear(year);
			ul.setWorkingDays(workingdays);
			ul.setTotalDaysWorked(tdw);
			ul.setTotalLeaves(total_leaves);
			ul.setPaidLeaves(paid_leaves);
			ul.setUnpaidLeaves(unpaid_leaves);
			repo.save(ul);
			
			
		}
		return mav;
	}
	
	@GetMapping("/downloadSalarySlip")
	public ModelAndView downloadSalarySlip(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("salaryslip");
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] arr = dfs.getMonths();
		List<String> months = new ArrayList<>();
		for (int i = 0; i < arr.length - 1; i++) {
			months.add(arr[i]);
		}
		List<Integer> years = new ArrayList<>();
		for (int i = 2018; i <= 2028; i++) {
			years.add(i);
		}
		mav.addObject("years", years);
		String eCode = user.getEmpCode();
		System.out.println(eCode);
		mav.addObject("empCode", eCode);
		mav.addObject("months", months);
		return mav;
	}

	
	@GetMapping("/downloadSlip")
	@ResponseBody
	public ModelAndView downloadPDF(@RequestParam("month") String month,@RequestParam("year") Integer year, @RequestParam("empCode") String empCode, @SessionAttribute("user") User user) {
		Salary list = salaryService.findSalary(empCode, month, year);
		ModelAndView mav = new ModelAndView("pdfView");
		UserLeaves uleave = leavesService.getLeavesDetails(empCode, month, year);
		System.out.println("bbb"+uleave);
		mav.addObject("list", list);
		mav.addObject("user", user);
		mav.addObject("leave", uleave);
		mav.addObject("month", month);
		String year1 = Integer.toString(year);
		mav.addObject("year", year1);
		return mav;
	}  
}
