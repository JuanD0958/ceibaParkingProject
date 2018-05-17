package com.ceiba.parking.application.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.ceiba.parking.application.bussines.ParkingPrices;
import com.ceiba.parking.application.constants.ConstantTypeVehicle;

@Entity
@Component
public class Ticket implements ParkingPrices, ConstantTypeVehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketNumber;
	private String licencePlate;
	private int typeVehicle;
	private Date startTime;
	private Date endTime;
	private BigDecimal costParking;
	private boolean paid;

	public Ticket() {
	}

	public Ticket(Vehicle vehicle, Date startTime) {
		super();
		this.startTime = startTime;
		this.licencePlate = vehicle.getLicencePlate();
		this.typeVehicle = vehicle.getTypeVehicle();
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

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public int getTypeVehicle() {
		return typeVehicle;
	}

	public void setTypeVehicle(int typeVehicle) {
		this.typeVehicle = typeVehicle;
	}

}
