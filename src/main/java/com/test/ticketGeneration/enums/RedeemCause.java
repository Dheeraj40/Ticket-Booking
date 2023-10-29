package com.test.ticketGeneration.enums;

import lombok.Getter;

@Getter
public enum RedeemCause {
	INVALID_ID("Invalid Ticket ID."),
	REDEMED_TWICE("Given Ticket ID expired, Already been redeemed twice."),
	REDEMED_TIME_EXCEED("Given Ticket ID expired, Validation time exceeded 18 hours, Kindly generate ticket again."),
	REDEMED_WITH_INACTIVE("Given Ticket ID expired, Ticket Status is inactive either due to time exceed or redeemed twice.");
	
	

	String cause;
	RedeemCause(String cause) {
		this.cause=cause;
	}
	
}
