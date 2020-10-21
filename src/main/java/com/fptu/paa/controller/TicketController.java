package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.dto.CheckInRequest;
import com.fptu.paa.dto.CheckOutRequest;
import com.fptu.paa.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@PostMapping("/checkin")
	public ResponseEntity<String> checkinTicketByBikeID(@RequestBody CheckInRequest ticket) {
		try {
			String result = ticketService.checkInByBikeID(ticket.getBikeID(), ticket.getOwnerCheckInID(),
					ticket.getCheckInTime(), ticket.getCheckInBikeImage(), ticket.getCheckInFaceImage());
			if (result != null && !result.isEmpty()) {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			System.out.println("checkinTicketByBikeID: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check in failed!");
	}

	@PostMapping("/checkout")
	public ResponseEntity<String> checkoutTicketByBikeID(@RequestBody CheckOutRequest ticket) {
		try {
			String ticketKey = "TICKET" + ticket.getCheckInTime();
			String result = ticketService.checkOutByBikeID(ticketKey, ticket.getOwnerCheckOutID(),
					ticket.getCheckOutTime(), ticket.getCheckOutBikeImage(), ticket.getCheckOutFaceImage(),
					ticket.getPaymentType());
			if (result != null && !result.isEmpty()) {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			System.out.println("checkoutTicketByBikeID: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check out failed!");
	}

	@GetMapping("/getCheckOutTicket")
	public ResponseEntity<String> getCheckOutTicket(@PathVariable(required = true) String bikeID) {
		String result = "";
		try {
			result = ticketService.getCheckOutTicketByBikeID(bikeID);
		} catch (Exception e) {
			System.out.println("getCheckOutTicket: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/getall")
	public ResponseEntity<String> getAllTicket() {
		String result = "";
		try {
			result = ticketService.getAllTicket();
		} catch (Exception e) {
			System.out.println("getAllTicket: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}
}
