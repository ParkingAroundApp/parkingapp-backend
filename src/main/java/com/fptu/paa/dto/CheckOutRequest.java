package com.fptu.paa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckOutRequest {
	private String id;
	private String checkInTime;
	private String ownerCheckOutID;
	private String checkOutTime;
	private String checkOutBikeImage;
	private String checkOutFaceImage;
	private String paymentType;
}
