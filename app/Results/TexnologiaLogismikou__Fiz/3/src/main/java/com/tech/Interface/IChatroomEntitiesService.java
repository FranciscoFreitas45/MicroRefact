package com.tech.Interface;
public interface IChatroomEntitiesService {

   public boolean validateRoomnameExistance(String room_name);
   public ChatroomEntities getRoomByName(String room_name);
}