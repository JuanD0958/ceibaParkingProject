package com.ceiba.parking.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.parking.application.bussines.ParkingPrices;
import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.constants.ConstantTypeVehicle;
import com.ceiba.parking.application.domain.RequestRegister;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.exception.VehicleRegistrationException;
import com.ceiba.parking.application.repository.TicketRepositoryImpl;
import com.ceiba.parking.application.repository.jpa.CarRepository;
import com.ceiba.parking.application.repository.jpa.MotorcycleRepository;

@Service
public class TicketController implements ParkingPrices, ConstantTypeVehicle, ConstantMessageExceptions{
	@Autowired
	public CarRepository carRepository;
	@Autowired
	public MotorcycleRepository motorcycleRepository;
	@Autowired
	private TicketRepositoryImpl ticketRepository;
	
	public TicketController() {
	}

	public Ticket generateCarTicket(RequestRegister requestRegister) {
		Ticket ticket = null;
		ticket = new Ticket(requestRegister.getCar(), requestRegister.getStartTime());		
		ticketRepository.save(ticket);
		return ticket;
	}
	
	public Ticket generateMotorcycleTicket(RequestRegister requestRegister) {
		Ticket ticket = null;
		ticket = new Ticket(requestRegister.getMotorcycle(), requestRegister.getStartTime());		
		ticketRepository.save(ticket);
		return ticket;
	}	

		
	public Ticket validateTicket(Ticket ticket) {
		ticket = ticketRepository.findById(ticket.getTicketNumber());
		if(ticket.isPaid()) {
			throw new VehicleRegistrationException(TICKET_ALREADY_PAID);
		}
		return ticket;
	}
}