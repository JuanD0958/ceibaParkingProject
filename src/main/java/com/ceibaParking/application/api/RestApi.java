package com.ceibaParking.application.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.service.ParkingController;
@Service
@RestController
public class RestApi {
	@Autowired
	ParkingController parkingController;
	
	@PostMapping("/registerCar")
	public void registerCar(@RequestBody RequestRegister requestRegister) {
		parkingController.registerCar(requestRegister);
	}
	
	@RequestMapping("/")
	public RequestRegister example() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "26-05-2017 09:39";
		Car car = new Car("AAA000");
		RequestRegister request = new RequestRegister(car, formatter.parse(startDate));
		return request;
	}
}
