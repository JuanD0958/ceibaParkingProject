package com.ceibaParking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.repository.jpa.CarRepository;
import com.ceibaParking.application.repository.jpa.MotorcycleRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingControllerTest implements ConstantMessageExceptions {

	@InjectMocks
	@Autowired
	ParkingController parkingController;
	@Mock
	MotorcycleRepository motorcycleRepository;
	@Mock
	CarRepository carRepositoryMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerCarService() throws ParseException {
		// Arrange
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Car car = new Car("MMY000");
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		Mockito.when(carRepositoryMock.save(car)).thenReturn(car);
		parkingController.registerCar(request);
	}

	@Test
	public void noLawfulDayRegisterCar() {
		// Arrange

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "26-05-2017 09:39";
			Car car = new Car("AAA000");
			RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
			parkingController.registerCar(request);
			fail();
		} catch (Exception e) {
			assertEquals(NO_LAWFUL_DAY, e.getMessage());
		}
	}

	@Test
	public void noAvailablePlacesCar() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "11-05-2018 09:39";
			Car car = new Car("MMY000");
			List<Car> listCar = new ArrayList<>();
			for (int i = 0; i < 25; i++) {
				listCar.add(car);
			}
			RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
			Mockito.when(carRepositoryMock.findAll()).thenReturn(listCar);
			parkingController.registerCar(request);
			fail();
		} catch (Exception e) {
			assertEquals(NO_PLACES_AVAILABLES, e.getMessage());
		}
	}
	
	@Test
	public void retireCar() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 10:40";
		Car car = new Car("MMY000");
		Ticket ticket = new Ticket(car,formatter.parse(startDate));		
		assertEquals(1000, parkingController.retireCar(ticket, formatter.parse(endDate)));
	}
	
	@Test
	public void retireCarOneDaysThreeHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "16-05-2018 12:40";
		Car car = new Car("MMY000");
		Ticket ticket = new Ticket(car,formatter.parse(startDate));
		assertEquals(11000,parkingController.retireCar(ticket, formatter.parse(endDate)) );
	}
	
	@Test
	public void retireCarTenHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 19:40";
		Car car = new Car("MMY000");
		Ticket ticket = new Ticket(car,formatter.parse(startDate));
		assertEquals(8000, parkingController.retireCar(ticket, formatter.parse(endDate)) );
	}
}
