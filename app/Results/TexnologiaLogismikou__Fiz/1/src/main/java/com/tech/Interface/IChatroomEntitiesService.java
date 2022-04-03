package com.tech.Interface;
public interface IChatroomEntitiesService {

   public boolean validateRoomnameExistance(String room_name);
   public Long getNextID();
   public void add(ChatroomEntities newRecord);
   public ChatroomEntities getRoomByName(String room_name);
   public boolean delete(ChatroomEntities deleteRecord);
   public void setChatroomEntities(String room_name,Long room_id);
   public ChatroomEntities findByRoomID(Long room_id);
}