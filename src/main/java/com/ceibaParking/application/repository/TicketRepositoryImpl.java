package com.ceibaParking.application.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.constants.ConstantMessageExceptions;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.exception.VehicleRegistrationException;
import com.ceibaParking.application.repository.jpa.TicketRepository;

@Component
public class TicketRepositoryImpl implements ConstantMessageExceptions {
	@Autowired
	TicketRepository ticketRepository;

	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	public Ticket findById(Integer idTicket) {
		try {
			return ticketRepository.findById(idTicket).get();
		} catch (Exception e) {
			throw new VehicleRegistrationException(VEHICLE_NO_EXIST);
		}
	}
	

}
