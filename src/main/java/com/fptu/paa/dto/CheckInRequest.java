package com.fptu.paa.dto;

import com.fptu.paa.constant.TransmissionTypeName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckInRequest {
	private String id;
	private String licensePlate;
	private String staffCheckInID;
	private String checkInTime;
	private String checkInBikeImage;
	private String checkInFaceImage;
	private TransmissionTypeName transmissionType;
}
