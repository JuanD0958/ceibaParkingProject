package com.ceibaParking.application.business;

import com.ceibaParking.application.domain.Vehicle;

public interface ParkingRules {
	public boolean validateRegister(Vehicle vehicle);
}
