package com.fptu.paa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.constant.TransmissionTypeName;
import com.fptu.paa.entity.TransmissionType;

@Repository
public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long>{
	TransmissionType findByNameAndEnabled(TransmissionTypeName name, boolean enabled);
}
