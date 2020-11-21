package com.fptu.paa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fptu.paa.constant.TransmissionTypeName;
import com.fptu.paa.entity.TransmissionType;

@Service
public interface TransmissionTypeService {
	void insertDefaultTransmissionType();
		
	void disabledTranmissionType(TransmissionTypeName name);
	
	TransmissionType getActiveType(TransmissionTypeName name);

	List<TransmissionType> getListTransmissionType(boolean enabled);
}
