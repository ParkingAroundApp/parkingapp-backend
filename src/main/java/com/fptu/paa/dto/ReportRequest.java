package com.fptu.paa.dto;

public class ReportRequest {
	private String id;
	private String note;
	private String checkInTime;
	private String staffCheckOutID;
	private String reportTime;
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

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	public String getStaffCheckOutID() {
		return staffCheckOutID;
	}

	public void setStaffCheckOutID(String staffCheckOutID) {
		this.staffCheckOutID = staffCheckOutID;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
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
