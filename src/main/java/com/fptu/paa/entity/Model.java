package com.fptu.paa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "model", schema = "parkingdb")
public class Model {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	
	@Column(name="name")
	private String name;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "transmissionType")
	private String transmissionType;
	
	@Column(name = "volume")
	private String volume;
	
	@Column(name = "enabled")
	private boolean enabled;
}
