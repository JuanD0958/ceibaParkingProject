package com.ceibaParking.application.business;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.Vehicle;
import com.ceibaParking.application.exception.VehicleRegistrationException;
import com.ceibaParking.application.repository.CarRepositoryImpl;
import com.ceibaParking.application.repository.MotorcycleRepositoryImpl;

@Component
public class ParkingRulesImpl implements ParkingRules, ParkingPrices, ConstantTypeVehicle, ConstantMessageExceptions {
	@Autowired
	CarRepositoryImpl carRepositoryImpl;
	@Autowired
	MotorcycleRepositoryImpl motorcycleRepositoryImpl;
	@Override
	public boolean validateRegister(Vehicle vehicle, Date starTime) {
		boolean available = true;
		vehicleAlreadyParked(vehicle);
		availableSpot(vehicle);
		plateRestriction(vehicle, starTime);
		return available;
	}

	@Override
	public void plateRestriction(Vehicle vehicle, Date startTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);	
		if(vehicle.getLicencePlate().startsWith("A") && cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY ) {
			throw new VehicleRegistrationException(NO_LAWFUL_DAY);
		}
	}
	
	public void vehicleAlreadyParked(Vehicle vehicle) {
		if(carRepositoryImpl.existsById(vehicle.getLicencePlate())) {
			throw new VehicleRegistrationException(VEHICLE_ALREADY_PARKED);
		}
	}
	
	public void availableSpot(Vehicle vehicle) {
		if (vehicle.getTypeVehicle() == TYPE_CAR && carRepositoryImpl.numberOfCarsParked() > CAR_CAPACITY) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
		if (vehicle.getTypeVehicle() == TYPE_MOTORCYCLE && motorcycleRepositoryImpl.numberOfMotorcyclesParked() > MOTORCYCLE_CAPACITY) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
	}

}
