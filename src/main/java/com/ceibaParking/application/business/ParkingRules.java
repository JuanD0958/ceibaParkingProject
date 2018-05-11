package com.ceibaParking.application.business;

import java.util.Date;

import com.ceibaParking.application.domain.Vehicle;

public interface ParkingRules {
	public int CAR_HOUR_COST = 2000;
	public int MOTORCYCLE_HOUR_COST = 2000;
	public boolean validateRegister(Vehicle vehicle, Date starTime);
}
