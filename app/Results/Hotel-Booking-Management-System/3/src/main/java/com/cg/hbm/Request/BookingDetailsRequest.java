package com.cg.hbm.Request;
import com.cg.hbm.DTO.BookingDetails;
public interface BookingDetailsRequest {

   public void setBookingdetails(BookingDetails bookingdetails,int booking_id);
   public BookingDetails getBookingdetails(int booking_id);
}