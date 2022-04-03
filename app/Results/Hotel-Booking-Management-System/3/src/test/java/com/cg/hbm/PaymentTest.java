package com.cg.hbm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.hbm.DTO.BookingDetails;
import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.entites.Payments;
import com.cg.hbm.DTO.RoomDetails;
import com.cg.hbm.DTO.Transactions;
import com.cg.hbm.DTO.User;
import com.cg.hbm.service.IPaymentService;

@SpringBootTest
public class PaymentTest extends AbstractTest {

	@Autowired
	IPaymentService iService;

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getPayments() throws Exception {
		String uri = "/payment/23";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Payments payments = super.mapFromJson(content, Payments.class);
		assertEquals(23, payments.getPayment_id());
	}

	/**
	 * 
	 * @throws Exception
	 */

	@Test
	public void addPayment() throws Exception {
		String uri = "/payment/add";
		Date currentdate = new Date();
		Date todate = new Date(currentdate.getTime() + (10000 * 60 * 60 * 24));
		Hotel hotel = new Hotel("Hyderabad", "Ibaco", "FunTimesRoad", "bestest Hotel", 200, "u@gmail.com", "6776767789",
				"6578956788", "mom.com");
		RoomDetails room = new RoomDetails("167", 1208, "Ac Room", true, hotel);
		User u = new User("Hari", "fathima@gmail.com", "tinu", "customer", "7654367890", "Fathekan Nagar");
		hotel.setHotel_id(3);
		room.setRoom_id(2);
		u.setUser_id(1);
		BookingDetails bookingdetails = new BookingDetails(currentdate, todate, 16, 9, 24000, hotel, room, u);
		bookingdetails.setBooking_Id(104);
		Transactions transaction = new Transactions(58000);
		transaction.setTransaction_id(17);
		Payments p = new Payments(bookingdetails, transaction);
		p.setTransactions(transaction);
		p.setBookingdetails(bookingdetails);
		String inputJson = super.mapToJson(p);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Payments p1 = super.mapFromJson(content, Payments.class);
		assertEquals(58000, p1.getTransactions().getAmount());
	}

}
