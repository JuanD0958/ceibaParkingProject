package com.ceiba.parking.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.RequestRetire;
import com.ceiba.parking.application.service.ParkingController;
@Service
@RestController
public class RestApi {
	@Autowired
	ParkingController parkingController;
	
	@PostMapping("/registerCar")
	public ResponseEntity<?> registerCar(@RequestBody RequestRegister requestRegister) {
		try {
			return new ResponseEntity<>(parkingController.registerCar(requestRegister), HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("/retireCar")
	public ResponseEntity<?> retireCar(@RequestBody RequestRetire requestRetire) {		
		try {
			return new ResponseEntity<>(parkingController.solicitudeRetireCar(requestRetire.getTicket(), requestRetire.getEndTime()),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
	}
}
