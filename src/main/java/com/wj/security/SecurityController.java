package com.wj.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/entreprises";
	}
	
	@RequestMapping(value="/403")
	public String accesDenied() {
		
		return "403";
	}
	
}