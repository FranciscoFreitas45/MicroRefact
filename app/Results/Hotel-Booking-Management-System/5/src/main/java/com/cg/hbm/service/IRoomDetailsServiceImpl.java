package com.cg.hbm.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hbm.dao.IRoomDetailsRepository;
import com.cg.hbm.DTO.Hotel;
import com.cg.hbm.entites.RoomDetails;
import com.cg.hbm.exceptions.HotelNotFoundException;
import com.cg.hbm.exceptions.RoomDetailsNotFoundException;
import com.cg.hbm.exceptions.UserNotFoundException;



/****************************
 * @author L Mahendra
 * Description : It is a service class that provides the services to add a room details, remove a room details, update a room details and
 *               view room details.
 * Version 1.0
 * Created Date 24-March-2021
 ****************************/

@Service
@Transactional
public class IRoomDetailsServiceImpl implements IRoomDetailsService {
	@Autowired
	IRoomDetailsRepository rDao;
	
	
	/****************************
	 * Method                                  -addRoomDetails
	 * Description                             -To add the RoomDetails to the database
	 * @param roomDetails                      - RoomDetails to be added to the database
	 * @returns RoomDetails                    - returns RoomDetails after adding the RoomDetails to database 
	 * @throws RoomDetailsNotFoundException    - It is raised when RoomDetails already exists
	 ****************************/
	@Override
	public RoomDetails addRoomDetails(RoomDetails roomDetails) throws RoomDetailsNotFoundException{
		Optional<RoomDetails> r = rDao.findById(roomDetails.getRoom_id());
		if (r.isEmpty()) {
			return rDao.saveAndFlush(roomDetails);
		} else {
			throw new RoomDetailsNotFoundException("RoomDetails already exists");
		}
	}
	

	/****************************
	 * Method                                  -updateRoomDetails
	 * Description                             -To update the RoomDetails to the database
	 * @param room_id,roomDetails              - RoomDetails to be updated to the database
	 * @returns RoomDetails                    - returns RoomDetails after updating the RoomDetails to database 
	 * @throws RoomDetailsNotFoundException    - It is raised when RoomDetails does not exists
	 ****************************/

	@Override
	public RoomDetails updateRoomDetails(int roomId, RoomDetails roomDetails) throws RoomDetailsNotFoundException {
		// TODO Auto-generated method stub
		Optional<RoomDetails> r = rDao.findById(roomId);
		if (r.isEmpty()) {
			throw new RoomDetailsNotFoundException("RoomDetails not found");
		}
		else
		rDao.save(roomDetails);
		return roomDetails;
		
	}
	
	/****************************
	 * Method                                  -showAllRoomDetails
	 * Description                             -To show  all RoomDetails to the database
	 * @param room_id                          -to remove room details by ID.
	 * @returns RoomDetails                    - returns RoomDetails after updating the RoomDetails to database 
	 * @throws RoomDetailsNotFoundException    - It is raised when RoomDetails does not exists
	 ****************************/
	
	@Override
	public RoomDetails removeRoomDetails(int room_id) throws RoomDetailsNotFoundException{
			Optional<RoomDetails> op=rDao.findById(room_id);
			if(op.isPresent()) {
				rDao.deleteById(room_id);
				return op.get();
			}
			else throw new RoomDetailsNotFoundException("RoomDetails with given Id doesn't exist.");
			
	}
	
	
	
	/****************************
	 * Method                                :showAllRoomDetails
	 * Description                           :To get all the RoomDetails from the database
	 * @returns List of RoomDetails          - returns RoomDetails after fetching the database 
	 * @throws RoomDetailsNotFoundException  - It is raised when details does not found
	 ****************************/
    @Override
	public List<RoomDetails> showAllRoomDetails() throws RoomDetailsNotFoundException{
    	List<RoomDetails> r = rDao.findAll();
		if (r.isEmpty()) {
			throw new RoomDetailsNotFoundException("RoomDetails not found");
		}
		return r;
	}


    /****************************
	 * Method                                  :showRoomDetails
	 * Description                             :To get the particular RoomDetails from the database
	 * @param room_id                          -To fetch the details of given ID from the database
	 *@returns RoomDetails                     - returns details after fetching from  the database
	 * @throws RoomDetailsNotFoundException    - It is raised when details does not exists with such ID.
	 ****************************/

	@Override
	public RoomDetails showRoomDetails(int room_id) throws RoomDetailsNotFoundException{
		
		
		Optional<RoomDetails> r=rDao.findById(room_id);
		if(r.isEmpty()) {
			throw new RoomDetailsNotFoundException("Given RoomId does not exist");
		}
		return r.get();
			
		
		
		 
	}

}
	


