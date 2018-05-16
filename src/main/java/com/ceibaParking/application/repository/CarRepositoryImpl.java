package com.ceibaParking.application.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.repository.jpa.CarRepository;

@Component
public class CarRepositoryImpl {

	@Autowired
	CarRepository carRepository;

	public CarRepositoryImpl() {

	}

	public List<Car> findAllCars() {
		return carRepository.findAll();
	}

	public void registerCar(Car car) {
		carRepository.save(car);
	}

	public Car findCarByPlate(String plate) {
		return carRepository.findById(plate).get();
	}
		
	public int numberOfCarsParked() {
		return carRepository.findAll().size();
	}
	
	public boolean existsById(String licencePlate) {
		return carRepository.existsById(licencePlate);
	}
}
