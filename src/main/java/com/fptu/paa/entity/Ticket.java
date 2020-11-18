package com.fptu.paa.entity;

import java.util.Arrays;
import java.util.Objects;

import com.owlike.genson.annotation.JsonProperty;

public class Ticket {
	private String bikeID;

	private String licensePlate;

	private String staffCheckInID;

	private String checkinTime;

	private String staffCheckOutID;

	private String checkoutTime;

	private String nfcNumber;

	private String paymentType;

	private String[] checkinImages;

	private String[] checkoutImages;

	private String status;

	private String note;

	private String type;

	public Ticket() {

	}

	public Ticket(@JsonProperty("licensePlate") String licensePlate, @JsonProperty("bikeID") String bikeID,
			@JsonProperty("staffCheckInID") String staffCheckInID, @JsonProperty("checkinTime") String checkinTime,
			@JsonProperty("nfcNumber") String nfcNumber, @JsonProperty("checkinImages") String[] checkinImages,
			@JsonProperty("status") String status) {
		this.licensePlate = licensePlate;
		this.bikeID = bikeID;
		this.staffCheckInID = staffCheckInID;
		this.checkinTime = checkinTime;
		this.nfcNumber = nfcNumber;
		this.checkinImages = checkinImages;
		this.status = status;
		this.type = "ticket";
	}

	public Ticket(@JsonProperty("bikeID") String bikeID, @JsonProperty("licensePlate") String licensePlate,
			@JsonProperty("staffCheckInID") String staffCheckInID, @JsonProperty("checkinTime") String checkinTime,
			@JsonProperty("staffCheckOutID") String staffCheckOutID, @JsonProperty("checkoutTime") String checkoutTime,
			@JsonProperty("nfcNumber") String nfcNumber, @JsonProperty("paymentType") String paymentType,
			@JsonProperty("checkinImages") String[] checkinImages,
			@JsonProperty("checkoutImages") String[] checkoutImages, @JsonProperty("status") String status,
			@JsonProperty("note") String note, @JsonProperty("type") String type) {
		this.bikeID = bikeID;
		this.licensePlate = licensePlate;
		this.staffCheckInID = staffCheckInID;
		this.checkinTime = checkinTime;
		this.staffCheckOutID = staffCheckOutID;
		this.checkoutTime = checkoutTime;
		this.nfcNumber = nfcNumber;
		this.paymentType = paymentType;
		this.checkinImages = checkinImages;
		this.checkoutImages = checkoutImages;
		this.status = status;
		this.note = note;
		this.type = type;
	}

	public String getBikeID() {
		return bikeID;
	}

	public void setBikeID(String bikeID) {
		this.bikeID = bikeID;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getStaffCheckInID() {
		return staffCheckInID;
	}

	public void setStaffCheckInID(String staffCheckInID) {
		this.staffCheckInID = staffCheckInID;
	}

	public String getCheckinTime() {
		return checkinTime;
	}

	public void setCheckinTime(String checkinTime) {
		this.checkinTime = checkinTime;
	}

	public String getStaffCheckOutID() {
		return staffCheckOutID;
	}

	public void setStaffCheckOutID(String staffCheckOutID) {
		this.staffCheckOutID = staffCheckOutID;
	}

	public String getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(String checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public String getNfcNumber() {
		return nfcNumber;
	}

	public void setNfcNumber(String nfcNumber) {
		this.nfcNumber = nfcNumber;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String[] getCheckinImages() {
		return checkinImages;
	}

	public void setCheckinImages(String[] checkinImages) {
		this.checkinImages = checkinImages;
	}

	public String[] getCheckoutImages() {
		return checkoutImages;
	}

	public void setCheckoutImages(String[] checkoutImages) {
		this.checkoutImages = checkoutImages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Ticket{" + "bikeID='" + bikeID + '\'' + ", licensePlate='" + licensePlate + '\'' + ", staffCheckInID='"
				+ staffCheckInID + '\'' + ", checkinTime='" + checkinTime + '\'' + ", staffCheckOutID='"
				+ staffCheckOutID + '\'' + ", checkoutTime='" + checkoutTime + '\'' + ", nfcNumber='" + nfcNumber + '\''
				+ ", paymentType='" + paymentType + '\'' + ", checkinImages=" + Arrays.toString(checkinImages)
				+ ", checkoutImages=" + Arrays.toString(checkoutImages) + ", status='" + status + '\'' + ", note='"
				+ note + '\'' + ", type='" + type + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ticket ticket = (Ticket) o;
		return Objects.equals(getBikeID(), ticket.getBikeID()) && getLicensePlate().equals(ticket.getLicensePlate())
				&& getStaffCheckInID().equals(ticket.getStaffCheckInID())
				&& getCheckinTime().equals(ticket.getCheckinTime())
				&& Objects.equals(getStaffCheckOutID(), ticket.getStaffCheckOutID())
				&& Objects.equals(getCheckoutTime(), ticket.getCheckoutTime())
				&& Objects.equals(getNfcNumber(), ticket.getNfcNumber())
				&& getPaymentType().equals(ticket.getPaymentType())
				&& Arrays.equals(getCheckinImages(), ticket.getCheckinImages())
				&& Arrays.equals(getCheckoutImages(), ticket.getCheckoutImages())
				&& getStatus().equals(ticket.getStatus()) && Objects.equals(getNote(), ticket.getNote())
				&& getType().equals(ticket.getType());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getBikeID(), getLicensePlate(), getStaffCheckInID(), getCheckinTime(),
				getStaffCheckOutID(), getCheckoutTime(), getNfcNumber(), getPaymentType(), getStatus(), getNote(),
				getType());
		result = 31 * result + Arrays.hashCode(getCheckinImages());
		result = 31 * result + Arrays.hashCode(getCheckoutImages());
		return result;
	}
}