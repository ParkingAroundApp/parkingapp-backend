package com.fptu.paa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fptu.paa.constant.NFCStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "NFCs", schema = "parkingdb")
public class NFC {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "serialNumber", unique = true)
	private String serialNumber;

	@Column(name = "status", length = 16)
	@Enumerated(EnumType.STRING)
	private NFCStatus status;

	@Column(name = "enabled")
	private boolean enabled = true;
}
