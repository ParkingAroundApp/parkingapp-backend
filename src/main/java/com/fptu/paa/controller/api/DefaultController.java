package com.fptu.paa.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	
	@GetMapping(value = "")
	public String homePage() {
		return "redirect:/swagger-ui/";
	}
}
