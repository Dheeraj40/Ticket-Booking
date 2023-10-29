package com.test.ticketGeneration.res;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RecoveredTicket implements Serializable{

	private static final long serialVersionUID = 1L;
	Boolean isRedemable;
	@JsonInclude(Include.NON_NULL)
	String cause;
	@JsonInclude(Include.NON_NULL)
	String reedemed;
}
