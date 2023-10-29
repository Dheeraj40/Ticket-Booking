package com.test.ticketGeneration.res;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class StationInformation implements Serializable{

	
	private static final long serialVersionUID = 1981309989341408944L;
	
	Map<String, PriceDto> stations;
	
	
	public static void main(String[] args) throws JsonProcessingException {
		
		Map<String, PriceDto> d = new HashMap<String, PriceDto>();
		
		d.put("Jaipur", PriceDto.builder().price(Long.valueOf((long) (5))).startStation(true).build());
		d.put("Jhunjhunu", PriceDto.builder().price(Long.valueOf((long) (15))).startStation(true).build());
		d.put("Sikar", PriceDto.builder().price(Long.valueOf((long) (20))).startStation(true).build());
		d.put("Churu", PriceDto.builder().price(Long.valueOf((long) (10))).startStation(true).build());
		d.put("Bhiwani", PriceDto.builder().price(Long.valueOf((long) (50))).startStation(true).build());

		
		StationInformation s = StationInformation.builder().stations(d).build();
		
		
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(s));
	}

}
