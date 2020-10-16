package com.fptu.paa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fptu.paa.constant.ModelStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Models", schema = "parkingdb")
public class Model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name="brandName", length = 30)
	private String brandName;
	
	@Column(name = "modelCode", length = 30)
	private String modelCode;
	
	@Column(name = "transmissionType", length = 16)
	private String transmissionType;
	
	@Column(name = "volume", length = 16)
	private String volume;
	
	@Column(name = "status", length = 16)
	@Enumerated(EnumType.STRING)
	private ModelStatus status;
}
