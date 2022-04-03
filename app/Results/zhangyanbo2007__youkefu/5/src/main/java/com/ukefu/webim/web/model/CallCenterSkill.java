package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_callcenter_skill")
@org.hibernate.annotations.Proxy(lazy = false)
public class CallCenterSkill {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String skill;

 private  String hostid;

 private  String quene;

 private  String password;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String siptrunk;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public void setSiptrunk(String siptrunk){
    this.siptrunk = siptrunk;
}


public Date getUpdatetime(){
    return updatetime;
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


public void setQuene(String quene){
    this.quene = quene;
}


public String getQuene(){
    return quene;
}


public String getPassword(){
    return password;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public Date getCreatetime(){
    return createtime;
}


public String getSiptrunk(){
    return siptrunk;
}


public String getOrgi(){
    return orgi;
}


public String getHostid(){
    return hostid;
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


public void setSkill(String skill){
    this.skill = skill;
}


public String getSkill(){
    return skill;
}


}