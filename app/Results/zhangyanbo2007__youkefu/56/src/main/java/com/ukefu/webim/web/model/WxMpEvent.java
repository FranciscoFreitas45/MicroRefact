package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_wxmpevent")
@org.hibernate.annotations.Proxy(lazy = false)
public class WxMpEvent {

 private  long serialVersionUID;

 private  String id;

 private  String fromuser;

 private  String username;

 private  String creater;

 private  String orgi;

 private  String country;

 private  String city;

 private  String province;

 private  Date createtime;

 private  Date updatetime;

 private  String event;

 private  String channel;

 private  String model;

 private  String appid;

 private  String snsid;


public String getEvent(){
    return event;
}


public void setCountry(String country){
    this.country = country;
}


public String getCountry(){
    return country;
}


public void setProvince(String province){
    this.province = province;
}


public void setFromuser(String fromuser){
    this.fromuser = fromuser;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public void setChannel(String channel){
    this.channel = channel;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getUsername(){
    return username;
}


public String getFromuser(){
    return fromuser;
}


public Date getCreatetime(){
    return createtime;
}


public String getChannel(){
    return channel;
}


public String getProvince(){
    return province;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCity(){
    return city;
}


public String getModel(){
    return model;
}


public void setUsername(String username){
    this.username = username;
}


public void setCity(String city){
    this.city = city;
}


public void setSnsid(String snsid){
    this.snsid = snsid;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setEvent(String event){
    this.event = event;
}


public String getAppid(){
    return appid;
}


public String getOrgi(){
    return orgi;
}


public void setModel(String model){
    this.model = model;
}


public String getSnsid(){
    return snsid;
}


public void setCreater(String creater){
    this.creater = creater;
}


}