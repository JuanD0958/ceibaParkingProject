package com.ceiba.parking.application.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.parking.application.constants.ConstantMessageExceptions;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.exception.VehicleRegistrationException;
import com.ceiba.parking.application.repository.jpa.TicketRepository;

@Component
public class TicketRepositoryImpl implements ConstantMessageExceptions {
	@Autowired
	TicketRepository ticketRepository;

	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	public Ticket findById(Integer idTicket) {		
		Optional<Ticket> value = ticketRepository.findById(idTicket);
		if(value.isPresent()) {
			return value.get();			
		}
		throw new VehicleRegistrationException(VEHICLE_NO_EXIST);
	}
}
