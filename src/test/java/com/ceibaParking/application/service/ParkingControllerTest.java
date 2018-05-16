package com.ceibaParking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import com.ceibaParking.application.business.ParkingRulesImpl;
import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.Motorcycle;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.domain.Vehicle;
import com.ceibaParking.application.exception.VehicleRegistrationException;
import com.ceibaParking.application.repository.CarRepositoryImpl;
import com.ceibaParking.application.repository.MotorcycleRepositoryImpl;
import com.ceibaParking.application.repository.jpa.MotorcycleRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingControllerTest implements ConstantMessageExceptions {

	@InjectMocks
	@Autowired
	ParkingController parkingController;
	@Mock
	MotorcycleRepository motorcycleRepositoryMock;
	@Mock
	CarRepositoryImpl carRepositoryMock;
	@Mock
	TicketController TicketController;
	@Mock
	ParkingRulesImpl parkingRulesMock;
	@Mock
	CarRepositoryImpl carRepositoryImpl;
	
	@Autowired
	CarRepositoryImpl carRepository;
	@Autowired
	MotorcycleRepositoryImpl motorcycleRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerCarService() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Car car = new Car("MMY000");
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));		
		parkingController.registerCar(request);
		assertTrue(carRepository.existsById(car.getLicencePlate()));
	}
	
	@Test
	public void registerMotorcycleService() throws ParseException {
		// Arrange
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Motorcycle motorcycle = new Motorcycle("XYZ123A",125);
		RequestRegister request = new RequestRegister(motorcycle, formatter.parse(startDate));
		parkingController.registerMotorcycle(request);
		assertTrue(motorcycleRepository.existsById(motorcycle.getLicencePlate()));
	}

	@Test
	public void noLawfulDayRegisterCar() {
		// Arrange

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "26-05-2017 09:39";
			Car car = new Car("AAA000");
			RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
			Mockito.doThrow(new VehicleRegistrationException(NO_LAWFUL_DAY)).when(parkingRulesMock).validateRegister((Vehicle)Mockito.any(),(Date) Mockito.any());
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
			RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
			Mockito.doThrow(new VehicleRegistrationException(NO_PLACES_AVAILABLES)).when(parkingRulesMock).validateRegister((Vehicle)Mockito.any(),(Date) Mockito.any());
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
		Mockito.when(TicketController.validateTicket((Ticket)Mockito.any())).thenReturn(ticket);
		assertEquals(new BigDecimal(1000), parkingController.solicitudeRetireCar(ticket, formatter.parse(endDate)).getTotalToPay());
	}
	
	@Test
	public void retireCarOneDaysThreeHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "16-05-2018 12:40";
		Car car = new Car("MMY000");
		Ticket ticket = new Ticket(car,formatter.parse(startDate));
		Mockito.when(TicketController.validateTicket((Ticket)Mockito.any())).thenReturn(ticket);
		assertEquals(new BigDecimal(11000),parkingController.solicitudeRetireCar(ticket, formatter.parse(endDate)).getTotalToPay() );
	}
	
	@Test
	public void retireCarTenHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 19:40";
		Car car = new Car("MMY000");
		Ticket ticket = new Ticket(car,formatter.parse(startDate));
		Mockito.when(TicketController.validateTicket((Ticket)Mockito.any())).thenReturn(ticket);
		assertEquals(new BigDecimal(8000), parkingController.solicitudeRetireCar(ticket, formatter.parse(endDate)).getTotalToPay() );
	}
	
	@Test
	public void retireMotorcycleTenHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 10:40";
		Motorcycle moto = new Motorcycle("ABC123A",550);
		Ticket ticket = new Ticket(moto,formatter.parse(startDate));
		Mockito.when(TicketController.validateTicket((Ticket)Mockito.any())).thenReturn(ticket);
		assertEquals(new BigDecimal(2500), parkingController.solicitudeRetireMotorcycle(ticket, formatter.parse(endDate)).getTotalToPay() );
	}
}
