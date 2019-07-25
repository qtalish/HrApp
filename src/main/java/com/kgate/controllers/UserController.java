package com.kgate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.User;
import com.kgate.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository repo;
	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		User user = new User();
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		User user2 = repo.findUser(user.getEmail(), user.getPassword());
		if(user2==null) {
			mav.setViewName("login");
			return mav;
		}
		System.out.println(user.getEmail());
		mav.setViewName("abc");
		return mav;
	}
}
