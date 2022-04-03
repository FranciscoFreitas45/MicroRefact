package com.tech.repositories;
 import com.tech.models.entities.chatroom.ChatroomLocation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IChatroomLocationRepository extends JpaRepository<ChatroomLocation, Long>{


@Modifying
@Query("update ChatroomLocation u set u.room_lng = ?1,u.room_lat = ?2 where u.room_id = ?3")
public void setChatroomLocationLngAndLatById(float lng,float lat,Long room_id)
;

@Modifying
@Query("update ChatroomLocation u set u.room_max_range = ?1 where u.room_id = ?2")
public void setChatroomLocationMaxRangeById(Integer room_max_range,Long id)
;

public List<ChatroomLocation> findByMaxRange(Integer room_range)
;

public List<ChatroomLocation> findIfNear(float lng,float lat)
;

public ChatroomLocation checkIfNear(Long room_id,float lng,float lat)
;

public List<ChatroomLocation> findByRoomID(Long room_id)
;

}