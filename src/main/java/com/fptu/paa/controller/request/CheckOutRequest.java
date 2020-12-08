package com.fptu.paa.controller.request;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckOutRequest {
	@NotEmpty
	private String id;
	@NotEmpty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd-HH:mm:ss:SSS",timezone = "GMT+07:00")
	@Temporal(TemporalType.DATE)
	private Date checkInTime;
	private String staffCheckOutID;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd-HH:mm:ss:SSS",timezone = "GMT+07:00")
	@Temporal(TemporalType.DATE)
	private Date checkOutTime;
	private String checkOutBikeImage;
	private String checkOutFaceImage;
	private String paymentType;
	private String price;
	private String fareID;
}
