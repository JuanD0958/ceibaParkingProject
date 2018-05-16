package com.ceibaParking.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceibaParking.application.business.ParkingPrices;
import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.exception.VehicleRegistrationException;
import com.ceibaParking.application.repository.TicketRepositoryImpl;
import com.ceibaParking.application.repository.jpa.CarRepository;
import com.ceibaParking.application.repository.jpa.MotorcycleRepository;

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