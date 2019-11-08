package com.kgate.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kgate.entity.PropertiesConfig;
import com.kgate.entity.User;
import com.kgate.entity.UserDocument;
import com.kgate.repository.UserDocumentRepository;
import com.kgate.service.UserDocumentService;
import com.kgate.service.UserService;

@SessionAttributes("use")
@Controller
public class DocumentController {

	@Autowired
	PropertiesConfig config;

	@Autowired
	UserDocumentService userDocumentService;

	@Autowired
	UserDocumentRepository repo;

	@Autowired
	UserDocumentService uds;

	@RequestMapping(value = "/uploadDocumentAjax", method = RequestMethod.GET)
	public ModelAndView uploadDocuments(@ModelAttribute("userDocument") UserDocument userDocument,
			@RequestParam(name = "empCode") String empCode) {
		ModelAndView mav = new ModelAndView("adminDocuments");

		List<UserDocument> ud = userDocumentService.findDoc(empCode);
		mav.addObject("empCode", empCode);
		mav.addObject("uc", ud);
		return mav;
	}

	@PostMapping("/addAdminDocument")
	public ModelAndView addAdminDocument(@ModelAttribute("userDocument") UserDocument userDocument,
			@RequestParam("file") MultipartFile file, @SessionAttribute("user") User user,
			@RequestParam(name = "empCode") String empCode) throws IllegalStateException, IOException {
		ModelAndView mav = new ModelAndView();

		if (!file.getOriginalFilename().isEmpty()) {
			userDocument.setCreated(new Date());

			String destination = config.getFileLocation() + File.separator + "documents" + File.separator
					+ file.getOriginalFilename();
			File file2 = new File(destination);
			file.transferTo(file2);

			String url = config.getFileUrl() + "doc/" + file.getOriginalFilename();
			userDocument.setOriginalDocName(file.getOriginalFilename());
			userDocument.setDocument(url);
			userDocument.setDocumentType(file.getContentType());
			userDocumentService.saveDocument(userDocument);

			System.out.println("upload Called");
			mav.addObject("msg", "File Uploaded Successfully");
		} else {
			mav.addObject("msg", "Please select valid file");
		}

		System.out.println(user.getUserType());
		if (user.getUserType().equalsIgnoreCase("ADMIN")) {

			mav.addObject("empCode", empCode);
			mav.setViewName("redirect:/uploadDocumentAjax");
		} else {
			mav.addObject("empCode", empCode);
			mav.setViewName("redirect:/documents");
		}
		return mav;
	}

	@GetMapping("/delete-document1-{docId}")
	public ModelAndView delteDocument(@PathVariable int docId, @RequestParam(name = "empCode") String empCode) {
		ModelAndView mav = new ModelAndView("redirect:/uploadDocumentAjax");
		uds.deleteById(docId);
		mav.addObject("empCode", empCode);
		return mav;
	}
}
