package com.ceibaParking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.Motorcycle;

@SpringBootTest
@RunWith(SpringRunner.class)
public class parkingServiceTest {
	@Autowired
	ParkingService parkingService;
	@Test
	public void registerCarService() {
		// Arrange
		Car car = Car.builder()
				.licencePlate("MMY000")
				.startTime(new Date())
				.endTime(new Date()).build();
		// Act
		boolean registerCar = parkingService.registerCar(car);
		// Assert
		assertTrue(registerCar);
		assertEquals(car.getLicencePlate(), parkingService.findByPlate("").getLicencePlate());
	}

	@Test
	public void registerMotorcycleService() {
		// Arrange
		Motorcycle motorcycle = Motorcycle.builder()
				.licencePlate("MMY000A")
				.startTime(new Date())
				.endTime(new Date())
				.cubicCentimeters(125).build();
		boolean registerCar = parkingService.registerMotorcycle(motorcycle);
		// Assert
		assertTrue(registerCar);
	}

}
