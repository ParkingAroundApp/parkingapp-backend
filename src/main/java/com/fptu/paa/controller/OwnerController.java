package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.CheckInRequest;
import com.fptu.paa.dto.CheckOutRequest;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.TicketService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/owner")
@Api(value = "Owner",consumes = "application/json", description = "Owner API", tags = {"Owner"})
public class OwnerController {

	@Autowired
	BikeService bikeService;
	@Autowired
	TicketService ticketService;

	@PutMapping("/bike/checkin")
	public ResponseEntity<String> checkin(@RequestParam Long bikeId) {
		BikeStatus rs = bikeService.checkIn(bikeId);
		if (rs != null && rs == BikeStatus.PARKING) {
			return ResponseEntity.ok("Checkin success!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Checkin failed!");
	}
	

	@PostMapping("/createTicket")
	public ResponseEntity<String> checkinTicketBy(@RequestBody CheckInRequest ticket,
			@RequestParam(required = true, defaultValue = "false") boolean isNFC) {
		try {
			String result;
			if (!isNFC) {
				result = ticketService.checkInByBikeID(ticket.getId(), ticket.getOwnerCheckInID(),
						ticket.getCheckInTime(), ticket.getCheckInBikeImage(), ticket.getCheckInFaceImage());
			} else {
				result = ticketService.checkInByNFCID(ticket.getId(), ticket.getOwnerCheckInID(),
						ticket.getCheckInTime(), ticket.getCheckInBikeImage(), ticket.getCheckInFaceImage());
			}
			if (result != null && !result.isEmpty()) {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			System.out.println("checkinTicketBy: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create ticket failed!");
	}

	@PostMapping("/bike/checkout")
	public ResponseEntity<String> checkoutTicketByBikeID(@RequestBody CheckOutRequest ticket) {
		try {
			String ticketKey = "TICKET" + "_" + ticket.getCheckInTime() + "_" + ticket.getId();
			String result = ticketService.checkOutByBikeID(ticketKey, ticket.getOwnerCheckOutID(),
					ticket.getCheckOutTime(), ticket.getCheckOutBikeImage(), ticket.getCheckOutFaceImage(),
					ticket.getPaymentType());
			if (result != null && !result.isEmpty()) {
				bikeService.changeBikeStatus(Long.parseLong(ticket.getId()), BikeStatus.FINISH);
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			System.out.println("checkoutTicketByBikeID: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check out failed!");
	}
}
