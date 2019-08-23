package com.kgate.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.User;
import com.kgate.repository.AttendanceRepository;
import com.kgate.repository.UserRepository;
import com.kgate.service.UserService;

@Controller
public class AdminAjaxController {

	@Autowired
	UserRepository repo;
	@Autowired
	UserService userService;
	@Autowired
	AttendanceRepository attrepo;
	@RequestMapping(value = "/listEmp", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> viewlist() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> userList = userService.findEmployee();
		if (userList != null) {
			map.put("list", userList);
		} else {
			map.put("msg", "Empty data");
		}
		return map;
	}

	@RequestMapping(value = "/deleteEmployeeAjax", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteEmployee(@RequestBody User user) {
		Map<String, Object> map = new HashMap<>();
		repo.deleteById(user.getId());
		return map;
	}

	@RequestMapping(value = "/editEmployeeAjax", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = repo.getOne(userId);
		System.out.println(user);
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", user);
		return mav;
	}
	@GetMapping("/changeStatus")
	@ResponseBody
	public Map<String,Object> changeStatus(@RequestParam Integer id,@RequestParam String status){
		Map<String, Object> map = new HashMap<>();
			System.out.println("id::::: "+id+"   status::::::::   "+status);
			 attrepo.updateStatus(id,status);
		return map;
	}
	
	@GetMapping("changeRemarks")
	@ResponseBody
	public Map<String,Object> changeRemarks(@RequestParam Integer id,@RequestParam String remarks){
		Map<String, Object> map = new HashMap<>();
			System.out.println("id::::: "+id+"   status::::::::   "+remarks);
			 attrepo.updateRemarks(id,remarks);
			 map.put("msg","Remarks Update Successfully");
		return map;
	}
}