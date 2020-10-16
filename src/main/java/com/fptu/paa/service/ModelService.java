package com.fptu.paa.service;

import org.springframework.stereotype.Service;

import com.fptu.paa.entity.Model;

@Service
public interface ModelService {
	public Model createNewModel(String brandName,String modelCode, String transType, String volume);
}
