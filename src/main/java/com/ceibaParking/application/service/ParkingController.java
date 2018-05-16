package com.ceibaParking.application.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceibaParking.application.business.CalculateTime;
import com.ceibaParking.application.business.CalculatorCost;
import com.ceibaParking.application.business.ParkingRulesImpl;
import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.PaymentSlip;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.repository.CarRepositoryImpl;
import com.ceibaParking.application.repository.MotorcycleRepositoryImpl;

@Service
public class ParkingController implements ConstantTypeVehicle, ConstantMessageExceptions {
	@Autowired
	private CarRepositoryImpl carRepository;
	@Autowired
	private MotorcycleRepositoryImpl motorcycleRepository;
	@Autowired
	private TicketController ticketController;
	@Autowired
	private CalculateTime calculateTime;
	@Autowired
	private CalculatorCost calculateCost;
	@Autowired
	ParkingRulesImpl parkingRules;
	@Autowired
	Ticket ticket;

	public ParkingController() {
	}
	
	public Ticket registerCar(RequestRegister requestRegister) {
		parkingRules.validateRegister(requestRegister.getCar(), requestRegister.getStartTime());
		Ticket ticket = ticketController.generateCarTicket(requestRegister);
		carRepository.registerCar(requestRegister.getCar());
		return ticket;
	}

	public void registerMotorcycle(RequestRegister requestRegister) {
		parkingRules.validateRegister(requestRegister.getMotorcycle(), requestRegister.getStartTime());
		ticketController.generateMotorcycleTicket(requestRegister);
		motorcycleRepository.registerMotorcycle(requestRegister.getMotorcycle());
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
}
