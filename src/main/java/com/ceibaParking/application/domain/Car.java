package com.ceibaParking.application.domain;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.ceibaParking.application.constants.ConstantTypeVehicle;

@Component
@Entity
public class Car extends Vehicle implements ConstantTypeVehicle{	
	public Car() {
		
	}
	
	public Car(String licencePlate,Date startTime,Date endTime) {
		this.licencePlate= licencePlate;
		this.startTime= startTime;
		this.endTime= endTime;
    	this.typeVehicle = TYPE_CAR;
    }
}
