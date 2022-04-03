package com.tech.Interface;
public interface IChatroomPrivilegesService {

   public void add(ChatroomPrivileges newRecord);
   public ChatroomPrivileges findByRoomId(Long room_id);
   public void setChatroomPrivileges(String room_privileges,Boolean room_password_protected,String room_password,String room_access_method,Long room_id);
}