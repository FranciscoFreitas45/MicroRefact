package com.tech.DTO;
 import com.tech.models.dtos.chatroom.ChatroomBlacklistDTO;
import com.tech.models.entities.embeddedIds.CRBlacklistComposite;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
public class ChatroomBlacklist implements Serializable{

 private  Long room_id;

 private  Long room_member;

 private  Date room_ban_time;

 private  Date room_expiration_time;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";

public ChatroomBlacklist() {
}public ChatroomBlacklist(Long room_id, Long member_id, ChatroomBlacklistDTO DTO) {
    this(room_id, member_id, DTO.getExpiration_date());
}public ChatroomBlacklist(Long room_id, Long room_member, Date room_expiration_time) {
    this.room_id = room_id;
    this.room_member = room_member;
    this.room_ban_time = new Date();
    this.room_expiration_time = room_expiration_time;
}
public Date getRoom_expiration_time(){
    return room_expiration_time;
}


public Date getRoom_ban_time(){
    return room_ban_time;
}


public Long getRoom_id(){
    return room_id;
}


public Long getRoom_member(){
    return room_member;
}


public void setRoom_expiration_time(Date room_expiration_time){
    this.room_expiration_time = room_expiration_time;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ room_id).concat("/setRoom_expiration_time"))

.queryParam("room_expiration_time",room_expiration_time)
;
restTemplate.put(builder.toUriString(),null);
}


}