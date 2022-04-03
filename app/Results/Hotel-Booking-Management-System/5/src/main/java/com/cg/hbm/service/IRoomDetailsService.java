/*package com.cg.hbm.service;

import java.util.List;

import com.cg.hbm.entites.RoomDetails;

public interface IRoomDetailsService {
	public List<RoomDetails> addRoomDetails(RoomDetails roomDetails);
	public RoomDetails updateRoomDetails(RoomDetails roomDetails);
	public List<RoomDetails> removeRoomDetails(int room_id);
	public List<RoomDetails> showAllRoomDetails();
	public RoomDetails showRoomDetails(int id);
}

//List<RoomDetails> updateRoomDetails(RoomDetails roomDetails)
// List<RoomDetails> removeRoomDetails(Integer room_id)
//c User showRoomDetails(Integer room_id)*/
package com.cg.hbm.service;

import java.util.List;


import com.cg.hbm.entites.RoomDetails;
import com.cg.hbm.exceptions.RoomDetailsNotFoundException;
/***************************************************************************************************************
 *@author          	Mahendra
 *Description      	It is a IRoomDetailsService Interface and provides methods for the Implementation class.  
 *Version          	1.0
 *Created Date    	31-MAR-2021
 **************************************************************************************************************/

public interface IRoomDetailsService {
	public RoomDetails addRoomDetails(RoomDetails roomDetails) throws RoomDetailsNotFoundException;
	
	public List<RoomDetails> showAllRoomDetails() throws RoomDetailsNotFoundException;
	public RoomDetails showRoomDetails(int room_id) throws RoomDetailsNotFoundException;
	RoomDetails updateRoomDetails(int roomId, RoomDetails roomDetails) throws RoomDetailsNotFoundException;
	RoomDetails removeRoomDetails(int room_id) throws RoomDetailsNotFoundException;
}
