package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.NFCStatus;
import com.fptu.paa.dto.NFCDTO;
import com.fptu.paa.entity.NFC;

@Service
public interface NFCService {
	public NFC insertNFCCard(NFCDTO nfcCard);
	public boolean changeNFCStatus(NFCStatus nfcStatus);
}
