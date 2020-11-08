package com.fptu.paa.dto;

public class ReportRequest {
	private String id;
	private String checkInTime;
	private String ownerCheckOutID;
	private String reportTime;
	private String reportBikeImage;
	private String reportFaceImage;
	
	
	public ReportRequest() {
	}
	
	public ReportRequest(String id, String checkInTime, String ownerCheckOutID, String reportTime,
			String reportBikeImage, String reportFaceImage) {
		super();
		this.id = id;
		this.checkInTime = checkInTime;
		this.ownerCheckOutID = ownerCheckOutID;
		this.reportTime = reportTime;
		this.reportBikeImage = reportBikeImage;
		this.reportFaceImage = reportFaceImage;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getOwnerCheckOutID() {
		return ownerCheckOutID;
	}
	public void setOwnerCheckOutID(String ownerCheckOutID) {
		this.ownerCheckOutID = ownerCheckOutID;
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
