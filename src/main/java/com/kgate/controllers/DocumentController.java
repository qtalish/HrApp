package com.kgate.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.User;
import com.kgate.entity.UserDocument;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.service.UserDocumentService;

@Controller
public class DocumentController {

	@Autowired
	UserDocumentService userDocumentService;

	@Autowired
	UserDocumentRepository repo;
	
	@Autowired
	UserDocumentService uds;

	@RequestMapping(value = "/uploadDocumentAjax", method = RequestMethod.GET)
	public ModelAndView uploadDocuments(HttpServletRequest request,
			@ModelAttribute("userDocument") UserDocument userDocument, @RequestParam(name = "empCode") String empCode, @RequestParam(name = "userType") String userType) {

		System.out.println("usertype "+userType);
		System.out.println("Employee Code " + empCode);
		List<UserDocument> uc = uds.findByString(empCode);
		ModelAndView mav = new ModelAndView("adminDocuments");
		mav.addObject("uc", uc);
		mav.addObject("empCode", empCode);
		mav.addObject("userType", userType);
		return mav;
	}

	@PostMapping("/addDocument")
	public ModelAndView addDocument(@ModelAttribute("userDocument") UserDocument userDocument,
			@RequestParam("file") MultipartFile file, @SessionAttribute("user") User user) {
		ModelAndView mav = new ModelAndView();
		System.out.println(user.getUserType());
		if(user.getUserType().equals("Employee")) {
			mav.setViewName("redirect:/documents");
		}
		else {
			mav.setViewName("redirect:/uploadDocumentAjax");
		}
		if (!file.getOriginalFilename().isEmpty()) 
		{
		userDocument.setCreated(new Date());
		
		try {
			userDocument.setDocument(file.getBytes());
			userDocument.setDocumentType(file.getContentType());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			
			userDocumentService.saveDocument(userDocument);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("upload Called");
		mav.addObject("msg", "File Uploaded Successfully");
		}
		else
		{
			mav.addObject("msg", "Please select valid file");
		}
		return mav;
		
	}
}
