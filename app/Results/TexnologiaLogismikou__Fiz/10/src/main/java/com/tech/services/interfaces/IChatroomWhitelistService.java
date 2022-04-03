package com.tech.services.interfaces;
 import com.tech.models.entities.chatroom.ChatroomBlacklist;
import com.tech.models.entities.chatroom.ChatroomWhitelist;
import java.util.List;
import javax.transaction.Transactional;
public interface IChatroomWhitelistService {


@Transactional
public void add(ChatroomWhitelist newRecord)
;

@Transactional
public List<ChatroomWhitelist> findByRoomMember(Long room_member)
;

@Transactional
public ChatroomWhitelist findByRoomIDAndRoomMember(Long room_id,Long room_member)
;

@Transactional
public List<ChatroomWhitelist> findByRoomID(Long room_id)
;

@Transactional
public Long countRecordsOfMember(Long member_id)
;

@Transactional
public Long countRecordsOfRoom(Long room_id)
;

@Transactional
public boolean delete(ChatroomWhitelist deleteRecord)
;

@Transactional
public Long countRecords()
;

}