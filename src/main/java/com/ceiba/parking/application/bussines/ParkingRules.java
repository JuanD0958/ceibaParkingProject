package com.ceiba.parking.application.bussines;

import java.util.Date;

import com.ceiba.parking.application.domain.Vehicle;

public interface ParkingRules {
	public int CAR_CAPACITY = 200;
	public int MOTORCYCLE_CAPACITY = 10;  
	public boolean validateRegister(Vehicle vehicle, Date starTime);	
	public void plateRestriction(Vehicle vehicle, Date startTime);
}
