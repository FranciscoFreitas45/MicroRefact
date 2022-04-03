package com.cg.hbm.service;
 import java.util.List;
import com.cg.hbm.entites.RoomDetails;
import com.cg.hbm.exceptions.RoomDetailsNotFoundException;
public interface IRoomDetailsService {


public RoomDetails updateRoomDetails(int roomId,RoomDetails roomDetails)
;

public RoomDetails addRoomDetails(RoomDetails roomDetails)
;

public RoomDetails removeRoomDetails(int room_id)
;

public List<RoomDetails> showAllRoomDetails()
;

public RoomDetails showRoomDetails(int room_id)
;

}