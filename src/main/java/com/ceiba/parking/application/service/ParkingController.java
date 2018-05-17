package com.ceiba.parking.application.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.application.bussines.CalculatorCost;
import com.ceiba.parking.application.bussines.ParkingRulesImpl;
import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.constants.ConstantTypeVehicle;
import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.domain.VehicleDTO;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;

@Service
public class ParkingController implements ConstantTypeVehicle, ConstantMessageExceptions {
	@Autowired
	private VehicleRepositoryImpl vehicleRepository;
	@Autowired
	private TicketController ticketController;
	@Autowired
	private CalculatorCost calculateCost;
	@Autowired
	ParkingRulesImpl parkingRules;
	@Autowired
	Ticket ticket;

	
	public Ticket registerVehicle(RequestRegister requestRegister) {
		parkingRules.validateRegister(requestRegister.getVehicle(), requestRegister.getStartTime());
		Ticket ticketCreated = ticketController.generateVehicleTicket(requestRegister);
		vehicleRepository.registerVehicle(requestRegister.getVehicle());
		return ticketCreated;
	}
	
	public Ticket solicitudeRetireVehicle(Ticket ticket, Date endTime) {
		ticket = ticketController.validateTicket(ticket);
		Vehicle vehicle = vehicleRepository.findCarByPlate(ticket.getLicencePlate());
		ticket.setEndTime(endTime);
		ticket.setCostParking(new BigDecimal(calculateCost.calculateParkingCost(ticket,vehicle,endTime)));
		return ticket;	
	}
	
	
	public void registerPayment(Ticket ticket) {
		ticketController.registerPayment(ticket);
	}
	
	public VehicleDTO searchVehicle(String licencePlate) {
		Ticket ticketFoud = ticketController.searchVehicle(licencePlate);
		return new VehicleDTO(ticketFoud.getLicencePlate(),ticketFoud.getStartTime(),ticketFoud.getTypeVehicle());
	}
}
