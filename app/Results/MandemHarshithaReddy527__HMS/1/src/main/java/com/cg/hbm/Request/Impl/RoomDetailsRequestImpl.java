package com.cg.hbm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.hbm.DTO.RoomDetails;
import com.cg.hbm.Request.RoomDetailsRequest;
public class RoomDetailsRequestImpl implements RoomDetailsRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setRoomdetails(RoomDetails roomdetails,int room_id){
 restTemplate.put("http://5/BookingDetails/{id}/RoomDetails/setRoomdetails",roomdetails,room_id);
 return ;
}


public RoomDetails getRoomdetails(int room_id){
 RoomDetails aux = restTemplate.getForObject("http://5/BookingDetails/{id}/RoomDetails/getRoomdetails",RoomDetails.class,room_id);
return aux;
}


}