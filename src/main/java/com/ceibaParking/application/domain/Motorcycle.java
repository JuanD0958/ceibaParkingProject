package com.ceibaParking.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ceibaParking.application.constants.ConstantTypeVehicle;


@Entity
public class Motorcycle extends Vehicle implements ConstantTypeVehicle{
	@Column
	public int cubicCentimeters;	
	
	public Motorcycle() {
    	typeVehicle = 2;
    }
	
	public Motorcycle(String licencePlate,Date startTime,Date endTime,int cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
		this.licencePlate= licencePlate;
		this.startTime= startTime;
		this.endTime= endTime;
    	this.typeVehicle = TYPE_MOTORCYCLE;
    }	
	
}
