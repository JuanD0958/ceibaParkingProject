package com.ceibaParking.application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.domain.Motorcycle;
import com.ceibaParking.application.repository.jpa.MotorcycleRepository;

@Component
public class MotorcycleRepositoryImpl {
	@Autowired
	MotorcycleRepository motorcycleRepository;
	
	public void registerMotorcycle(Motorcycle motorcycle) {
		motorcycleRepository.save(motorcycle);
	}
	
	public int numberOfMotorcyclesParked() {
		return motorcycleRepository.findAll().size();
	}
	
	public boolean existsById(String licencePlate) {
		return motorcycleRepository.existsById(licencePlate);
	}
}
