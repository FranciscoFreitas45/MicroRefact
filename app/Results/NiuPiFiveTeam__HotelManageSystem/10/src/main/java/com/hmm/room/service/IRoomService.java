package com.hmm.room.service;
 import java.util.List;
import com.hmm.room.entity.Room;
public interface IRoomService {


public List<Room> findRoomByFloorId(Long floorId)
;

public Iterable<Room> findAllRoom()
;

public void changeCheckOutRoomStatus(String roomNo)
;

public Room changeEmptyToCheckIn(String selectRoomNo)
;

}