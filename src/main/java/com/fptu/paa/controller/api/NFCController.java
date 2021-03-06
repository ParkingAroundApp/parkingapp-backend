package com.fptu.paa.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fptu.paa.constant.NFCStatus;
import com.fptu.paa.entity.NFC;
import com.fptu.paa.repository.NFCRepository;
import com.fptu.paa.service.NFCService;
import com.fptu.paa.service.TicketService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/nfc")
@Api(consumes = "application/json")
public class NFCController {
	@Autowired
	NFCRepository nfcRepository;
	@Autowired
	NFCService nfcService;
	@Autowired
	TicketService ticketService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<NFC> getNFCById(@PathVariable Long id) {
		NFC nfc = nfcRepository.findNFCById(id);
		if (nfc != null) {
			return new ResponseEntity<NFC>(nfc, HttpStatus.OK);
		}
		return new ResponseEntity<NFC>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "")
	public ResponseEntity<NFC> getNFCBySerialNumber(@RequestParam String serialNumber) {
		NFC nfc = nfcRepository.findNFCBySerialNumberIgnoreCase(serialNumber);
		if (nfc != null) {
			return new ResponseEntity<NFC>(nfc, HttpStatus.OK);
		}
		return new ResponseEntity<NFC>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/insert")
	public ResponseEntity<NFC> insertNFC(String serialNumber) {
		NFC nfc = nfcService.insertNFCCard(serialNumber);
		if (nfc != null) {
			return new ResponseEntity<NFC>(nfc, HttpStatus.OK);
		}
		return new ResponseEntity<NFC>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/changeStatus")
	public ResponseEntity<NFC> changeStatus(String serialNumber, NFCStatus nfcStatus) {
		NFC nfc = nfcService.changeNFCStatus(serialNumber, nfcStatus);
		if (nfc != null) {
			return new ResponseEntity<NFC>(nfc, HttpStatus.OK);
		}
		return new ResponseEntity<NFC>(HttpStatus.BAD_REQUEST);
	}
}
