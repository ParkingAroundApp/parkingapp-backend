package com.fptu.paa.dto;

import com.fptu.paa.constant.TransmissionTypeName;

public class ModelRegistrationDTO {
	private String brandName;
	
	private String modelCode;
	
	private TransmissionTypeName typeName;
	
	private String volume;

	
	public ModelRegistrationDTO() {
		super();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public TransmissionTypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(TransmissionTypeName typeName) {
		this.typeName = typeName;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
}
