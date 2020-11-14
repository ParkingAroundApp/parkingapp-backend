package com.fptu.paa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.TransactionType;
import com.fptu.paa.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/transaction")
@Slf4j
public class TransactionController {
	@Autowired
	TransactionService transcationService;

	@GetMapping("/all/inMonth")
	public ResponseEntity<String> getAllInMonth(@RequestParam boolean isPayment, @RequestParam String startDate,
			@RequestParam String endDate, @RequestParam String pageSize,
			@RequestParam(defaultValue = "") String bookmark) {
		String result = "";
		try {
			String tmpResult = transcationService.getAllTransactionInMonth(startDate, endDate, isPayment, pageSize,
					bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			log.error("Getting list transaction by in month error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/byUserID/inMonth")
	public ResponseEntity<String> getListByUserIdInMonth(@RequestParam String userID, @RequestParam String year,
			@RequestParam String month) {
		String result = "";
		try {
			String tmpResult = transcationService.getTransactionByUserIdInMonth(userID, year, month);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			log.error("Getting list transaction by userID in month error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred!");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/byUserID/{userID}")
	public ResponseEntity<String> getListByUserId(@PathVariable String userID, @RequestParam String pageSize,
			@RequestParam(defaultValue = "") String bookmark, @RequestParam(defaultValue = "") String endDate,
			@RequestParam String startDate, TransactionType transactionType) {
		String result = "";
		try {
			if (transactionType != TransactionType.PAYMENT_NFC) {
				String tmpResult = transcationService.getTransactionByUserId(userID, startDate, endDate, pageSize,
						transactionType, bookmark);
				if (tmpResult != null) {
					result = tmpResult;
				}
			}
		} catch (Exception e) {
			log.error("Getting all transaction by userID with pagination error: " + e.getMessage());
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
			log.error("Getting list payment transaction error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/recharge")
	public ResponseEntity<String> getRecharge(@RequestParam String pageSize,
			@RequestParam(defaultValue = "") String bookmark) {
		String result = "";
		try {
			String tmpResult = transcationService.getAllTopUpTransaction(pageSize, bookmark);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			log.error("Getting list top-up transaction error: " + e.getMessage());
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
			log.error("Getting list transaction by NFC Serial error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<String> getTransactionDetail(@PathVariable String id, @RequestParam String createTime) {
		String result = "";
		try {
			String tmpResult = transcationService.getTransactionDetail(createTime, id);
			if (tmpResult != null) {
				result = tmpResult;
			}
		} catch (Exception e) {
			log.error("Getting detail transaction error: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something wrong!");
		}
		return ResponseEntity.ok(result);
	}
}
