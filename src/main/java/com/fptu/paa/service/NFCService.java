package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.NFCStatus;
import com.fptu.paa.entity.NFC;

@Service
public interface NFCService {
	public NFC insertNFCCard(String  serialNumber);
	
	public NFC changeNFCStatus(String serialNumber, NFCStatus nfcStatus);
	
	public NFC getNFCBySerial(String serialNumber);

	void initSampleNFC();
}
