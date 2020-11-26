package com.fptu.paa.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Fares", schema = "parkingdb")
public class Fare {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "startDay")
	private String startDay;

	@Column(name = "endDay")
	private String endDay;

	@Column(name = "allDayCost")
	private BigDecimal allDayCost;

	@Column(name = "limitParkingTime")
	private int limitParkingTime;

	@Column(name = "initialDayCost")
	private BigDecimal initialDayCost;

	@Column(name = "startNight")
	private String startNight;

	@Column(name = "endNight")
	private String endNight;

	@Column(name = "initialNightCost")
	private BigDecimal initialNightCost;

	@Column(name = "createDate")
	private Timestamp createDate;

	@Column(name = "guest")
	private boolean guest;

	@Column(name = "enabled")
	private boolean enabled = true;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "transmissionType_id")
	private TransmissionType transmissionType;
}
