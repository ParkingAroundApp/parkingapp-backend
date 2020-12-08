package com.fptu.paa.controller.request;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReportRequest {
	private String id;
	private String note;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd-HH:mm:ss:SSS", timezone = "GMT+07:00")
	private Date checkInTime;
	private String staffCheckOutID;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd-HH:mm:ss:SSS", timezone = "GMT+07:00")
	@Temporal(TemporalType.DATE)
	private Date reportTime;

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	private String reportBikeImage;
	private String reportFaceImage;

	public ReportRequest() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getStaffCheckOutID() {
		return staffCheckOutID;
	}

	public void setStaffCheckOutID(String staffCheckOutID) {
		this.staffCheckOutID = staffCheckOutID;
	}

	public String getReportBikeImage() {
		return reportBikeImage;
	}

	public void setReportBikeImage(String reportBikeImage) {
		this.reportBikeImage = reportBikeImage;
	}

	public String getReportFaceImage() {
		return reportFaceImage;
	}

	public void setReportFaceImage(String reportFaceImage) {
		this.reportFaceImage = reportFaceImage;
	}

}
