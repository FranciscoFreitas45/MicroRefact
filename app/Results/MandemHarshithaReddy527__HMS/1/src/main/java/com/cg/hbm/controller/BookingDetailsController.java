package com.cg.hbm.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hbm.entites.BookingDetails;
import com.cg.hbm.exceptions.BookingDetailsNotFoundException;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.service.IBookingDetailsService;
@RestController
@RequestMapping("/bookingdetails")
public class BookingDetailsController {

@Autowired
 private IBookingDetailsService bService;


@PostMapping("/add")
public ResponseEntity<BookingDetails> addBookingDetails(BookingDetails bookingdetails){
    BookingDetails resultBookingDetails = bService.addBookingDetails(bookingdetails);
    return new ResponseEntity<BookingDetails>(resultBookingDetails, HttpStatus.OK);
}


@PutMapping("/bookingdetails")
public ResponseEntity<BookingDetails> updateBookingDetails(BookingDetails bookingDetails){
    BookingDetails resultBookingDetails = bService.updateBookingDetails(bookingDetails.getBooking_id(), bookingDetails);
    return new ResponseEntity<BookingDetails>(resultBookingDetails, HttpStatus.OK);
}


@GetMapping("/all")
public ResponseEntity<List<BookingDetails>> showAllBookingDetails(){
    List<BookingDetails> resultBookingDetails = bService.showAllBookingDetails();
    return new ResponseEntity<List<BookingDetails>>(resultBookingDetails, HttpStatus.OK);
}


@GetMapping("/{booking_id}")
public ResponseEntity<BookingDetails> showBookingDetails(int booking_id){
    BookingDetails b = bService.showBookingDetails(booking_id);
    if (b != null) {
        return new ResponseEntity<BookingDetails>(b, HttpStatus.OK);
    } else {
        return new ResponseEntity<BookingDetails>(HttpStatus.NOT_FOUND);
    }
}


@DeleteMapping("/{booking_id}")
public BookingDetails removeBookingDetails(int booking_id){
    return bService.removeBookingDetails(booking_id);
}


}