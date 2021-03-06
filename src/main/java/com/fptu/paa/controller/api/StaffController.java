package com.fptu.paa.controller.api;

import java.math.BigDecimal;

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
import com.fptu.paa.constant.NFCStatus;
import com.fptu.paa.constant.PaymentType;
import com.fptu.paa.controller.request.CheckInRequest;
import com.fptu.paa.controller.request.CheckOutRequest;
import com.fptu.paa.dto.BikeDetailDTO;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.entity.NFC;
import com.fptu.paa.service.BikeService;
import com.fptu.paa.service.NFCService;
import com.fptu.paa.service.TicketService;
import com.fptu.paa.service.UserService;
import com.fptu.paa.utils.DateUtils;
import com.owlike.genson.Genson;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/staff")
@Api(value = "Staff", consumes = "application/json", description = "Staff API", tags = { "Staff" })
public class StaffController {

	@Autowired
	BikeService bikeService;
	@Autowired
	TicketService ticketService;
	@Autowired
	UserService userService;
	@Autowired
	NFCService nfcService;

	@PostMapping("/createTicket")
	public ResponseEntity<String> createCheckinTicket(@RequestBody CheckInRequest ticket,
			@RequestParam(required = true, defaultValue = "false") boolean isNFC) {
		try {
			String result = "";
			if (!isNFC) {
				BikeViewDTO bike = bikeService.getBike(Long.valueOf(ticket.getId()));
				if (bike != null && bike.getStatus() == BikeStatus.KEEPING) {
					BikeDetailDTO bikeDetail = new BikeDetailDTO(bike);
					Genson genson = new Genson();
					result = ticketService.checkInByBikeID(ticket.getLicensePlate(), ticket.getId(),
							ticket.getStaffCheckInID(), bike.getUserViewDTO().getId().toString(),
							DateUtils.formattedDate(ticket.getCheckInTime()), ticket.getCheckInBikeImage(),
							ticket.getCheckInFaceImage(), ticket.getTransmissionType().name(),
							genson.serialize(bikeDetail));
				}
			} else {
				NFC nfc = nfcService.getNFCBySerial(ticket.getId());
				if (nfc != null && nfc.getStatus() == NFCStatus.KEEPING) {
					result = ticketService.checkInByNFCID(ticket.getLicensePlate(), ticket.getId(),
							ticket.getStaffCheckInID(), DateUtils.formattedDate(ticket.getCheckInTime()),
							ticket.getCheckInBikeImage(), ticket.getCheckInFaceImage(),
							ticket.getTransmissionType().name());
				}
			}
			if (result != null && !result.isEmpty()) {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			System.out.println("checkinTicket: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create ticket failed!");
	}

	@PutMapping("/bike/checkin")
	public ResponseEntity<String> checkin(@RequestParam Long bikeId) {
		boolean success = bikeService.checkIn(bikeId);
		if (success) {
			return ResponseEntity.ok("Checkin success!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Checkin failed!");
	}

	@PostMapping("/bike/checkout")
	public ResponseEntity<String> checkoutBikeTicket(@RequestBody CheckOutRequest ticket) {
		String result = "Check out failed!";
		try {
			BikeViewDTO bike = bikeService.getBike(Long.valueOf(ticket.getId()));
			if (bike != null && bike.getStatus().equals(BikeStatus.KEEPING)) {
				Long userID = bike.getUserViewDTO().getId();
				BigDecimal cost = new BigDecimal(ticket.getPrice());
				if (cost.compareTo(new BigDecimal(0)) == 1) {
					String ticketKey = "TICKET" + "_" + DateUtils.formattedDate(ticket.getCheckInTime()) + "_"
							+ bike.getLicensePlate();
					String tmpResult = ticketService.checkOutByID(ticketKey, ticket.getStaffCheckOutID(),
							DateUtils.formattedDate(ticket.getCheckOutTime()), ticket.getCheckOutBikeImage(),
							ticket.getCheckOutFaceImage(), ticket.getPaymentType(), ticket.getPrice(),
							String.valueOf(userID), ticket.getFareID());
					if (tmpResult != null && !tmpResult.isEmpty()) {
						// Make wallet payment
						if (ticket.getPaymentType().equals(PaymentType.WALLET.name())) {
							userService.ticketPaymnet(ticket.getPrice(), userID);
						}
						// Change bike status
						bikeService.changeBikeStatus(Long.parseLong(ticket.getId()), BikeStatus.FINISH);
						return ResponseEntity.ok(tmpResult);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("checkoutTicketByBikeID: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	}

	@PutMapping(value = "/nfc/checkin")
	public ResponseEntity<NFC> checkin(@RequestParam String serialNumber) {
		NFC nfc = nfcService.getNFCBySerial(serialNumber);
		// Only check-in when status is FINISH
		if (nfc != null && nfc.getStatus() == NFCStatus.FINISH) {
			nfc = nfcService.changeNFCStatus(serialNumber, NFCStatus.KEEPING);
			if (nfc != null) {
				return new ResponseEntity<NFC>(nfc, HttpStatus.OK);
			}
		}
		return new ResponseEntity<NFC>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/nfc/checkout")
	public ResponseEntity<String> checkoutNfcTicket(@RequestBody CheckOutRequest ticket) {
		try {
			// Call service
			BigDecimal cost = new BigDecimal(ticket.getPrice());
			if (nfcService.getNFCBySerial(ticket.getId()) != null && cost.compareTo(new BigDecimal(0)) == 1) {
				String ticketKey = "TICKET" + "_" + DateUtils.formattedDate(ticket.getCheckInTime()) + "_"
						+ ticket.getId();
				String result = ticketService.checkOutByID(ticketKey, ticket.getStaffCheckOutID(),
						DateUtils.formattedDate(ticket.getCheckOutTime()), ticket.getCheckOutBikeImage(),
						ticket.getCheckOutFaceImage(), ticket.getPaymentType(), ticket.getPrice(), "",
						ticket.getFareID());
				// If success respond 200
				if (result != null && !result.isEmpty()) {
					nfcService.changeNFCStatus(ticket.getId(), NFCStatus.FINISH);
					return ResponseEntity.ok(result);
				}
			}
		} catch (Exception e) {
			System.out.println("checkoutNfcTicket: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check out failed!");
	}

	@PostMapping("/ticket")
	public ResponseEntity<String> getTicketByStaffIdAndDate(@RequestBody String staffID, @RequestParam String date,
			@RequestParam String pageSize, @RequestParam(defaultValue = "") String bookmark,
			@RequestParam(defaultValue = "true") boolean isCheckIn) {
		try {
			String result = ticketService.getTicketByStaffIdAndDate(staffID, date, pageSize, bookmark, isCheckIn);
			// If success respond 200
			if (result != null) {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			System.out.println("getTicketByStaffIdAndDate: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request failed!");
	}
}
