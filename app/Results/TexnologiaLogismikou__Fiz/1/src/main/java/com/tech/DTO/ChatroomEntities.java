package com.tech.DTO;
 import com.tech.models.dtos.chatroom.ChatroomCreationDTO;
import com.tech.models.entities.embeddedIds.ChatroomEntitiesComposite;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
public class ChatroomEntities implements Serializable{

 private  Long room_id;

 private  Long room_creator;

 private  String room_name;

 private  Date room_creation_date;

 private  Date room_last_activity;

public ChatroomEntities() {
}public ChatroomEntities(Long room_id, Long user_id, ChatroomCreationDTO DTO) {
    this(room_id, user_id, DTO.getRoom_name());
}public ChatroomEntities(Long room_id, Long room_creator, String room_name) {
    this.room_id = room_id;
    this.room_creator = room_creator;
    this.room_name = room_name;
    this.room_creation_date = new Date();
    this.room_last_activity = new Date(Calendar.getInstance().getTimeInMillis() + 1000);
}
public String getRoom_name(){
    return room_name;
}


public Date getRoom_last_activity(){
    return room_last_activity;
}


public Long getRoom_id(){
    return room_id;
}


public Date getRoom_creation_date(){
    return room_creation_date;
}


public Long getRoom_creator(){
    return room_creator;
}


}