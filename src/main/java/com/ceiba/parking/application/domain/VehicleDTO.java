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
}
