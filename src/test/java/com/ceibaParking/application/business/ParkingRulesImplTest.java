package com.ceibaParking.application.business;

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

import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.repository.CarRepositoryImpl;
import com.ceibaParking.application.repository.MotorcycleRepositoryImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingRulesImplTest implements ConstantMessageExceptions {

	@InjectMocks
	@Autowired
	ParkingRulesImpl parkingRulesImpl;

	@Mock
	CarRepositoryImpl carRepositoryImplMock;
	
	@Mock
	MotorcycleRepositoryImpl motorcycleRepositoryImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidateRegister() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Car car = new Car("MMY000");
		Mockito.when(carRepositoryImplMock.existsById(Mockito.anyString())).thenReturn(false);
		assertTrue(parkingRulesImpl.validateRegister(car, formatter.parse(startDate)));
	}

	@Test
	public void testPlateRestriction() throws ParseException {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
			String startDate = "26-05-2017 09:39";
			Car car = new Car("AMY000");
			Mockito.when(carRepositoryImplMock.existsById(Mockito.anyString())).thenReturn(false);
			parkingRulesImpl.plateRestriction(car, formatter.parse(startDate));
		} catch (Exception e) {
			assertEquals(NO_LAWFUL_DAY, e.getMessage());
		}
	}

	@Test
	public void testVehicleAlreadyParked() {
		try {
			Car car = new Car("MMY000");
			Mockito.when(carRepositoryImplMock.existsById(Mockito.anyString())).thenReturn(true);
			parkingRulesImpl.vehicleAlreadyParked(car);
		} catch (Exception e) {
			assertEquals(VEHICLE_ALREADY_PARKED, e.getMessage());
		}
	}

	@Test
	public void testAvailableCarSpot() {
		try {
			Car car = new Car("MMY000");
			Mockito.when(carRepositoryImplMock.numberOfCarsParked()).thenReturn(21);
			parkingRulesImpl.vehicleAlreadyParked(car);
		} catch (Exception e) {
			assertEquals(NO_PLACES_AVAILABLES, e.getMessage());
		}

	}
	
	@Test
	public void testAvailableMotorcycleSpot() {
		try {
			Car car = new Car("ABC000A");
			Mockito.when(motorcycleRepositoryImpl.numberOfMotorcyclesParked()).thenReturn(11);
			parkingRulesImpl.vehicleAlreadyParked(car);
		} catch (Exception e) {
			assertEquals(NO_PLACES_AVAILABLES, e.getMessage());
		}

	}

}
