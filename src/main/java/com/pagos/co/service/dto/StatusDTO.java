package com.pagos.co.service.dto;

import com.pagos.co.domain.enumeration.PaymentStatus;

public class StatusDTO {

	private PaymentStatus status;

	private String description;

	public StatusDTO(PaymentStatus status, String description) {
		this.status = status;
		this.description = description;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
