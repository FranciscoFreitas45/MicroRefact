package com.tech.models.entities.chatroom;
 import com.tech.models.dtos.chatroom.ChatroomCreationDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQueries({ @NamedQuery(name = "ChatroomPrivileges.findByRoomId", query = "SELECT p FROM ChatroomPrivileges p WHERE p.room_id = ?1"), @NamedQuery(name = "ChatroomPrivileges.findByPrivileges", query = "SELECT p FROM ChatroomPrivileges p WHERE p.room_privileges = ?1"), @NamedQuery(name = "ChatroomPrivileges.validateAccess", query = "SELECT p FROM ChatroomPrivileges p WHERE p.room_id = ?1 AND p.room_password = ?2") })
@Entity
@Table(name = "chatroom_privileges")
public class ChatroomPrivileges implements Serializable{

@Id
 private  Long room_id;

@Column(name = "room_privileges")
 private  String room_privileges;

@Column(name = "room_password_protected")
 private  boolean room_password_protected;

@Column(name = "room_password")
 private  String room_password;

@Column(name = "room_access_method")
 private  String room_access_method;

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


public boolean isRoom_password_protected(){
    return room_password_protected;
}


public String getRoom_access_method(){
    return room_access_method;
}


}