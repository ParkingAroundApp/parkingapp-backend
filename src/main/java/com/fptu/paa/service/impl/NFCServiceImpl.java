package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.NFCStatus;
import com.fptu.paa.entity.NFC;
import com.fptu.paa.repository.NFCRepository;
import com.fptu.paa.service.NFCService;

@Service
@Transactional
public class NFCServiceImpl implements NFCService {
	@Autowired
	NFCRepository nfcRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public NFC changeNFCStatus(String serialNumber, NFCStatus nfcStatus) {
		NFC nfc = nfcRepository.findNFCBySerialNumber(serialNumber);
		if (nfc != null) {
			nfc.setStatus(nfcStatus);
			nfc = nfcRepository.save(nfc);
			return nfc;
		}
		return null;
	}

	@Override
	public NFC insertNFCCard(String serialNumber) {
		if (serialNumber != null) {
			NFC nfc = new NFC();
			nfc.setSerialNumber(serialNumber);
			nfc.setStatus(NFCStatus.FINISH);
			nfc = nfcRepository.save(nfc);
			return nfc;
		}
		return null;
	}

	@Override
	public NFC getNFCBySerial(String serialNumber) {
		if (serialNumber != null && !serialNumber.isEmpty()) {
			NFC nfc = nfcRepository.findNFCBySerialNumber(serialNumber);
			if (nfc != null) {
				return nfc;
			}
		}
		return null;
	}

}
