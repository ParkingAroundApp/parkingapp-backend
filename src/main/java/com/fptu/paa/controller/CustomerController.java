package com.fptu.paa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.dto.BikeRegisterDTO;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.dto.LoginRequest;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.TicketService;
import com.fptu.paa.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/customer")
@Api(consumes = "application/json", description = "Customer API", tags = { "Customer" })
public class CustomerController {
	@Autowired
	BikeService bikeService;
	@Autowired
	TicketService ticketService;
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
		String jwt = "";
		try {
			jwt = userService.loginViaGmail(loginRequest);
			if (jwt == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something wrong!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong credentials!");
		}
		return ResponseEntity.ok(jwt);
	}

	@PostMapping(value = "/bike")
	public ResponseEntity<BikeRegisterDTO> registerBike(BikeRegisterDTO registerBike) {
		registerBike = bikeService.registerBike(registerBike);
		return new ResponseEntity<BikeRegisterDTO>(registerBike, HttpStatus.OK);
	}



	@DeleteMapping(value = "/bike")
	public ResponseEntity<String> deleteBike(@RequestParam Long bikeId) {
		bikeService.deleteBike(bikeId);
		return ResponseEntity.ok("Success");
	}
	
	@GetMapping("")
	public ResponseEntity<UserViewDTO> getUserDetail() {
		UserViewDTO userViewDTO = userService.getCurrentUser();
		if(userViewDTO != null) {
			return new ResponseEntity<UserViewDTO>(userViewDTO, HttpStatus.OK);
		}
		return new ResponseEntity<UserViewDTO>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/bike")
	public ResponseEntity<List<BikeViewDTO>> getBikesByUser() {
		UserViewDTO userView = userService.getCurrentUser();
		List<BikeViewDTO> bikeList = bikeService.getAllActiveBikeByUserid(userView.getId());
		if (bikeList != null && !bikeList.isEmpty()) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/ticket")
	public ResponseEntity<String> getTicketListByCustomerID(@RequestParam String userId) {
		String result = "No available ticket!";
		try {
			String tmp = ticketService.getListTicketByCustomerID(userId);
			if (!tmp.isEmpty()) {
				result = tmp;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/ticket/detail")
	public ResponseEntity<String> getTicketDetail(@RequestParam String checkInTime, @RequestParam String bikeID) {
		String result = "No available ticket!";
		try {
			String ticketKey = "TICKET" + "_" + checkInTime + "_" + bikeID;
			String tmp = ticketService.getTicketDetail(ticketKey);
			if (!tmp.isEmpty()) {
				result = tmp;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}
}
