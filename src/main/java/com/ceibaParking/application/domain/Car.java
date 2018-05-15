package com.ceibaParking.application.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import com.ceibaParking.application.constants.ConstantTypeVehicle;

@Component
@Entity
@DiscriminatorColumn(name = "TYPE")
public class Car extends Vehicle implements ConstantTypeVehicle{
	public Car() {

	}

	public Car(String licencePlate) {
		this.licencePlate = licencePlate;
		this.typeVehicle = TYPE_CAR;
	}
}
