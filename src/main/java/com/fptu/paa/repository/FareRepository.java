package com.fptu.paa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.entity.Fare;

@Repository
public interface FareRepository extends JpaRepository<Fare, Long>{

}
