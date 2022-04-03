package com.hmm.Request;
import com.hmm.DTO.Room;
public interface RoomRequest {

   public void setChildNodes(List<Room> childNodes,Long floorId);
   public List<Room> getChildNodes(Long floorId);
}