package com.ceiba.parking.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Vehicle {
	@Id
	private String licencePlate;
	@Column
	private int typeVehicle;
	@Column
	private int cubicCentimeters;

	public Vehicle() {
		super();
	}

	public Vehicle(String licencePlate, int typeVehicle, int cubicCentimeters) {
		super();
		this.licencePlate = licencePlate;
		this.typeVehicle = typeVehicle;
		this.cubicCentimeters = cubicCentimeters;
	}

	public int getCubicCentimeters() {
		return cubicCentimeters;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public int getTypeVehicle() {
		return typeVehicle;
	}

	public void setCubicCentimeters(int cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public void setTypeVehicle(int typeVehicle) {
		this.typeVehicle = typeVehicle;
	}
}
