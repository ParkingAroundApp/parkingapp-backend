package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.dto.LoginRequest;
import com.fptu.paa.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/auth")
@Api(consumes = "application/json", description = "Provide JWT Token")
public class AuthController {

	@Autowired
	UserService userService;

	// Login via Username-Password
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest,
			@RequestParam(defaultValue = "false") boolean isGmail) {
		String jwt = "";
		try {
			jwt = isGmail ? userService.loginViaGmail(loginRequest) : userService.loginViaUsername(loginRequest);
			if (jwt == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something wrong!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong credentials!");
		}
		return ResponseEntity.ok(jwt);
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
}
