package com.ceibaParking.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass 
public abstract class Vehicle {
	@Id
	protected String licencePlate;
	@Column
	protected int typeVehicle;
	@Column
	protected Date startTime;
	@Column
	protected Date endTime;
	
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public int getTypeVehicle() {
		return typeVehicle;
	}
	public void setTypeVehicle(int typeVehicle) {
		this.typeVehicle = typeVehicle;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
