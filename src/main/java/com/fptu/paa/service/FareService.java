package com.fptu.paa.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.NewFareSetting;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.Ticket;
import com.fptu.paa.entity.TransmissionType;

@Service
public interface FareService {
	BigDecimal fareCalculation(Ticket ticket, TransmissionType type, boolean isETicket);

	Fare saveFareSetting(NewFareSetting newSetting);
}
