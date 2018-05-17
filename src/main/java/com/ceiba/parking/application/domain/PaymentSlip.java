package com.ceiba.parking.application.domain;

import java.math.BigDecimal;

public class PaymentSlip {
	private Vehicle vehicle;
	private BigDecimal totalToPay;
	private String timeSpent;
	

	public PaymentSlip() {

	}

	public PaymentSlip(BigDecimal totalToPay, String timeSpent) {
		super();
		this.totalToPay = totalToPay;
		this.timeSpent = timeSpent;
	}

	public BigDecimal getTotalToPay() {
		return totalToPay;
	}

	public void setTotalToPay(BigDecimal totalToPay) {
		this.totalToPay = totalToPay;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
