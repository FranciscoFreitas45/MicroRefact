package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_act_formfilter")
@org.hibernate.annotations.Proxy(lazy = false)
public class FormFilter implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String name;

 private  Date createtime;

 private  Date updatetime;

 private  String parentid;

 private  String batid;

 private  String filtertype;

 private  String tableid;

 private  String datastatus;

 private  String status;

 private  int filternum;

 private  int conditional;

 private  int execnum;

 private  String description;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setExecnum(int execnum){
    this.execnum = execnum;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public int getConditional(){
    return conditional;
}


public void setTableid(String tableid){
    this.tableid = tableid;
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


public String getBatid(){
    return batid;
}


public void setDatastatus(String datastatus){
    this.datastatus = datastatus;
}


public void setDescription(String description){
    this.description = description;
}


public String getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public String getTableid(){
    return tableid;
}


public void setBatid(String batid){
    this.batid = batid;
}


public Date getCreatetime(){
    return createtime;
}


public String getFiltertype(){
    return filtertype;
}


public void setFiltertype(String filtertype){
    this.filtertype = filtertype;
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


public String getParentid(){
    return parentid;
}


public String getDatastatus(){
    return datastatus;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setStatus(String status){
    this.status = status;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public void setFilternum(int filternum){
    this.filternum = filternum;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public int getExecnum(){
    return execnum;
}


public void setConditional(int conditional){
    this.conditional = conditional;
}


public int getFilternum(){
    return filternum;
}


}