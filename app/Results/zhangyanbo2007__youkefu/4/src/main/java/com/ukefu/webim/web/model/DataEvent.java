package com.ukefu.webim.web.model;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_dataevent")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataEvent {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String modifyid;

 private  User user;

 private  String creater;

 private  Date createtime;

 private  String content;

 private  String eventtype;

 private  String dataid;

 private  List<PropertiesEvent> eventList;


public void setName(String name){
    this.name = name;
}


public String getModifyid(){
    return modifyid;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public String getEventtype(){
    return eventtype;
}


@OneToOne
@JoinColumn(name = "creater", insertable = false, updatable = false, unique = false)
public User getUser(){
    return user;
}


public String getContent(){
    return content;
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


public void setEventList(List<PropertiesEvent> eventList){
    this.eventList = eventList;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "modifyid")
public List<PropertiesEvent> getEventList(){
    return eventList;
}


public String getDataid(){
    return dataid;
}


public Date getCreatetime(){
    return createtime;
}


public void setModifyid(String modifyid){
    this.modifyid = modifyid;
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


public void setDataid(String dataid){
    this.dataid = dataid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setUser(User user){
    this.user = user;
}


public void setEventtype(String eventtype){
    this.eventtype = eventtype;
}


}