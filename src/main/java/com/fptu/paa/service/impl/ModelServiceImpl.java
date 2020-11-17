package com.fptu.paa.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.dto.ModelRegistrationDTO;
import com.fptu.paa.entity.Model;
import com.fptu.paa.entity.TransmissionType;
import com.fptu.paa.repository.ModelRepository;
import com.fptu.paa.repository.TransmissionTypeRepository;
import com.fptu.paa.service.ModelService;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {
	@Autowired
	ModelRepository modelRepository;
	@Autowired
	TransmissionTypeRepository transmissionTypeRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public ModelRegistrationDTO insertNewModel(ModelRegistrationDTO modelRegistration) {
		if (modelRegistration != null) {
			Model model = modelRepository.findModelByBrandNameAndModelCodeAndVolume(modelRegistration.getBrandName(),
					modelRegistration.getModelCode(), modelRegistration.getVolume());
			if (model == null) {
				model = modelMapper.map(modelRegistration, Model.class);
				TransmissionType transmissionType = transmissionTypeRepository
						.findByNameAndEnabled(modelRegistration.getTypeName(), true);
				if (transmissionType != null) {
					model.setTransmissionType(transmissionType);
					model = modelRepository.save(model);
					return modelRegistration;
				}
			}
		}
		return null;
	}

}
