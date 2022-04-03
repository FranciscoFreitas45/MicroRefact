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


import com.cg.hbm.entites.BookingDetails;
import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.DTO.RoomDetails;
import com.cg.hbm.DTO.User;
import com.cg.hbm.service.IBookingDetailsService;
@SpringBootTest
public class BookingDetailsTest extends AbstractTest {
	@Autowired
	IBookingDetailsService bService;
	
	
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getShowAllBookingDetails() throws Exception {
		String uri = "/bookingdetails/all";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BookingDetails bookingdetails[] = super.mapFromJson(content, BookingDetails[].class);
		assertEquals(9, bookingdetails[0].getNo_of_children());
	}
	/**
	 * 
	 * @throws Exception
	 */

	@Test
	public void addBookingDetails() throws Exception {
		String uri="/bookingdetails/add";
		
		Hotel hotel=new Hotel("Vijayawada","kennara", "gjhjhkjlmll","Best Hotel", 18,"manikonda@gmail.com", "9898986788", "9898984530", "SmartHotelManerva.com"
				);
				hotel.setHotel_id(3);
		RoomDetails roomdetails=new RoomDetails("102",1300, "DoubleDelux",true,hotel);


		User user=new User("Varshitha","hars@gmail.com","varsha","searching", "9876543210","jhbjkhnhjk");
		user.setUser_id(9);
		Date currentdate=new Date();
		Date todate=new Date(currentdate.getTime()+(10000*60*60*24));
		BookingDetails bookingdetails=new BookingDetails(currentdate,todate,480, 100,40000, hotel,roomdetails,user);
		bookingdetails.setAmount(45000);
		bookingdetails.setBooked_from(currentdate);
		bookingdetails.setBooked_from(todate);
		bookingdetails.setNo_of_adults(450);
		bookingdetails.setNo_of_children(80);
		bookingdetails.setBooking_id(3);
		bookingdetails.setHotel(hotel);
		bookingdetails.setRoomdetails(roomdetails);
		bookingdetails.setUser(user);
		String inputJson=super.mapToJson(bookingdetails);
		MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType. APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content=mvcResult.getResponse().getContentAsString(); 
		BookingDetails b=super.mapFromJson(content, BookingDetails.class);
		assertEquals(450,b.getNo_of_adults());
	}
	/**
	 * 
	 * @throws Exception
	 */
	
	@Test
	public void getBookingDetails() throws Exception {
		String uri = "/bookingdetails/7";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BookingDetails bookingdetails = super.mapFromJson(content, BookingDetails.class);
		assertEquals(16, bookingdetails.getNo_of_adults());
	}
		

}