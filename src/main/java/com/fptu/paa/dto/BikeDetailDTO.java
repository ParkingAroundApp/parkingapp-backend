package com.fptu.paa.dto;

public class BikeDetailDTO {
	private String model;
	private String ownerName;
	private String chassisNum;
	private String color;
	private String frontCertificateImage;
	private String afterCertificateImage;

	public BikeDetailDTO() {
		super();
	}

	public BikeDetailDTO(BikeViewDTO bike) {
		this.model = bike.getModel().getBrandName() + "_" + bike.getModel().getModelCode();
		this.ownerName = bike.getOwnerName();
		this.chassisNum = bike.getChassisNum();
		this.color = bike.getColor();
		this.frontCertificateImage = bike.getFrontCertificateImage();
		this.afterCertificateImage = bike.getAfterCertificateImage();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public String getFrontCertificateImage() {
		return frontCertificateImage;
	}

	public void setFrontCertificateImage(String frontCertificateImage) {
		this.frontCertificateImage = frontCertificateImage;
	}

	public String getAfterCertificateImage() {
		return afterCertificateImage;
	}

	public void setAfterCertificateImage(String afterCertificateImage) {
		this.afterCertificateImage = afterCertificateImage;
	}

}
