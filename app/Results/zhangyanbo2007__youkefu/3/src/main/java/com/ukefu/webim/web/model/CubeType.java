package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_cube_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class CubeType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  int inx;

 private  Date createtime;

 private  String creater;

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


public void setInx(int inx){
    this.inx = inx;
}


public Date getCreatetime(){
    return createtime;
}


public int getInx(){
    return inx;
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


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getParentid(){
    return parentid;
}


}