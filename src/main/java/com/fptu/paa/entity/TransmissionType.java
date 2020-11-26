package com.fptu.paa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fptu.paa.constant.TransmissionTypeName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TransmissionTypes", schema = "parkingdb")
public class TransmissionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 16)
	@Enumerated(EnumType.STRING)
	private TransmissionTypeName name;
	
	@Column(name = "note", length = 16)
	private String note;

	@Column(name = "enabled")
	private boolean enabled = true;

}
