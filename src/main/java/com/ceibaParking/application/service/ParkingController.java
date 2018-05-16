package com.ceibaParking.application.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceibaParking.application.business.CalculateTime;
import com.ceibaParking.application.business.CalculatorCost;
import com.ceibaParking.application.business.ParkingRules;
import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.PaymentSlip;
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
	private CalculateTime calculateTime;
	@Autowired
	private CalculatorCost calculateCost;

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

	public Ticket registerCar(RequestRegister requestRegister) {
		validateRegister(requestRegister.getCar(), requestRegister.getStartTime());
		Ticket ticket = ticketController.generateCarTicket(requestRegister);
		carRepository.save(requestRegister.getCar());
		return ticket;
	}

	public void registerMotorcycle(RequestRegister requestRegister) {
		validateRegister(requestRegister.getMotorcycle(), requestRegister.getStartTime());
		ticketController.generateMotorcycleTicket(requestRegister);
		motorcycleRepository.save(requestRegister.getMotorcycle());
	}

	@Override
	public boolean validateRegister(Vehicle vehicle, Date starTime) {
		boolean available = true;
		vehicleAlreadyParked(vehicle);
		availableSpot(vehicle);
		plateRestriction(vehicle, starTime);
		return available;
	}
	
	public PaymentSlip solicitudeRetireCar(Ticket ticket, Date endTime) {
		ticket = ticketController.validateTicket(ticket);		
		PaymentSlip paymentSlip = new PaymentSlip(ticket.getCar(),new BigDecimal(calculateCost.calculateCarParkingCost(ticket,endTime)),calculateTime.returnDaysAndHoursSpent(ticket, endTime));
		return paymentSlip;
	}
	
	public PaymentSlip solicitudeRetireMotorcycle(Ticket ticket, Date endTime) {
		PaymentSlip paymentSlip = new PaymentSlip(ticket.getCar(),new BigDecimal(calculateCost.calculateMotorcycleParkingCost(ticket,endTime)),calculateTime.returnDaysAndHoursSpent(ticket, endTime));
		return paymentSlip;
	}		
	
	public void vehicleAlreadyParked(Vehicle vehicle) {
		if(carRepository.existsById(vehicle.getLicencePlate())) {
			throw new VehicleRegistrationException(VEHICLE_ALREADY_PARKED);
		}
	}
}
