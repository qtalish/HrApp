package com.kgate.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.Attendance;
import com.kgate.entity.DailyReport;
import com.kgate.entity.Leave;
import com.kgate.entity.MessageEmployee;
import com.kgate.entity.User;
import com.kgate.entity.UserDocument;
import com.kgate.repository.MessageRepository;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.repository.UserRepository;
import com.kgate.service.DailyReportService;
import com.kgate.service.HrCallingSheetService;
import com.kgate.service.HrDailyReportService;
import com.kgate.service.UserDocumentService;
import com.kgate.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {

	private Logger log = Logger.getLogger(UserController.class);

	@Autowired
	HrDailyReportService hrDailyReportService;

	@Autowired
	UserService userService;

	@Autowired
	MessageRepository msgrepo;

	@Autowired
	DailyReportService dailyReportService;

	@Autowired
	UserDocumentRepository docRepo;

	@Autowired
	UserDocumentService userDocumentService;

	@Autowired
	HrCallingSheetService hrCallingSheetService;
	@Autowired
	UserRepository userRepo;

	static int workload = 12;

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
		System.out.println("------------------->" + user);
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		User user2 = userRepo.findByEmail(user.getEmail());
		System.out.println("user..........." + user2);
		if (user2 == null) {
			mav.addObject("msg", "Please Enter Valid User Details");
			mav.setViewName("login");
			return mav;
		}
		System.out.println("........." + user2);
		boolean validate = BCrypt.checkpw(user.getPassword(), user2.getPassword());
		if (!validate) {
			log.info("Invalid");
			mav.setViewName("login");
			mav.addObject("msg", "Please Enter Valid User Details");
			return mav;
		}
		if (user2.getUserType().equalsIgnoreCase("DEVELOPER")) {
			mav.addObject("user", user2);
			mav.setViewName("empDash");
			log.info("Developer Logged In");
			System.out.println("Developer Logged In");
		} else if (user2.getUserType().equalsIgnoreCase("ADMIN")) {
			mav.setViewName("home2");
			mav.addObject("user", user2);
			System.out.println("Admin Logged In");
		} else if (user2.getUserType().equalsIgnoreCase("HR")) {
			mav.setViewName("hrDash");
			mav.addObject("user", user2);
			System.out.println("HR Logged In");
		} else if (user2.getUserType().equalsIgnoreCase("OPERATIONS")) {
			mav.setViewName("operationsDash");
			mav.addObject("user", user2);
			System.out.println("Operations Logged In");
		} else if (user2.getUserType().equalsIgnoreCase("MARKETING")) {
			mav.setViewName("marketingDash");
			mav.addObject("user", user2);
			System.out.println("Marketing Employee Logged In");
		} else if (user2.getUserType().equalsIgnoreCase("ACCOUNTS")) {
			mav.setViewName("accountsDash");
			mav.addObject("user", user2);
			System.out.println("Developer Logged In");
		}
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}

		return "redirect:/";
	}

	@GetMapping("/register")
	public ModelAndView register(@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("register");
		List<String> userType = new ArrayList<>();
		userType.add("DEVELOPER");
		userType.add("HR");
		userType.add("OPERATIONS");
		userType.add("MARKETING");
		userType.add("ACCOUNTS");
		mav.addObject("userType", userType);
		mav.addObject("user", new User());
		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user") User user) throws ParseException {
		ModelAndView mav = new ModelAndView("home2");
		UserController uc = new UserController();
		String salt = BCrypt.gensalt(workload);
		String hspwd = BCrypt.hashpw(user.getPassword(), salt);
		user.setPassword(hspwd);
		userService.save(user);
		uc.sendMail(user.getEmail(),
				"<font color=\"red\">Hello </font> " + user.getFname() + ",<br>"
						+ "<font color=\"red\">Your Username is: </font> " + user.getEmail() + "<br>"
						+ "<font color=\"red\">Your Password is: </font>" + user.getPassword(),
				"Login Details");
		return mav;
	}

	@GetMapping("/viewEmployees")
	public ModelAndView viewEmployees(@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("home2");
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
				return new PasswordAuthentication("gulfarooqui1@gmail.com", "Infinite#7326");
			}

		});

		Message message1 = new MimeMessage(session);

		try {

			message1.setFrom(new InternetAddress("test@gmail.com"));
			message1.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message1.setSubject(subject);
			message1.setContent(message, "text/html");

			Transport.send(message1);

			System.out.println("Done");

		} catch (MessagingException e1) {
			throw new RuntimeException(e1);
		}

	}

	@GetMapping("/employeeDash")
	public ModelAndView viewEmployeeDash(@SessionAttribute("user") User user) {
		System.out.println("before condition" + user.getUserType());
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("empDash");
	}

	@GetMapping("/profile")
	public ModelAndView viewProfile(HttpServletRequest request, @SessionAttribute("user") User user)
			throws UnsupportedEncodingException {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}

		ModelAndView mav = new ModelAndView("employeeProfile");
		mav.addObject("type", user.getUserType());
		System.out.println(user);
		if (user.getImage() == null) {
			mav.addObject("user", user);
			mav.addObject("id", user.getId());
			return mav;
		} else {
			mav.addObject("id", user.getId());
			byte[] encodeBase64 = Base64.encodeBase64(user.getImage());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			mav.addObject("userImage", base64Encoded);
			return mav;
		}
	}

	@GetMapping("/attendance")
	public ModelAndView viewAttendance(@SessionAttribute("user") User user) throws ParseException {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("employeeAttendance");
		mav.addObject("attd", new Attendance());
		Date date = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		String strDate2 = sm.format(date);
		Date dt = sm.parse(strDate2);
		System.out.println("............. " + date);
		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime = formatter2.parse(formatter2.format(today));
		System.out.println(">>>>>>>>>>>> " + todayWithZeroTime);
		List<Attendance> list = userService.getAttendance(todayWithZeroTime);
		List<Integer> list2 = new ArrayList<>();
		for (Attendance attendance : list) {
			list2.add(attendance.getId());
		}

		List<String> status = new ArrayList<>();
		status.add("Present");
		status.add("Absent");
		status.add("Half-Day");
		status.add("Week-Off");
		status.add("Holiday");
		Attendance attd = new Attendance();
		mav.addObject("attd", attd);
		mav.addObject("ustatus", status);
		mav.addObject("list", list);
		mav.addObject("list2", list2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		mav.addObject("dd", strDate);
		return mav;
	}

	@GetMapping("/documents")
	public ModelAndView viewDocuments(@ModelAttribute("userDocument") UserDocument userDocument,
			@SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("employeeDocuments");

		String type = user.getUserType();
		mav.addObject("type", type);
		userDocument.setEmpCode(user.getEmpCode());
		List<UserDocument> ud = userDocumentService.findDoc(user.getEmpCode());
		System.out.println("list" + ud);
		mav.addObject("uc", ud);
		mav.addObject("empCode", user.getEmpCode());
		return mav;
	}

	@GetMapping("/delete-document-{docId}")
	public String delteDocument(@PathVariable int docId) {
		userDocumentService.deleteById(docId);
		return "redirect:/documents";
	}

//Attendance
	@GetMapping("SearchEmp")
	public ModelAndView SearchEmp(@RequestParam("attDate") Date date, @SessionAttribute("user") User user)
			throws ParseException {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("employeeAttendance");
		List<Attendance> list = userService.getAttendance(date);
		List<Integer> list2 = new ArrayList<>();
		for (Attendance attendance : list) {
			list2.add(attendance.getId());
		}

		List<String> status = new ArrayList<>();
		status.add("Present");
		status.add("Absent");
		status.add("Half-Day");
		status.add("Week-Off");
		status.add("Holiday");
		Attendance attd = new Attendance();
		mav.addObject("attd", attd);
		mav.addObject("ustatus", status);
		mav.addObject("list", list);
		mav.addObject("list2", list2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		System.out.println("Date Format with yyyy-mm-dd : " + strDate);
		mav.addObject("dd", strDate);
		return mav;
	}

	@PostMapping("/forgetPassword")
	@ResponseBody
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

	}

	// Leave
	@GetMapping("/leave")
	public ModelAndView leave(@ModelAttribute("leaveApplication") Leave leave, @SessionAttribute("user") User user) {
		if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("leave");
		mav.addObject("type", user.getUserType());
		return mav;
	}

	@PostMapping("/send")
	public ModelAndView send(@ModelAttribute("leaveApplication") Leave leave, @RequestParam("file") MultipartFile file,
			@SessionAttribute("user") User user) throws ParseException, IOException {
		ModelAndView mav = new ModelAndView("redirect:/leave");
		leave.setContent(file.getBytes());
		UserController uc = new UserController();
		userService.save(leave);
		uc.sendMail("vartak.m.akshay@gmail.com",
				"<font color=\"red\"> DATE : </font> " + leave.getFromDate() + " TO " + leave.getToDate() + "<br>"
						+ "<font color=\"red\"> Message : </font>" + leave.getMessage(),
				"SUBJECT : " + leave.getSubject());
		return mav;
	}

	@PostMapping("/uploadImage")
	public ModelAndView uploadImage(@ModelAttribute("user") User user, @RequestParam("fileEmp") MultipartFile file)
			throws IOException {
		ModelAndView mav = new ModelAndView();
		user.setImage(file.getBytes());
		userService.save(user);
		mav = new ModelAndView("redirect:/profile");
		return mav;
	}

	@GetMapping("/messageEmployee")
	public ModelAndView messageEmployee(@SessionAttribute("user") User user2) {
		if (user2.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("message", new MessageEmployee());
		List<User> userList = userService.findEmployee();
		Map<String, String> emp = new HashMap<String, String>();
		for (User user : userList) {
			emp.put(user.getEmail(), user.getFname());
		}
		mav.addObject("userList", userList);
		mav.addObject("emp", emp);
		return mav;
	}

	@PostMapping("/sendMail")
	public ModelAndView sendMail(@ModelAttribute("message") MessageEmployee message) {
		ModelAndView mav = new ModelAndView("redirect:/messageEmployee");
		Date date = new Date();
		UserController uc = new UserController();
		message.setDate(date);

		msgrepo.save(message);
		uc.sendMail(message.getTo(), message.getMessageEmp(), message.getSubjectMessage());
		return mav;
	}

	@RequestMapping(value = "/downloadDoc-{docId}", method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int docId, HttpServletResponse response,
			@SessionAttribute("user") User user) throws IOException {

		UserDocument document = userDocumentService.download(docId);
		response.setContentLength(document.getDocument().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getDname() + "\"");
		FileCopyUtils.copy(document.getDocument(), response.getOutputStream());
		if (user.getUserType().equals("ADMIN")) {
			return "redirect:/documents";
		} else {
			return "redirect:/uploadDocumentAjax";
		}
	}
}