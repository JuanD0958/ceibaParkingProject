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

@Service
public class TicketController implements ParkingPrices, ConstantTypeVehicle, ConstantMessageExceptions{
	@Autowired
	private TicketRepositoryImpl ticketRepository;	

	public Ticket generateVehicleTicket(RequestRegister requestRegister) {
		Ticket ticket = null;
		ticket = new Ticket(requestRegister.getVehicle(), requestRegister.getStartTime());		
		ticketRepository.createTicket(ticket);
		return ticket;
	}
		
	public Ticket validateTicket(Ticket ticket) {
		ticket = ticketRepository.findTicketByPlate(ticket.getLicencePlate());
		if(ticket == null) {
			throw new VehicleRegistrationException(TICKET_ALREADY_PAID);
		}
		return ticket;
	}
	
	public void registerPayment(Ticket paymentSlip) {
		ticketRepository.registerPayment(paymentSlip);
	}
	
	public Ticket searchVehicle(String licencePlate) {
		return ticketRepository.findTicketByPlate(licencePlate);		
	}
	
	
}