package com.fptu.paa.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fptu.paa.constant.TransmissionTypeName;

public class NewFareSetting {
	private Timestamp startDay;

	private Timestamp endDay;
	
	private BigDecimal allDayCost;

	private BigDecimal initialDayCost;

	private Timestamp dayTurnDuration;

	private Timestamp dayOverTurnTime;

	private BigDecimal dayOverCost;

	private Timestamp startNight;

	private Timestamp endNight;

	private BigDecimal initialNightCost;

	private Timestamp nightTurnDuration;

	private Timestamp nightOverTurnTime;

	private BigDecimal nightOverCost;

	private Timestamp createDate;

	private Timestamp expirationDate;

	private boolean eTicket;
	
	private TransmissionTypeName typeName;
	
	public NewFareSetting() {
		
	}

	public Timestamp getStartDay() {
		return startDay;
	}

	public void setStartDay(Timestamp startDay) {
		this.startDay = startDay;
	}

	public Timestamp getEndDay() {
		return endDay;
	}

	public void setEndDay(Timestamp endDay) {
		this.endDay = endDay;
	}

	public BigDecimal getAllDayCost() {
		return allDayCost;
	}

	public void setAllDayCost(BigDecimal allDayCost) {
		this.allDayCost = allDayCost;
	}

	public BigDecimal getInitialDayCost() {
		return initialDayCost;
	}

	public void setInitialDayCost(BigDecimal initialDayCost) {
		this.initialDayCost = initialDayCost;
	}

	public Timestamp getDayTurnDuration() {
		return dayTurnDuration;
	}

	public void setDayTurnDuration(Timestamp dayTurnDuration) {
		this.dayTurnDuration = dayTurnDuration;
	}

	public Timestamp getDayOverTurnTime() {
		return dayOverTurnTime;
	}

	public void setDayOverTurnTime(Timestamp dayOverTurnTime) {
		this.dayOverTurnTime = dayOverTurnTime;
	}

	public BigDecimal getDayOverCost() {
		return dayOverCost;
	}

	public void setDayOverCost(BigDecimal dayOverCost) {
		this.dayOverCost = dayOverCost;
	}

	public Timestamp getStartNight() {
		return startNight;
	}

	public void setStartNight(Timestamp startNight) {
		this.startNight = startNight;
	}

	public Timestamp getEndNight() {
		return endNight;
	}

	public void setEndNight(Timestamp endNight) {
		this.endNight = endNight;
	}

	public BigDecimal getInitialNightCost() {
		return initialNightCost;
	}

	public void setInitialNightCost(BigDecimal initialNightCost) {
		this.initialNightCost = initialNightCost;
	}

	public Timestamp getNightTurnDuration() {
		return nightTurnDuration;
	}

	public void setNightTurnDuration(Timestamp nightTurnDuration) {
		this.nightTurnDuration = nightTurnDuration;
	}

	public Timestamp getNightOverTurnTime() {
		return nightOverTurnTime;
	}

	public void setNightOverTurnTime(Timestamp nightOverTurnTime) {
		this.nightOverTurnTime = nightOverTurnTime;
	}

	public BigDecimal getNightOverCost() {
		return nightOverCost;
	}

	public void setNightOverCost(BigDecimal nightOverCost) {
		this.nightOverCost = nightOverCost;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean iseTicket() {
		return eTicket;
	}

	public void seteTicket(boolean eTicket) {
		this.eTicket = eTicket;
	}

	public TransmissionTypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(TransmissionTypeName typeName) {
		this.typeName = typeName;
	}
	
	
}
