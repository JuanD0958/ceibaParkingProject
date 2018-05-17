package com.ceiba.parking.application.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.RequestRetire;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.service.ParkingController;

@Service
@RestController
public class RestApi {
	@Autowired
	ParkingController parkingController;

	@PostMapping("/registerVehicle")
	public ResponseEntity<?> registerCar(@RequestBody RequestRegister requestRegister) {
		try {
			return new ResponseEntity<>(parkingController.registerVehicle(requestRegister), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("/retireCar")
	public ResponseEntity<?> retireCar(@RequestBody RequestRetire requestRetire) {		
		try {
			return new ResponseEntity<>(parkingController.solicitudeRetireVehicle(requestRetire.getTicket(), requestRetire.getEndTime()),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/payParking")
	public ResponseEntity<?> payParking(@RequestBody Ticket ticket) {
		try {
			parkingController.registerPayment(ticket);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
	
	

	 @GetMapping("/searchVehicle")
	 public ResponseEntity<?> searchVehicle(@RequestBody String licencePlate) {
	 try {
	 return new ResponseEntity<>(parkingController.searchVehicle(licencePlate),HttpStatus.CREATED);
	 }catch(Exception e) {
	 return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
	 }
	 }
}
