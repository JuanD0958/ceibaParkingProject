package com.ceibaParking.application.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.Motorcycle;
import com.ceibaParking.application.repository.CarRepository;
import com.ceibaParking.application.repository.MotorcycleRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@RestController
public class ParkingService{
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private MotorcycleRepository motorcycleRepository;
	
	@PostMapping("/registerCar")
	public boolean registerCar(Car car) {
		car.setStartTime(new Date());
		carRepository.save(car);
		return true;
	}

	@PostMapping("/registerMotorcycle")
	public boolean registerMotorcycle(Motorcycle motorcicle) {
		motorcycleRepository.save(motorcicle);
		return true;
	}

	public Car findByPlate(String string) {
		return carRepository.findById("MMY000").get();
	}
}
