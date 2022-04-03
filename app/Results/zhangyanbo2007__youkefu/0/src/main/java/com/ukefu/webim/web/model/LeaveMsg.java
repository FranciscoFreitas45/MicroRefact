package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_leavemsg")
@org.hibernate.annotations.Proxy(lazy = false)
public class LeaveMsg {

 private  String id;

 private  String orgi;

 private  Date createtime;

 private  String creater;

 private  String name;

 private  String mobile;

 private  String address;

 private  String email;

 private  String content;

 private  String msgstatus;

 private  String qq;

 private  String userid;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public void setAddress(String address){
    this.address = address;
}


public void setQq(String qq){
    this.qq = qq;
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


public void setMsgstatus(String msgstatus){
    this.msgstatus = msgstatus;
}


public String getQq(){
    return qq;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public Date getCreatetime(){
    return createtime;
}


public void setEmail(String email){
    this.email = email;
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


public String getMobile(){
    return mobile;
}


public String getEmail(){
    return email;
}


public String getAddress(){
    return address;
}


public String getMsgstatus(){
    return msgstatus;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


}