package com.ceiba.parking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.ceiba.parking.application.bussines.ParkingRulesImpl;
import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.exception.VehicleRegistrationException;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingControllerTest implements ConstantMessageExceptions {

	@InjectMocks
	@Autowired
	ParkingController parkingControllerMock;
	@Mock
	VehicleRepositoryImpl vehicleRepositoryMock;
	@Mock
	TicketController TicketControllerMock;
	@Mock
	ParkingRulesImpl parkingRulesMock;
	
	@Autowired
	ParkingController parkingController;
	@Autowired
	VehicleRepositoryImpl vehicleRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerCarVehicleService() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle car = new Vehicle("MMY000", 1, 1500);
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		parkingController.registerVehicle(request);
//		assertTrue(vehicleRepository.existsById(car.getLicencePlate()));
	}

	@Test
	public void registerMotorcycleVehicleService() throws ParseException {
		// Arrange
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle motorcycle = new Vehicle("XYZ123A", 2, 400);
		RequestRegister request = new RequestRegister(motorcycle, formatter.parse(startDate));
		parkingController.registerVehicle(request);
//		assertTrue(vehicleRepository.existsById(motorcycle.getLicencePlate()));
	}

	@Test
	public void noLawfulDayregisterVehicle() {
		// Arrange

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "26-05-2017 09:39";
			Vehicle vehicle = new Vehicle("AAA000", 1, 1200);
			RequestRegister request = new RequestRegister(vehicle, formatter.parse(startDate));
			Mockito.doThrow(new VehicleRegistrationException(NO_LAWFUL_DAY)).when(parkingRulesMock)
					.validateRegister((Vehicle) Mockito.any(), (Date) Mockito.any());
			parkingControllerMock.registerVehicle(request);
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
			Vehicle vehicle = new Vehicle("MMY000", 1, 1200);
			RequestRegister request = new RequestRegister(vehicle, formatter.parse(startDate));
			Mockito.doThrow(new VehicleRegistrationException(NO_PLACES_AVAILABLES)).when(parkingRulesMock)
					.validateRegister((Vehicle) Mockito.any(), (Date) Mockito.any());
			parkingControllerMock.registerVehicle(request);
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
		Vehicle vehicle = new Vehicle("MMY000", 1, 1200);
		Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
		Mockito.when(TicketControllerMock.validateTicket((Ticket) Mockito.any())).thenReturn(ticket);
		Mockito.when(vehicleRepositoryMock.findCarByPlate(ticket.getLicencePlate())).thenReturn(vehicle);
		assertEquals(new BigDecimal(1000),
				parkingControllerMock.solicitudeRetireVehicle(ticket, formatter.parse(endDate)).getCostParking());
	}

	@Test
	public void retireCarOneDaysThreeHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "16-05-2018 12:40";
		Vehicle vehicle = new Vehicle("MMY000", 1, 1200);
		Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
		Mockito.when(TicketControllerMock.validateTicket((Ticket) Mockito.any())).thenReturn(ticket);
		Mockito.when(vehicleRepositoryMock.findCarByPlate(ticket.getLicencePlate())).thenReturn(vehicle);
		assertEquals(new BigDecimal(11000),
				parkingControllerMock.solicitudeRetireVehicle(ticket, formatter.parse(endDate)).getCostParking());
	}

	@Test
	public void retireCarTenHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 19:40";
		Vehicle vehicle = new Vehicle("MMY000", 1, 1200);
		Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
		Mockito.when(TicketControllerMock.validateTicket((Ticket) Mockito.any())).thenReturn(ticket);
		Mockito.when(vehicleRepositoryMock.findCarByPlate(ticket.getLicencePlate())).thenReturn(vehicle);
		assertEquals(new BigDecimal(8000),
				parkingControllerMock.solicitudeRetireVehicle(ticket, formatter.parse(endDate)).getCostParking());
	}

	@Test
	public void retireMotorcycleTenHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 10:40";
		Vehicle moto = new Vehicle("ABC123A", 2, 550);
		Ticket ticket = new Ticket(moto, formatter.parse(startDate));
		Mockito.when(TicketControllerMock.validateTicket((Ticket) Mockito.any())).thenReturn(ticket);
		Mockito.when(vehicleRepositoryMock.findCarByPlate(ticket.getLicencePlate())).thenReturn(moto);
		assertEquals(new BigDecimal(2500),
				parkingControllerMock.solicitudeRetireVehicle(ticket, formatter.parse(endDate)).getCostParking());
	}
	
	@Test
	public void registerPayment() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		Vehicle moto = new Vehicle("ABC123A", 2, 550);
		Ticket ticket = new Ticket(moto, formatter.parse(startDate));
		Mockito.doNothing().when(TicketControllerMock).registerPayment(ticket);
		parkingControllerMock.registerPayment(ticket);
	}
	
	@Test
	public void searchVehicleTest() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		Vehicle car = new Vehicle("MMY000", 1, 550);
		Ticket ticket = new Ticket(car, formatter.parse(startDate));
		Mockito.when(TicketControllerMock.searchVehicle(car.getLicencePlate())).thenReturn(ticket);
		assertNotNull(parkingControllerMock.searchVehicle("MMY000"));
	}
}
