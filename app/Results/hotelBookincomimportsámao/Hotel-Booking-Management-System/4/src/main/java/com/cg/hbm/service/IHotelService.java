
package com.cg.hbm.service;


import java.util.List;



import com.cg.hbm.entites.Hotel;
import com.cg.hbm.exceptions.HotelNotFoundException;
/*************************************************************************************************
 *@author          	Harshitha
 *Description      	It is a IHotelService Interface and provides methods for the Implementation class.  
 *Version          	1.0
 *Created Date    	31-MAR-2021
 *************************************************************************************************/

public interface IHotelService {
	public Hotel addHotel(Hotel hotel)throws HotelNotFoundException;

	//public Hotel removeHotel(int hotel_id)throws HotelNotFoundException;
	public List<Hotel> showAllHotels()throws HotelNotFoundException;
	public Hotel showHotel(int hotel_id) throws HotelNotFoundException;
	Hotel removeHotel(int hotel_id) throws HotelNotFoundException;

	Hotel updateHotel(int hotelId, Hotel hotel) throws HotelNotFoundException;

	
}

	
	
	


