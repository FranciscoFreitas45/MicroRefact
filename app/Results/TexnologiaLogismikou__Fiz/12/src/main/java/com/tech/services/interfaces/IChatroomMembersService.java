package com.tech.services.interfaces;
 import com.tech.models.entities.chatroom.ChatroomMembers;
import java.util.List;
import javax.transaction.Transactional;
public interface IChatroomMembersService {


@Transactional
public void add(ChatroomMembers newRecord)
;

@Transactional
public List<ChatroomMembers> findByRoomMember(Long room_member)
;

@Transactional
public boolean checkIfMemberExistsInChatroom(Long member_id,Long room_id)
;

@Transactional
public List<ChatroomMembers> findByRoomId(Long room_id)
;

@Transactional
public Long countRecordsOfMember(Long member_id)
;

@Transactional
public Long countRecordsOfRoom(Long room_id)
;

@Transactional
public ChatroomMembers findByRoomIdAndMember(Long member_id,Long room_id)
;

@Transactional
public boolean delete(ChatroomMembers deleteRecord)
;

@Transactional
public Long countRecords()
;

}