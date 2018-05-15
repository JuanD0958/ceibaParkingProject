package com.ceibaParking.application.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.ceibaParking.application.business.ParkingPrices;
import com.ceibaParking.application.constants.ConstantTypeVehicle;

@Entity
@Component
public class Ticket implements ParkingPrices, ConstantTypeVehicle{

	@Id
	private String ticketNumber;
	@OneToOne
	private Car car;
	@OneToOne
	private Motorcycle motorcycle;
	private Date startTime;
	private Date endTime;
	private BigDecimal costParking;

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

	public long calcualteParkingHours(Date endTime) {
		 final int MILLI_TO_HOUR = 1000 * 60 * 60;
		 return (int) (endTime.getTime() - startTime.getTime()) / MILLI_TO_HOUR;
	}

	public long calcualteParkingDays(Date endTime) {
	    long diff = endTime.getTime() - startTime.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public long calculateCarParkingCost(Date endTime) {
		long daysOfParking = calcualteParkingDays(endTime);
		long hoursOfParking = calcualteParkingHours(endTime) - daysOfParking * 24;
		long costParking = 0;
		
		costParking = daysOfParking * CAR_DAY_COST;		
		if(hoursOfParking >= HOUR_BEGIN_DAY_CHARGE && hoursOfParking <= HOUR_FINISH_DAY_CHARGE) {
			costParking += CAR_DAY_COST;
		}else {
			costParking += hoursOfParking * CAR_HOUR_COST;
		}
		return costParking;
	}
	
	public long calculateMotorcycleParkingCost(Date endTime) {
		long daysOfParking = calcualteParkingDays(endTime);
		long hoursOfParking = calcualteParkingHours(endTime) - daysOfParking * 24;
		long costParking = 0;
		
		costParking = daysOfParking * CAR_DAY_COST;		
		if(hoursOfParking >= HOUR_BEGIN_DAY_CHARGE && hoursOfParking <= HOUR_FINISH_DAY_CHARGE) {
			costParking += MOTORCYCLE_DAY_COST;
		}else {
			costParking += hoursOfParking * MOTORCYCLE_HOUR_COST;
		}	
		if(motorcycle.cubicCentimeters > HIGH_CYLINDERED_MOTORCYCLE) {
			costParking += HIGH_CYLINDERED_MOTORCYCLE_CHARGE;
		}				
		return costParking;
	}

}
