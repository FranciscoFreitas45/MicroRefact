package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_session_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class SessionType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String ctype;

 private  String parentid;

 private  String dicid;

 private  String description;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;

 private  String organ;


public void setName(String name){
    this.name = name;
}


public String getCtype(){
    return ctype;
}


public String getName(){
    return name;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setDicid(String dicid){
    this.dicid = dicid;
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


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setCtype(String ctype){
    this.ctype = ctype;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getDicid(){
    return dicid;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getCode(){
    return code;
}


public String getParentid(){
    return parentid;
}


}