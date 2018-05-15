package com.ceibaParking.application.domain;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class RequestRegister {
	private Vehicle vehicle;
	private Date startTime;

	public RequestRegister() {

	}

	public RequestRegister(Vehicle vehicle, Date startTime) {
		super();
		this.setVehicle(vehicle);
		this.setStartTime(startTime);
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
