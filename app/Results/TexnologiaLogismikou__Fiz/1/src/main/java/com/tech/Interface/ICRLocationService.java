package com.tech.Interface;
public interface ICRLocationService {

   public void add(ChatroomLocation saveRecord);
   public boolean checkIfStillInside(Long room_id,float lng,float lat);
   public void setNewMaxRange(int room_max_range,Long room_id);
   public List<ChatroomLocation> findIfNear(float lng,float lat);
   public void setNewLngLat(float lng,float lat,Long room_id);
}