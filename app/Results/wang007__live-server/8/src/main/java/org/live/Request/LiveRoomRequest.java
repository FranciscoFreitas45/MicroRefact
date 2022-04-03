package org.live.Request;
import org.live.DTO.LiveRoom;
public interface LiveRoomRequest {

   public LiveRoom getLiveRoom(String idWHGX);
   public void setLiveRoom(LiveRoom liveRoom,String idWHGX);
}