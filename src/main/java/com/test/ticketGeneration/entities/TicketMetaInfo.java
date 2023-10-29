package com.test.ticketGeneration.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class TicketMetaInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tktmetainfo_generator")
	@SequenceGenerator(allocationSize = 1, initialValue = 1,name="tktmetainfo_generator", sequenceName = "ticketmetainfo_seq")
	Long tktId;
	
	String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "start_station_id", referencedColumnName = "station_id")
	StationLkp startStation;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "end_station_id", referencedColumnName = "station_id")
	StationLkp endStation;
	
	Long price;
	
	Integer reedemCount;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date validatedTo;
	
	
	

}
