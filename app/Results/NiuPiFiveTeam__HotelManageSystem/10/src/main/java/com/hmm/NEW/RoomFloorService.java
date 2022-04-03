package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.room.repository.RoomRepository;
import com.hmm.room.entity.Room;
@Service
public class RoomFloorService {

@Autowired
 private RoomRepository roomrepository;


public void setChildNodes(Long floorId,List<Room> childNodes){
roomrepository.setChildNodes(floorId,childNodes);
}


public List<Room> getChildNodes(Long floorId){
return roomrepository.getChildNodes(floorId);
}


}