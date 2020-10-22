package com.fptu.paa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.entity.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

	Bike findBikeById(Long id);

	List<Bike> findBikeByUser_id(Long user_id);

	List<Bike> findBikeByUser_idAndStatus(Long user_id, BikeStatus status);

	List<Bike> findBikeByUser_idAndEnabled(Long user_id, boolean enabled);

	List<Bike> findBikeByStatus(BikeStatus status);

	Bike findBikeByLicensePlate(String licensePlate);

	@Modifying
	@Query("Update Bike b SET b.enabled = false WHERE b.id = :id")
	void disableBike(Long id);
}
