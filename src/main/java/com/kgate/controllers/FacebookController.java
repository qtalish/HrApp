/*
 * package com.kgate.controllers;
 * 
 * import org.springframework.social.connect.ConnectionRepository; import
 * org.springframework.social.facebook.api.Facebook; import
 * org.springframework.social.facebook.api.PagedList; import
 * org.springframework.social.facebook.api.Post; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * @Controller public class FacebookController {
 * 
 * private Facebook facebook;
 * 
 * private ConnectionRepository cr;
 * 
 * 
 * public FacebookController(Facebook facebook, ConnectionRepository cr) {
 * super(); this.facebook = facebook; this.cr = cr; }
 * 
 * public FacebookController() { super(); }
 * 
 * @GetMapping("/facebook") public String helloFacebook(Model model) { if
 * (!facebook.isAuthorized()) { return "redirect:/connect/facebook"; }
 * 
 * 
 * model.addAttribute(facebook.userOperations().getUserProfile()); PagedList
 * homeFeed = facebook.feedOperations().getHomeFeed();
 * model.addAttribute("feed", homeFeed);
 * 
 * return "adminDash"; } }
 */