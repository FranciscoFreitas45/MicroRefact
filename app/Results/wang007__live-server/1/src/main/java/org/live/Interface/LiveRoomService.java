package org.live.Interface;
public interface LiveRoomService {

   public LiveRoom findLiveRoomByMobileUser(MobileUser user);
   public LiveRoom getLiveRoomByRoomNum(String roomNum);
   public void reportLiveRoom(String userId,String liveRoomId);
}