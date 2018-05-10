package com.ceibaParking.application.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CostParking {
	@Id
	private int typeVehicle;
	private BigDecimal costFactor;
	
}
