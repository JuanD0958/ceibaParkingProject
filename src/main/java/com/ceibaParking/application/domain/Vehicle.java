package com.ceibaParking.application.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
	protected String licencePlate;	
	protected BigDecimal costFactor;
	protected int typeVehicle;
}
