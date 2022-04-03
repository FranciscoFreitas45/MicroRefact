package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_quality")
@org.hibernate.annotations.Proxy(lazy = false)
public class Quality {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  int score;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  String qualitytype;

 private  String description;

 private  Date updatetime;

 private  String parentid;

 private  String orgi;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public String getQualitytype(){
    return qualitytype;
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


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
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


public void setParentid(String parentid){
    this.parentid = parentid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
}


public int getScore(){
    return score;
}


public String getCode(){
    return code;
}


public String getParentid(){
    return parentid;
}


public void setScore(int score){
    this.score = score;
}


}