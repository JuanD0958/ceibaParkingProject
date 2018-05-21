package com.ceiba.parking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.repository.TicketRepositoryImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingControllerIntegrationTest  implements ConstantMessageExceptions {

	
	@Autowired
	ParkingController parkingController;
	@Autowired
	TicketController ticketController;
	@Autowired
	TicketRepositoryImpl ticketpository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void registerCarVehicleService() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle car = new Vehicle("IMY020", 1, 1500);
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		parkingController.registerVehicle(request);
		assertEquals(car.getLicencePlate(), ticketpository.findTicketByPlate(car.getLicencePlate()).getLicencePlate());
	}

	@Test
	public void registerMotorcycleVehicleService() throws ParseException {
		// Arrange
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle motorcycle = new Vehicle("IBD23A", 2, 400);
		RequestRegister request = new RequestRegister(motorcycle, formatter.parse(startDate));
		parkingController.registerVehicle(request);
		assertEquals(motorcycle.getLicencePlate(), ticketpository.findTicketByPlate(motorcycle.getLicencePlate()).getLicencePlate());
	}

	@Test
	public void searchVehicleTestNotFound() throws ParseException {
		try {
		parkingController.searchVehicle("IMY0");
		fail();
		}catch(Exception e) {
			assertEquals(VEHICLE_NO_EXIST, e.getMessage());			
		}
	}
	
	@Test
	public void searchVehicleTestFound() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle car = new Vehicle("IMY003", 1, 1500);
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		parkingController.registerVehicle(request);		
		assertEquals(car.getLicencePlate(), ticketpository.findTicketByPlate(car.getLicencePlate()).getLicencePlate());
	}

	@Test
	public void retireCar() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 10:40";
		Vehicle vehicle = new Vehicle("IMY004", 1, 1200);
		RequestRegister request = new RequestRegister(vehicle, formatter.parse(startDate));
		Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
		parkingController.registerVehicle(request);		
		assertEquals(new BigDecimal(1000),
				parkingController.solicitudeRetireVehicle(ticket, formatter.parse(endDate)).getCostParking());
	}
	
	@Test
	public void payCarParking() throws ParseException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "15-05-2018 09:39";
			String endDate = "15-05-2018 10:40";
			Vehicle vehicle = new Vehicle("IMY005", 1, 1200);
			RequestRegister request = new RequestRegister(vehicle, formatter.parse(startDate));
			Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
			parkingController.registerVehicle(request);		
			ticket = parkingController.solicitudeRetireVehicle(ticket, formatter.parse(endDate));
			parkingController.registerPayment(ticket);
			ticketpository.findTicketByPlate(vehicle.getLicencePlate());
			fail();
		}catch(Exception e) {
			assertEquals(VEHICLE_NO_EXIST, e.getMessage());
		}		
	}
	
}
