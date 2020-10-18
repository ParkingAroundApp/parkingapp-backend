package com.fptu.paa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.BikeRegisterDTO;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.dto.UserViewDTO;
import com.fptu.paa.entity.Bike;
import com.fptu.paa.repository.BikeRepository;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/bike")
@Api(consumes = "application/json", description = "This controller will allow User to "
		+ "Register new bike - Approve bike - Delete Bike - Checkin - Checkout")
public class ParkingController {
	@Autowired
	BikeService bikeService;

	@Autowired
	UserService userService;

	@Autowired
	BikeRepository bikeRepo;

	// sample api
	@GetMapping(value = "{id}")
	public ResponseEntity<BikeViewDTO> getBike(@PathVariable Long id) {
		BikeViewDTO bike = bikeService.getBike(id);
		if (bike != null) {
			return new ResponseEntity<BikeViewDTO>(bike, HttpStatus.OK);
		}
		return new ResponseEntity<BikeViewDTO>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "licensePlate")
	public ResponseEntity<BikeViewDTO> getBikeByLicensePlate( String plateNumber) {
		BikeViewDTO bike = bikeService.getBikeByPlateNumber(plateNumber);
		if (bike != null) {
			return new ResponseEntity<BikeViewDTO>(bike, HttpStatus.OK);
		}
		return new ResponseEntity<BikeViewDTO>(HttpStatus.BAD_REQUEST);
	}


	@GetMapping(value = "register")
	public ResponseEntity<BikeRegisterDTO> registerBike(BikeRegisterDTO registerBike) {
//		Bike bike = bikeRepo.findById(1L).get();

		registerBike = bikeService.registerBike(registerBike);
		return new ResponseEntity<BikeRegisterDTO>(registerBike, HttpStatus.OK);
	}

	@GetMapping(value = "getAllByUser")
	public ResponseEntity<List<BikeViewDTO>> getBikesByUser() {
		UserViewDTO userView = userService.getCurrentUser();
		List<BikeViewDTO> bikeList = bikeService.getAllBikeByUserid(userView.getId());
		if (bikeList !=null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}
	@GetMapping(value = "getAllByStatus")
	public ResponseEntity<List<BikeViewDTO>> getBikesByStatus(BikeStatus status) {
		List<BikeViewDTO> bikeList = bikeService.getAllBikesByStatus(status);
		if (bikeList !=null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "getActiveByUser")
	public ResponseEntity<List<BikeViewDTO>> getActiveByUser() {
		UserViewDTO userView = userService.getCurrentUser();
		List<BikeViewDTO> bikeList = bikeService.getAllActiveBikeByUserid(userView.getId());
		if (bikeList !=null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}
	@GetMapping(value = "getAllByUserId/{userId}")
	public ResponseEntity<List<BikeViewDTO>> getBikesByUserId(@PathVariable Long userId) {
		List<BikeViewDTO> bikeList = bikeService.getAllBikeByUserid(userId);
		if (bikeList !=null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "getActiveByUserId/{userId}")
	public ResponseEntity<List<BikeViewDTO>> getActiveByUserId(@PathVariable Long userId) {
		List<BikeViewDTO> bikeList = bikeService.getAllActiveBikeByUserid(userId);
		if (bikeList !=null) {
			return new ResponseEntity<List<BikeViewDTO>>(bikeList, HttpStatus.OK);
		}
		return new ResponseEntity<List<BikeViewDTO>>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BikeViewDTO> changeStatus(@PathVariable(value = "id") Long id, BikeStatus status) {
		BikeViewDTO bike = bikeService.changeBikeStatus(id, status);
		if (bike != null) {

			return new ResponseEntity<BikeViewDTO>(bike, HttpStatus.OK);
		}
		return new ResponseEntity<BikeViewDTO>(HttpStatus.BAD_REQUEST);
	}
}
