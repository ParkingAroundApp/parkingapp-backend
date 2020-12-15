package com.fptu.paa.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.FareType;
import com.fptu.paa.controller.request.NewFareRequest;
import com.fptu.paa.dto.TicketPriceResponse;
import com.fptu.paa.entity.Fare;
import com.fptu.paa.entity.TransmissionType;
import com.fptu.paa.repository.FareRepository;
import com.fptu.paa.repository.TransmissionTypeRepository;
import com.fptu.paa.service.FareService;
import com.fptu.paa.utils.DateUtils;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;

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
	public TicketPriceResponse fareCalculation(String checkin, String checkout, TransmissionType type,
			boolean isGuest) {
		// Step 1: Find fare setting by transmission type
		Fare fare = fareRepo.findFareByTransmissionType_idAndEnabledAndGuest(type.getId(), true, isGuest);
		if (fare != null) {
			String checkInDate = checkin.split("-")[0];
			LocalDateTime checkInTime = LocalDateTime.parse(checkin, formatter);
			LocalDateTime checkOutTime = LocalDateTime.parse(checkout, formatter);
			Duration totalParkingTime = Duration.between(checkInTime, checkOutTime);
			String date = checkInDate;
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
			// Begin ticket price calculation
			BigDecimal totalPrice = new BigDecimal(BigInteger.ZERO, 2);
			FareType fareType = null;
			int count = 0;
			Long maxParkingTime = Long.valueOf(fare.getLimitParkingTime());
			while (totalParkingTime.toMillis() > 0) {
				if (count > 0) {
					checkInTime = checkInTime.plusHours(24);
					startDay = startDay.plusHours(24);
					endDay = endDay.plusHours(24);
					startNight = startNight.plusHours(24);
					endNight = endNight.plusHours(24);
				}
				Long parkingTime = totalParkingTime.toMinutes();
				FareType preferType = fare.getInitialDayCost().compareTo(fare.getInitialNightCost()) == 1
						? FareType.DAY_SHIFT
						: FareType.NIGHT_SHIFT;
				if (parkingTime > maxParkingTime) { // CASE 1: Tinh gia gui xe theo ngay
					fareType = FareType.ALL_DAY;
				} else { // CASE 2: Tinh gia gui xe theo ca sang hoac toi
					if (DateUtils.isBetween(checkInTime, startDay, endDay, "==")
							&& DateUtils.isBetween(checkOutTime, startDay, endDay, "==")) {
						fareType = FareType.DAY_SHIFT;
					} else if (DateUtils.isBetween(checkInTime, startNight, endNight, "==")
							&& DateUtils.isBetween(checkOutTime, startNight, endNight, "==")) {
						fareType = FareType.NIGHT_SHIFT;
					} else { // SANG + TOI
						Duration parkingDayTime, parkingNightTime;
						// Compare duration between to shift (DAY and NIGHT)
						// Choose the shift which have greater duration
						if (DateUtils.isBetween(checkInTime, startDay, endDay, "==")) {
							parkingDayTime = Duration.between(checkInTime, startNight);
							parkingNightTime = Duration.between(startNight, checkOutTime);
						} else {
							parkingNightTime = Duration.between(checkInTime, startDay);
							parkingDayTime = Duration.between(startDay, checkOutTime);
						}
						int result = parkingNightTime.compareTo(parkingDayTime);
						fareType = result == 0 ? preferType : result == 1 ? FareType.NIGHT_SHIFT : FareType.DAY_SHIFT;
					}
				}
				totalPrice = ticketPrice(fareType, fare, totalParkingTime);
				count++;

				totalParkingTime = totalParkingTime.minusHours(24);
			}
			TicketPriceResponse response = new TicketPriceResponse();
			response.setFare(fare);
			response.setTotalPrice(totalPrice.toString());
			return response;
		}
		return null;
	}

	private BigDecimal ticketPrice(FareType fareType, Fare fare, Duration totalParkingTime) {
		// Set up price
		BigDecimal result = new BigDecimal(BigInteger.ZERO, 2);
		BigDecimal dayPrice = fare.getInitialDayCost();
		BigDecimal nightPrice = fare.getInitialNightCost();
		BigDecimal allDayPrice = fare.getAllDayCost();

		if (fareType != null) {
			switch (fareType) {
			case DAY_SHIFT:
				result = result.add(dayPrice);
				break;
			case NIGHT_SHIFT:
				result = result.add(nightPrice);
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
	public Fare saveNewFare(NewFareRequest newSetting) {
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
			oldSetting.setEnabled(false);
			fareRepo.save(oldSetting);
		}
	}

	@Override
	public void initDefaultFare() throws FileNotFoundException {
		List<Fare> fares = fareRepo.findAll();

		if (fares.isEmpty()) {
			Path fareData = Paths.get("fareData.json");
			Genson genson = new Genson();
			List<NewFareRequest> list = genson.deserialize(new FileInputStream(fareData.toFile()), new GenericType<List<NewFareRequest>>(){});
			for (NewFareRequest newFareSetting : list) {
				saveNewFare(newFareSetting);
			}
		}
	}

	@Override
	public List<Fare> updateSetting(List<NewFareRequest> fares) {
		List<Fare> result = new ArrayList<>();
		for (NewFareRequest newFareSetting : fares) {
			Fare fare = saveNewFare(newFareSetting);
			if (fare != null) {
				result.add(fare);
			}
		}
		return result;
	}

	@Override
	public List<Fare> getFaresByGuest(boolean isGuest) {
		return fareRepo.findFareByEnabledAndGuest(true,isGuest);
	}

}
