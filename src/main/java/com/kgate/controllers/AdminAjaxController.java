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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.User;
import com.kgate.repository.UserRepository;

@Controller
public class AdminAjaxController {

	@Autowired
	UserRepository repo;

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
		initialPage = initialPage-1;
	}catch(Exception e) { }
		Pageable pageable =PageRequest.of(initialPage, 3);
		Page<User> userList = repo.findAll(pageable);
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
	public ModelAndView editEmployee(HttpServletRequest request, @ModelAttribute("user") User user2) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = repo.getOne(userId);
		System.out.println(user);

		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", user);
		return mav;
	}
}