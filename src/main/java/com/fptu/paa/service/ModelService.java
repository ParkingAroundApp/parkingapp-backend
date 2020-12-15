package com.fptu.paa.service;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import com.fptu.paa.dto.ModelRegistrationDTO;

@Service
public interface ModelService {
	public ModelRegistrationDTO insertNewModel(ModelRegistrationDTO modelRegistration);

	public int initDefaultModels() throws FileNotFoundException;
}
