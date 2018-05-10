package com.ceibaParking.application.domain;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car extends Vehicle {
    
	public Car() {
    	typeVehicle = 1;
    }
	
	@Builder
	public Car(String licencePlate,int typeVehicle,Date startTime,Date endTime) {
		this.licencePlate= licencePlate;
		this.startTime= startTime;
		this.endTime= endTime;
    	typeVehicle = 1;
    }
}
