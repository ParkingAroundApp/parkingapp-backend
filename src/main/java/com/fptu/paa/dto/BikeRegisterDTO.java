package com.fptu.paa.dto;

import com.fptu.paa.entity.Model;

public class BikeRegisterDTO {
	private String ownerName;
	private String licensePlate;
	private String chassisNum;
	private String color;
	private Long model_id;
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getModel_id() {
		return model_id;
	}
	public void setModel_id(Long model_id) {
		this.model_id = model_id;
	}
	public String getChassisNum() {
		return chassisNum;
	}
	public void setChassisNum(String chassisNum) {
		this.chassisNum = chassisNum;
	}
}
