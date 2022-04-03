package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_workorder_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class WorkOrderType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  Date updatetime;

 private  String parentid;

 private  String orgi;

 private  boolean bpm;

 private  String processid;

 private  boolean sla;

 private  String slaid;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setProcessid(String processid){
    this.processid = processid;
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


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public void setId(String id){
    this.id = id;
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


public String getParentid(){
    return parentid;
}


public void setBpm(boolean bpm){
    this.bpm = bpm;
}


public boolean isSla(){
    return sla;
}


public void setUsername(String username){
    this.username = username;
}


public void setSla(boolean sla){
    this.sla = sla;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public boolean isBpm(){
    return bpm;
}


public String getSlaid(){
    return slaid;
}


public void setSlaid(String slaid){
    this.slaid = slaid;
}


public String getOrgi(){
    return orgi;
}


public String getProcessid(){
    return processid;
}


public void setCreater(String creater){
    this.creater = creater;
}


}