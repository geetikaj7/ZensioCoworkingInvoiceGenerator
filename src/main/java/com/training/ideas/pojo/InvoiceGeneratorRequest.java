package com.training.ideas.pojo;

import lombok.Data;

@Data
public class InvoiceGeneratorRequest {

	private Double openSeats;

	private Double cabinSeats;

	private Double conferenceRoom;

	private Double meals;
}
