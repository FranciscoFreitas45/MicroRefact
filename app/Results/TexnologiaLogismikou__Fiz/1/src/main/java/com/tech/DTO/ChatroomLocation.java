package com.tech.DTO;
 import com.tech.models.dtos.chatroom.ChatroomCreationDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
public class ChatroomLocation implements Serializable{

 private  Long room_id;

 private  float room_lat;

 private  float room_lng;

 private  int room_max_range;

public ChatroomLocation() {
}public ChatroomLocation(Long room_id, ChatroomCreationDTO DTO) {
    this(room_id, DTO.getRoom_lat(), DTO.getRoom_lng(), DTO.getRoom_max_range());
}public ChatroomLocation(Long room_id, float room_lat, float room_lng, int room_max_range) {
    this.room_id = room_id;
    this.room_lat = room_lat;
    this.room_lng = room_lng;
    this.room_max_range = room_max_range;
}
public int getRoom_max_range(){
    return room_max_range;
}


public Long getRoom_id(){
    return room_id;
}


public float getRoom_lat(){
    return room_lat;
}


public float getRoom_lng(){
    return room_lng;
}


}