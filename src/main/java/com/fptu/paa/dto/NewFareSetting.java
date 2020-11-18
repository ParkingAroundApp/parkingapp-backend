package com.fptu.paa.dto;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.fptu.paa.constant.TransmissionTypeName;

public class NewFareSetting {

	private String startDay;

	private String endDay;

	private BigDecimal allDayCost;

	private int limitParkingTime;

	private BigDecimal initialDayCost;

	private int dayTurnDuration;

	private int dayOverTurnTime;

	private BigDecimal dayOverCost;

	private String startNight;

	private String endNight;

	private BigDecimal initialNightCost;

	private int nightTurnDuration;

	private int nightOverTurnTime;

	private BigDecimal nightOverCost;

	@Column(name = "guest")
	private boolean guest;

	private TransmissionTypeName typeName;

	public NewFareSetting() {

	}
	
	

	public NewFareSetting(String startDay, String endDay, BigDecimal allDayCost, int limitParkingTime,
			BigDecimal initialDayCost, int dayTurnDuration, int dayOverTurnTime, BigDecimal dayOverCost,
			String startNight, String endNight, BigDecimal initialNightCost, int nightTurnDuration,
			int nightOverTurnTime, BigDecimal nightOverCost, boolean guest, TransmissionTypeName typeName) {
		super();
		this.startDay = startDay;
		this.endDay = endDay;
		this.allDayCost = allDayCost;
		this.limitParkingTime = limitParkingTime;
		this.initialDayCost = initialDayCost;
		this.dayTurnDuration = dayTurnDuration;
		this.dayOverTurnTime = dayOverTurnTime;
		this.dayOverCost = dayOverCost;
		this.startNight = startNight;
		this.endNight = endNight;
		this.initialNightCost = initialNightCost;
		this.nightTurnDuration = nightTurnDuration;
		this.nightOverTurnTime = nightOverTurnTime;
		this.nightOverCost = nightOverCost;
		this.guest = guest;
		this.typeName = typeName;
	}



	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public BigDecimal getAllDayCost() {
		return allDayCost;
	}

	public void setAllDayCost(BigDecimal allDayCost) {
		this.allDayCost = allDayCost;
	}

	public int getLimitParkingTime() {
		return limitParkingTime;
	}

	public void setLimitParkingTime(int limitParkingTime) {
		this.limitParkingTime = limitParkingTime;
	}

	public BigDecimal getInitialDayCost() {
		return initialDayCost;
	}

	public void setInitialDayCost(BigDecimal initialDayCost) {
		this.initialDayCost = initialDayCost;
	}

	public int getDayTurnDuration() {
		return dayTurnDuration;
	}

	public void setDayTurnDuration(int dayTurnDuration) {
		this.dayTurnDuration = dayTurnDuration;
	}

	public int getDayOverTurnTime() {
		return dayOverTurnTime;
	}

	public void setDayOverTurnTime(int dayOverTurnTime) {
		this.dayOverTurnTime = dayOverTurnTime;
	}

	public BigDecimal getDayOverCost() {
		return dayOverCost;
	}

	public void setDayOverCost(BigDecimal dayOverCost) {
		this.dayOverCost = dayOverCost;
	}

	public String getStartNight() {
		return startNight;
	}

	public void setStartNight(String startNight) {
		this.startNight = startNight;
	}

	public String getEndNight() {
		return endNight;
	}

	public void setEndNight(String endNight) {
		this.endNight = endNight;
	}

	public BigDecimal getInitialNightCost() {
		return initialNightCost;
	}

	public void setInitialNightCost(BigDecimal initialNightCost) {
		this.initialNightCost = initialNightCost;
	}

	public int getNightTurnDuration() {
		return nightTurnDuration;
	}

	public void setNightTurnDuration(int nightTurnDuration) {
		this.nightTurnDuration = nightTurnDuration;
	}

	public int getNightOverTurnTime() {
		return nightOverTurnTime;
	}

	public void setNightOverTurnTime(int nightOverTurnTime) {
		this.nightOverTurnTime = nightOverTurnTime;
	}

	public BigDecimal getNightOverCost() {
		return nightOverCost;
	}

	public void setNightOverCost(BigDecimal nightOverCost) {
		this.nightOverCost = nightOverCost;
	}


	public boolean isGuest() {
		return guest;
	}

	public void setGuest(boolean guest) {
		this.guest = guest;
	}

	public TransmissionTypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(TransmissionTypeName typeName) {
		this.typeName = typeName;
	}
	
	

}
