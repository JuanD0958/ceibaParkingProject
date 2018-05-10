package com.ceibaParking.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Motorcycle extends Vehicle{
	public int cubicCentimeters;
	public Motorcycle() {
    	typeVehicle = 2;
    }
	public Motorcycle(int cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
    	typeVehicle = 2;
    }
	
	
}
