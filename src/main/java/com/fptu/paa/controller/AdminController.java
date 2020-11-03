package com.fptu.paa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.constant.RoleName;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.dto.RequestMetaData;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.TicketService;
import com.fptu.paa.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/admin")
@Api(value = "Admin",consumes = "application/json", description = "Admin API", tags = {"Admin"})
public class AdminController {
	@Autowired
	BikeService bikeService;
	@Autowired
	TicketService ticketService;
	@Autowired
	UserService userService;
	

	@PutMapping(value ="/bike/verify")
	public ResponseEntity<String> verifyBike(@RequestParam Long bikeId, BikeStatus bikeStatus){
		BikeViewDTO rs = bikeService.changeBikeStatus(bikeId, bikeStatus);
		if (rs != null) {
			return ResponseEntity.ok("Verify success!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verify failed!");
	}
	
	@GetMapping(value = "/bike/{userId}")
	public ResponseEntity<List<BikeViewDTO>> getBikesByUserId(@PathVariable Long userId) {
		List<BikeViewDTO> bikeList = bikeService.getAllBikeByUserid(userId);
		if (bikeList != null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/bike/status")
	public ResponseEntity<List<BikeViewDTO>> getBikesByStatus(@RequestParam BikeStatus status) {
		List<BikeViewDTO> bikeList = bikeService.getAllBikesByStatus(status);
		if (bikeList != null && !bikeList.isEmpty()) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}
  
	@GetMapping(value = "/getAllUsersByRole")
	public ResponseEntity<List<UserViewDTO>> getAllUsersByRole(RoleName roleName) {
		List<UserViewDTO> userList  = userService.getUsersByRole(roleName) ;
		if (userList != null) {
			return new ResponseEntity<List<UserViewDTO>>(userList, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserViewDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/ticket")
	public ResponseEntity<String> getListTicketInDateRange(@RequestParam String year, @RequestParam String month, @RequestBody RequestMetaData metaData) {
		String result = "";
		try {
			String tmpResult = ticketService.getListTicketInDateRange(year, month, metaData.getPageSize(), metaData.getBookmark());
			if (!tmpResult.isEmpty()) {
				result = tmpResult;
			}
		} catch (Exception e) {
			System.out.println("getAllTicket: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}
}
