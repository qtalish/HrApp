package com.kgate.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
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
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.google.common.io.Files;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.kgate.entity.Attendance;
import com.kgate.entity.DailyReport;
import com.kgate.entity.Leave;
import com.kgate.entity.MessageEmployee;
import com.kgate.entity.PropertiesConfig;
import com.kgate.entity.User;
import com.kgate.entity.UserDocument;
import com.kgate.entity.UserLeaves;
import com.kgate.repository.MessageRepository;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.repository.UserRepository;
import com.kgate.service.DailyReportService;
import com.kgate.service.HrCallingSheetService;
import com.kgate.service.HrDailyReportService;
import com.kgate.service.LeavesService;
import com.kgate.service.UserDocumentService;
import com.kgate.service.UserService;

import springfox.documentation.spring.web.paths.Paths;

@Controller
@SessionAttributes("user")
public class UserController {

	private Logger log = Logger.getLogger(UserController.class);

	@Autowired
	PropertiesConfig config;

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

	@Autowired
	LeavesService leavesService;

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
		user.setEmail("admin@kgate.in");
		user.setPassword("1234");
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		Date date = new Date();
		User user2 = userRepo.findByEmail(user.getEmail());
		System.out.println("........." + user2);
		boolean validate = BCrypt.checkpw(user.getPassword(), user2.getPassword());
		if (!validate) {

			mav.setViewName("login");
			mav.addObject("msg", "Please Enter Valid User Details");
			return mav;
		}
		if (user2.getLastLogin() == null) {
			System.out.println("last time login " + user2.getLastLogin());
			mav.setViewName("redirect:/editEmployeeProfile?id=" + user2.getId());
			mav.addObject("user", user2);
		} else if (user2.getUserType().equalsIgnoreCase("DEVELOPER")) {
			mav.addObject("user", user2);
			mav.setViewName("empDash");
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
		user2.setLastLogin(date);
		userRepo.save(user2);
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

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user") User user) throws ParseException {

		ModelAndView mav = new ModelAndView("home2");
		UserController uc = new UserController();
		String salt = BCrypt.gensalt(workload);
		String password = user.getPassword();
		String hspwd = BCrypt.hashpw("1234", salt);
		user.setPassword(hspwd);
		userService.save(user);
		uc.sendMail(user.getEmail(),
				"<font color=\"red\">Hello </font> " + user.getFname() + ",<br>"
						+ "<font color=\"red\">Your Username is: </font> " + user.getEmail() + "<br>"
						+ "<font color=\"red\">Your Password is: </font>" + "1234",
				"Login Details");
		return mav;
	}

