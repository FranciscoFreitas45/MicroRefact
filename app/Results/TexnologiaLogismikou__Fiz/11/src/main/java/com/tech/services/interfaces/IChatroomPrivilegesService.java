package com.tech.services.interfaces;
 import com.tech.models.entities.chatroom.ChatroomPrivileges;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
public interface IChatroomPrivilegesService {


@Transactional
public void add(ChatroomPrivileges newRecord)
;

@Transactional
public List<ChatroomPrivileges> findByPrivileges(String room_privileges)
;

@Transactional
public ChatroomPrivileges validateAccess(Long room_id,String room_password)
;

@Transactional
public void setChatroomPrivileges(String room_privileges,Boolean room_password_protected,String room_password,String room_access_method,Long room_id)
;

@Transactional
public Long countRecordsOfPrivileges(String room_privileges)
;

@Transactional
public ChatroomPrivileges findByRoomId(Long room_id)
;

@Transactional
public boolean delete(ChatroomPrivileges deleteRecord)
;

@Transactional
public Long countRecords()
;

}