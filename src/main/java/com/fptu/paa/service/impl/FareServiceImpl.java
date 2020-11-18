package com.fptu.paa.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.FareType;
import com.fptu.paa.dto.NewFareSetting;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.Ticket;
import com.fptu.paa.entity.TransmissionType;
import com.fptu.paa.repository.FareRepository;
import com.fptu.paa.repository.TransmissionTypeRepository;
import com.fptu.paa.service.FareService;

@Service
public class FareServiceImpl implements FareService {
	@Autowired
	private FareRepository fareRepo;
	@Autowired
	private TransmissionTypeRepository transTypeRepo;
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public BigDecimal fareCalculation(Ticket ticket, TransmissionType type, boolean isETicket) {
		// Step 1: Find fare setting by transmisstion type
		Fare fare = fareRepo.findFareByTransmissionType_idAndEnabledAndGuest(type.getId(), true, isETicket);
		if (fare != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss:SSS");
			String tmpCheckInTime = ticket.getCheckinTime();
			String tmpCheckOutTime = ticket.getCheckoutTime();

			String checkIndate = tmpCheckInTime.split("-")[0];
			String checkOutdate = tmpCheckOutTime.split("-")[0];
			// Set up day shift and night shift
			String tStartDay = checkIndate + "-" + fare.getStartDay();
			String tEndDay = checkIndate + "-" + fare.getEndDay();
			String tStartNight = checkIndate + "-" + fare.getStartNight();
			String tEndNight = checkOutdate + "-" + fare.getEndNight();

			LocalDateTime startDay = LocalDateTime.parse(tStartDay, formatter);
			LocalDateTime endDay = LocalDateTime.parse(tEndDay, formatter);
			LocalDateTime startNight = LocalDateTime.parse(tStartNight, formatter);
			LocalDateTime endNight = LocalDateTime.parse(tEndNight, formatter);
			if (endNight.isBefore(startNight)) {
				endNight = endNight.plusHours(24);
			}
			// Parking duration calculation
			LocalDateTime checkInTime = LocalDateTime.parse(tmpCheckInTime, formatter);
			LocalDateTime checkOutTime = LocalDateTime.parse(tmpCheckOutTime, formatter);
			Duration totalParkingTime = Duration.between(checkInTime, checkOutTime);
			// Begin ticket price calculation
			Long maxParkingTime = 10L;
			Long parkingTime = totalParkingTime.toHours();
			FareType fareType = null;
			if (parkingTime > maxParkingTime) { // CASE 1: ALL DAY CASE
				if (parkingTime < 24L) {
					fareType = FareType.ALL_DAY;
				} else {

				}
			} else { // CASE 2: DAY OR NIGHT SHIFT
				if (checkInTime.isAfter(startDay) && checkOutTime.isBefore(endDay)) { // CA SANG
					fareType = FareType.DAY_SHIFT;
				} else if (checkInTime.isAfter(startNight) && checkOutTime.isBefore(endNight)) { // CA TOI
					fareType = FareType.NIGHT_SHIFT;
				} else {
					// Compare duration between to shift (DAY and NIGHT)
					// Choose the shift which have greater duration
					Duration parkingDayTime, parkingNightTime;
					if (checkInTime.isAfter(startDay) && checkInTime.isBefore(endDay)) {
						parkingDayTime = Duration.between(checkInTime, endDay);
						parkingNightTime = Duration.between(startNight, checkOutTime);
					} else {
						parkingNightTime = Duration.between(checkInTime, endNight);
						parkingDayTime = Duration.between(endNight, checkOutTime);
					}
					fareType = parkingNightTime.toHours() > parkingDayTime.toHours() ? FareType.NIGHT_SHIFT
							: FareType.DAY_SHIFT;
				}
			}
			return ticketPrice(fareType, fare);
		}
		return null;
	}

	private BigDecimal ticketPrice(FareType fareType, Fare fare) {
		// Set up price
		BigDecimal totalPrice = new BigDecimal("0");
		BigDecimal dayPrice = fare.getInitialDayCost();
		BigDecimal nightPrice = fare.getInitialNightCost();
		BigDecimal allDayPrice = fare.getAllDayCost();
		if (fareType != null) {
			switch (fareType) {
			case DAY_SHIFT:
				totalPrice = totalPrice.add(dayPrice);
				break;
			case NIGHT_SHIFT:
				totalPrice = totalPrice.add(nightPrice);
				break;
			case ALL_DAY:
				totalPrice = totalPrice.add(allDayPrice);
				break;
			default:
				break;
			}
		}
		return totalPrice;
	}

	@Override
	public Fare saveFareSetting(NewFareSetting newSetting) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss:SSS");
		String tmpCheckInTime = "2020/08/16-00:00:00:000";
		String tmpCheckOutTime = "2020/08/16-00:00:00:000";

		String checkIndate = tmpCheckInTime.split("-")[0];
		String checkOutdate = tmpCheckOutTime.split("-")[0];
		// Set up day shift and night shift
		String tStartDay = checkIndate + "-" + newSetting.getStartDay();
		String tEndDay = checkIndate + "-" + newSetting.getEndDay();
		String tStartNight = checkIndate + "-" + newSetting.getStartNight();
		String tEndNight = checkOutdate + "-" + newSetting.getEndNight();
		LocalDateTime startDay = LocalDateTime.parse(tStartDay, formatter);
		LocalDateTime endDay = LocalDateTime.parse(tEndDay, formatter);
		LocalDateTime startNight = LocalDateTime.parse(tStartNight, formatter);
		LocalDateTime endNight = LocalDateTime.parse(tEndNight, formatter);
		if (endNight.isBefore(startNight)) {
			endNight = endNight.plusHours(24);
		}
		// DAY_SHIFT RANGE
		Duration dayShift = Duration.between(startDay, endDay);
		// NIGHT_SHIFT RANGE
		Duration nightShift = Duration.between(startNight, endNight);
		if (nightShift.isNegative()) {
			nightShift = nightShift.plus(Duration.ofHours(24));
		}
		//Verify if new setting is validated
		if (dayShift.plus(nightShift).toHours() == 24L) {
			// Begin save a new setting
			TransmissionType transmissionType = transTypeRepo.findByNameAndEnabled(newSetting.getTypeName(), true);
			//Disabled old setting
			disableOldSetting(transmissionType.getId(), newSetting.iseTicket());
			//Init new setting object
			Fare newFare = modelMapper.map(newSetting, Fare.class);
			newFare.setTransmissionType(transmissionType);
			return fareRepo.save(newFare);
		}
		return null;
	}
	
	private void disableOldSetting(Long transmissionTypeID, boolean isGuest) {
		 Fare oldSetting = fareRepo.findFareByTransmissionType_idAndEnabledAndGuest(transmissionTypeID, true, isGuest);
		 oldSetting.setEnabled(false);
		 fareRepo.save(oldSetting);
	}

}
