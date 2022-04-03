package com.cg.hbm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.entites.BookingDetails;
@RestController
@CrossOrigin
public class BookingDetailsPaymentsController {

@Autowired
 private BookingDetailsPaymentsService bookingdetailspaymentsservice;


@PutMapping
("/Payments/{id}/BookingDetails/setBookingdetails")
public void setBookingdetails(@PathVariable(name="id") int booking_id,@RequestBody BookingDetails bookingdetails){
bookingdetailspaymentsservice.setBookingdetails(booking_id,bookingdetails);
}


@GetMapping
("/Payments/{id}/BookingDetails/getBookingdetails")
public BookingDetails getBookingdetails(@PathVariable(name="id") int booking_id){
return bookingdetailspaymentsservice.getBookingdetails(booking_id);
}


}