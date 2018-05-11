package com.ceibaParking.application.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceibaParking.application.business.ParkingRulesImpl;
import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.Vehicle;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingControllerTest implements ConstantMessageExceptions {
	@Autowired
	ParkingController parkingController;
	@Autowired
	ParkingRulesImpl parkingSpot;
	
	private ParkingRulesImpl parkingRulesImpl;
	
	
	
	@Test
	public void registerCarService() throws ParseException {
		// Arrange
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Car car = new Car("MMY000");
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		parkingController.registerCar(request);
		parkingController.findByPlate("MMY000");
	}
	
	
	@Test
	public void noLawfulDayRegisterCar(){
		// Arrange
		
		try {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
				String startDate = "26-05-2017 09:39";
				Car car = new Car("AAA000");
				RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
				parkingController.registerCar(request);
				fail();
		}catch(Exception e){
				assertEquals(e.getMessage(), NO_LAWFUL_DAY);
		}
	}
	
//	@Test
//	public void noAvailablePlacesCar(){
//		// Arrange
//		try {		
//			
//				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
//				String startDate = "26-05-2017 09:39";
//				Car car = new Car("AAA000");
//				parkingRulesImpl = Mockito.mock(ParkingRulesImpl.class);
//				Mockito.when(parkingRulesImpl.validateRegister(car, new Date())).thenThrow(throwables)
//				RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
//				parkingController.registerCar(request);
//				fail();
//		}catch(Exception e){
//				assertEquals(e.getMessage(), NO_LAWFUL_DAY);
//		}
//	}
//	

	

}
