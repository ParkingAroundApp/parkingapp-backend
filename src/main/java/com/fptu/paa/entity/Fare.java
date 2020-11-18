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
	private Timestamp startDay;

	@Column(name = "endDay")
	private Timestamp endDay;
	
	@Column(name = "allDayCost")
	private BigDecimal allDayCost;

	@Column(name = "initialDayCost")
	private BigDecimal initialDayCost;

	@Column(name = "dayTurnDuration")
	private Timestamp dayTurnDuration;

	@Column(name = "dayOverTurnTime")
	private Timestamp dayOverTurnTime;

	@Column(name = "dayOverCost")
	private BigDecimal dayOverCost;

	@Column(name = "startNight")
	private Timestamp startNight;

	@Column(name = "endNight")
	private Timestamp endNight;

	@Column(name = "initialNightCost")
	private BigDecimal initialNightCost;

	@Column(name = "nightTurnDuration")
	private Timestamp nightTurnDuration;

	@Column(name = "nightOverTurnTime")
	private Timestamp nightOverTurnTime;

	@Column(name = "nightOverCost")
	private BigDecimal nightOverCost;

	@Column(name = "createDate")
	private Timestamp createDate;

	@Column(name = "expirationDate")
	private Timestamp expirationDate;

	@Column(name = "guest")
	private boolean guest;

	@Column(name = "enabled")
	private boolean enabled = true;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "transmissionType_id")
	private TransmissionType transmissionType;
}
