package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_callcenter_skillext")
@org.hibernate.annotations.Proxy(lazy = false)
public class SkillExtention {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  Date createtime;

 private  Date updatetime;

 private  String skillid;

 private  String extention;

 private  String hostid;


public void setName(String name){
    this.name = name;
}


public void setExtention(String extention){
    this.extention = extention;
}


public String getName(){
    return name;
}


public String getExtention(){
    return extention;
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


public void setType(String type){
    this.type = type;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public Date getCreatetime(){
    return createtime;
}


public String getType(){
    return type;
}


public void setSkillid(String skillid){
    this.skillid = skillid;
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


public String getSkillid(){
    return skillid;
}


}