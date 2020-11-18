package com.fptu.paa.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.NewFareSetting;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.TransmissionType;

@Service
public interface FareService {
	BigDecimal fareCalculation(String checkInTime, String checkOutTime, TransmissionType type, boolean isGuest);

	Fare saveFareSetting(NewFareSetting newSetting);
	
	void createDefaultFare();
}
