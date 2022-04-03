package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_agentuser_contacts")
@Proxy(lazy = false)
public class AgentUserContacts implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String username;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String contactsid;

 private  String userid;

 private  String appid;

 private  String channel;


public void setUsername(String username){
    this.username = username;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getUsername(){
    return username;
}


public String getAppid(){
    return appid;
}


public String getContactsid(){
    return contactsid;
}


public Date getCreatetime(){
    return createtime;
}


public String getChannel(){
    return channel;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


}