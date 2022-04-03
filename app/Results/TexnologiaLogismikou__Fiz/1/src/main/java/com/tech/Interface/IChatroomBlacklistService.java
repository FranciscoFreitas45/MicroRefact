package com.tech.Interface;
public interface IChatroomBlacklistService {

   public ChatroomBlacklist findByRoomIDAndRoomMember(Long room_id,Long room_member);
   public void setNewTime(Long room_id,Long member_id,Date room_expiration_time);
   public boolean delete(ChatroomBlacklist deleteRecord);
   public void add(ChatroomBlacklist newRecord);
}