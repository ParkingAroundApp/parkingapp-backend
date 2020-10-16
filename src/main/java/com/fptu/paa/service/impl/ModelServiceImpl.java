package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fptu.paa.entity.Model;
import com.fptu.paa.service.ModelService;

@Service
@Transactional
public class ModelServiceImpl  implements ModelService{

	@Override
	public Model createNewModel(String brandName, String modelCode, String transType, String volume) {
		// TODO Auto-generated method stub
		return null;
	}

}
