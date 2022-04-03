package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_blacklist")
@org.hibernate.annotations.Proxy(lazy = false)
public class BlackEntity {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String userid;

 private  String contactid;

 private  String sessionid;

 private  Date createtime;

 private  int controltime;

 private  Date endtime;

 private  String agentuser;

 private  String channel;

 private  String creater;

 private  String agentid;

 private  String phone;

 private  String openid;

 private  String agentserviceid;

 private  String description;

 private  int times;

 private  int chattime;


public String getPhone(){
    return phone;
}


public String getOpenid(){
    return openid;
}


public String getAgentuser(){
    return agentuser;
}


public void setContactid(String contactid){
    this.contactid = contactid;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getAgentserviceid(){
    return agentserviceid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public int getChattime(){
    return chattime;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public Date getCreatetime(){
    return createtime;
}


public String getChannel(){
    return channel;
}


public void setId(String id){
    this.id = id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getSessionid(){
    return sessionid;
}


public int getControltime(){
    return controltime;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setAgentid(String agentid){
    this.agentid = agentid;
}


public int getTimes(){
    return times;
}


public void setAgentuser(String agentuser){
    this.agentuser = agentuser;
}


public String getAgentid(){
    return agentid;
}


public void setControltime(int controltime){
    this.controltime = controltime;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public String getContactid(){
    return contactid;
}


public void setChattime(int chattime){
    this.chattime = chattime;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public void setOpenid(String openid){
    this.openid = openid;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setTimes(int times){
    this.times = times;
}


public Date getEndtime(){
    return endtime;
}


}