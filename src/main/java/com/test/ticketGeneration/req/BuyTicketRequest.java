package com.test.ticketGeneration.req;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BuyTicketRequest implements Serializable{

	private static final long serialVersionUID = 8345343344331450908L;
	
	String fromStation;
	String toStation;
	Long price;
	

}
