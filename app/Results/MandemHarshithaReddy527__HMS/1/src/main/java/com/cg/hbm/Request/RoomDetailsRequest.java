package com.cg.hbm.Request;
import com.cg.hbm.DTO.RoomDetails;
public interface RoomDetailsRequest {

   public void setRoomdetails(RoomDetails roomdetails,int room_id);
   public RoomDetails getRoomdetails(int room_id);
}