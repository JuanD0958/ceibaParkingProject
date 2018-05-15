package com.ceibaParking.application.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceibaParking.application.business.ParkingRules;
import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.Car;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.domain.Vehicle;
import com.ceibaParking.application.exception.VehicleRegistrationException;
import com.ceibaParking.application.repository.jpa.CarRepository;
import com.ceibaParking.application.repository.jpa.MotorcycleRepository;

@Service
public class ParkingController implements ParkingRules, ConstantTypeVehicle, ConstantMessageExceptions {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private MotorcycleRepository motorcycleRepository;
	@Autowired
	private TicketController ticketController;

	@Autowired
	Ticket ticket;

	public ParkingController() {
	}

	private void availableSpot(Vehicle vehicle) {
		if (vehicle.getTypeVehicle() == TYPE_CAR && numberOfCarsParked() > CAR_CAPACITY) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
		if (vehicle.getTypeVehicle() == TYPE_MOTORCYCLE && numberOfMotorcyclesParked() > MOTORCYCLE_CAPACITY) {
			throw new VehicleRegistrationException(NO_PLACES_AVAILABLES);
		}
	}

	public Car findByPlate(String plate) {
		return carRepository.findById(plate).get();
	}

	public int numberOfCarsParked() {
		return carRepository.findAll().size();
	}

	public int numberOfMotorcyclesParked() {
		return motorcycleRepository.findAll().size();
	}

	@Override
	public void plateRestriction(Vehicle vehicle, Date startTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime);	
		if(vehicle.getLicencePlate().startsWith("A") && cal.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && cal.get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY ) {
			throw new VehicleRegistrationException(NO_LAWFUL_DAY);
		}
	}

	public void registerCar(RequestRegister requestRegister) {
		validateRegister(requestRegister.getCar(), requestRegister.getStartTime());
		ticketController.generateCarTicket(requestRegister);
		carRepository.save(requestRegister.getCar());
	}

	public void registerMotorcycle(RequestRegister requestRegister) {
		validateRegister(requestRegister.getMotorcycle(), requestRegister.getStartTime());
		ticketController.generateMotorcycleTicket(requestRegister);
		motorcycleRepository.save(requestRegister.getMotorcycle());
	}

	@Override
	public boolean validateRegister(Vehicle vehicle, Date starTime) {
		boolean available = true;
		availableSpot(vehicle);
		plateRestriction(vehicle, starTime);
		return available;
	}
	
	public long retireCar(Ticket ticket, Date endTime) {
		carRepository.delete(ticket.getCar());		
		return ticketController.calculateCarParkingCost(ticket,endTime);
	}
	
	public long retireMotorCycle(Ticket ticket, Date endTime) {
		motorcycleRepository.delete(ticket.getMotorcycle());
		return ticketController.calculateMotorcycleParkingCost(ticket,endTime);
	}
	
	
	
}
