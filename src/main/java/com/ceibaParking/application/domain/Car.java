package com.ceibaParking.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Car extends Vehicle {
    
	public Car() {
    	typeVehicle = 1;
    }
}
