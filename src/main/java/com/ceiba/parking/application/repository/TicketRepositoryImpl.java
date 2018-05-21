package com.ceiba.parking.application.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.constants.ConstantTypeVehicle;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.exception.VehicleRegistrationException;
import com.ceiba.parking.application.repository.jpa.TicketRepository;

@Component
public class TicketRepositoryImpl implements ConstantMessageExceptions, ConstantTypeVehicle {
	@Autowired
	TicketRepository ticketRepository;

	public void createTicket(Ticket ticket) {
		ticketRepository.save(ticket);
	}
	
	public Ticket findTicketByPlate(String licencePlate) {
		Ticket ticket = ticketRepository.findTicketByLicencePlateAndPaidFalse(licencePlate);
		if(ticket == null) {
			throw new VehicleRegistrationException(VEHICLE_NO_EXIST);
		}
		return ticket;
	}
	
	public void registerPayment(Ticket ticket) {
		ticket.setPaid(true);
		ticketRepository.save(ticket);
	}
	
	public boolean existsVehicleParked(String licencePlate) {
		return ticketRepository.existsTicketByLicencePlateAndPaidFalse(licencePlate);
	}
	
}
