package com.fptu.paa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@RequestMapping(value = "/")
	public String homePage() {
		return "HelloWorld!";
	}
	
	@RequestMapping(value = "/swagger")
	public String swaggerPage() {
		return "redirect:/swagger-ui/";
	}
}
