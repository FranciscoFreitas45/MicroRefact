package com.cg.hbm.DTO;
import com.cg.hbm.DTO.Hotel;




/***************************************************************************************************************
 *@author          	L Mahendra
 *Description      	It is simple Pojo class for RoomDetails having different attributes.
 *Version          	1.0
 *Created Date    	22-MAR-2021
 **************************************************************************************************************/

public class RoomDetails {
	
	private int room_id;
	private String room_no;
	private String room_type;
	private double rate_per_day;
	private boolean isAvailable;

	private Hotel hotel;

	public RoomDetails() {
	}

	/**
	 * 
	 * @param room_no
	 * @param rate_per_day
	 * @param room_type
	 * @param isAvailable
	 * @param hotel
	 */

	public RoomDetails(String room_no, double rate_per_day, String room_type, boolean isAvailable, Hotel hotel) {
		super();
		this.room_no = room_no;
		this.room_type = room_type;
		this.rate_per_day = rate_per_day;
		this.isAvailable = isAvailable;
		this.hotel = hotel;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public double getRate_per_day() {
		return rate_per_day;
	}

	public void setRate_per_day(double rate_per_day) {
		this.rate_per_day = rate_per_day;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}