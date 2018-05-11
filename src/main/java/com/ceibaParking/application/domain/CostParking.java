package com.ceibaParking.application.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CostParking {
	@Id
	private int typeVehicle;
	private BigDecimal costFactor;

	public int getTypeVehicle() {
		return typeVehicle;
	}

	public void setTypeVehicle(int typeVehicle) {
		this.typeVehicle = typeVehicle;
	}

	public BigDecimal getCostFactor() {
		return costFactor;
	}

	public void setCostFactor(BigDecimal costFactor) {
		this.costFactor = costFactor;
	}

}
