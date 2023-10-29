package com.test.ticketGeneration.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class PriceDto implements Serializable{
	

	private static final long serialVersionUID = 18451205120563L;
	@JsonInclude(Include.NON_NULL)
	Boolean startStation;
	@JsonInclude(Include.NON_NULL)
	Boolean lastStation;
	
	Long price;
}
