package com.hmm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.room.repository.FloorRepository;
import com.hmm.room.entity.Floor;
@Service
public class FloorRoomService {

@Autowired
 private FloorRepository floorrepository;


public Floor getFloorNode(Long floorIdW7A6){
return floorrepository.getFloorNode(floorIdW7A6);
}


public void setFloorNode(Long floorIdW7A6,Floor floorNode){
floorrepository.setFloorNode(floorIdW7A6,floorNode);
}


}