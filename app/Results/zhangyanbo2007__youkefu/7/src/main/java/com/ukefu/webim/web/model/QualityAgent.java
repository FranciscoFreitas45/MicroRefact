package com.ukefu.webim.web.model;
 import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_qc_callagent")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityAgent {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String name;

 private  Date createtime;

 private  Date updatetime;

 private  String parentid;

 private  String actid;

 private  String batid;

 private  String filtertype;

 private  String tableid;

 private  String distype;

 private  String distarget;

 private  int disnum;

 private  String datastatus;

 private  String status;

 private  String description;

@Transient
 private  AtomicInteger disnames;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getDistarget(){
    return distarget;
}


@Transient
public AtomicInteger getDisnames(){
    return disnames;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public void setDisnames(AtomicInteger disnames){
    this.disnames = disnames;
}


public Date getCreatetime(){
    return createtime;
}


public void setDisnum(int disnum){
    this.disnum = disnum;
}


public String getActid(){
    return actid;
}


public String getFiltertype(){
    return filtertype;
}


public void setFiltertype(String filtertype){
    this.filtertype = filtertype;
}


public int getDisnum(){
    return disnum;
}


public void setId(String id){
    this.id = id;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setActid(String actid){
    this.actid = actid;
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


public void setDistarget(String distarget){
    this.distarget = distarget;
}


public String getDatastatus(){
    return datastatus;
}


public void setDistype(String distype){
    this.distype = distype;
}


public String getDistype(){
    return distype;
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


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


}