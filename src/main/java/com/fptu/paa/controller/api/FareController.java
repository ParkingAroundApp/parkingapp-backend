package com.fptu.paa.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.TransmissionTypeName;
import com.fptu.paa.controller.request.NewFareRequest;
import com.fptu.paa.dto.TicketPriceResponse;
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
	public ResponseEntity<List<Fare>> updateFareSetting(@RequestBody List<NewFareRequest> fares) {
		if (fares == null || fares.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		List<Fare> result = fareService.updateSetting(fares);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "")
	public ResponseEntity<List<Fare>> getFareSetting(@RequestParam boolean isGuest) {
		List<Fare> result = fareService.getFaresByGuest(isGuest);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/price")
	public ResponseEntity<TicketPriceResponse> ticketPriceCalculation(@RequestParam String checkInTime,
			@RequestParam String checkOutTime, @RequestParam TransmissionTypeName typeName,
			@RequestParam(defaultValue = "false") boolean isGuest) {
		TransmissionType type = transTypeService.getActiveType(typeName);
		TicketPriceResponse result = null;
		if (type != null) {
			result = fareService.fareCalculation(checkInTime, checkOutTime, type, isGuest);
		}
		return ResponseEntity.ok(result);
	}
}
