package com.fptu.paa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.entity.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long> {
	Fare findFareByTransmissionType_idAndEnabledAndGuest(Long transmissionTypeID, boolean enabled, boolean guest);
	List<Fare> findFareByEnabledAndGuest(boolean enabled, boolean guest);
}
