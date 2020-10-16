package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.dto.BikeRegisterDTO;
import com.fptu.paa.entity.Bike;
import com.fptu.paa.service.BikeService;

@Service
@Transactional
public class BikeServiceImpl implements BikeService{

	@Override
	public Bike registerBike(BikeRegisterDTO newBike) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike approveBike(BikeStatus bikeStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike deleteBike(BikeStatus bikeStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike getBikeByPlateNumber(String plateNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
