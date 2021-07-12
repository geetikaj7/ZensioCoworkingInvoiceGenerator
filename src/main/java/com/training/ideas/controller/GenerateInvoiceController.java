package com.training.ideas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.ideas.pojo.InvoiceGeneratorRequest;
import com.training.ideas.pojo.InvoiceGeneratorResponse;
import com.training.ideas.service.GenerateInvoiceService;

@RestController
public class GenerateInvoiceController {

	@Autowired
	GenerateInvoiceService generateInvoiceService;

	@PostMapping("/generateInvoice")
	public InvoiceGeneratorResponse generateInvoice(@RequestBody InvoiceGeneratorRequest request) {
		return generateInvoiceService.generateInvoice(request);
	}

}
