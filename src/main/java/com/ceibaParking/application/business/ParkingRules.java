package com.ceibaParking.application.business;

import java.util.Date;

import com.ceibaParking.application.domain.Vehicle;

public interface ParkingRules {
	public int CAR_CAPACITY = 20;
	public int MOTORCYCLE_CAPACITY = 10;  
	public boolean validateRegister(Vehicle vehicle, Date starTime);	
	public void plateRestriction(Vehicle vehicle, Date startTime);
}
