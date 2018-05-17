package com.ceiba.parking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.parking.application.bussines.ParkingRulesImpl;
import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.repository.TicketRepositoryImpl;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;

public class TicketControllerTest implements ConstantMessageExceptions{

	@InjectMocks
	@Autowired
	TicketController ticketControllerMock;
	@Mock
	TicketRepositoryImpl ticketRepositoryMock;
	@Mock
	ParkingRulesImpl parkingRulesMock;		
	@Autowired
	ParkingController parkingController;
	@Autowired
	VehicleRepositoryImpl vehicleRepository;
	
	Vehicle vehicle;

	@Before
	public void setup() {
		vehicle = new Vehicle("MMY000", 1, 1200);
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testValidateTicket() {
		Ticket ticket = new Ticket(vehicle,new Date());
		Mockito.when(ticketRepositoryMock.findTicketByPlate(vehicle.getLicencePlate())).thenReturn(ticket);
		assertNotNull(ticketControllerMock.validateTicket(ticket));		
	}
	
	@Test
	public void testValidateTicketTicketNotFound() {
		try {
			Ticket ticket = new Ticket(vehicle,new Date());
			Mockito.when(ticketRepositoryMock.findTicketByPlate(vehicle.getLicencePlate())).thenReturn(null);
			assertNotNull(ticketControllerMock.validateTicket(ticket));	
			fail();
		}catch(Exception e) {
			assertEquals(TICKET_ALREADY_PAID, e.getMessage());
		}
		
	}

	@Test
	public void testSearchVehicle() {
		Ticket ticket = new Ticket(vehicle,new Date());
		Mockito.when(ticketRepositoryMock.findTicketByPlate(vehicle.getLicencePlate())).thenReturn(ticket);
		assertNotNull(ticketControllerMock.searchVehicle(ticket.getLicencePlate()));	
	}

}
