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
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.service.IHotelService;
import org.springframework.http.MediaType;




/*****************************************************************************************************
 * @author          Harshitha
 * Description      It is a Controller class that provides the
                    services to add a customer, remove a customer, update a customer and view a customer
 *****************************************************************************************************/
@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	IHotelService hService;
	/**************************************************************************************************
	 * Method                             addHotel 
	 * Description                        To add the hoteldetails to the database
	 * @param hotel                       hoteldetails to be added to the database
	 * @param RequestBody                 It maps the HttpRequest to handler methods of MVC and RestController
	 * @returns Hotel                     returns hoteldetails after adding the hoteldetails to database 
	 * @throws HotelNotFoundException     It is raised when hotel already exists
	 
	 **************************************************************************************************/
	
	@PostMapping("/add")
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) throws HotelNotFoundException  {
		Hotel resulthotel=hService.addHotel(hotel);
		return new ResponseEntity<Hotel>(resulthotel,HttpStatus.OK) ;
	}
	/*****************************************************************************************************
	 * Method                            removeHotel 
	 * Description                       To remove the hoteldetails from the database
	 * @param customer                   hoteldetails will be deleted from the database
	 * @param PathVariable                
	 * @returns Hotel                    returns HotelDetails 
	 * @throws HotelNotFoundException    It is raised when hotel with id already exists
	 ******************************************************************************************************/
	@DeleteMapping("/{hotel_id}")
	public Hotel removeHotel(@PathVariable int hotel_id) throws HotelNotFoundException {
		return hService.removeHotel(hotel_id);
	}
	/*****************************************************************************************************
	 * Method                            updateHotel 
	 * Description                       To update the hoteldetails in the database
	 * @param hotel                      hoteldetails to be updated
	 * @returns Hotel                    returns hoteldetails after updating the hoteldetails to database 
	 * @throws HotelNotFoundException    It is raised when hotelId not found
	 ****************************************************************************************************/
	@PutMapping("/hotel")
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) throws HotelNotFoundException {
		Hotel resultHotel = hService.updateHotel(hotel.getHotel_id(),hotel);
		return new ResponseEntity<Hotel>(resultHotel, HttpStatus.OK);
	}
	
	
	/***********************************************************************************************
	 * Method                            ShowAllHotels
	 * Description                       To get all the hoteldetails from the database
	 * @returns List<Hotel>              returns hoteldetails after fetching from the database 
	 * @throws HotelNotFoundException    It is raised when hotel does not found
	 ************************************************************************************************/
	@GetMapping(value = "/all",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> showAllHotels() throws HotelNotFoundException{
		List<Hotel> resultHotel=hService.showAllHotels();
		return new ResponseEntity<List<Hotel>>(resultHotel, HttpStatus.OK);
	}
	
	
	/************************************************************************************************
	 * Method                             showHotel 
	 * Description                        To find the hoteldetails from the database using id
	 * @param hotel                       To fetch the hoteldetails from the database
	 * @returns Hotel                     returns hoteldetails with id after fetching from the database
	 * @throws HotelNotFoundException     It is raised when hoteldetails does not exists
	 *************************************************************************************************/
	@GetMapping(value ="/{hotel_id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel>showHotel(@PathVariable int hotel_id) throws HotelNotFoundException {
		Hotel h=hService.showHotel(hotel_id);
		if(h!=null) {
			return new ResponseEntity<Hotel>(h,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
		}
		
	}

}
	

