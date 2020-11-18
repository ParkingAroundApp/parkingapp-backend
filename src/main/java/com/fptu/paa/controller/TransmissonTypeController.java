package com.fptu.paa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.entity.TransmissionType;
import com.fptu.paa.service.TransmissionTypeService;

@RestController
@RequestMapping("/api/transmissiontype")
public class TransmissonTypeController {
	@Autowired
	TransmissionTypeService transTypeService;

	@GetMapping("/all/{isEnabled}")
	public ResponseEntity<List<TransmissionType>> getAllByEnabled(@RequestParam boolean isEnabled) {
		List<TransmissionType> result = null;
		try {
			result = transTypeService.getListTransmissionType(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(result);
	}
}
