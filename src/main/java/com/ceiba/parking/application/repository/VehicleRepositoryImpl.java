package com.ceiba.parking.application.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.repository.jpa.VehicleRepository;

@Component
public class VehicleRepositoryImpl {
	@Autowired
	VehicleRepository vehicleRepository;

	@Transactional
	public void registerVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	public Vehicle findCarByPlate(String plate) {
		Optional<Vehicle> value = vehicleRepository.findById(plate);
		if (value.isPresent()) {
			return value.get();
		}
		return null;
	}

	public int numberOfCarsParked() {
		return vehicleRepository.findAllCars().size();
	}

	public int numberOfMotorcyclesParked() {
		return vehicleRepository.findAllMotorcycles().size();
	}
}
