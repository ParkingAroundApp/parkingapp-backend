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

import com.fptu.paa.dto.TicketDTO;
import com.fptu.paa.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@PostMapping("/checkin")
	public ResponseEntity<String> checkinTicketByBikeID(@RequestBody TicketDTO ticket) {
		try {
			ticketService.checkInByBikeID(ticket.getBikeID(), ticket.getOwnerCheckInID(), ticket.getCheckinTime(),
					ticket.getCheckinImages()[0], ticket.getCheckinImages()[1]);
		} catch (Exception e) {
			System.out.println("checkinTicketByBikeID: " + e.getMessage());
			return ResponseEntity.ok("Exception: Done");
		}
		return ResponseEntity.ok("Done");
	}

	@PostMapping("/checkout")
	public ResponseEntity<String> checkoutTicketByBikeID(@RequestBody TicketDTO ticket) {
		try {
			String ticketKey = "TICKET" + ticket.getCheckinTime();
			ticketService.checkOutByBikeID(ticketKey, ticket.getOwnerCheckOutID(), ticket.getCheckoutTime(),
					ticket.getCheckoutImages()[0], ticket.getCheckoutImages()[1], ticket.getPaymentType());
		} catch (Exception e) {
			System.out.println("checkoutTicketByBikeID: " + e.getMessage());
			return ResponseEntity.ok("Exception: Done");
		}
		return ResponseEntity.ok("Done");
	}

	@GetMapping("/get")
	public ResponseEntity<String> getCheckOutTicket(@PathVariable(required = true) String bikeID) {
		String result = "";
		try {
			result = ticketService.getCheckOutTicketByBikeID(bikeID);
		} catch (Exception e) {
			System.out.println("getCheckOutTicket: " + e.getMessage());
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something wrong!");
		}
		return ResponseEntity.ok(result);
	}
}
