package com.cg.hbm.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hbm.dao.IHotelRepository;
import com.cg.hbm.entites.Hotel;
import com.cg.hbm.exceptions.HotelNotFoundException;

/**************************************************************************************
* @author            Harshitha 
* Description        It is a service class that provides the services to add a hotel, 
* 					 remove a hotel, update a hotel , get a hotel, show all hotels.
* 
* Version 1.0        Created Date 24-March-2021
***************************************************************************************/
@Service
@Transactional
public class IHotelServiceImpl implements IHotelService {
	@Autowired
	IHotelRepository hDao;
	/***************************************************************************************************************
	 * Method                                addHotel 
	 * Description                           To add the HotelDetails to the database
	 * @param hotel                          HotelDetails to be added to the database
	 * @returns Hotel                        returns Hotel after adding the hotelDetails with the hotel_id to the database 
	 * @throws HotelNotFoundException        It is raised when hotel already exists
	 ****************************************************************************************************************/
	@Override
	public Hotel addHotel(Hotel hotel) throws HotelNotFoundException{
		Optional<Hotel> h = hDao.findById(hotel.getHotel_id());
		if (h.isEmpty()) {
			return hDao.saveAndFlush(hotel);
		} else {
			throw new HotelNotFoundException("Hotel already exists");
		}
	}
	

	/***********************************************************************************************
	 * Method                           updateHotel
	 * Description                      To update the hoteldetails in the database
	 * @param hotel                     hoteldetails to be updated
	 * @returns Hotel                   returns Hotel after adding the hoteldetails with hotelId to database 
	 * @throws HotelNotFoundException   It is raised when hotelId not found
	 
     ***************************************************************************************************/
	
	@Override
	public Hotel updateHotel(int hotelId, Hotel hotel) throws HotelNotFoundException {
		// TODO Auto-generated method stub
		Optional<Hotel> h = hDao.findById(hotelId);
		if (h.isEmpty()) {
			throw new HotelNotFoundException("Hotel not found");
		}
		else
		hDao.save(hotel);
		return hotel;
		
	}
	
	/****************************************************************************************
	 * Method                           removeHotel
	 * Description                      To remove the hoteldetails in the database
	 * @param hotel_id                  hoteldetails to be removed with hotel_id from the database
	 * @returns Hotel                   returns Hotel
	 * @throws HotelNotFoundException   It is raised when hotel_id does not exists
	 * CreatedBy                        Harshitha 
	 * Created Date                     23-MAR-2021
	 *****************************************************************************************/


	@Override
	public Hotel removeHotel(int hotel_id) throws HotelNotFoundException{
			Optional<Hotel> op=hDao.findById(hotel_id);
			if(op.isPresent()) {
				hDao.deleteById(hotel_id);
				return op.get();
			}
			else throw new HotelNotFoundException("Hotel with given Id doesn't exist.");
			
	}
	
	
	/******************************************************************************************
	 * Method                           showAllHotels
	 * Description                      To get all the hoteldetails from the database
	 * @param hotel                     To fetch all the hoteldetails from the database
	 * @returns Hotel                   returns hoteldetails after fetching from the database 
	 * @throws HotelNotFoundException   It is raised when hotel does not found
	 * CreatedBy                        Harshitha
	 * Created Date                     23-MAR-2021
	 *******************************************************************************************/
	
    @Override
	public List<Hotel> showAllHotels() throws HotelNotFoundException{
    	List<Hotel> h = hDao.findAll();
		if (h.isEmpty()) {
			throw new HotelNotFoundException("Hotel not found");
		}
		return h;
	}

    
    /*********************************************************************************************
   	 * Method                           showhotel 
   	 * Description                      To get the hoteldetails from the database
   	 * @param hotel                     To fetch the hoteldetails from the database
   	 * @returns Hotel                   returns hoteldetails with hotel_id after fetching the database
   	 * @throws HotelNotFoundException   It is raised when hotel does not exists
   	 ***********************************************************************************************/

	

	@Override
	public Hotel showHotel(int hotel_id) throws HotelNotFoundException{

		
		Optional<Hotel> h=hDao.findById(hotel_id);
		if(h.isEmpty()) {
			throw new HotelNotFoundException("Given HotelId does not exist");
		}
		return h.get();
			
		
		
		 
		}

		 
	
	}
	

