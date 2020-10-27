package com.fptu.paa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.entity.NFC;

@Repository
public interface NFCRepository extends JpaRepository<NFC, Long> {
	NFC findNFCBySerialNumber(String serialNumber);

	NFC findNFCById(Long id);
}
