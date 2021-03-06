package com.tech.repositories;
 import com.tech.models.entities.chatroom.ChatroomBlacklist;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ICRBlacklist extends JpaRepository<ChatroomBlacklist, Long>{


public List<ChatroomBlacklist> findByRoomMember(Long room_member)
;

public ChatroomBlacklist findByRoomIDAndRoomMember(Long room_id,Long room_member)
;

public List<ChatroomBlacklist> findByRoomID(Long room_id)
;

@Modifying
@Query("update ChatroomBlacklist u set u.room_expiration_time = ?1 where u.room_id = ?2 AND u.room_member = ?3")
public void setChatroomBlacklist(Date room_expiration_time,Long room_id,Long room_member)
;

}