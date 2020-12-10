package com.fptu.paa.controller.request;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fptu.paa.constant.TransmissionTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckInRequest {
	@NotEmpty
	private String id;
	@NotEmpty
	private String licensePlate;
	private String staffCheckInID;
	@NotEmpty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd-HH:mm:ss:SSS", timezone = "GMT+07:00")
	@Temporal(TemporalType.DATE)
	private Date checkInTime;
	private String checkInBikeImage;
	private String checkInFaceImage;
	private TransmissionTypeName transmissionType;
}
