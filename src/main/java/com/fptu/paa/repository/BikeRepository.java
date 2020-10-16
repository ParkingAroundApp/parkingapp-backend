package com.fptu.paa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.entity.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long>{

}
