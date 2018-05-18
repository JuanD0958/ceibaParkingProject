package com.ceiba.parking.application.bussines;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parking.application.domain.Ticket;
import com.ceiba.parking.application.domain.Vehicle;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CalculateCostTest {

	@Autowired
	CalculatorCost calculateCost;

	@Test
	public void testCalculateCarParkingCost() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 10:40";
		Vehicle vehicle = new Vehicle("MMY000", 1, 1200);
		Ticket ticket = new Ticket(vehicle, formatter.parse(startDate));
		assertEquals(1000, calculateCost.calculateCarParkingCost(ticket, formatter.parse(endDate)));
	}

	@Test
	public void testCalculateMotorcycleParkingCostHighCilinder() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 10:40";
		Vehicle moto = new Vehicle("ABC123A", 2, 550);
		Ticket ticket = new Ticket(moto, formatter.parse(startDate));		
		assertEquals(2500,
				calculateCost.calculateMotorcycleParkingCost(ticket,moto.getCubicCentimeters() ,formatter.parse(endDate)));
	}
	
	@Test
	public void testCalculateMotorcycleParkingCostLowCilinder() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 10:40";
		Vehicle moto = new Vehicle("ABC123A", 2, 125);
		Ticket ticket = new Ticket(moto, formatter.parse(startDate));		
		assertEquals(500,
				calculateCost.calculateMotorcycleParkingCost(ticket,moto.getCubicCentimeters() ,formatter.parse(endDate)));
	}
	
	@Test
	public void testCalculateMotorcycleParkingCostTenHours() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy' 'HH:mm");
		String startDate = "15-05-2018 09:39";
		String endDate = "15-05-2018 19:40";
		Vehicle moto = new Vehicle("ABC123A", 2, 125);
		Ticket ticket = new Ticket(moto, formatter.parse(startDate));		
		assertEquals(6000,
				calculateCost.calculateMotorcycleParkingCost(ticket,moto.getCubicCentimeters() ,formatter.parse(endDate)));
	}

}
