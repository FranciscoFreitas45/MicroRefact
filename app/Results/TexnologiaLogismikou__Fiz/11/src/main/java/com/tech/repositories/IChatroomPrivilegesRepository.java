package com.tech.repositories;
 import com.tech.models.entities.chatroom.ChatroomPrivileges;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IChatroomPrivilegesRepository extends JpaRepository<ChatroomPrivileges, Long>{


public List<ChatroomPrivileges> findByPrivileges(String room_privileges)
;

public ChatroomPrivileges validateAccess(Long room_id,String room_password)
;

@Modifying
@Query("update ChatroomPrivileges u set u.room_privileges = ?1, u.room_password_protected = ?2," + " u.room_password = ?3, u.room_access_method = ?4 where u.room_id = ?5")
public void setChatroomEntity(String room_privileges,Boolean room_password_protected,String room_password,String room_access_method,Long room_id)
;

public ChatroomPrivileges findByRoomId(Long room_id)
;

}