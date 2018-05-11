package com.ceibaParking.application.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceibaParking.application.business.ParkingRulesImpl;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
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
public class ParkingService implements ConstantTypeVehicle{
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private MotorcycleRepository motorcycleRepository;
	@Autowired
	ParkingRulesImpl parkingRulesImpl;
			
	
	@PostMapping("/registerCar")
	public boolean registerCar(Car car) {
		car.setStartTime(new Date());
		if(parkingRulesImpl.validateRegister(car)) {
			carRepository.save(car);			
		}
		return true;
	}


	@PostMapping("/registerMotorcycle")
	public boolean registerMotorcycle(Motorcycle motorcycle) {
		if(parkingRulesImpl.validateRegister(motorcycle)) {
			motorcycleRepository.save(motorcycle);			
		}
		return true;
	}

	public Car findByPlate(String string) {
		return carRepository.findById("MMY000").get();
	}
}
