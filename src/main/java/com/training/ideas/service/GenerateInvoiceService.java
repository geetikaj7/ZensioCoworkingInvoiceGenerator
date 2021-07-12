package com.training.ideas.service;

import org.springframework.stereotype.Service;

import com.training.ideas.pojo.InvoiceGeneratorRequest;
import com.training.ideas.pojo.InvoiceGeneratorResponse;

@Service
public class GenerateInvoiceService {

	public InvoiceGeneratorResponse generateInvoice(InvoiceGeneratorRequest request) {
		Double oSeat = 0.0, cSeat = 0.0, cRoom = 0.0, m = 0.0;
		if (request.getOpenSeats() != null) {
			oSeat = request.getOpenSeats();
		} if (request.getCabinSeats() != null) {
			cSeat = request.getCabinSeats();
		} if (request.getConferenceRoom() != null) {
			cRoom = request.getConferenceRoom();
		} if (request.getMeals() != null) {
			m = request.getMeals();
		}
		Double openSeat = openSeats(oSeat);
		Double cabinSeat = cabinSeats(cSeat);
		Double confRoomHrs = conferenceRoom(cRoom, oSeat, cSeat);
		Double confRoom = calcConfWithGst(confRoomHrs);
		Double meal = meals(m);
		Double total = totalValue(openSeat, cabinSeat, confRoom, meal);
		Double gstTotal = totalGst(oSeat, cSeat, m, confRoomHrs);
		InvoiceGeneratorResponse response = createResponse(openSeat, cabinSeat, confRoom, meal, total, gstTotal);
		return response;

	}

	private Double totalGst(Double openSeats, Double cabinSeats, Double meals, Double confRoomHrs) {
		Double gstMealValue = gstValue(meals, 100, 12);
		Double gstCabinSeatVal = gstValue(cabinSeats, 10000, 18);
		Double gstOpenSeatVal = gstValue(openSeats, 5000, 18);
		Double gstConfRoomVal = gstValue(confRoomHrs, 200, 18);
		return gstMealValue + gstOpenSeatVal + gstCabinSeatVal + gstConfRoomVal;
	}

	private Double totalValue(Double openSeat, Double cabinSeat, Double confRoom, Double meal) {
		return openSeat + cabinSeat + meal + confRoom;
	}

	private Double conferenceRoom(Double confRoom, Double openSeat, Double cabinSeat) {
		Double freeHrsOpenSeat = openSeat * 5;
		Double freeHrsCabinSeat = cabinSeat * 10;
		Double confRoomHrs = confRoom - (freeHrsCabinSeat + freeHrsOpenSeat);
		return (confRoomHrs > 0) ? confRoomHrs : 0;
	}

	private Double calcConfWithGst(Double confRoomHrs) {
		Double gstConfRoomVal = gstValue(confRoomHrs, 200, 18);
		return gstConfRoomVal + confRoomHrs * 200;
	}

	private InvoiceGeneratorResponse createResponse(Double openSeat, Double cabinSeat, Double confRoom, Double meal,
			Double total, Double gstTotal) {
		InvoiceGeneratorResponse response = new InvoiceGeneratorResponse();
		response.setOpenSeats(openSeat);
		response.setCabinSeats(cabinSeat);
		response.setConferenceRoom(confRoom);
		response.setMeals(meal);
		response.setTotal(total);
		response.setTotalGST(gstTotal);
		return response;
	}

	private Double meals(Double meals) {
		Double mealWithoutGST = meals * 100;
		Double gstMealValue = gstValue(meals, 100, 12);
		return gstMealValue + mealWithoutGST;
	}

	private Double cabinSeats(Double cabinSeats) {
		Double cabSeatWithoutGST = cabinSeats * 10000;
		Double gstCabinSeatVal = gstValue(cabinSeats, 10000, 18);
		return gstCabinSeatVal + cabSeatWithoutGST;
	}

	private Double openSeats(Double openSeats) {
		Double openSeatWithoutGST = openSeats * 5000;
		Double gstOpenSeatVal = gstValue(openSeats, 5000, 18);
		return gstOpenSeatVal + openSeatWithoutGST;
	}

	private Double gstCalculation(Double value, Integer gstPerc) {
		return value * gstPerc / 100;
	}

	private Double gstValue(Double value, Integer cost, Integer gstPerc) {
		Double mealWithoutGST = value * cost;
		Double gstMealValue = gstCalculation(mealWithoutGST, gstPerc);
		return gstMealValue;
	}
}
