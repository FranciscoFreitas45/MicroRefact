package com.tech.DTO;
 import com.tech.models.dtos.chatroom.ChatroomCreationDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
public class ChatroomPrivileges implements Serializable{

 private  Long room_id;

 private  String room_privileges;

 private  boolean room_password_protected;

 private  String room_password;

 private  String room_access_method;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://11";

public ChatroomPrivileges() {
}public ChatroomPrivileges(Long room_id, ChatroomCreationDTO DTO) {
    this(room_id, DTO.getRoom_privilege(), DTO.hasPassword(), DTO.getPassword(), DTO.getAccess_method());
}public ChatroomPrivileges(Long room_id, String room_privileges, boolean room_password_protected, String room_password, String room_access_method) {
    this.room_id = room_id;
    this.room_privileges = room_privileges;
    this.room_password_protected = room_password_protected;
    this.room_password = room_password;
    this.room_access_method = room_access_method;
}
public Long getRoom_id(){
    return room_id;
}


public String getRoom_password(){
    return room_password;
}


public String getRoom_privileges(){
    return room_privileges;
}


public String getRoom_access_method(){
    return room_access_method;
}


public boolean isRoom_password_protected(){
    return room_password_protected;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ room_id).concat("/isRoom_password_protected"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}