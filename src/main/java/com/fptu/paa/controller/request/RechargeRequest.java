package com.fptu.paa.controller.request;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RechargeRequest {
	private String userID;
	private String amount;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd-HH:mm:ss:SSS", timezone = "GMT+07:00")
	@Temporal(TemporalType.DATE)
	private Date createTime;

	public RechargeRequest() {

	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
