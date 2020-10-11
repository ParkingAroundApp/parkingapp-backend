package com.fptu.paa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{

}
