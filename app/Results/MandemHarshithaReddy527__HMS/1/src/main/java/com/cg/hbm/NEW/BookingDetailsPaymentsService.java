package com.cg.hbm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.dao.IBookingDetailsRepository;
import com.cg.hbm.entites.BookingDetails;
@Service
public class BookingDetailsPaymentsService {

@Autowired
 private IBookingDetailsRepository ibookingdetailsrepository;


public void setBookingdetails(int booking_id,BookingDetails bookingdetails){
ibookingdetailsrepository.setBookingdetails(booking_id,bookingdetails);
}


public BookingDetails getBookingdetails(int booking_id){
return ibookingdetailsrepository.getBookingdetails(booking_id);
}


}