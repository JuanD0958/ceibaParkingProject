package com.ceibaParking.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
public class ParkingSpot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int spotNumber;
	@Column
	public String licencePlate;
	@Column
	public int typeSpot;
}