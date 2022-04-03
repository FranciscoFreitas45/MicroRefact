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
import com.cg.hbm.service.IBookingDetailsService;
import org.springframework.web.client.RestTemplate;
import com.cg.hbm.DTO.User;


@RestController
@RequestMapping("/bookingdetails")
/**
 * 
 * @author Harshitha
 *
 */
public class BookingDetailsController {
	
	@Autowired
	IBookingDetailsService bService;
	@Autowired
	RestTemplate restTemplate;
	/**************************************************************************************************
	 * Method                                      addBookingDetails
	 * Description                                 To add the bookingdetails to the database
	 * @param bookingdetails                       bookingdetails to be added to the database
	 * @param RequestBody                          It maps the HttpRequest to handler methods of MVC and RestController
	 * @returns BookingDetails                     returns bookingdetails after adding the hoteldetails to database 
	 * @throws BookingDetailsNotFoundException     It is raised when bookingdetails already exists
	 
	 **************************************************************************************************/
	
	@PostMapping("/add")
	public ResponseEntity<BookingDetails> addBookingDetails(@RequestBody BookingDetails bookingdetails) throws Exception {
		BookingDetails resultBookingDetails=bService.addBookingDetails(bookingdetails);
		return new ResponseEntity<BookingDetails>(resultBookingDetails,HttpStatus.OK) ;
	}
	/*****************************************************************************************************
	 * Method                                       removeBookingDetails 
	 * Description                                  To remove the bookingdetails from the database
	 * @param bookingDetails                        bookingdetails will be deleted from the database
	 * @param PathVariable                
	 * @returns BookingDetails                      returns BookingDetails 
	 * @throws BookingDetailsNotFoundException      It is raised when bookingdetails with id already exists
	 ******************************************************************************************************/
	@DeleteMapping("/{booking_id}")
	public BookingDetails removeBookingDetails(@PathVariable int booking_id)throws BookingDetailsNotFoundException {
		return bService.removeBookingDetails(booking_id);
	}
	/*****************************************************************************************************
	 * Method                                     updateBookingDetails
	 * Description                                To update the bookingdetails in the database
	 * @param bookingdetails                      bookingdetails to be updated
	 * @returns BookingDetails                    returns bookingdetails after updating the bookingdetails to database 
	 * @throws BookingDetailsNotFoundException    It is raised when booking_id not found
	 ****************************************************************************************************/
	@PutMapping("/bookingdetails")
	public ResponseEntity<BookingDetails> updateBookingDetails(@RequestBody BookingDetails bookingDetails) throws BookingDetailsNotFoundException {
		BookingDetails resultBookingDetails = bService.updateBookingDetails(bookingDetails.getBooking_id(),bookingDetails);
		return new ResponseEntity<BookingDetails>(resultBookingDetails, HttpStatus.OK);
	}
	/***********************************************************************************************
	 * Method                                     ShowAllBookingDetails
	 * Description                                To get all the bookingdetails from the database
	 * @returns List<BookingDetails>                       returns bookingdetails after fetching from the database 
	 * @throws BookingDetailsNotFoundException    It is raised when bookingdetails does not found
	 ************************************************************************************************/
	@GetMapping("/all")
	public ResponseEntity<List<BookingDetails>>showAllBookingDetails() throws BookingDetailsNotFoundException{
		List<BookingDetails> resultBookingDetails=bService.showAllBookingDetails();
		return new ResponseEntity<List<BookingDetails>>(resultBookingDetails, HttpStatus.OK);
	}
	/************************************************************************************************
	 * Method                                      showBookingDetails 
	 * Description                                 To find the bookingdetails from the database using id
	 * @param bookingdetails                       To fetch the bookingdetails from the database
	 * @returns BookingDetails                     returns bookingdetails with id after fetching from the database
	 * @throws BookingDetailsNotFoundException     It is raised when hoteldetails does not exists
	 *************************************************************************************************/
	@GetMapping("/{booking_id}")
	public ResponseEntity<BookingDetails>showBookingDetails(@PathVariable int booking_id) throws BookingDetailsNotFoundException {
		BookingDetails b=bService.showBookingDetails(booking_id);
		if(b!=null) {
			return new ResponseEntity<BookingDetails>(b,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<BookingDetails>(HttpStatus.NOT_FOUND);
		}
		
	}
}
 
/*
 * import java.util.List; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.cg.hbm.entites.BookingDetails; import com.cg.hbm.entites.Hotel;
 * import com.cg.hbm.entites.RoomDetails; import
 * com.cg.hbm.exceptions.BookingDetailsNotFoundException; import
 * com.cg.hbm.exceptions.HotelNotFoundException; import
 * com.cg.hbm.exceptions.RoomDetailsNotFoundException; import
 * com.cg.hbm.service.IBookingDetailsService; import
 * com.cg.hbm.service.IHotelService; import
 * com.cg.hbm.service.IRoomDetailsService;
 * 
 * @RestController
 * 
 * @RequestMapping("/bookingdetails") public class BookingDetailsController {
 * 
 * @Autowired IBookingDetailsService bSer;
 *//**
	 * 
	 * @param bookingdetails
	 * @return BookingDetails
	 */
/*
 * 
 * @PostMapping("/add") public BookingDetails addBookingDetails(@RequestBody
 * BookingDetails bookingdetails) { return
 * bSer.addBookingDetails(bookingdetails); }
 * 
 *//**
	 * 
	 * @param bookingdetails
	 * @return BookingDetails
	 */
/*
 * 
 * @PutMapping("/update") public BookingDetails
 * updateBookingDetails(@RequestBody BookingDetails bookingdetails) { return
 * bSer.updateBookingDetails(bookingdetails); }
 * 
 * 
 *//**
	 * 
	 * @param bookingdetails
	 * @return BookingDetails
	 * @throws BookingDetailsNotFoundException
	 */
/*
 * @DeleteMapping("/remove") public BookingDetails
 * removeBookingDetails(@RequestBody BookingDetails bookingdetails)throws
 * BookingDetailsNotFoundException {
 * 
 * try { return bSer.removeBookingDetails(bookingdetails); }catch(Exception e) {
 * throw new
 * BookingDetailsNotFoundException("booking_id not available please enter a valid number"
 * ); } }
 * 
 *//**
	 * 
	 * @return List<BookingDetails>
	 */
/*
 * @GetMapping("/all") public List<BookingDetails> showAllBookingDetails(){
 * return bSer.showAllBookingDetails(); }
 *//**
	 * 
	 * @param booking_id
	 * @return BookingDetails
	 * @throws BookingDetailsNotFoundException
	 *//*
		 * 
		 * @GetMapping("/{booking_id}") public BookingDetails
		 * showBookingDetails(@PathVariable int booking_id)throws
		 * BookingDetailsNotFoundException {
		 * 
		 * try { return bSer.showBookingDetails(booking_id); }catch(Exception e) { throw
		 * new
		 * BookingDetailsNotFoundException("bookingid not available.....give valid number"
		 * ); } }
		 * 
		 * }
		 * 
		 * 
		 */