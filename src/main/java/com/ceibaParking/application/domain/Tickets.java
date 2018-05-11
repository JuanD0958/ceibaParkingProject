package com.ceibaParking.application.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tickets {
	@Id
	private int typeVehicle;
	private BigDecimal costFactor;
}
