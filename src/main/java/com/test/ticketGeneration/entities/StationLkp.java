package com.test.ticketGeneration.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StationLkp {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_generator" )
	@SequenceGenerator(allocationSize = 1, initialValue = 1,name="station_generator", sequenceName = "stationlkp_seq")
	@Column(name = "station_id")
	Long stationId;
	String stationName; 
	Boolean lastStation;
	Boolean firstStation;
	Long price;	
}
