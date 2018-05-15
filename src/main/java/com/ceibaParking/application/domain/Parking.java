package com.ceibaParking.application.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ceibaParking.application.business.ParkingRules;

@Component
public class Parking implements ParkingRules{
	private int carParkingPlaces;
	private int motorCycleParkingPlaces;

	public Parking() {
	}

	public Parking(int carParkingPlaces, int motorCycleParkingPlaces) {
		super();
		this.carParkingPlaces = carParkingPlaces;
		this.motorCycleParkingPlaces = motorCycleParkingPlaces;
	}

	public int getCarParkingPlaces() {
		return carParkingPlaces;
	}

	public void setCarParkingPlaces(int carParkingPlaces) {
		this.carParkingPlaces = carParkingPlaces;
	}

	public int getMotorCycleParkingPlaces() {
		return motorCycleParkingPlaces;
	}

	public void setMotorCycleParkingPlaces(int motorCycleParkingPlaces) {
		this.motorCycleParkingPlaces = motorCycleParkingPlaces;
	}	

	@Override
	public boolean validateRegister(Vehicle vehicle, Date starTime) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void plateRestriction(Vehicle vehicle, Date startTime) {
		// TODO Auto-generated method stub
		
	}


}
