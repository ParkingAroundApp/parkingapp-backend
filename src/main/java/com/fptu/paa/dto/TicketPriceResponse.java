package com.fptu.paa.dto;

import com.fptu.paa.entity.Fare;

public class TicketPriceResponse {
	private Fare fare;
	private String totalPrice;

	public TicketPriceResponse() {

	}

	public Fare getFare() {
		return fare;
	}

	public void setFare(Fare fare) {
		this.fare = fare;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
