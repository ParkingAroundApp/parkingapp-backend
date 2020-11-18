package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.entity.Fare;
import com.fptu.paa.service.FareService;

@RestController
@RequestMapping("/api/fare")
public class FareController {
	@Autowired
	FareService fareService;
	
	
	@PutMapping(value = "")
	public Fare saveFareSettings(@RequestBody Fare newFare) {
		return null;
	}
}
