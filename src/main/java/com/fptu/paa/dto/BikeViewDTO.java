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
	private UserViewDTO userViewDTO;
	private Model model;
	private String afterCertificateImage;
	private String frontCertificateImage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getChassisNum() {
		return chassisNum;
	}
	public void setChassisNum(String chassisNum) {
		this.chassisNum = chassisNum;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public UserViewDTO getUserViewDTO() {
		return userViewDTO;
	}
	public void setUserViewDTO(UserViewDTO userViewDTO) {
		this.userViewDTO = userViewDTO;
	}
	public String getAfterCertificateImage() {
		return afterCertificateImage;
	}
	public void setAfterCertificateImage(String afterCertificateImage) {
		this.afterCertificateImage = afterCertificateImage;
	}
	public String getFrontCertificateImage() {
		return frontCertificateImage;
	}
	public void setFrontCertificateImage(String frontCertificateImage) {
		this.frontCertificateImage = frontCertificateImage;
	}
	
	
}