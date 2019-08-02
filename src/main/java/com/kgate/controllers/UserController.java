package com.kgate.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.User;
import com.kgate.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository repo;

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		User user = new User();
		String userType[] = { "Admin", "Employee" };
		mav.addObject("userType", userType);
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		User user2 = repo.findUser(user.getEmail(), user.getPassword(), user.getUserType());
		System.out.println("user:::::::::: " + user2);
		if (user2 == null) {
			mav.setViewName("login");
			mav.addObject("msg", "User Name or Password is invalid");
			String userType[] = { "Admin", "Employee" };
			mav.addObject("userType", userType);
			return mav;
		}
		System.out.println(user.getEmail());
		if (user.getUserType().equals("Employee")) {
			System.out.println("Employee Login");
			mav.setViewName("empDash");
		} else {
			mav.setViewName("adminDash");
		}
		return mav;
	}

	@GetMapping("/register")
	public ModelAndView register(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("abc");
		repo.save(user);
		return mav;
	}

	@GetMapping("/viewEmployees")
	public ModelAndView viewEmployees() {
		ModelAndView mav = new ModelAndView("employeelist");
		List<User> userList = repo.findAll();
		System.out.println(userList);
		mav.addObject("users", userList);
		return mav;
	}

	@GetMapping("/employeeDash")
	public ModelAndView viewEmployeeDash() {
		ModelAndView mav = new ModelAndView("empDash");
		return mav;
	}

	@GetMapping("/profile")
	public ModelAndView viewProfile() {
		ModelAndView mav = new ModelAndView("employeeProfile");
		return mav;
	}

	@GetMapping("/attendance")
	public ModelAndView viewAttendance() {
		ModelAndView mav = new ModelAndView("employeeAttendance");
		return mav;
	}

	@GetMapping("/documents")
	public ModelAndView viewDocuments() {
		ModelAndView mav = new ModelAndView("employeeDocuments");
		return mav;
	}

}
