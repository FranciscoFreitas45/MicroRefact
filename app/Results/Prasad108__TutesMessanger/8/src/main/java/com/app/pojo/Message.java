package com.app.pojo;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "message", catalog = "tutesmessanger")
public class Message {

 private  Integer id;

 private  Role role;

 private  Integer receiverId;

 private  Boolean status;

 private  String text;

public Message() {
}public Message(Role role, Integer receiverId, Boolean status, String text) {
    this.role = role;
    this.receiverId = receiverId;
    this.status = status;
    this.text = text;
}
public void setReceiverId(Integer receiverId){
    this.receiverId = receiverId;
}


@Column(name = "text", length = 500)
public String getText(){
    return this.text;
}


public void setRole(Role role){
    this.role = role;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "role_id")
public Role getRole(){
    return this.role;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "receiver_id")
public Integer getReceiverId(){
    return this.receiverId;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "status")
public Boolean getStatus(){
    return this.status;
}


public void setStatus(Boolean status){
    this.status = status;
}


public void setText(String text){
    this.text = text;
}


}