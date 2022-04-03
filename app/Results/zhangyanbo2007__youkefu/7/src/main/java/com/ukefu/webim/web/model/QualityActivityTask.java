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
@Table(name = "uk_qc_activity_task")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityActivityTask implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String actid;

 private  String batid;

 private  String filterid;

 private  Date createtime;

 private  Date updatetime;

 private  String datastatus;

 private  String status;

 private  String exectype;

 private  int namenum;

 private  int execnum;

 private  int renum;

 private  int reorgannum;

 private  int assigned;

 private  int assignedorgan;

 private  int assignedai;

 private  int notassigned;

 private  String description;

 private  String name;


public void setName(String name){
    this.name = name;
}


public int getNotassigned(){
    return notassigned;
}


public void setFilterid(String filterid){
    this.filterid = filterid;
}


public String getName(){
    return name;
}


public void setExecnum(int execnum){
    this.execnum = execnum;
}


public int getAssignedai(){
    return assignedai;
}


public void setReorgannum(int reorgannum){
    this.reorgannum = reorgannum;
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


public String getBatid(){
    return batid;
}


public int getAssigned(){
    return assigned;
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


public int getAssignedorgan(){
    return assignedorgan;
}


public void setBatid(String batid){
    this.batid = batid;
}


public void setRenum(int renum){
    this.renum = renum;
}


public void setNotassigned(int notassigned){
    this.notassigned = notassigned;
}


public String getActid(){
    return actid;
}


public Date getCreatetime(){
    return createtime;
}


public int getNamenum(){
    return namenum;
}


public void setExectype(String exectype){
    this.exectype = exectype;
}


public int getReorgannum(){
    return reorgannum;
}


public void setAssigned(int assigned){
    this.assigned = assigned;
}


public String getFilterid(){
    return filterid;
}


public void setId(String id){
    this.id = id;
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


public void setAssignedorgan(int assignedorgan){
    this.assignedorgan = assignedorgan;
}


public String getExectype(){
    return exectype;
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


public int getRenum(){
    return renum;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public void setAssignedai(int assignedai){
    this.assignedai = assignedai;
}


public String getOrgi(){
    return orgi;
}


public void setNamenum(int namenum){
    this.namenum = namenum;
}


public void setCreater(String creater){
    this.creater = creater;
}


public int getExecnum(){
    return execnum;
}


}