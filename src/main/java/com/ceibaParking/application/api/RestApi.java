package com.ceibaParking.application.api;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.PaymentSlip;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.RequestRetire;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.service.ParkingController;
@Service
@RestController
public class RestApi {
	@Autowired
	ParkingController parkingController;
	
	@PostMapping("/registerCar")
	public ResponseEntity<?> registerCar(@RequestBody RequestRegister requestRegister) {
		try {
			return new ResponseEntity<Ticket>(parkingController.registerCar(requestRegister), HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("/retireCar")
	public ResponseEntity<?> retireCar(@RequestBody RequestRetire requestRetire) {		
		try {
			return new ResponseEntity<PaymentSlip>(parkingController.solicitudeRetireCar(requestRetire.getTicket(), requestRetire.getEndTime()),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping("/")
	public RequestRetire example() throws ParseException {
		RequestRetire p = new RequestRetire(new Ticket(new Car(),new Date()));
		return p;
	}
}
