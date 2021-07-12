package com.training.ideas.pojo;

import lombok.Data;

@Data
public class InvoiceGeneratorResponse {

	private Double openSeats;

	private Double cabinSeats;

	private Double conferenceRoom;

	private Double meals;

	private Double total;

	private Double totalGST;
}
