package com.ceiba.parking.application.domain;

import java.math.BigDecimal;

public class PaymentSlip {
	private Car car;
	private Motorcycle motorcycle;
	private BigDecimal totalToPay;
	private String timeSpent;
	
	public PaymentSlip() {
		
	}
	
	public PaymentSlip(Car car, BigDecimal totalToPay, String timeSpent) {
		super();
		this.car = car;
		this.totalToPay = totalToPay;
		this.timeSpent = timeSpent;
	}
	public PaymentSlip(Motorcycle motorcycle, BigDecimal totalToPay, String timeSpent) {
		super();
		this.motorcycle = motorcycle;
		this.totalToPay = totalToPay;
		this.timeSpent = timeSpent;
	}
	
	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Motorcycle getMotorcycle() {
		return motorcycle;
	}
	public void setMotorcycle(Motorcycle motorcycle) {
		this.motorcycle = motorcycle;
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
}
