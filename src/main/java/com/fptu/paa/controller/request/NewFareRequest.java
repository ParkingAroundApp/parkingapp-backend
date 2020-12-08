package com.fptu.paa.controller.request;

import java.math.BigDecimal;

import com.fptu.paa.constant.TransmissionTypeName;

public class NewFareRequest {

	private String startDay;

	private String endDay;

	private BigDecimal allDayCost;

	private int limitParkingTime;

	private BigDecimal initialDayCost;

	private String startNight;

	private String endNight;

	private BigDecimal initialNightCost;

	private boolean guest;

	private TransmissionTypeName typeName;

	public NewFareRequest() {

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
