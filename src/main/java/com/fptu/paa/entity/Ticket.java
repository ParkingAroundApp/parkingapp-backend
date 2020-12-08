package com.fptu.paa.entity;

import java.util.Arrays;
import java.util.Objects;

import com.owlike.genson.annotation.JsonProperty;
public class Ticket {
    private String bikeID;

    private String bikeDetail;

    private String licensePlate;

    private String staffCheckInID;

    private String checkinTime;

    private String staffCheckOutID;

    private String checkoutTime;

    private String reportTime;

    private String nfcNumber;

    private String paymentType;

    private String[] checkinImages;

    private String[] checkoutImages;

    private String[] reportImages;

    private String note;

    private String status;

    private String type;

    public Ticket() {

    }

    public Ticket(@JsonProperty("licensePlate") String licensePlate, @JsonProperty("bikeID") String bikeID, @JsonProperty("staffCheckInID") String staffCheckInID,
                  @JsonProperty("checkinTime") String checkinTime, @JsonProperty("nfcNumber") String nfcNumber,
                  @JsonProperty("checkinImages") String[] checkinImages, @JsonProperty("status") String status) {
        this.licensePlate = licensePlate;
        this.bikeID = bikeID;
        this.staffCheckInID = staffCheckInID;
        this.checkinTime = checkinTime;
        this.nfcNumber = nfcNumber;
        this.checkinImages = checkinImages;
        this.status = status;
        this.type = "ticket";
    }

    public Ticket(@JsonProperty("bikeID") String bikeID,@JsonProperty("bikeDetail") String bikeDetail,@JsonProperty("licensePlate") String licensePlate,
                  @JsonProperty("staffCheckInID")String staffCheckInID,@JsonProperty("checkinTime") String checkinTime,@JsonProperty("staffCheckOutID") String staffCheckOutID,
                  @JsonProperty("checkoutTime")String checkoutTime,@JsonProperty("reportTime") String reportTime,@JsonProperty("nfcNumber") String nfcNumber,
                  @JsonProperty("paymentType")String paymentType,@JsonProperty("checkinImages") String[] checkinImages,
                  @JsonProperty("checkoutImages")String[] checkoutImages,@JsonProperty("reportImages") String[] reportImages,
                  @JsonProperty("note")String note,@JsonProperty("status") String status,@JsonProperty("type") String type) {
        this.bikeID = bikeID;
        this.bikeDetail = bikeDetail;
        this.licensePlate = licensePlate;
        this.staffCheckInID = staffCheckInID;
        this.checkinTime = checkinTime;
        this.staffCheckOutID = staffCheckOutID;
        this.checkoutTime = checkoutTime;
        this.reportTime = reportTime;
        this.nfcNumber = nfcNumber;
        this.paymentType = paymentType;
        this.checkinImages = checkinImages;
        this.checkoutImages = checkoutImages;
        this.reportImages = reportImages;
        this.note = note;
        this.status = status;
        this.type = type;
    }

    public String getBikeID() {
        return bikeID;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public String getBikeDetail() {
        return bikeDetail;
    }

    public void setBikeDetail(String bikeDetail) {
        this.bikeDetail = bikeDetail;
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

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
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

    public String[] getReportImages() {
        return reportImages;
    }

    public void setReportImages(String[] reportImages) {
        this.reportImages = reportImages;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getBikeID(), ticket.getBikeID()) &&
                Objects.equals(getBikeDetail(), ticket.getBikeDetail()) &&
                getLicensePlate().equals(ticket.getLicensePlate()) &&
                getStaffCheckInID().equals(ticket.getStaffCheckInID()) &&
                getCheckinTime().equals(ticket.getCheckinTime()) &&
                Objects.equals(getStaffCheckOutID(), ticket.getStaffCheckOutID()) &&
                Objects.equals(getCheckoutTime(), ticket.getCheckoutTime()) &&
                Objects.equals(getReportTime(), ticket.getReportTime()) &&
                Objects.equals(getNfcNumber(), ticket.getNfcNumber()) &&
                getPaymentType().equals(ticket.getPaymentType()) &&
                Arrays.equals(getCheckinImages(), ticket.getCheckinImages()) &&
                Arrays.equals(getCheckoutImages(), ticket.getCheckoutImages()) &&
                Arrays.equals(getReportImages(), ticket.getReportImages()) &&
                Objects.equals(getNote(), ticket.getNote()) &&
                getStatus().equals(ticket.getStatus()) &&
                getType().equals(ticket.getType());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getBikeID(), getBikeDetail(), getLicensePlate(), getStaffCheckInID(), getCheckinTime(), getStaffCheckOutID(), getCheckoutTime(), getReportTime(), getNfcNumber(), getPaymentType(), getNote(), getStatus(), getType());
        result = 31 * result + Arrays.hashCode(getCheckinImages());
        result = 31 * result + Arrays.hashCode(getCheckoutImages());
        result = 31 * result + Arrays.hashCode(getReportImages());
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "bikeID='" + bikeID + '\'' +
                ", bikeDetail='" + bikeDetail + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", staffCheckInID='" + staffCheckInID + '\'' +
                ", checkinTime='" + checkinTime + '\'' +
                ", staffCheckOutID='" + staffCheckOutID + '\'' +
                ", checkoutTime='" + checkoutTime + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", nfcNumber='" + nfcNumber + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", checkinImages=" + Arrays.toString(checkinImages) +
                ", checkoutImages=" + Arrays.toString(checkoutImages) +
                ", reportImages=" + Arrays.toString(reportImages) +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}