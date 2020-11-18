package com.fptu.paa.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.FareType;
import com.fptu.paa.constant.TransmissionTypeName;
import com.fptu.paa.dto.NewFareSetting;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.TransmissionType;
import com.fptu.paa.repository.FareRepository;
import com.fptu.paa.repository.TransmissionTypeRepository;
import com.fptu.paa.service.FareService;

@Service
@Transactional
public class FareServiceImpl implements FareService {
	@Autowired
	private FareRepository fareRepo;
	@Autowired
	private TransmissionTypeRepository transTypeRepo;
	private ModelMapper modelMapper = new ModelMapper();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss:SSS");

	@Override
	public BigDecimal fareCalculation(String checkin, String checkout, TransmissionType type, boolean isGuest) {
		// Step 1: Find fare setting by transmisstion type
		Fare fare = fareRepo.findFareByTransmissionType_idAndEnabledAndGuest(type.getId(), true, isGuest);
		if (fare != null) {
			LocalDateTime checkInTime = LocalDateTime.parse(checkin, formatter);
			LocalDateTime checkOutTime = LocalDateTime.parse(checkout, formatter);
			Duration totalParkingTime = Duration.between(checkInTime, checkOutTime);
			// Begin ticket price calculation
			BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO, 2);

			FareType fareType = null;
			while (totalParkingTime.toHours() >= 0) {
				fareType = shiftCalucation(totalParkingTime, fare, checkInTime, checkOutTime);
				totalPrice = ticketPrice(fareType, fare, totalParkingTime);
				totalParkingTime = totalParkingTime.minusHours(24);
			}
			return totalPrice;
		}
		return null;
	}

	private FareType shiftCalucation(Duration totalParkingTime, Fare fare, LocalDateTime checkInTime,
			LocalDateTime checkOutTime) {
		FareType fareType;
		String date = "2020/08/11";
		// Set up day shift and night shift
		String tStartDay = date + "-" + fare.getStartDay() + ":000";
		String tEndDay = date + "-" + fare.getEndDay() + ":000";
		String tStartNight = date + "-" + fare.getStartNight() + ":000";
		String tEndNight = date + "-" + fare.getEndNight() + ":000";

		LocalDateTime startDay = LocalDateTime.parse(tStartDay, formatter);
		LocalDateTime endDay = LocalDateTime.parse(tEndDay, formatter);
		LocalDateTime startNight = LocalDateTime.parse(tStartNight, formatter);
		LocalDateTime endNight = LocalDateTime.parse(tEndNight, formatter);
		if (endNight.isBefore(startNight)) {
			endNight = endNight.plusHours(24);
		}
		// Begin
		Long parkingTime = totalParkingTime.toHours();
		Long maxParkingTime = Long.valueOf(fare.getLimitParkingTime());
		if (parkingTime > maxParkingTime) { // CASE 1: ALL DAY CASE
			fareType = FareType.ALL_DAY;
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
		return fareType;
	}

	private BigDecimal ticketPrice(FareType fareType, Fare fare, Duration totalParkingTime) {
		// Set up price
		BigDecimal result = new BigDecimal(BigInteger.ZERO, 2);
		BigDecimal dayPrice = fare.getInitialDayCost();
		BigDecimal nightPrice = fare.getInitialNightCost();
		BigDecimal allDayPrice = fare.getAllDayCost();
		// Set up fine price
		Long dayTurnTime = Long.valueOf(fare.getDayTurnDuration());
		Long overDayTurnTime = Long.valueOf(fare.getDayOverTurnTime());
		BigDecimal overDayPrice = fare.getDayOverCost();

		Long nightTurnTime = Long.valueOf(fare.getNightTurnDuration());
		Long overNightTurnTime = Long.valueOf(fare.getNightOverTurnTime());
		BigDecimal overNightPrice = fare.getNightOverCost();
		if (fareType != null) {
			switch (fareType) {
			case DAY_SHIFT:
				result = result.add(dayPrice);
				// Check if parking over turn time
				if (totalParkingTime.toHours() > dayTurnTime) {
					Duration remainderTime = totalParkingTime.minusHours(dayTurnTime);
					Long time = remainderTime.dividedBy(overDayTurnTime).toHours();
					BigDecimal finePrice = overDayPrice.multiply(new BigDecimal(time));
					result = result.add(finePrice);
				}
				break;
			case NIGHT_SHIFT:
				result = result.add(nightPrice);
				if (totalParkingTime.toHours() > nightTurnTime) {
					Duration remainderTime = totalParkingTime.minusHours(nightTurnTime);
					Long time = remainderTime.dividedBy(overNightTurnTime).toHours();
					BigDecimal finePrice = overNightPrice.multiply(new BigDecimal(time));
					result = result.add(finePrice);
				}
				break;
			case ALL_DAY:
				result = result.add(allDayPrice);
				break;
			default:
				break;
			}
		}
		return result;
	}

	@Override
	public Fare saveFareSetting(NewFareSetting newSetting) {
		String tmpCheckInTime = "2020/08/16-00:00:00:000";
		String tmpCheckOutTime = "2020/08/16-00:00:00:000";

		String checkIndate = tmpCheckInTime.split("-")[0];
		String checkOutdate = tmpCheckOutTime.split("-")[0];
		// Set up day shift and night shift
		String tStartDay = checkIndate + "-" + newSetting.getStartDay() + ":000";
		String tEndDay = checkIndate + "-" + newSetting.getEndDay() + ":000";
		String tStartNight = checkIndate + "-" + newSetting.getStartNight() + ":000";
		String tEndNight = checkOutdate + "-" + newSetting.getEndNight() + ":000";
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
		// Verify if new setting is validated
		if (dayShift.plus(nightShift).toHours() == 24L) {
			// Begin save a new setting
			TransmissionType transmissionType = transTypeRepo.findByNameAndEnabled(newSetting.getTypeName(), true);
			// Disabled old setting
			disableOldSetting(transmissionType.getId(), newSetting.isGuest());
			// Init new setting object
			Fare newFare = modelMapper.map(newSetting, Fare.class);
//			formatter.format(Instant.now());
			newFare.setCreateDate(Timestamp.from(Instant.now()));
			newFare.setTransmissionType(transmissionType);
			return fareRepo.save(newFare);
		}
		return null;
	}

	private void disableOldSetting(Long transmissionTypeID, boolean isGuest) {
		Fare oldSetting = fareRepo.findFareByTransmissionType_idAndEnabledAndGuest(transmissionTypeID, true, isGuest);
		if (oldSetting != null) {
			oldSetting.setExpirationDate(Timestamp.from(Instant.now()));
			oldSetting.setEnabled(false);
			fareRepo.save(oldSetting);
		}
	}

	@Override
	public boolean createDefaultFare() {
		NewFareSetting newFareSetting = new NewFareSetting();
		newFareSetting.setTypeName(TransmissionTypeName.XE_GA);
		newFareSetting.setAllDayCost(new BigDecimal("9000"));
		newFareSetting.setLimitParkingTime(10);
		newFareSetting.setGuest(false);
		// Set up day
		newFareSetting.setStartDay("05:00:00");
		newFareSetting.setEndDay("21:00:00");
		newFareSetting.setDayTurnDuration(4);
		newFareSetting.setInitialDayCost(new BigDecimal("4000"));
		newFareSetting.setDayOverTurnTime(1);
		newFareSetting.setDayOverCost(new BigDecimal("1000"));
		// Set up night
		newFareSetting.setStartNight("21:00:00");
		newFareSetting.setEndNight("05:00:00");
		newFareSetting.setNightTurnDuration(3);
		newFareSetting.setInitialNightCost(new BigDecimal("5000"));
		newFareSetting.setNightOverTurnTime(1);
		newFareSetting.setNightOverCost(new BigDecimal("2000"));
		Fare newFare = saveFareSetting(newFareSetting);
		return newFare != null;
	}

}
