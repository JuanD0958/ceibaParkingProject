package com.ceibaParking.application.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Vehicle {
	@Id
	protected String licencePlate;
	@Column
	protected int typeVehicle;

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
}
