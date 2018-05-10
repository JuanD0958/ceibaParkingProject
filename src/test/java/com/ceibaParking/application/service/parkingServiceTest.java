package com.ceibaParking.application.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.Motorcycle;

public class parkingServiceTest {
	


	@Test
	public void registerCar() {
		// Arrange
		Car car = Car.builder().build();
		// Act
		ParkingService parkingService = ParkingService.builder().build();
		boolean registerCar = parkingService.registerCar(car);
		// Assert
		assertTrue(registerCar);
	}

	@Test
	public void registerMotorcycle() {
		// Arrange
		Motorcycle motorcycle = Motorcycle.builder().build();
		// Act
		ParkingService parkingService = ParkingService.builder().build();
		boolean registerCar = parkingService.registerMotorcycle(motorcycle);
		// Assert
		assertTrue(registerCar);
	}

}
