package com.ceiba.parking.application.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.repository.TicketRepositoryImpl;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingRulesImplTest implements ConstantMessageExceptions {

	@InjectMocks
	@Autowired
	ParkingRulesImpl parkingRulesImpl;

	@Mock
	VehicleRepositoryImpl vehicleRepositoryImplMock;
	@Mock
	TicketRepositoryImpl ticketRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidateRegister() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Vehicle vehicle = new Vehicle("MMY001",1,1500);
		Mockito.when(ticketRepository.existsVehicleParked(Mockito.anyString())).thenReturn(false);
		assertTrue(parkingRulesImpl.validateRegister(vehicle, formatter.parse(startDate)));
	}

	@Test
	public void testPlateRestriction() throws ParseException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "26-05-2017 09:39";
			Vehicle vehicle = new Vehicle("MMY000",1,1500);
			Mockito.when(ticketRepository.existsVehicleParked(Mockito.anyString())).thenReturn(false);
			parkingRulesImpl.plateRestriction(vehicle, formatter.parse(startDate));
		} catch (Exception e) {
			assertEquals(NO_LAWFUL_DAY, e.getMessage());
		}
	}

	@Test
	public void testVehicleAlreadyParked() {
		try {
			Vehicle vehicle = new Vehicle("MMY000",1,1500);
			Mockito.when(ticketRepository.existsVehicleParked(Mockito.anyString())).thenReturn(true);
			parkingRulesImpl.vehicleAlreadyParked(vehicle);
		} catch (Exception e) {
			assertEquals(VEHICLE_ALREADY_PARKED, e.getMessage());
		}
	}

	@Test
	public void testAvailableCarSpot() {
		try {
			Vehicle vehicle = new Vehicle("MMY000",1,1500);
			Mockito.when(vehicleRepositoryImplMock.numberOfCarsParked()).thenReturn(201);
			parkingRulesImpl.availableSpot(vehicle);
		} catch (Exception e) {
			assertEquals(NO_PLACES_AVAILABLES, e.getMessage());
		}

	}
	
	@Test
	public void testAvailableMotorcycleSpot() {
		try {
			Vehicle vehicle = new Vehicle("MMY000",1,1500);
			Mockito.when(vehicleRepositoryImplMock.numberOfMotorcyclesParked()).thenReturn(201);
			parkingRulesImpl.availableSpot(vehicle);
		} catch (Exception e) {
			assertEquals(NO_PLACES_AVAILABLES, e.getMessage());
		}

	}

}
