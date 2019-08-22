package com.kgate.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.User;

import com.kgate.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {

	
	
	@Autowired
	UserService userService;

//	@InitBinder
//	public void bindingPreparation(WebDataBinder binder) {
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
//		binder.registerCustomEditor(Date.class, orderDateEditor);
//	}
	@InitBinder
	public void initConverter(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		System.out.println("abc");
		User user = new User();
		user.setEmail("qtalish97@gmail.com");
		user.setPassword("1234");
		String userType[] = { "Admin", "Employee" };
		mav.addObject("userType", userType);
		System.out.println(userType);
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		 User user2 = userService.findUser(user.getEmail(), user.getPassword(), user.getUserType());
		System.out.println("user:::::::::: " + user2);
		if (user2 == null) {
			mav.setViewName("login");
			mav.addObject("msg", "User Name or Password is invalid");
			String userType[] = { "Admin", "Employee" };
			mav.addObject("userType", userType);
			return mav;
		}
		System.out.println(user);
		System.out.println(user.getEmail());
		if (user.getUserType().equals("Employee")) {
			System.out.println("Employee Login");
			if(user.getEmail().equals("vartakakshay@rediffmail.com")) {
			mav.setViewName("birthday");
			}
			else
			{
				mav.addObject("user", user2);
				mav.setViewName("empDash"); 
			}
		} else {
			mav.setViewName("adminDash");
		}
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		return "redirect:/";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		return "adminDash";
	}

	@GetMapping("/register")
	public ModelAndView register(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("register");
		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user") User user) throws ParseException {
		ModelAndView mav = new ModelAndView("home2");
		user.setUserType("Employee");
		UserController uc = new UserController();
		/*
		 * uc.sendMail(user.getEmail(), "Your login id is: " + user.getEmail() +
		 * "\n Your Password is: " + user.getPassword(), //
		 * "Your Credential and Details");
		 * 
		 * String email = user.getEmail(); String email2 = userService.findByEmail(email);
		 * if(email.equals(email2)) { ModelAndView mav2 = new ModelAndView("register");
		 * } else {
		 */
		/*
		 * User user2 = userService.findUser(user.getEmail(), user.getPassword(),
		 * user.getUserType()); if(user.getEmail().equals(user2.getEmail())) {
		 * mav.setViewName("register"); mav.addObject("msg", "User Already Exists");
		 * return mav; }
		System.out.println("asdfghjhgfds");
		 */
		userService.save(user);
	return mav;
}

	@GetMapping("/viewEmployees")
	public ModelAndView viewEmployees() {
//		ModelAndView mav = new ModelAndView("employeelist");
		ModelAndView mav = new ModelAndView("home2");
		List<User> userList = userService.findAll();
		System.out.println(userList);
		mav.addObject("users", userList);
		return mav;
	}

	public void sendMail(String to, String message, String subject) {
		final User u = new User();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gulfarooqui1@gmail.com", "Gmail#7326");
			}

		});

		Message message1 = new MimeMessage(session);

		try {

			message1.setFrom(new InternetAddress("test@gmail.com"));
			message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message1.setSubject(subject);
			message1.setText(message);

			Transport.send(message1);

			System.out.println("Done");

		} catch (MessagingException e1) {
			throw new RuntimeException(e1);
		}
		// return "employeelist";

	}

	@GetMapping("/employeeDash")
	public ModelAndView viewEmployeeDash() {
		ModelAndView mav = new ModelAndView("empDash");
		return mav;
	}

	@GetMapping("/profile")
	public ModelAndView viewProfile(HttpServletRequest request,@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("employeeProfile");
				System.out.println(user);
		mav.addObject("user", user);
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

	@PostMapping("/forgetPassword")
	@ResponseBody
//	public Map<String, Object> forgetPassword(@RequestBody User user) {
	public ResponseEntity<?> forgetPassword(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("inside forgot");
		System.out.println("inside forgot" + user.getEmail());
		User user2 = userService.fetchPassword(user.getEmail());
		if (user2 == null) {
			map.put("msg", "User does not exist!");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		} else {
			UserController uc = new UserController();
			uc.sendMail(user2.getEmail(), "Your Password is : " + user2.getPassword(), "Login Credentials");
			map.put("msg", "Your Password has been send to your email successfully");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
//		System.out.println("11111111111111");
//		return map;

	}
}
