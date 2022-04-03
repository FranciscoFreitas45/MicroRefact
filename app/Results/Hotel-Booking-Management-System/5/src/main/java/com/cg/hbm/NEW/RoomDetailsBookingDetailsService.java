package com.cg.hbm.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.dao.IRoomDetailsRepository;
import com.cg.hbm.entites.RoomDetails;
@Service
public class RoomDetailsBookingDetailsService {

@Autowired
 private IRoomDetailsRepository iroomdetailsrepository;


public void setRoomdetails(int room_id,RoomDetails roomdetails){
iroomdetailsrepository.setRoomdetails(room_id,roomdetails);
}


public RoomDetails getRoomdetails(int room_id){
return iroomdetailsrepository.getRoomdetails(room_id);
}


}