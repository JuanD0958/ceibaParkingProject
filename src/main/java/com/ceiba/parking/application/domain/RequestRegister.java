package com.ceiba.parking.application.domain;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class RequestRegister {

	public RequestRegister(Vehicle vehicle) {
		super();
		this.setVehicle(vehicle);
		this.startTime = new Date();
	}

	public RequestRegister() {

	}

	public RequestRegister(Vehicle vehicle, Date startTime) {
		super();
		this.setVehicle(vehicle);
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	private Vehicle vehicle;
	private Date startTime;

}
