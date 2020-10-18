package com.fptu.paa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.constant.ModelStatus;
import com.fptu.paa.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{
	Model findModelById (Long id);
	List<Model> findModelByStatus(ModelStatus status);
}
