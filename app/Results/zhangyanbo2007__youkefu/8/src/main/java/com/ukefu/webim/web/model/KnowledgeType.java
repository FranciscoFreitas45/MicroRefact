package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_xiaoe_kbs_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class KnowledgeType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  Date createtime;

 private  String parentid;

 private  String typeid;

 private  String creater;

 private  String username;

 private  Date updatetime;

 private  String orgi;

 private  String area;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setArea(String area){
    this.area = area;
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
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setTypeid(String typeid){
    this.typeid = typeid;
}


public String getTypeid(){
    return typeid;
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


public String getCode(){
    return code;
}


public String getArea(){
    return area;
}


public String getParentid(){
    return parentid;
}


}