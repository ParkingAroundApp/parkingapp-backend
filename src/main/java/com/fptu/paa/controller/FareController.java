package com.fptu.paa.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.TransmissionTypeName;
import com.fptu.paa.dto.NewFareSetting;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.TransmissionType;
import com.fptu.paa.service.FareService;
import com.fptu.paa.service.TransmissionTypeService;

@RestController
@RequestMapping("/api/fare")
public class FareController {
	@Autowired
	FareService fareService;
	@Autowired
	TransmissionTypeService transTypeService;

	@PutMapping(value = "")
	public Fare saveFareSettings(@RequestBody NewFareSetting newFare) {
		return fareService.saveFareSetting(newFare);
	}

	@GetMapping(value = "/price")
	public ResponseEntity<String> ticketPriceCalculation(@RequestParam String checkInTime,
			@RequestParam String checkOutTime, @RequestParam TransmissionTypeName typeName,
			@RequestParam(defaultValue = "false") boolean isGuest) {
		TransmissionType type = transTypeService.getActiveType(typeName);
		String result = null;
		if (type != null) {
			BigDecimal price = fareService.fareCalculation(checkInTime, checkOutTime, type, isGuest);
			if (price != null) {
				result = price.toString();
			}
		}
		return ResponseEntity.ok(result);
	}
}
