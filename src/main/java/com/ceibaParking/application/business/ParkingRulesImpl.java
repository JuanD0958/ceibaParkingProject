package com.ceibaParking.application.business;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.Vehicle;
import com.ceibaParking.application.repository.CarRepository;
import com.ceibaParking.application.repository.MotorcycleRepository;

@Component
public class ParkingRulesImpl implements ParkingRules, ConstantTypeVehicle{
	
	@Autowired
	CarRepository carRepository;
	@Autowired
	MotorcycleRepository motorcycleRepository;
	@Override
	public boolean validateRegister(Vehicle vehicle) {
		boolean isAvailable = false;
		isAvailable = availableSpot(vehicle, isAvailable);
		isAvailable = plateRestriction(vehicle, isAvailable);
		return isAvailable;
	}

	private boolean availableSpot(Vehicle vehicle, boolean isAvailable) {
		if(vehicle.getTypeVehicle()==TYPE_CAR) {
			isAvailable = carRepository.findAll().size()<20;
		}
		if(vehicle.getTypeVehicle()==TYPE_MOTORCYCLE) {
			isAvailable = motorcycleRepository.findAll().size()<10;
		}
		return isAvailable;
	}
	
	private boolean plateRestriction(Vehicle vehicle, boolean isAvailable) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(vehicle.getStartTime());
		if(vehicle.getLicencePlate().startsWith("A") && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			isAvailable = false;
		}
		return isAvailable;
	}
}