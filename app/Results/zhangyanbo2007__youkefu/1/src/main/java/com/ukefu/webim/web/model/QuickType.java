package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_quick_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class QuickType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  int inx;

 private  String quicktype;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  Date startdate;

 private  Date enddate;

 private  boolean enable;

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


public boolean isEnable(){
    return enable;
}


public Date getStartdate(){
    return startdate;
}


public void setStartdate(Date startdate){
    this.startdate = startdate;
}


public Date getCreatetime(){
    return createtime;
}


public Date getEnddate(){
    return enddate;
}


public void setId(String id){
    this.id = id;
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


public String getCode(){
    return code;
}


public String getParentid(){
    return parentid;
}


public void setUsername(String username){
    this.username = username;
}


public void setEnable(boolean enable){
    this.enable = enable;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setInx(int inx){
    this.inx = inx;
}


public int getInx(){
    return inx;
}


public String getQuicktype(){
    return quicktype;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setQuicktype(String quicktype){
    this.quicktype = quicktype;
}


public void setEnddate(Date enddate){
    this.enddate = enddate;
}


}