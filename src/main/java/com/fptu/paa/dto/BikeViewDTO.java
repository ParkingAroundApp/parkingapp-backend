package com.fptu.paa.dto;

import com.fptu.paa.constant.BikeStatus;

public class BikeViewDTO {
	private Long id;
	private String ownerName;
	private String licensePlate;
	private String chassisNum;
	private String color;
	private BikeStatus status;
	private Long user_id;
	private Long model_id;
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getChassisNum() {
		return chassisNum;
	}
	public void setChassisNum(String chassisNum) {
		this.chassisNum = chassisNum;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getModel_id() {
		return model_id;
	}
	public void setModel_id(Long model_id) {
		this.model_id = model_id;
	}
	public BikeStatus getStatus() {
		return status;
	}
	public void setStatus(BikeStatus status) {
		this.status = status;
	}
}
