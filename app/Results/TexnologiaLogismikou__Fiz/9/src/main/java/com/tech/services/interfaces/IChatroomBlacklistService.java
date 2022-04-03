package com.tech.services.interfaces;
 import com.tech.models.entities.chatroom.ChatroomBlacklist;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
public interface IChatroomBlacklistService {


@Transactional
public void add(ChatroomBlacklist newRecord)
;

@Transactional
public void setNewTime(Long room_id,Long member_id,Date room_expiration_time)
;

@Transactional
public List<ChatroomBlacklist> findByRoomMember(Long room_member)
;

@Transactional
public ChatroomBlacklist findByRoomIDAndRoomMember(Long room_id,Long room_member)
;

@Transactional
public List<ChatroomBlacklist> findByRoomID(Long room_id)
;

@Transactional
public Long countRecordsOfMember(Long member_id)
;

@Transactional
public Long countRecordsOfRoom(Long room_id)
;

@Transactional
public boolean delete(ChatroomBlacklist deleteRecord)
;

@Transactional
public Long countRecords()
;

}