package com.ceibaParking.application.domain;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class RequestRegister {
	
	public RequestRegister(Motorcycle motorcycle) {
		super();
		this.motorcycle = motorcycle;
		this.startTime = new Date();
	}

	public RequestRegister(Car car) {
		super();
		this.car = car;
		this.startTime = new Date();
	}

	public RequestRegister() {
		
	}
	
	public RequestRegister(Car car, Date startTime) {
		super();
		this.car = car;
		this.startTime = startTime;
	}

	public RequestRegister(Motorcycle motorcycle, Date startTime) {
		super();
		this.motorcycle = motorcycle;
		this.startTime = startTime;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	private Car car;
	private Motorcycle motorcycle;
	private Date startTime;

}
