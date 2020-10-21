package com.fptu.paa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckInRequest {
	private String bikeID;
	private String ownerCheckInID;
	private String checkInTime;
	private String checkInBikeImage;
	private String checkInFaceImage;
}