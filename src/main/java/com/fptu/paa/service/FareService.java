package com.fptu.paa.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.NewFareSetting;
import com.fptu.paa.dto.TicketPriceResponse;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.TransmissionType;

@Service
public interface FareService {
	TicketPriceResponse fareCalculation(String checkInTime, String checkOutTime, TransmissionType type,
			boolean isGuest);

	List<Fare> updateSetting(List<NewFareSetting> fares);

	Fare saveNewFare(NewFareSetting newSetting);

	void createDefaultFare() throws FileNotFoundException;
	
	List<Fare> getFaresByGuest(boolean isGuest);
}
