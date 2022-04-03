package com.tech.models.entities;
 import com.tech.models.dtos.MessageDTO;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@NamedQueries({ @NamedQuery(name = "Message.findByMessageId", query = "SELECT p FROM Message p WHERE p.id = ?1"), @NamedQuery(name = "Message.findBySenderId", query = "SELECT p FROM Message p WHERE p.userid = ?1"), @NamedQuery(name = "Message.findByChatRoom", query = "SELECT p FROM Message p WHERE p.chatroom_id = ?1") // @NamedQuery(named = "Message.FindByDateOfSend", query = "SELECT p FROM Message p WHERE p.dateSent = ?1")
})
@Table(name = "messages")
public class Message implements Serializable{

@Id
@Column(name = "id")
 private  Long id;

@Column(name = "userid")
 private  Long userid;

@Column(name = "message")
 private  String message;

@Column(name = "datesent")
 private  Date dateSent;

@Column(name = "chatroom_id")
 private  Long chatroom_id;

@Column(name = "ttl")
 private  Integer ttl;

protected Message() {
}public Message(Long id, Long userid, Long chatroom_id, MessageDTO DTO) {
    this(id, userid, DTO.getMessage(), chatroom_id, DTO.getTtl());
}public Message(Long id, Long userid, String message, Long chatroom, Integer ttl) {
    this.message = message;
    this.id = id;
    this.userid = userid;
    this.dateSent = new Date();
    this.chatroom_id = chatroom;
    this.ttl = ttl;
}
public String getMessage(){
    return message;
}


public Long getId(){
    return id;
}


public Date getDate(){
    return dateSent;
}


public Long getChatroom(){
    return chatroom_id;
}


public Long getUserid(){
    return userid;
}


public Integer getTtl(){
    return ttl;
}


}