package com.tech.services.interfaces;
 import com.tech.models.entities.chatroom.ChatroomEntities;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
public interface IChatroomEntitiesService {


@Transactional
public void add(ChatroomEntities newRecord)
;

@Transactional
public Long getNextID()
;

@Transactional
public ChatroomEntities findByRoomCreator(Long room_creator)
;

@Transactional
public void updateLastActivity(Date last_activity,Long room_id)
;

@Transactional
public ChatroomEntities findByRoomID(Long room_id)
;

@Transactional
public boolean checkIfChatroomEntityExists(Long room_id)
;

@Transactional
public boolean validateRoomnameExistance(String room_name)
;

@Transactional
public ChatroomEntities getRoomByName(String room_name)
;

@Transactional
public boolean delete(ChatroomEntities deleteRecord)
;

@Transactional
public void setChatroomEntities(String room_name,Long room_id)
;

@Transactional
public Long countRecords()
;

}