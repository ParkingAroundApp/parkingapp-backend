package com.fptu.paa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.BikeRegisterDTO;
import com.fptu.paa.dto.BikeViewDTO;
import com.fptu.paa.entity.Bike;

@Service
public interface BikeService {
	public BikeViewDTO getBike(Long bike_id);

	public BikeRegisterDTO registerBike(BikeRegisterDTO newBike);

	public Bike approveBike(BikeStatus bikeStatus);

	public BikeViewDTO changeBikeStatus(Long bike_id, BikeStatus bikeStatus);

	public Bike deleteBike(BikeStatus bikeStatus);

	public BikeViewDTO getBikeByPlateNumber(String plateNumber);

	public List<BikeViewDTO> getAllBikeByUserid(Long user_id);

	public List<BikeViewDTO> getAllActiveBikeByUserid(Long user_id);
	
	public List<BikeViewDTO> getAllBikesByStatus(BikeStatus status);
	
	public boolean checkIn(Long bikeId);
}
