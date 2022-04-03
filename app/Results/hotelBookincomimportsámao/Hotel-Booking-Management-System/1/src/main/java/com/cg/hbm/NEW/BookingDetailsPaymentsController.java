package com.cg.hbm.NEW;
import org.springframework.web.bind.annotation.*;
import com.cg.hbm.entites.BookingDetails;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@CrossOrigin
public class BookingDetailsPaymentsController {

@Autowired

 private BookingDetailsPaymentsService bookingdetailspaymentsservice;


@PutMapping
("/Payments/{id}/BookingDetails/setBookingdetails")
public void setBookingdetails(@PathVariable(name="id") int booking_id,@RequestBody BookingDetails bookingdetails){
System.out.println(bookingdetails);
bookingdetailspaymentsservice.setBookingdetails(booking_id,bookingdetails);
}


@GetMapping
("/Payments/{id}/BookingDetails/getBookingdetails")
public BookingDetails getBookingdetails(@PathVariable(name="id") int booking_id){
    System.out.println(booking_id);
return bookingdetailspaymentsservice.getBookingdetails(booking_id);
}


}