package com.ceiba.parking.application.bussines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingRulesImplTest implements ConstantMessageExceptions {

	@InjectMocks
	@Autowired
	ParkingRulesImpl parkingRules;

	@Mock
	VehicleRepositoryImpl vehicleRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testPlateRestriction() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "26-05-2017 09:39";
			Vehicle vehicle = new Vehicle("AAA000", 1, 1200);
			parkingRules.plateRestriction(vehicle, formatter.parse(startDate));
			fail();
		} catch (Exception e) {
			assertEquals(NO_LAWFUL_DAY, e.getMessage());
		}
	}

	@Test
	public void testCarAvailableSpot() {
		try {
			Vehicle vehicle = new Vehicle("MMY000", 1, 1200);
			Mockito.when(vehicleRepository.numberOfCarsParked()).thenReturn(200);
			parkingRules.availableSpot(vehicle);
			fail();
		} catch (Exception e) {
			assertEquals(NO_PLACES_AVAILABLES, e.getMessage());
		}
	}

	@Test
	public void testMotorcycleAvailableSpot() {
		try {
			Vehicle vehicle = new Vehicle("MMY000", 2, 1200);
			Mockito.when(vehicleRepository.numberOfMotorcyclesParked()).thenReturn(200);
			parkingRules.availableSpot(vehicle);
			fail();
		} catch (Exception e) {
			assertEquals(NO_PLACES_AVAILABLES, e.getMessage());
		}
	}

}
