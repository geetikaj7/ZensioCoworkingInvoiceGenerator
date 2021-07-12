package com.training.ideas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.ideas.pojo.InvoiceGeneratorRequest;
import com.training.ideas.pojo.InvoiceGeneratorResponse;
import com.training.ideas.service.GenerateInvoiceService;

@SpringBootTest
public class GenerateInvoiceServiceTest {
	@Autowired
	GenerateInvoiceService service;

	@Test
	void generateInvoiceTest() {
		InvoiceGeneratorRequest request = setReq(0.0, 1.0, 50.0, 10.0);
		InvoiceGeneratorResponse response = service.generateInvoice(request);
		assertEquals(0.0, response.getOpenSeats());
		assertEquals(11800.0, response.getCabinSeats());
		assertEquals(9440.0, response.getConferenceRoom());
		assertEquals(1120.0, response.getMeals());
		assertEquals(22360.0, response.getTotal());
		assertEquals(3360.0, response.getTotalGST());
	}

	private InvoiceGeneratorRequest setReq(Double openSeat, Double cabinSeat, Double confRoom, Double meal) {
		InvoiceGeneratorRequest request = new InvoiceGeneratorRequest();
		request.setOpenSeats(openSeat);
		request.setCabinSeats(cabinSeat);
		request.setConferenceRoom(confRoom);
		request.setMeals(meal);
		return request;
	}

}
