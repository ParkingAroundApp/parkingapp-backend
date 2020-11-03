package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.dto.RequestMetaData;
import com.fptu.paa.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@GetMapping("")
	public ResponseEntity<String> getListById(@RequestParam(required = true) String id, @RequestParam String bookmark,
			@RequestParam(required = true, defaultValue = "false") boolean isNFC) {
		String result = "";
		try {
			result = isNFC ? ticketService.getListNFCTicket(id, bookmark)
					: ticketService.getListBikeTicket(id, bookmark);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/all")
	public ResponseEntity<String> getAllTicket(@RequestBody RequestMetaData metaData) {
		String result = "";
		try {
			String tmpResult = ticketService.getAllTicket(metaData.getPageSize(), metaData.getBookmark());
			if (!tmpResult.isEmpty()) {
				result = tmpResult;
			}
		} catch (Exception e) {
			System.out.println("getAllTicket: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/checkout")
	public ResponseEntity<String> getCheckOutTicket(@RequestParam(required = true) String id,
			@RequestParam(required = true, defaultValue = "false") boolean isNFC) {
		String result = "No available ticket!";
		try {
			String tmpResult = isNFC ? ticketService.getCheckOutTicketByNFC(id)
					: ticketService.getCheckOutTicketByBikeID(id);
			if (!tmpResult.isEmpty()) {
				result = tmpResult;
			}
		} catch (Exception e) {
			System.out.println("getCheckOutTicket: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}
}
