package com.ceibaParking.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Vehicle {
	@Id
	protected String licencePlate;
	@Column
	protected int typeVehicle;
	@Column
	protected Date startTime;
	@Column
	protected Date endTime;
}
