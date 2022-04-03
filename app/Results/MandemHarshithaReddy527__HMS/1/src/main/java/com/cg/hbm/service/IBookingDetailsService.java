package com.cg.hbm.service;
 import java.util.List;
import com.cg.hbm.entites.BookingDetails;
import com.cg.hbm.exceptions.BookingDetailsNotFoundException;
public interface IBookingDetailsService {


public BookingDetails addBookingDetails(BookingDetails bookingdetails)
;

public BookingDetails updateBookingDetails(int bookingId,BookingDetails bookingDetails)
;

public List<BookingDetails> showAllBookingDetails()
;

public BookingDetails showBookingDetails(int booking_id)
;

public BookingDetails removeBookingDetails(int booking_id)
;

}