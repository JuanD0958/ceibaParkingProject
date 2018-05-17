package com.ceiba.parking.application.bussines;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.parking.application.constants.ConstantTypeVehicle;
import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.domain.Vehicle;
import com.ceiba.parking.application.repository.VehicleRepositoryImpl;

@Component
public class CalculatorCost implements ParkingPrices,ConstantTypeVehicle {
	@Autowired
	CalculateTime calculateTime;
	@Autowired
	Ticket ticket;
	@Autowired
	VehicleRepositoryImpl vehicleRepository;
	
	public long calculateParkingCost(Ticket ticket, Vehicle vehicle, Date endTime) {
		long totalValue = 0;
		if(vehicle.getTypeVehicle() == TYPE_CAR) {
			totalValue = calculateCarParkingCost(ticket, endTime);
		}
		if(vehicle.getTypeVehicle() == TYPE_MOTORCYCLE) {
			totalValue = calculateMotorcycleParkingCost(ticket,vehicle.getCubicCentimeters(), endTime);
		}
		return totalValue;
	}

	public long calculateCarParkingCost(Ticket ticket, Date endTime) {
		long daysOfParking = calculateTime.calcualteParkingDays(ticket, endTime);
		long hoursOfParking = calculateTime.calcualteParkingHours(ticket, endTime) - daysOfParking * 24;
		long costParking = 0;

		costParking = daysOfParking * CAR_DAY_COST;
		if (hoursOfParking >= HOUR_BEGIN_DAY_CHARGE && hoursOfParking <= HOUR_FINISH_DAY_CHARGE) {
			costParking += CAR_DAY_COST;
		} else {
			costParking += hoursOfParking * CAR_HOUR_COST;
		}
		return costParking;
	}

	public long calculateMotorcycleParkingCost(Ticket ticket,int cubicCentimeters, Date endTime) {
		long daysOfParking = calculateTime.calcualteParkingDays(ticket, endTime);
		long hoursOfParking = calculateTime.calcualteParkingHours(ticket, endTime) - daysOfParking * 24;
		long costParking = 0;

		costParking = daysOfParking * CAR_DAY_COST;
		if (hoursOfParking >= HOUR_BEGIN_DAY_CHARGE && hoursOfParking <= HOUR_FINISH_DAY_CHARGE) {
			costParking += MOTORCYCLE_DAY_COST;
		} else {
			costParking += hoursOfParking * MOTORCYCLE_HOUR_COST;
		}
		if (cubicCentimeters > HIGH_CYLINDERED_MOTORCYCLE) {
			costParking += HIGH_CYLINDERED_MOTORCYCLE_CHARGE;
		}
		return costParking;
	}
}
