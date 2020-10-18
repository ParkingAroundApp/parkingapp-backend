package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.repository.UserRepository;
import com.fptu.paa.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/user")
@Api(consumes = "application/json")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository UserRepository;
	//sample api
	@PostMapping
	public String updateUser() {
		return "test-updateUser";
	}
	@GetMapping(value = "currentUser")
	public ResponseEntity<UserViewDTO> getUserDetail() {
		UserViewDTO userViewDTO = userService.getCurrentUser();
		if(userViewDTO != null) {
			return new ResponseEntity<UserViewDTO>(userViewDTO, HttpStatus.OK);
		}
		return new ResponseEntity<UserViewDTO>(HttpStatus.BAD_REQUEST);
	}
}
