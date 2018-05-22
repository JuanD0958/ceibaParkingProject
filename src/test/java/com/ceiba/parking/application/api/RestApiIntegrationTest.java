package com.ceiba.parking.application.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.RequestRetire;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.domain.VehicleDTO;
import com.ceiba.parking.application.repository.TicketRepositoryImpl;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;
import com.ceiba.parking.application.service.ParkingController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RestApiIntegrationTest implements ConstantMessageExceptions {
	
	@LocalServerPort
	int randomServerPort;
	
	@Autowired
	ParkingController parkingController;
	@Autowired
	TicketRepositoryImpl ticketRepository;
	@Autowired
	VehicleRepositoryImpl vehicleRepositoryImpl;
	
	private TestRestTemplate restTemplate = new TestRestTemplate();
	
	@After
	public void deleteVehicles() {
		vehicleRepositoryImpl.deleteAllVehicles();
	}
	
	@Test
	public void registerVehicle() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle car = new Vehicle("MMY010", 1, 1500);
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		ResponseEntity<Ticket> responseEntity = restTemplate.postForEntity("http://localhost:"+randomServerPort+"/registerVehicle",request ,Ticket.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void retireVehicle() throws ParseException {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "15-05-2018 09:39";
			Vehicle vehicle = new Vehicle("MMY013", 1, 1200);
			RequestRegister request = new RequestRegister(vehicle, formatter.parse(startDate));
			Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
			parkingController.registerVehicle(request);
			RequestRetire requestRetire = new RequestRetire(ticket, new Date());
			ResponseEntity<Ticket> responseEntity = restTemplate.postForEntity("http://localhost:"+randomServerPort+"/retireVehicle",requestRetire ,Ticket.class);	
			assertEquals(HttpStatus.OK, responseEntity.getStatusCode());		
	}
	
	@Test
	public void retireVehicleError() throws ParseException {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "15-05-2018 09:39";
			Vehicle vehicle = new Vehicle("MMY014", 1, 1200);
			Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
			//parkingController.registerVehicle(request);
			RequestRetire requestRetire = new RequestRetire(ticket, new Date());
			ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:"+randomServerPort+"/retireVehicle",requestRetire ,String.class);	
			assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());		
	}
	
	@Test
	public void searchVehicleTestFound() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle car = new Vehicle("MMY015", 1, 1500);
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		parkingController.registerVehicle(request);		
		ResponseEntity<VehicleDTO> responseEntity = restTemplate.getForEntity("http://localhost:"+randomServerPort+"/searchVehicle?licencePlate="+car.getLicencePlate(), VehicleDTO.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void searchVehicleTestNotFound() throws ParseException {
		Vehicle car = new Vehicle("MMY016", 1, 1500);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:"+randomServerPort+"/searchVehicle?licencePlate="+car.getLicencePlate(), String.class);
		assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
	}
	
	@Test
	public void payCarParking() throws ParseException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "15-05-2018 09:39";
			String endDate = "15-05-2018 10:40";
			Vehicle vehicle = new Vehicle("MMY017", 1, 1200);
			RequestRegister request = new RequestRegister(vehicle, formatter.parse(startDate));
			Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
			parkingController.registerVehicle(request);		
			ticket = parkingController.solicitudeRetireVehicle(ticket, formatter.parse(endDate));
			restTemplate.postForEntity("http://localhost:"+randomServerPort+"/payParking",ticket ,Object.class);
			ticketRepository.findTicketByPlate(vehicle.getLicencePlate());
			fail();
		}catch(Exception e) {
			assertEquals(VEHICLE_NO_EXIST, e.getMessage());
		}		
	}
	
	@Test
	public void searchVehiclesParked() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle car = new Vehicle("MMY025", 1, 1500);
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		parkingController.registerVehicle(request);		
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity("http://localhost:"+randomServerPort+"/vehiclesParked", Object[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
