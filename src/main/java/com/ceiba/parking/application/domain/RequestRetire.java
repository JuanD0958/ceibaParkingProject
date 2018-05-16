package com.ceiba.parking.application.domain;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class RequestRetire {

	private Ticket ticket;
	private Date endTime;
	
	public RequestRetire() {
		
	}
	public RequestRetire(Ticket ticket, Date endTime) {
		super();
		this.ticket = ticket;
		this.endTime = endTime;
	}
	
	public RequestRetire(Ticket ticket) {
		super();
		this.ticket = ticket;
		this.endTime = new Date();
	}
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
