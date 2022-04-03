package com.tech.services.interfaces;
 import com.tech.models.entities.chatroom.ChatroomLocation;
import java.util.List;
import javax.transaction.Transactional;
public interface ICRLocationService {


@Transactional
public boolean checkIfStillInside(Long room_id,float lng,float lat)
;

@Transactional
public void add(ChatroomLocation saveRecord)
;

@Transactional
public void setNewMaxRange(int room_max_range,Long room_id)
;

@Transactional
public List<ChatroomLocation> findByMaxRange(Integer room_range)
;

@Transactional
public List<ChatroomLocation> findIfNear(float lng,float lat)
;

@Transactional
public List<ChatroomLocation> findByRoomID(Long room_id)
;

@Transactional
public void setNewLngLat(float lng,float lat,Long room_id)
;

}