package com.ceiba.parking.application.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parking.application.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	Ticket findTicketByLicencePlateAndPaidFalse(String licencePlate);	
	boolean existsTicketByLicencePlateAndPaidFalse(String licencePlate);
	List<Ticket> findAllTicketByPaidFalse();
}
