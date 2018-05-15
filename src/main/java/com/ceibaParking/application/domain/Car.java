package com.ceibaParking.application.domain;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.ceibaParking.application.business.CostCalculator;
import com.ceibaParking.application.constants.ConstantTypeVehicle;

@Component
@Entity
public class Car extends Vehicle implements ConstantTypeVehicle, CostCalculator {
	public Car() {

	}

	public Car(String licencePlate) {
		this.licencePlate = licencePlate;
		this.typeVehicle = TYPE_CAR;
	}

	@Override
	public double getCost(long parkingHours) {
		// TODO Auto-generated method stub
		return 0;
	}
}
