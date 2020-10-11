package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.entity.Bike;
import com.fptu.paa.repository.BikeRepository;
import com.fptu.paa.service.BikeService;

@RestController
@RequestMapping("/api/bike")
public class BikeController {

	@Autowired
	BikeService BikeService;

	@Autowired
	BikeRepository bikeRepo;

	@GetMapping(value = "get")
	public String getBike() {
		Bike bike = bikeRepo.findById(1L).get();
		return bike.toString();
	}
}
