package com.cg.hbm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.entites.RoomDetails;
import com.cg.hbm.service.IRoomDetailsService;

@SpringBootTest
public class RoomDetailsTest extends AbstractTest {

	@Autowired
	IRoomDetailsService rService;

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
	public void deleteRoom() throws Exception {
		String uri = "/room_details/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RoomDetails room = super.mapFromJson(content, RoomDetails.class);
		String delUri = "/room_details/1";
		String inputJson = super.mapToJson(room);
		this.mvc.perform(
				MockMvcRequestBuilders.delete(delUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void addRoomDetails() throws Exception {
		Hotel hotel = new Hotel("city", "hotel_name", "address", "description", 200, "email", "phone1", "phone2",
				"website");
		hotel.setHotel_id(3);		
		RoomDetails room = new RoomDetails();
		room.setRate_per_day(1130);
		room.setHotel(hotel);
		room.setRoom_no("12345");
		room.setRoom_type("udx");
		room.setAvailable(true);
		String uri = "/room_details/add";
		String inputJson = super.mapToJson(room);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RoomDetails newRoom = super.mapFromJson(content, RoomDetails.class);
		assertEquals("12345", newRoom.getRoom_no());
	}

	@Test
	public void UpdateRoom() throws Exception {
		String uri = "/room_details/15";
		String putUri = "/room_details/roomDetails";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RoomDetails room = super.mapFromJson(content, RoomDetails.class);
		room.setRoom_no("99");
		room.setRoom_type("Ac");
		String inputJson = super.mapToJson(room);
		MvcResult mvcResult1 = mvc.perform(
				MockMvcRequestBuilders.put(putUri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status1 = mvcResult1.getResponse().getStatus();
		assertEquals(200, status1);
		String content1 = mvcResult1.getResponse().getContentAsString();
		RoomDetails roomup = super.mapFromJson(content1, RoomDetails.class);
		assertEquals("Ac", roomup.getRoom_type());
		assertEquals("99", roomup.getRoom_no());

	}

	@Test
	public void getRoomDetails() throws Exception {
		String uri = "/room_details/all";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		RoomDetails room[] = super.mapFromJson(content, RoomDetails[].class);
		assertEquals(1, room[0].getRoom_id());
	}

}
