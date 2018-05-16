package com.ceiba.parking.application.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.application.bussines.CalculateTime;
import com.ceiba.parking.application.bussines.CalculatorCost;
import com.ceiba.parking.application.bussines.ParkingRulesImpl;
import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.constants.ConstantTypeVehicle;
import com.ceiba.parking.application.domain.PaymentSlip;
import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.repository.CarRepositoryImpl;
import com.ceiba.parking.application.repository.MotorcycleRepositoryImpl;

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

	
	public Ticket registerCar(RequestRegister requestRegister) {
		parkingRules.validateRegister(requestRegister.getCar(), requestRegister.getStartTime());
		Ticket ticketCreated = ticketController.generateCarTicket(requestRegister);
		carRepository.registerCar(requestRegister.getCar());
		return ticketCreated;
	}

	public void registerMotorcycle(RequestRegister requestRegister) {
		parkingRules.validateRegister(requestRegister.getMotorcycle(), requestRegister.getStartTime());
		ticketController.generateMotorcycleTicket(requestRegister);
		motorcycleRepository.registerMotorcycle(requestRegister.getMotorcycle());
	}
	
	public PaymentSlip solicitudeRetireCar(Ticket ticket, Date endTime) {
		ticket = ticketController.validateTicket(ticket);		
		return  new PaymentSlip(ticket.getCar(),new BigDecimal(calculateCost.calculateCarParkingCost(ticket,endTime)),calculateTime.returnDaysAndHoursSpent(ticket, endTime));		
	}
	
	public PaymentSlip solicitudeRetireMotorcycle(Ticket ticket, Date endTime) {
		return new PaymentSlip(ticket.getCar(),new BigDecimal(calculateCost.calculateMotorcycleParkingCost(ticket,endTime)),calculateTime.returnDaysAndHoursSpent(ticket, endTime));
	}		
}
