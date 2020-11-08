package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	@Autowired
	TransactionService transcationService;

	@GetMapping("/inMonth")
	public ResponseEntity<String> getListByUserIdInMonth(@RequestParam String userID, @RequestParam String year,
			@RequestParam String month) {
		String result = "";
		try {
			String tmpResult = transcationService.getTransactionByUserIdInMonth(userID, year, month);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/byUserID/{userID}")
	public ResponseEntity<String> getListByUserId(@PathVariable String userID, @RequestParam String pageSize,
			@RequestParam(defaultValue = "") String bookmark) {
		String result = "";
		try {
			String tmpResult = transcationService.getTransactionByUserId(userID, pageSize, bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/payment")
	public ResponseEntity<String> getPayment(@RequestParam(defaultValue = "false") boolean isNFC,
			@RequestParam String pageSize, @RequestParam(defaultValue = "") String bookmark) {
		String result = "";
		try {
			String tmpResult = transcationService.getAllPayemntTransactionByType(isNFC, pageSize, bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/recharge")
	public ResponseEntity<String> getRecharge(@RequestParam String pageSize, @RequestParam(defaultValue = "") String bookmark) {
		String result = "";
		try {
			String tmpResult = transcationService.getAllTopUpTransaction(pageSize, bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/byNFC/{nfcSerial}")
	public ResponseEntity<String> getByNFCSerial(@PathVariable String nfcSerial, @RequestParam String pageSize,
			@RequestParam(defaultValue = "") String bookmark) {
		String result = "";
		try {
			String tmpResult = transcationService.getNFCTransactionBySerial(nfcSerial, pageSize, bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}
}
