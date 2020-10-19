package com.fptu.paa.dto;

import com.fptu.paa.constant.BikeStatus;
import com.fptu.paa.entity.Model;

public class BikeViewDTO {
	private Long id;
	private String ownerName;
	private String licensePlate;
	private String chassisNum;
	private String color;
	private BikeStatus status;
	private Long user_id;
	private Model model;
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

	public BikeStatus getStatus() {
		return status;
	}
	public void setStatus(BikeStatus status) {
		this.status = status;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
}
