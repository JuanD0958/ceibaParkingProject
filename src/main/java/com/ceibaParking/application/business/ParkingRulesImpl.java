package com.ceibaParking.application.business;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.Vehicle;
import com.ceibaParking.application.exception.VehicleRegistrationException;
import com.ceibaParking.application.repository.CarRepository;
import com.ceibaParking.application.repository.MotorcycleRepository;

@Component
public class ParkingRulesImpl implements ParkingRules, ConstantTypeVehicle, ConstantMessageExceptions{
	
	@Autowired
	CarRepository carRepository;
	@Autowired
	MotorcycleRepository motorcycleRepository;
	@Override
	public boolean validateRegister(Vehicle vehicle, Date starTime) {
		boolean available = true;
		availableSpot(vehicle);
		plateRestriction(vehicle, starTime);
		return available;
	}

	public void availableSpot(Vehicle vehicle) {
		if(vehicle.getTypeVehicle()==TYPE_CAR && carRepository.findAll().size()>=CAR_HOUR_COST) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
		if(vehicle.getTypeVehicle()==TYPE_MOTORCYCLE && motorcycleRepository.findAll().size()<MOTORCYCLE_HOUR_COST) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
	}
	
	private void plateRestriction(Vehicle vehicle, Date startTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);
		if(vehicle.getLicencePlate().startsWith("A") && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			throw new VehicleRegistrationException(NO_LAWFUL_DAY);
		}
	}
}