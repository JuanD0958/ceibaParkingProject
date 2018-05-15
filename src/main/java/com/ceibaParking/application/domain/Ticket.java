package com.ceibaParking.application.domain;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.business.ParkingPrices;
import com.ceibaParking.application.constants.ConstantTypeVehicle;

//@Entity
@Component
public class Ticket implements ParkingPrices, ConstantTypeVehicle{

//	@Id
	private String ticketNumber;
	@Autowired
//	@ManyToOne
//	@JoinColumn(name = "licencePlate")
	private Vehicle vehicle;
	private Date startTime;

	public Ticket() {
	}

	public Ticket(Vehicle vehicle, Date startTime) {
		super();
		this.vehicle = vehicle;
		this.startTime = startTime;
	}

	public long calcualteParkingHours(Date endTime) {
		 final int MILLI_TO_HOUR = 1000 * 60 * 60;
		 return (int) (endTime.getTime() - startTime.getTime()) / MILLI_TO_HOUR;
	}

	public long calcualteParkingDays(Date endTime) {
	    long diff = endTime.getTime() - startTime.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public long calculateCost(Date endTime) {		
		if(vehicle.getTypeVehicle() == TYPE_CAR) {
			return calculateCarParkingCost(endTime);
		}
		if(vehicle.getTypeVehicle() == TYPE_MOTORCYCLE) {
			
		}
		return 0;
	}

	private long calculateCarParkingCost(Date endTime) {
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

	
}
