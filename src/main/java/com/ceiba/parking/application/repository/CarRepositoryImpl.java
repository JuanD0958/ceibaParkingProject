package com.ceiba.parking.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.parking.application.domain.Car;
import com.ceiba.parking.application.repository.jpa.CarRepository;

@Component
public class CarRepositoryImpl {

	@Autowired
	CarRepository carRepository;

	public List<Car> findAllCars() {
		return carRepository.findAll();
	}

	public void registerCar(Car car) {
		carRepository.save(car);
	}

	public Car findCarByPlate(String plate) {
		Optional<Car> value = carRepository.findById(plate);
		if(value.isPresent()) {
			return value.get();			
		}
		return null;
	}
		
	public int numberOfCarsParked() {
		return carRepository.findAll().size();
	}
	
	public boolean existsById(String licencePlate) {
		return carRepository.existsById(licencePlate);
	}
}
