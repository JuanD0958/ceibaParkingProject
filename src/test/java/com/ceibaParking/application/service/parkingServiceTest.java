package com.ceibaParking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceibaParking.application.business.ParkingRulesImpl;
import com.ceibaParking.application.domain.Car;

@SpringBootTest
@RunWith(SpringRunner.class)
public class parkingServiceTest {
	@Autowired
	ParkingService parkingService;
	@Autowired
	ParkingRulesImpl parkingSpot;
	@Test
	public void registerCarService() throws ParseException {
		// Arrange
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Car car = new Car("MMY000",formatter.parse(startDate),new Date());
		// Act
		boolean registerCar = parkingService.registerCar(car);
		//Mockito.when(parkingService.registerCar(car)).thenReturn(true);
		// Assert
		assertTrue(registerCar);
		assertEquals(car.getLicencePlate(), parkingService.findByPlate("MMY000").getLicencePlate());
	}

//	@Test
//	public void registerMotorcycleService() {
//		// Arrange
//		
//		Motorcycle motorcycle = Motorcycle.builder()
//				.licencePlate("MMY000A")
//				.startTime(new Date())
//				.endTime(new Date())
//				.cubicCentimeters(125).build();
//		Mockito.when(parkingService.registerMotorcycle(motorcycle)).thenReturn(true);
//		boolean registerCar = parkingService.registerMotorcycle(motorcycle);
//		// Assert
//		assertTrue(registerCar);
//	}

}
