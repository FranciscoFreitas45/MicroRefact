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
@Table(name = "uk_qc_formfilter")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityFormFilter implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;

 private  String username;

 private  String status;

 private  String filtertype;

 private  String tablename;

 private  boolean datastatus;

 private  String organ;

 private  String description;

 private  int filternum;

 private  int conditional;

 private  int execnum;


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


public void setDatastatus(boolean datastatus){
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


public String getUsername(){
    return username;
}


public String getTablename(){
    return tablename;
}


public void setTablename(String tablename){
    this.tablename = tablename;
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


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getCode(){
    return code;
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


public boolean isDatastatus(){
    return datastatus;
}


}