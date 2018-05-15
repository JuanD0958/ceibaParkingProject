package com.ceibaParking.application.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceibaParking.application.business.ParkingPrices;
import com.ceibaParking.application.constants.ConstantTypeVehicle;
import com.ceibaParking.application.domain.RequestRegister;
import com.ceibaParking.application.domain.Ticket;
import com.ceibaParking.application.repository.jpa.CarRepository;
import com.ceibaParking.application.repository.jpa.MotorcycleRepository;
import com.ceibaParking.application.repository.jpa.TicketRepository;

@Service
public class TicketController implements ParkingPrices, ConstantTypeVehicle{
	@Autowired
	public CarRepository carRepository;
	@Autowired
	public MotorcycleRepository motorcycleRepository;
	@Autowired
	public TicketRepository ticketRepository;
	
	public TicketController() {
		// TODO Auto-generated constructor stub
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
	
	
	public long calcualteParkingHours(Ticket ticket,Date endTime) {
		 final int MILLI_TO_HOUR = 1000 * 60 * 60;
		 return (int) (endTime.getTime() - ticket.getStartTime().getTime()) / MILLI_TO_HOUR;
	}
	
	public long calcualteParkingDays(Ticket ticket,Date endTime) {
	    long diff = endTime.getTime() - ticket.getStartTime().getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public long calculateCarParkingCost(Ticket ticket,Date endTime) {
		long daysOfParking = calcualteParkingDays(ticket,endTime);
		long hoursOfParking = calcualteParkingHours(ticket,endTime) - daysOfParking * 24;
		long costParking = 0;
		
		costParking = daysOfParking * CAR_DAY_COST;		
		if(hoursOfParking >= HOUR_BEGIN_DAY_CHARGE && hoursOfParking <= HOUR_FINISH_DAY_CHARGE) {
			costParking += CAR_DAY_COST;
		}else {
			costParking += hoursOfParking * CAR_HOUR_COST;
		}
		return costParking;
	}
	
	public long calculateMotorcycleParkingCost(Ticket ticket,Date endTime) {
		long daysOfParking = calcualteParkingDays(ticket,endTime);
		long hoursOfParking = calcualteParkingHours(ticket,endTime) - daysOfParking * 24;
		long costParking = 0;
		
		costParking = daysOfParking * CAR_DAY_COST;		
		if(hoursOfParking >= HOUR_BEGIN_DAY_CHARGE && hoursOfParking <= HOUR_FINISH_DAY_CHARGE) {
			costParking += MOTORCYCLE_DAY_COST;
		}else {
			costParking += hoursOfParking * MOTORCYCLE_HOUR_COST;
		}	
		if(ticket.getMotorcycle().cubicCentimeters > HIGH_CYLINDERED_MOTORCYCLE) {
			costParking += HIGH_CYLINDERED_MOTORCYCLE_CHARGE;
		}				
		return costParking;
	}
}