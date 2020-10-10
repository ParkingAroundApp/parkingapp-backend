package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.dto.LoginRequest;
import com.fptu.paa.dto.RegisterRequest;
import com.fptu.paa.security.MyUserDetail;
import com.fptu.paa.security.jwt.JwtTokenProvider;
import com.fptu.paa.service.UserService;

@RestController
@RequestMapping("/auth/")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		//If authentication success
		//Set authentication information into SecurityHolder
		SecurityContextHolder.getContext().setAuthentication(authentication);

		//Create and send JWT back to user
		String jwt = jwtTokenProvider.generateToken((MyUserDetail) authentication.getPrincipal());
		return ResponseEntity.ok(jwt);
	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.ok().body("Registered Successfully");
	}
}
