package com.fptu.paa.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fptu.paa.controller.request.NewFareRequest;
import com.fptu.paa.dto.TicketPriceResponse;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.TransmissionType;

@Service
public interface FareService {
	TicketPriceResponse fareCalculation(String checkInTime, String checkOutTime, TransmissionType type,
			boolean isGuest);

	List<Fare> updateSetting(List<NewFareRequest> fares);

	Fare saveNewFare(NewFareRequest newSetting);

	void initDefaultFare() throws FileNotFoundException;
	
	List<Fare> getFaresByGuest(boolean isGuest);
}
