package com.cg.hbm.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hbm.dao.IBookingDetailsRepository;
import com.cg.hbm.entites.BookingDetails;
import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.exceptions.BookingDetailsNotFoundException;
import com.cg.hbm.exceptions.HotelNotFoundException;

/**************************************************************************************
* @author            Harshitha 
* Description        It is a service class that provides the services to add  bookingdetails, 
* 					 remove bokingdetails, update bookingdetails, get bookingdetails,
*                    show all bookingdetails.
* 
* Version 1.0        Created Date 24-March-2021
***************************************************************************************/
@Service
public class IBookingDetailsServiceImpl implements IBookingDetailsService {

	
	@Autowired
	IBookingDetailsRepository bDao;

	/***************************************************************************************************************
	 * Method                                         addBookingDetails
	 * Description                                    To add the BookingDetails to the database
	 * @param bookingdetails                          BookingDetails to be added to the database
	 * @returns BookingDetails                        returns BookingDetails after adding the bookingdetails
	                                                  with booking_id to the database 
	 * @throws BookingDetailsNotFoundException        It is raised when bookingdetails already exists
	 ****************************************************************************************************************/

	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingdetails) throws BookingDetailsNotFoundException {
		
		Optional<BookingDetails> b = bDao.findById(bookingdetails.getBooking_id());
		if (b.isEmpty()) {
			return bDao.saveAndFlush(bookingdetails);
		} else {
			throw new BookingDetailsNotFoundException("BookingDetails already exists");
		}
	}

	
	/***********************************************************************************************
	 * Method                           updateBookingDetails 
	 * Description                      To update the bookingdetails in the database
	 * @param hotel                     bookingdetails to be updated
	 * @returns Hotel                   returns Hotel after adding the bookingdetails with bookingId
	 *                                  to database 
	 * @throws HotelNotFoundException   It is raised when bookingId not found
	 
     ***************************************************************************************************/
	
	
	@Override
	public BookingDetails updateBookingDetails(int bookingId, BookingDetails bookingDetails) throws BookingDetailsNotFoundException {
		// TODO Auto-generated method stub
		Optional<BookingDetails> b = bDao.findById(bookingId);
		if (b.isEmpty()) {
			throw new BookingDetailsNotFoundException("BookingDetails not found");
		}
		else
		bDao.save(bookingDetails);
		return bookingDetails;
		
	}

	
	/******************************************************************************************
	 * Method                                    showAllBookingDetails
	 * Description                               To get all the bookingdetails from the database
	 * @param bookingdetails                     To fetch all the bookingdetails from the database
	 * @returns BookingDetails                   returns bookingdetails after fetching from the database 
	 * @throws BookinDetailsNotFoundException    It is raised when bookingdetails does not found
	 * CreatedBy                                 Harshitha
	 * Created Date                              23-MAR-2021
	 *******************************************************************************************/

	@Override
	public List<BookingDetails> showAllBookingDetails() throws BookingDetailsNotFoundException {
		List<BookingDetails> boo = bDao.findAll();
		if (boo.isEmpty()) {
			throw new BookingDetailsNotFoundException("BookingDetails not found");
		}
		return boo;
	}

	/*********************************************************************************************
	 * Method                                    showBookingDetails 
	 * Description                               To get the bookingdetails from the database
	 * @param bookingdetails                     To fetch the bookingdetails from the database
	 * @returns BookingDetails                   returns bookingdetails with booking_id after fetching the database
	 * @throws BookingDetailsNotFoundException   It is raised when bookingdetails does not exists
	 * CreatedBy                                 Keerthi
	 * Created Date                              23-MAR-2021
	 ***********************************************************************************************/
	
	@Override
	public BookingDetails showBookingDetails(int booking_id) throws BookingDetailsNotFoundException {
		Optional<BookingDetails> b=bDao.findById(booking_id);
		if(b.isEmpty()) {
			throw new BookingDetailsNotFoundException("Given BookingDetailsId does not exist");
		}
		return b.get();
	}

	/****************************************************************************************
	 * Method                                    removeBookingDetails
	 * Description                               To remove the bookingdetails in the database
	 * @param booking_id                         bookingdetails to be removed with booking_id from the database
	 * @returns BookingDetails                   returns BookingDetails
	 * @throws BookingDetailsNotFoundException   It is raised when booking_id does not exists
	 * CreatedBy                                 Harshitha 
	 * Created Date                              23-MAR-2021
	 *****************************************************************************************/
	
	@Override
	public BookingDetails removeBookingDetails(int booking_id) throws BookingDetailsNotFoundException {
		
		Optional<BookingDetails> op=bDao.findById(booking_id);
		if(op.isPresent()) {
			bDao.deleteById(booking_id);
			return op.get();
		}
		else throw new BookingDetailsNotFoundException("BookingDetails with given Id doesn't exist.");
	}
}
