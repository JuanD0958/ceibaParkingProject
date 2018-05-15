package com.ceibaParking.application.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.ceibaParking.application.business.CostCalculator;
import com.ceibaParking.application.constants.ConstantTypeVehicle;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE")
public class Motorcycle extends Vehicle implements ConstantTypeVehicle, CostCalculator {
	@Column
	public int cubicCentimeters;

	public Motorcycle() {
		typeVehicle = 2;
	}

	public Motorcycle(String licencePlate, int cubicCentimeters) {
		this.cubicCentimeters = cubicCentimeters;
		this.licencePlate = licencePlate;
		this.typeVehicle = TYPE_MOTORCYCLE;
	}

	@Override
	public double getCost(long parkingHours) {
		// TODO Auto-generated method stub
		return 0;
	}

}
