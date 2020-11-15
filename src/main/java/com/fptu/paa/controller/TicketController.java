package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.dto.ReportRequest;
import com.fptu.paa.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@GetMapping("/nfc/{serial}")
	public ResponseEntity<String> getListByNFCSerial(@PathVariable String serial,
			@RequestParam(defaultValue = "") String bookmark, @RequestParam String pageSize,
			@RequestParam String startDate, @RequestParam(defaultValue = "") String endDate) {
		String result = "";
		try {
			String tmpResult = ticketService.getListNFCTicket(serial, startDate, endDate, pageSize, bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{plateNumber}")
	public ResponseEntity<String> getListByPlateNumber(@PathVariable String plateNumber,
			@RequestParam(defaultValue = "") String bookmark, String pageSize, String startDate, String endDate) {
		String result = "";
		try {
			String tmpResult = ticketService.getListTicketByPlateNumber(plateNumber, startDate, endDate, pageSize,
					bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/all")
	public ResponseEntity<String> getAllTicket(@RequestParam String startDate, @RequestParam String endDate,
			@RequestParam String pageSize, @RequestParam(defaultValue = "") String bookmark) {
		String result = "";
		try {
			String tmpResult = ticketService.getAllTicket(startDate, endDate, pageSize, bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/month")
	public ResponseEntity<String> getTicketInMonth(@RequestParam String userId, @RequestParam String month,
			@RequestParam String year) {
		String result = "No available ticket!";
		try {
			String tmp = ticketService.getListTicketByCustomerID(userId, year, month);
			if (tmp != null) {
				result = tmp;
			}
		} catch (Exception e) {
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
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/report")
	public ResponseEntity<String> reportTicket(@RequestBody ReportRequest reportRequest) {
		String result = "Report Fail!";
		try {
			String tmpResult = ticketService.reportTicket(reportRequest.getCheckInTime(), reportRequest.getId(),
					reportRequest.getOwnerCheckOutID(), reportRequest.getReportTime(),
					reportRequest.getReportBikeImage(), reportRequest.getReportFaceImage());
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/history")
	public ResponseEntity<String> getTicketHistory(@RequestParam String checkInTime, String key) {
		String result = "No history found!";
		try {
			String tmpResult = ticketService.getTicketHistory(checkInTime, key);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/inday")
	public ResponseEntity<String> getTicketInDate(@RequestParam String date, @RequestParam String pageSize,
			@RequestParam(defaultValue = "") String bookmark) {
		try {
			String result = ticketService.getTicketInDate(date, pageSize, bookmark);
			// If success respond 200
			if (result != null && !result.isEmpty()) {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			System.out.println("getTicketInDate: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check out failed!");
	}

}
