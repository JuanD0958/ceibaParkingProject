package com.ceibaParking.application.service;

import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.Motorcycle;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
public class ParkingService {
	public boolean registerCar(Car car) {
		return true;
	}

	public boolean registerMotorcycle(Motorcycle motorcicle) {
		return true;
	}
}
