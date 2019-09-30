package com.kgate.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
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

import com.itextpdf.text.log.SysoCounter;
import com.kgate.entity.Attendance;
import com.kgate.entity.DailyReport;
import com.kgate.entity.Leave;
import com.kgate.entity.MessageEmployee;
import com.kgate.entity.User;
import com.kgate.entity.UserDocument;
import com.kgate.repository.MessageRepository;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.service.DailyReportService;
//github.com/Vartak1905/HrApp.git
import com.kgate.service.UserDocumentService;
import com.kgate.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MessageRepository msgrepo;

	@Autowired
	UserDocumentService uds;

	@Autowired
	DailyReportService dailyReportService;

	@Autowired
	UserDocumentService userDocumentService;
	@Autowired
	UserDocumentRepository docRepo;

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
		user.setEmail("gulfarooqui1@gmail.com");
		user.setPassword("1234");
		String userType[] = { "Admin", "Employee" };
		mav.addObject("userType", userType);
		System.out.println(userType);
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("user") User user, @SessionAttribute("user") User userSession) {
		ModelAndView mav = new ModelAndView();

		User user2 = userService.findUser(user.getEmail(), user.getPassword());
		

		System.out.println("user:::::::::: " + user2);
		
		if (user2 == null) {
			mav.setViewName("login");
			mav.addObject("msg", "Please Enter Valid User Details");
			return mav;
		}
		if (user2.getUserType().equals("Employee")) {
			System.out.println("Employee Login");
			mav.addObject("user", user2);
			mav.setViewName("redirect:/profile");
		} else {
			mav.addObject("user",user2);
			mav.setViewName("home2");
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
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		User user = new User();
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user") User user) throws ParseException {
		ModelAndView mav = new ModelAndView("home2");
		user.setUserType("Employee");
		UserController uc = new UserController();
		uc.sendMail(user.getEmail(),
				"<font color=\"red\">Hello </font> " + user.getFname() + ",<br>"
						+ "<font color=\"red\">Your Username is: </font> " + user.getEmail() + "<br>"
						+ "<font color=\"red\">Your Password is: </font>" + user.getPassword(),
				"Login Details");
		/*
		 * uc.sendMail(user.getEmail(), "Your login id is: " + user.getEmail() +
		 * "\n Your Password is: " + user.getPassword(), "Your Credential and Details");
		 */
		/*
		 * uc.sendMail(user.getEmail(), "Your login id is: " + user.getEmail() +
		 * "\n Your Password is: " + user.getPassword(), //
		 * "Your Credential and Details");
		 * 
		 * String email = user.getEmail(); String email2 =
		 * userService.findByEmail(email); if(email.equals(email2)) { ModelAndView mav2
		 * = new ModelAndView("register"); } else {
		 */
		/*
		 * User user2 = userService.findUser(user.getEmail(), user.getPassword(),
		 * user.getUserType()); if(user.getEmail().equals(user2.getEmail())) {
		 * mav.setViewName("re	gister"); mav.addObject("msg", "User Already Exists");
		 * return mav; } System.out.println("asdfghjhgfds");
		 */

		userService.save(user);
		return mav;
	}

	@GetMapping("/viewEmployees")
	public ModelAndView viewEmployees(@SessionAttribute("user") User user) {
//		ModelAndView mav = new ModelAndView("employeelist");
		ModelAndView mav = new ModelAndView("home2");
//	List<User> userList = userService.findEmployee();
//		System.out.println(userList);
//		mav.addObject("users", userList);
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
		// return "employeelist";

	}

	@GetMapping("/employeeDash")
	public ModelAndView viewEmployeeDash() {
		ModelAndView mav = new ModelAndView("empDash");
		return mav;
	}

	@GetMapping("/profile")
	public ModelAndView viewProfile(HttpServletRequest request, @SessionAttribute("user") User user)
			throws UnsupportedEncodingException {

		ModelAndView mav = new ModelAndView("employeeProfile");
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
	public ModelAndView viewAttendance() {
		ModelAndView mav = new ModelAndView("employeeAttendance");
		mav.addObject("attd", new Attendance());
		Date date = new Date();
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

	@GetMapping("/documents")

	public ModelAndView viewDocuments(@ModelAttribute("userDocument") UserDocument userDocument,
			@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("employeeDocuments");
		userDocument.setEmpCode(user.getEmpCode());
		List<UserDocument> ud = userDocumentService.findDoc(user.getEmpCode());
		System.out.println("list" + ud);
		mav.addObject("uc", ud);
		return mav;
	}

	@GetMapping("/delete-document-{docId}")
	public String delteDocument(@PathVariable int docId) {
		uds.deleteById(docId);
		return "redirect:/documents";
	}

//Attendance

	@GetMapping("SearchEmp")
	public ModelAndView SearchEmp(@RequestParam("attDate") Date date) {
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

	/*
	 * @GetMapping("SearchEmp") public ModelAndView
	 * SearchEmp(@RequestParam("attDate") Date date) { ModelAndView mav = new
	 * ModelAndView("employeeAttendance"); System.out.println("test:: " + date);
	 * List<Attendance> list = userService.getAttendance(date); List<Attendance> li
	 * = new ArrayList<>(); Attendance a = new Attendance(); mav.addObject("a", a);
	 * String status[] = { "Present", "Absent" }; Map<String, String> map = new
	 * HashMap<String, String>(); map.put("Present", "Present"); map.put("Absent",
	 * "Absent"); mav.addObject("status",map);
	 * System.out.println("111111111111112222222222" + Arrays.toString(status));
	 * 
	 * // mav.addObject("ustatus", Arrays.toString(status));
	 * mav.addObject("ustatus", status); // System.out.println(status); //
	 * System.out.println(Arrays.toString(status)); for (int i = 0; i < list.size();
	 * i++) { Attendance att = new Attendance();
	 * att.setFirstName(list.get(i).getFirstName());
	 * att.setStatus(list.get(i).getStatus()); System.out.println("ttttt::::: " +
	 * att); li.add(att); } System.out.println("1111111111111111" + li);
	 * mav.addObject("list", list); return mav; }
	 */

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

	}

	// Leave
	@GetMapping("/leave")
	public ModelAndView leave(@ModelAttribute("leave") Leave leave) {
		ModelAndView mav = new ModelAndView("leave");
		return mav;
	}

	@PostMapping("/send")
	public ModelAndView send(@ModelAttribute("leave") Leave leave, @RequestParam("file") MultipartFile file)
			throws ParseException, IOException {
		ModelAndView mav = new ModelAndView("redirect:/leave");
		leave.setContent(file.getBytes());
		userService.save(leave);
		return mav;
	}

	@GetMapping("uploadForm")
	public ModelAndView uploadForm() {
		return new ModelAndView("uploadimage");
	}

	@PostMapping("/uploadImage")
	public ModelAndView uploadImage(@ModelAttribute("user") User user, @RequestParam("file") MultipartFile file)
			throws IOException {
		ModelAndView mav = new ModelAndView();
		user.setImage(file.getBytes());
		userService.save(user);
		mav = new ModelAndView("redirect:/profile");
		return mav;
	}

	@RequestMapping(value = "/backToAdmin", method = RequestMethod.POST)
	public ModelAndView backToAdmin() {
		ModelAndView mav = new ModelAndView("adminDash");
		return mav;
	}

	@RequestMapping(value = "/backToEmployee", method = RequestMethod.POST)
	public ModelAndView back(@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("empDash");
		return mav;
	}

	@GetMapping("/messageEmployee")
	public ModelAndView messageEmployee() {
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
		System.out.println("1111" + message.getSubjectMessage());
		System.out.println("1111" + message.getMessageEmp());
		System.out.println(message.getTo());
		System.out.println("1111");
		UserController uc = new UserController();
		message.setDate(date);

		msgrepo.save(message);
		uc.sendMail(message.getTo(), message.getMessageEmp(), message.getSubjectMessage());
		return mav;
	}

	@GetMapping("/searchEmployee")
	public ModelAndView searchEmployee(@RequestParam("txt") String txt) {
		List<User> listEmp = userService.searchEmployee(txt);
		ModelAndView mav = new ModelAndView("employeelist");
		mav.addObject("listEmp", listEmp);
		return mav;
	}

	@GetMapping("/dailyReport")
	public ModelAndView getDailyReport() {
		ModelAndView mav = new ModelAndView("dailyReport");
		mav.addObject("dailyReport", new DailyReport());
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
		dailyReportService.saveReport(dailyReport);
		mav.addObject("dailyReport", new DailyReport());
		return mav;
	}

	@GetMapping("/workReport")
	public ModelAndView dailyReport() {
		ModelAndView mav = new ModelAndView("workReport");
		DailyReport dailyReport = new DailyReport();
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

	@RequestMapping(value = "/downloadDoc-{docId}", method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int docId, HttpServletResponse response) throws IOException {

		UserDocument document = userDocumentService.download(docId);
//		response.setContentType(document.getDocumentType());
		System.out.println("ddd" + document);
		response.setContentLength(document.getDocument().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getDname() + "\"");
		FileCopyUtils.copy(document.getDocument(), response.getOutputStream());
		return "redirect:/documents";
	}

}