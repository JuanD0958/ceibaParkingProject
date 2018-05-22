package com.ceiba.parking.application.domain;

import java.util.Date;

public class TcrmDTO {
	private String unit;
	private float value;
	private boolean success;

	public TcrmDTO(String unit, float value, Date updatedAt, boolean success) {
		super();
		this.unit = unit;
		this.value = value;
		this.updatedAt = updatedAt;
		this.setSuccess(success);
	}

	private Date updatedAt;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
