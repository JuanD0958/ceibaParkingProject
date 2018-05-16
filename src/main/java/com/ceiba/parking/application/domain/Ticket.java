package com.ceiba.parking.application.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.ceiba.parking.application.bussines.ParkingPrices;
import com.ceiba.parking.application.constants.ConstantTypeVehicle;

@Entity
@Component
public class Ticket implements ParkingPrices, ConstantTypeVehicle{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketNumber;
	@OneToOne(cascade = {CascadeType.ALL})
	private Car car;
	@OneToOne(cascade = {CascadeType.ALL})
	private Motorcycle motorcycle;
	private Date startTime;
	private Date endTime;
	private BigDecimal costParking;
	private boolean paid;

	public Ticket() {
	}

	public Ticket(Car car, Date startTime) {
		super();
		this.startTime = startTime;
		this.car = car;
	}
	
	public Ticket(Motorcycle motorcycle, Date startTime) {
		super();	
		this.startTime = startTime;
		this.motorcycle = motorcycle;
	}
	
	public Car getCar() {
		return car;
	}

	public Motorcycle getMotorcycle() {
		return motorcycle;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setMotorcycle(Motorcycle motorcycle) {
		this.motorcycle = motorcycle;
	}

	public BigDecimal getCostParking() {
		return costParking;
	}

	public void setCostParking(BigDecimal costParking) {
		this.costParking = costParking;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
