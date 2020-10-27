package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.ModelRegistrationDTO;

@Service
public interface ModelService {
//	public Model createNewModel(String brandName,String modelCode, String transType, String volume);
	public ModelRegistrationDTO insertNewModel(ModelRegistrationDTO modelRegistration);
}
