package com.test.ticketGeneration.response;

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
public class StationDetail implements Serializable{

	
	private static final long serialVersionUID = 1981309989341408944L;
	
	Map<String, PriceDto> stations;
	
	
	public static void main(String[] args) throws JsonProcessingException {
		
		Map<String, PriceDto> d = new HashMap<String, PriceDto>();
		for(int i = 0; i < 5; i++) {
			
			
				d.put(String.valueOf((char)('A'+i)), (i == 0 ? PriceDto.builder().price(Long.valueOf((long) (i*5))).startStation(true).build()
						: i == 4 ? PriceDto.builder().price(Long.valueOf((long) (i*5))).lastStation(true).build() 
								: PriceDto.builder().price(Long.valueOf((long) (i*5))).build()));
			
		}
		
		
		StationDetail s = StationDetail.builder().stations(d).build();
		
		
		System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(s));
	}

}
