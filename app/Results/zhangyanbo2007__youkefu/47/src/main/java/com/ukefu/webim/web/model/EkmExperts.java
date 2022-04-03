package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_experts")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmExperts {

 private  long serialVersionUID;

 private  String id;

 private  String userid;

 private  String username;

 private  String roleid;

 private  String organ;

 private  String bustype;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  int auditimes;

 private  int auditpass;

 private  int auditreject;

 private  boolean datastatus;


public int getAuditimes(){
    return auditimes;
}


public int getAuditreject(){
    return auditreject;
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


public int getAuditpass(){
    return auditpass;
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


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setAuditreject(int auditreject){
    this.auditreject = auditreject;
}


public void setAuditpass(int auditpass){
    this.auditpass = auditpass;
}


public void setAuditimes(int auditimes){
    this.auditimes = auditimes;
}


public void setUsername(String username){
    this.username = username;
}


public String getBustype(){
    return bustype;
}


public String getRoleid(){
    return roleid;
}


public void setBustype(String bustype){
    this.bustype = bustype;
}


public void setRoleid(String roleid){
    this.roleid = roleid;
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


public boolean isDatastatus(){
    return datastatus;
}


}