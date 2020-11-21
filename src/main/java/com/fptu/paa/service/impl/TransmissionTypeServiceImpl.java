package com.fptu.paa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fptu.paa.constant.TransmissionTypeName;
import com.fptu.paa.entity.TransmissionType;
import com.fptu.paa.repository.TransmissionTypeRepository;
import com.fptu.paa.service.TransmissionTypeService;

@Service
public class TransmissionTypeServiceImpl implements TransmissionTypeService {
	@Autowired
	TransmissionTypeRepository transRepo;

	@Override
	public void insertDefaultTransmissionType() {
		if (transRepo.findAll().isEmpty()) {
			transRepo.save(new TransmissionType(1L, TransmissionTypeName.XE_DAP, true));
			transRepo.save(new TransmissionType(2L, TransmissionTypeName.XE_GA, true));
			transRepo.save(new TransmissionType(3L, TransmissionTypeName.XE_SO, true));
		}
	}

	@Override
	public List<TransmissionType> getListTransmissionType(boolean enabled) {
		return transRepo.findByEnabled(enabled);
	}

	@Override
	public void disabledTranmissionType(TransmissionTypeName name) {
		TransmissionType type = transRepo.findByNameAndEnabled(name, true);
		if (type != null) {
			type.setEnabled(false);
			transRepo.save(type);
		}
	}

	@Override
	public TransmissionType getActiveType(TransmissionTypeName name) {
		return transRepo.findByNameAndEnabled(name, true);
	}

}
