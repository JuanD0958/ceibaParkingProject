package com.ceiba.parking.application.domain;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class RequestRetire {

	private Ticket ticket;
	private Date endTime;
	
	public RequestRetire() {
		
	}
	
	public Ticket getTicket() {
		return ticket;
	}

	public Date getEndTime() {
		return endTime;
	}
}
