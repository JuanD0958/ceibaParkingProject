package com.ceiba.parking.application.bussines;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.constants.ConstantTypeVehicle;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.exception.VehicleRegistrationException;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;

@Component
public class ParkingRulesImpl implements ParkingRules, ParkingPrices, ConstantTypeVehicle, ConstantMessageExceptions {
	@Autowired
	VehicleRepositoryImpl vehicleRepositoryImpl;
	
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
		if(vehicleRepositoryImpl.existsById(vehicle.getLicencePlate())) {
			throw new VehicleRegistrationException(VEHICLE_ALREADY_PARKED);
		}
	}
	
	public void availableSpot(Vehicle vehicle) {
		if (vehicle.getTypeVehicle() == TYPE_CAR && vehicleRepositoryImpl.numberOfCarsParked() >= CAR_CAPACITY) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
		if (vehicle.getTypeVehicle() == TYPE_MOTORCYCLE && vehicleRepositoryImpl.numberOfMotorcyclesParked() >= MOTORCYCLE_CAPACITY) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
	}

}
