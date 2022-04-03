package com.tech.Interface;
public interface IChatroomMembersService {

   public void add(ChatroomMembers newRecord);
   public boolean checkIfMemberExistsInChatroom(Long member_id,Long room_id);
   public boolean delete(ChatroomMembers deleteRecord);
}