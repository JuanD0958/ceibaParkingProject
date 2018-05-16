package com.ceibaParking.application.business;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceibaParking.application.domain.Ticket;

@Component
public class CalculateTime {
	public CalculateTime() {
	}

	@Autowired
	Ticket ticket;

	public long calcualteParkingHours(Ticket ticket, Date endTime) {
		final int MILLI_TO_HOUR = 1000 * 60 * 60;
		return (int) (endTime.getTime() - ticket.getStartTime().getTime()) / MILLI_TO_HOUR;
	}

	public long calcualteParkingDays(Ticket ticket, Date endTime) {
		long diff = endTime.getTime() - ticket.getStartTime().getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public String returnDaysAndHoursSpent(Ticket ticket, Date endTime) {
		String response = "Time spent: ";
		long daysOfParking = calcualteParkingDays(ticket, endTime);
		long hoursOfParking = calcualteParkingHours(ticket, endTime) - daysOfParking * 24;
		if (daysOfParking > 0) {
			response = daysOfParking + " days";
		}
		if (hoursOfParking > 0) {
			response = daysOfParking + " hours";
		}
		return response;
	}
}
