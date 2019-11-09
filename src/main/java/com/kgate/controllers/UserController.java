package com.kgate.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
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
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import javax.swing.SwingUtilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.itextpdf.text.log.SysoCounter;
import com.kgate.entity.Attendance;
import com.kgate.entity.DailyReport;
import com.kgate.entity.Leave;
import com.kgate.entity.MessageEmployee;
import com.kgate.entity.PropertiesConfig;
import com.kgate.entity.User;
import com.kgate.entity.UserDocument;
import com.kgate.entity.UserLeaves;
import com.kgate.repository.AttendanceRepository;
import com.kgate.repository.LeaveRepository;
import com.kgate.repository.MessageRepository;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.repository.UserRepository;
import com.kgate.service.DailyReportService;
import com.kgate.service.HrCallingSheetService;
import com.kgate.service.HrDailyReportService;
import com.kgate.service.LeavesService;
import com.kgate.service.UserDocumentService;
import com.kgate.service.UserService;
import com.kgate.util.MultipleLinesChart;

import io.swagger.models.Model;

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
	
	@Autowired
	AttendanceRepository ar;
	
	@Autowired
	LeaveRepository leaveRepository;

	static int workload = 12;

	@InitBinder
	public void initConverter(WebDataBinder binder) {
		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	
	@GetMapping("/")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("user", new User());
		return mav;
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		Date date = new Date();
		User user2 = userRepo.findByEmail(user.getEmail());
		if (user2 == null) {
			mav.addObject("msg", "Please Enter Valid User Details");
			mav.setViewName("login");
			return mav;
		}
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
		} /* else if (user2.getUserType().equalsIgnoreCase("DEVELOPER")) { */

		else if (user2.getUserType().equalsIgnoreCase("DEVELOPER")) {
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
	public ModelAndView register(@SessionAttribute("user") User user,Locale locale) {
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
		mav.addObject("user2", new User());
		return mav;
	}	

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("user2") User user,@SessionAttribute("user") User user2) throws ParseException {
		ModelAndView mav = new ModelAndView();
	mav.addObject("type",user2.getUserType());
	if (user2.getUserType().equalsIgnoreCase("ADMIN")) {
			mav.setViewName("register");
		} else {
			mav.setViewName("employeeEdit");
		}
	
		UserController uc = new UserController();
		String password = user.getPassword();
		String salt = BCrypt.gensalt(workload);
		String hspwd = BCrypt.hashpw(user.getPassword(), salt);
			
			if(user2.getUserType().equalsIgnoreCase("ADMIN"))
			{
				user.setPassword(hspwd);
				System.out.println("1");
			}
			else
			{
			user.setUserType(user2.getUserType());
			user.setPassword(user2.getPassword());
			System.out.println("2");
			}
			System.out.println("3");
		
			userService.save(user);
		   uc.sendMail(user.getEmail(),
				"<font color=\"red\">Hello </font> " + user.getFname() + ",<br>"
						+ "<font color=\"red\">Your Username is: </font> " + user.getEmail() + "<br>"
						+ "<font color=\"red\">Your Password is: </font>" +password,
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

			System.out.println("Email sent");

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
		
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(new Date());
		String joiningDate = sdf.format(user.getJoiningDate());
		
		
		LocalDate dateBefore = LocalDate.parse(joiningDate);
		LocalDate dateAfter = LocalDate.parse(currentDate);
	
		 dateBefore = LocalDate.of(dateBefore.getYear(),dateBefore.getMonth(),dateBefore.getDayOfMonth());
		 dateAfter = LocalDate.of(dateAfter.getYear(),dateAfter.getMonth(),dateAfter.getDayOfMonth());
		 
		
		 Period period = Period.between(dateBefore, dateAfter);
		//	String exp = (period.getYears() + " Years " + period.getMonths() + " Months "+ period.getDays() + " Days ");
			
			String day = period.getDays()+""; 
            String month = period.getMonths()+"";
		    String year= period.getYears()+"";
		    System.out.println("year........"+year);
		    
		     if(year.equalsIgnoreCase("0")&&month.equalsIgnoreCase("0"))
		    {
		    	String exp = day+" Days ";
		    	mav.addObject("exp",exp);
		    }
		     else if(year.equalsIgnoreCase("0"))
		    {
		    	System.out.println("inside");
		    	String exp = month+" Months "+day+" Days ";
		    	mav.addObject("exp",exp);
		    }
		    else
		    {
		    	String exp = (period.getYears() + " Years " + period.getMonths() + " Months "+ period.getDays() + " Days ");
		    	mav.addObject("exp",exp);
		    }
		   
		    
		 //   mav.addObject("exp",exp);
		
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
		Date date = new Date();
		String month = Month.of(date.getMonth() + 1).name();
		System.out.println("emp"+user.getEmpCode()+" month"+month+" year "+date.getYear() + 1900);
		UserLeaves ul = leavesService.getBalanceLeave(user.getEmpCode(), month, date.getYear() + 1900);
		mav.addObject("type", user.getUserType());
		mav.addObject("ul", ul);
		System.out.println(">>>>>>>>>>>>>>>>>>> "+ul);
		List<Leave> leaveList = leaveRepository.findByEmpCode(user.getEmpCode());
		System.out.println(">>>>>>"+leaveList);
		mav.addObject("leaveList", leaveList);
	
		return mav;
	}

	@PostMapping("/send")
	public ModelAndView send(@ModelAttribute("leaveApplication") Leave leave, @RequestParam("file") MultipartFile file,
			@SessionAttribute("user") User user) throws ParseException, IOException {
		ModelAndView mav = new ModelAndView("redirect:/leave");
		
		if (!file.getOriginalFilename().isEmpty()) {

			String destination = config.getFileLocation() + File.separator + "leaveDocuments" + File.separator
					+ file.getOriginalFilename();
			File file2 = new File(destination);
			file.transferTo(file2);
		leave.setContent(file.getOriginalFilename()); 
		}
		leave.setStatus("Pending");
		leave.setFname(user.getFname());
		leave.setLname(user.getLname());
		leave.setEmpCode(user.getEmpCode());
		
		userService.save(leave);
		UserController uc = new UserController();
		try {
			uc.sendMail("qtalish97@gmail.com",
					"<font color=\"red\"> DATE : </font> " + leave.getFromDate() + " TO " + leave.getToDate() + "<br>"
							+ "<font color=\"red\"> Message : </font>" + leave.getMessage(),
					"SUBJECT : " + leave.getSubject()+" From "+user.getFname()+" "+user.getLname());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mav.setViewName("test");
		}
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

	
	@GetMapping("/lms")
	public ModelAndView lms(@SessionAttribute("user") User user)
	{ 
		/*if (user.getUserType() == null) {
			return new ModelAndView("redirect:/");
		}*/
	ModelAndView mav = new ModelAndView("lms");
	List<Leave> leaves = leaveRepository.findAll();
	List<String> status = new ArrayList<>();
	status.add("Approved");
	status.add("Disapproved");
	Leave leave = new Leave();
	mav.addObject("leave", leave);
	mav.addObject("status",status);
	mav.addObject("leaves",leaves);
	return mav;
	}
	
	@GetMapping("/btn")
	public ModelAndView btn(@RequestParam("button") String button,@RequestParam("id") Integer id )
	{
		ModelAndView mav = new ModelAndView();
		Leave leave = leaveRepository.getOne(id);
		System.out.println(leave);
		leave.setStatus(button);
		leaveRepository.saveAndFlush(leave);
		mav.setViewName("redirect:/lms");
		UserController uc = new UserController();

	 User user = userRepo.findByEmpCode(leave.getEmpCode());
	 
	 if(button.equalsIgnoreCase("Approved"))
	 {
		 uc.sendMail(user.getEmail(),"Hi "+user.getFname()+",<br> We are Happy to inform you that your leave from "+leave.getFromDate()+" To "+leave.getToDate()+"  has been Accepted.","Leave Request "+button);
	 }
	 else
	 {
		 uc.sendMail(user.getEmail(), "Hi "+user.getFname()+",<br> We are sorry to inform you that your leave from "+leave.getFromDate()+" To "+leave.getToDate()+" has been Rejected. "+"<p> &nbsp&nbsp&nbsp Kindly contact HR for the further information." ,"Leave Request "+button);
	 }
		
		return mav;
	}
}