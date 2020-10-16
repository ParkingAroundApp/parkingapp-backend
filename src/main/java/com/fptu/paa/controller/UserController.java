package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/user")
@Api(consumes = "application/json")
public class UserController {

	@Autowired
	UserService userService;
	
	//sample api
	@PostMapping
	public String updateUser() {
		return "test-updateUser";
	}
}
