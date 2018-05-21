package com.ceiba.parking.application.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class VehicleDTO {
	private String licencePlate;
	private int typeVehicle;
	private Date starDate;
	public VehicleDTO() {
		super();
	}
	public VehicleDTO(String licencePlate, Date starDate, int typeVehicle) {
		super();
		this.licencePlate = licencePlate;
		this.typeVehicle = typeVehicle;
		this.starDate = starDate;
	}
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
	public Date getStarDate() {
		return starDate;
	}
	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}
}
