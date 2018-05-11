package com.ceibaParking.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceibaParking.application.business.ParkingRulesImpl;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.Motorcycle;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.repository.CarRepository;
import com.ceibaParking.application.repository.MotorcycleRepository;


@Service
public class ParkingController implements ConstantTypeVehicle{
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private MotorcycleRepository motorcycleRepository;
	@Autowired
	ParkingRulesImpl parkingRulesImpl;
			
	
	public boolean registerCar(RequestRegister requestRegister) {		
		if(parkingRulesImpl.validateRegister(requestRegister.getVehicle(),requestRegister.getStartTime())) {
			carRepository.save((Car)requestRegister.getVehicle());			
		}
		return true;
	}


	public boolean registerMotorcycle(RequestRegister requestRegister) {
		if(parkingRulesImpl.validateRegister(requestRegister.getVehicle(),requestRegister.getStartTime())) {
			motorcycleRepository.save((Motorcycle)requestRegister.getVehicle());			
		}
		return true;
	}

	public Car findByPlate(String string) {
		return carRepository.findById("MMY000").get();
	}
}
