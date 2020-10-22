package com.fptu.paa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.service.BikeService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/admin")
@Api(value = "Admin",consumes = "application/json", description = "Admin API", tags = {"Admin"})
public class AdminController {
	@Autowired
	BikeService bikeService;
	
	@PutMapping(value ="/verifyBike")
	public ResponseEntity<String> verifyBike(@RequestParam Long bikeId, BikeStatus bikeStatus){
		BikeViewDTO rs = bikeService.changeBikeStatus(bikeId, bikeStatus);
		if (rs != null) {
			return ResponseEntity.ok("Verify success!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verify failed!");
	}
	
	@GetMapping(value = "/getAllByUserId/{userId}")
	public ResponseEntity<List<BikeViewDTO>> getBikesByUserId(@PathVariable Long userId) {
		List<BikeViewDTO> bikeList = bikeService.getAllBikeByUserid(userId);
		if (bikeList != null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/getAllByStatus")
	public ResponseEntity<List<BikeViewDTO>> getBikesByStatus(BikeStatus status) {
		List<BikeViewDTO> bikeList = bikeService.getAllBikesByStatus(status);
		if (bikeList != null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}
	
}
