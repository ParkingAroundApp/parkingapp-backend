package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.BikeRegisterDTO;
import com.fptu.paa.entity.Bike;

@Service
public interface BikeService {
	public Bike registerBike(BikeRegisterDTO newBike);

	public Bike approveBike(BikeStatus bikeStatus);

	public Bike deleteBike(BikeStatus bikeStatus);

	public Bike getBikeByPlateNumber(String plateNumber);
}
