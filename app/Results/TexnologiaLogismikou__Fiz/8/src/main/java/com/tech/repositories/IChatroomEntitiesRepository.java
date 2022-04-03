package com.tech.repositories;
 import com.tech.models.entities.chatroom.ChatroomEntities;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IChatroomEntitiesRepository extends JpaRepository<ChatroomEntities, Long>{


@Modifying
@Query("update ChatroomEntities u set u.room_name = ?1 where u.room_id = ?2")
public void setChatroomEntity(String room_name,Long room_id)
;

public ChatroomEntities findByRoomName(String room_name)
;

public ChatroomEntities findByRoomCreator(Long room_creator)
;

public ChatroomEntities findByRoomID(Long room_id)
;

@Modifying
@Query("UPDATE ChatroomEntities c SET c.room_last_activity = ?1 WHERE c.room_id = ?2")
public void setRoomLastActivityByRoomID(Date room_last_activity,Long room_id)
;

}