package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.TransmissionTypeName;
import com.fptu.paa.entity.TransmissionType;

@Service
public interface TransmissionTypeService {
	void insertDefaultTransmissionType();
	
	TransmissionType getActiveType(TransmissionTypeName name);
}