	@GetMapping("/viewEmployees")
	public ModelAndView viewEmployees(@SessionAttribute("user") User user) {
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
	public ModelAndView viewEmployeeDash() {
		ModelAndView mav = new ModelAndView("empDash");
		return mav;
	}

	@GetMapping("/profile")
	public ModelAndView viewProfile(HttpServletRequest request, @SessionAttribute("user") User user)
			throws UnsupportedEncodingException {

		ModelAndView mav = new ModelAndView("employeeProfile");
		mav.addObject("type", user.getUserType());
		System.out.println(user);
		if (user.getImage() == null) {
			mav.addObject("user", user);
			mav.addObject("id", user.getId());
			return mav;
		} else {
			mav.addObject("id", user.getId());
			String imageValue = user.getImage();
			System.out.println(">>>>>>>>>>>>>>>>>>> "+imageValue);
			mav.addObject("userImage", imageValue);
			return mav;
		}
	}

	@GetMapping("/attendance")
	public ModelAndView viewAttendance() throws ParseException {
		ModelAndView mav = new ModelAndView("employeeAttendance");
		mav.addObject("attd", new Attendance());
		Date date = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		String strDate2 = sm.format(date);
		Date dt = sm.parse(strDate2);
		System.out.println("............. " + date);
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
		mav.addObject("dd", strDate);
		return mav;
	}

	@GetMapping("/documents")
	public ModelAndView viewDocuments(@ModelAttribute("userDocument") UserDocument userDocument,
			@SessionAttribute("user") User user) {
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
	public ModelAndView SearchEmp(@RequestParam("attDate") Date date) throws ParseException {
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
	public ModelAndView leave(@ModelAttribute("leave") Leave leave, @SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("leave");
		Date date = new Date();
		String month = Month.of(date.getMonth() + 1).name();
		UserLeaves ul = leavesService.getBalanceLeave(user.getEmpCode(), month, date.getYear() + 1900);
		mav.addObject("type", user.getUserType());
		mav.addObject("ul", ul);
		return mav;
	}

	@PostMapping("/send")
	public ModelAndView send(@ModelAttribute("leave") Leave leave, @RequestParam("file") MultipartFile file,
			@RequestParam("fromDate") Date fdate, @RequestParam("toDate") Date tdate,
			@SessionAttribute("user") User user) throws ParseException, IOException {
		ModelAndView mav = new ModelAndView("redirect:/leave");
		leave.setContent(file.getBytes());

		UserController uc = new UserController();
		// uncomment the next line if done
		 userService.save(leave);
//		 uc.sendMail(to, message, subject);
		return mav;
	}

	@PostMapping("/uploadImage")
	public ModelAndView uploadImage(@ModelAttribute("user") User user, @RequestParam("fileImage") MultipartFile file)
			throws IOException {
		ModelAndView mav = new ModelAndView("redirect:/profile");
		if (!file.getOriginalFilename().isEmpty()) {
			System.out.println("testing: " + config.getFileLocation() + "/" + file.getOriginalFilename());
			String destination = config.getFileLocation() + File.separator + "images" + File.separator
					+ file.getOriginalFilename();
			File file2 = new File(destination);
			file.transferTo(file2);
			String url = config.getFileUrl() + "img/" + file.getOriginalFilename();
			user.setImageUrl(url);
			user.setImage("/HrApp/img/" +file.getOriginalFilename());
			userService.save(user);

			mav.addObject("msg", "File uploaded successfully");
		} else {
			mav.addObject("msg", "Please select valid file.");
		}
		return mav;
	}

	@GetMapping("/deleteImage")
	public ModelAndView deleteImage(@SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView("redirect:/profile");
		user.setImage(null);
		userService.save(user);
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
			emp.put(user.getEmail(), user.getFname() +" - "+ user.getDesignation());
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
		User user = userService.fetchPassword(message.getTo());
		System.out.println("1111");
		UserController uc = new UserController();
		message.setDate(date);
		msgrepo.save(message);
		uc.sendMail(message.getTo(), "Hello " + user.getFname()+"," + "<br>" +message.getMessageEmp(), message.getSubjectMessage());
		return mav;
	}

	@GetMapping("/searchEmployee")
	public ModelAndView searchEmployee(@RequestParam("txt") String txt) {
		List<User> listEmp = userService.searchEmployee(txt);
		ModelAndView mav = new ModelAndView("employeelist");
		mav.addObject("listEmp", listEmp);
		return mav;
	}

	@RequestMapping(value = "/downloadDoc-{docId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadDocument(@PathVariable int docId, HttpServletResponse response,
			@SessionAttribute("user") User user) throws IOException {

		UserDocument document = userDocumentService.download(docId);
		System.out.println("ddd" + document);
		String url = document.getDocument();
		String docName = document.getOriginalDocName();
		File file = new File(config.getFileLocation() + "/documents/" + docName);
		byte data[] = FileUtils.readFileToByteArray(file);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData(docName, docName);
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response2 = new ResponseEntity<>(data, headers, HttpStatus.OK);
		return response2;
	}

	@PostMapping("saveEditEmployee")
	public ModelAndView saveEditEmployee(@ModelAttribute("user") User user, @SessionAttribute("user") User user2) {
		ModelAndView mav = new ModelAndView("redirect:/profile");
		user.setUserType(user2.getUserType());
		user.setPassword(user2.getPassword());
		userService.save(user); 
		return mav;
	}

}