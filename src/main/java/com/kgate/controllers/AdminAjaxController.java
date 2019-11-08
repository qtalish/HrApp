package com.kgate.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.log.Logger;
import com.kgate.entity.User;
import com.kgate.entity.UserDocument;
import com.kgate.repository.AttendanceRepository;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.repository.UserRepository;
import com.kgate.service.UserService;

@Controller
public class AdminAjaxController {

	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AdminAjaxController.class);
	@Autowired
	UserRepository repo;

	@Autowired
	UserService userService;

	@Autowired
	AttendanceRepository attrepo;

	@Autowired
	UserDocumentRepository userDocumentRepository;

	@GetMapping("/changeStatus")
	@ResponseBody
	public Map<String, Object> changeStatus(@RequestParam Integer id, @RequestParam String status) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("id::::: " + id + "   status::::::::   " + status);
		attrepo.updateStatus(id, status);
		return map;
	}

	@GetMapping("changeRemarks")
	@ResponseBody
	public Map<String, Object> changeRemarks(@RequestParam Integer id, @RequestParam String remarks) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("id::::: " + id + "   status::::::::   " + remarks);
		attrepo.updateRemarks(id, remarks);
		map.put("msg", "Remarks Update Successfully");
		return map;
	}

	@InitBinder
	public void initConverter(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@RequestMapping(value = "/listEmp", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> viewlist(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int initialPage = 0;
		try {
			initialPage = Integer.parseInt(request.getParameter("page"));
			initialPage = initialPage - 1;
		} catch (Exception e) {
		}
		Pageable pageable = PageRequest.of(initialPage, 3);
		Page<User> userList = userService.findEmployeePage(pageable);
		System.out.println("saassasa"+userList);
		if (userList != null) {
			map.put("list", userList);
		} else {
			map.put("msg", "Empty data");
		}

		map.put("list", userList.getContent());
		map.put("pno", userList.getTotalPages());
		return map;
	}
	
	@RequestMapping(value = "/deleteEmployeeAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteEmployee(@RequestBody User user) {
		Map<String, Object> map = new HashMap<>();
		repo.deleteById(user.getId());
		return map;
	}

	@RequestMapping(value = "/editEmployeeAjax", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request, @ModelAttribute("user") User user2 ) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = repo.getOne(userId);
		ModelAndView mav = new ModelAndView("register");
		
		
		List<String> userType = new ArrayList<>();
		userType.add("DEVELOPER");
		userType.add("HR");
		userType.add("OPERATIONS");
		userType.add("MARKETING");
		userType.add("ACCOUNTS");
		mav.addObject("userType", userType);

		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/editEmployeeProfile", method = RequestMethod.GET)
	public ModelAndView editEmployeeProfile(HttpServletRequest request, @ModelAttribute("user") User user2,@SessionAttribute("user") User user3) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = repo.getOne(userId);
		ModelAndView mav = new ModelAndView("employeeEdit");
		System.out.println("......................................."+user3.getUserType());
		mav.addObject("type",user3.getUserType());
		List<String> userType = new ArrayList<>();
		userType.add("DEVELOPER");
		userType.add("HR");
		userType.add("OPERATIONS");
		userType.add("MARKETING");
		userType.add("ACCOUNTS");
		mav.addObject("userType", userType);
		mav.addObject("id", userId);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "/searchEmp", method = RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> getAll(HttpServletRequest request, @RequestParam("val") String st,User user) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("TTTTTTTTTTT::: "+st);
		int initialPage = 0;
		try {
			initialPage = Integer.parseInt(request.getParameter("page"));
			initialPage = initialPage - 1;
		} catch (Exception e) {
		}
		Pageable pageable = PageRequest.of(initialPage , 3);
		Page<User> list = userService.searchEmployee(pageable,st);
		System.out.println("aaaaas"+list);

		if (list != null) {
			map.put("status", "200");
			map.put("message", "Data found");
			map.put("list", list);
		} else {
			map.put("status", "404");
			map.put("message", "Data not found"); 

		}
		map.put("list", list.getContent());
		map.put("pno", list.getTotalPages());
		return map;
	}
}  