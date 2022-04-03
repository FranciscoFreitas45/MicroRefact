
package com.cg.hbm.service;


import java.util.List;



import com.cg.hbm.entites.BookingDetails;
import com.cg.hbm.exceptions.BookingDetailsNotFoundException;
/***************************************************************************************************************
 *@author          	Harshitha
 *Description      	It is a IBookingDetailsService Interface and provides methods for the Implementation class.  
 *Version          	1.0
 *Created Date    	31-MAR-2021
 **************************************************************************************************************/

public interface IBookingDetailsService {

	BookingDetails addBookingDetails(BookingDetails bookingdetails) throws BookingDetailsNotFoundException;

	BookingDetails updateBookingDetails(int bookingId, BookingDetails bookingDetails)
			throws BookingDetailsNotFoundException;

	List<BookingDetails> showAllBookingDetails() throws BookingDetailsNotFoundException;

	BookingDetails showBookingDetails(int booking_id) throws BookingDetailsNotFoundException;

	BookingDetails removeBookingDetails(int booking_id) throws BookingDetailsNotFoundException;

	
	
}

