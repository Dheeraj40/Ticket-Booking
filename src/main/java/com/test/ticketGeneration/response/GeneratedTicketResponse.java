package com.test.ticketGeneration.response;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class GeneratedTicketResponse implements Serializable{

	
	private static final long serialVersionUID = 1864531324354L;
	private static final String currentTimeZone = "Asia/Calcutta";
	Long ticketId;
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = currentTimeZone)
	Date validatedTill;
	

	
}
