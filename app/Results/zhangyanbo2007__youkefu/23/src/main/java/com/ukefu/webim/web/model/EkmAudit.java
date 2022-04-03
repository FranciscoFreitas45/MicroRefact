package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_audit")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmAudit {

 private  long serialVersionUID;

 private  String id;

 private  String knowid;

 private  Date knowtime;

 private  String pubstatus;

 private  String auditor;

 private  Date auditime;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String reject;

 private  int version;

 private  boolean datastatus;

 private  String knowtitle;

 private  String auditorname;

 private  String knowcreatername;

 private  String organ;


public String getPubstatus(){
    return pubstatus;
}


public String getKnowcreatername(){
    return knowcreatername;
}


public void setAuditorname(String auditorname){
    this.auditorname = auditorname;
}


public void setAuditime(Date auditime){
    this.auditime = auditime;
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


public void setKnowid(String knowid){
    this.knowid = knowid;
}


public String getAuditorname(){
    return auditorname;
}


public void setKnowtime(Date knowtime){
    this.knowtime = knowtime;
}


public Date getCreatetime(){
    return createtime;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public Date getKnowtime(){
    return knowtime;
}


public int getVersion(){
    return version;
}


public void setVersion(int version){
    this.version = version;
}


public void setPubstatus(String pubstatus){
    this.pubstatus = pubstatus;
}


public void setAuditor(String auditor){
    this.auditor = auditor;
}


public String getAuditor(){
    return auditor;
}


public String getKnowid(){
    return knowid;
}


public void setKnowcreatername(String knowcreatername){
    this.knowcreatername = knowcreatername;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public void setReject(String reject){
    this.reject = reject;
}


public String getOrgan(){
    return organ;
}


public String getReject(){
    return reject;
}


public String getKnowtitle(){
    return knowtitle;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public Date getAuditime(){
    return auditime;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setKnowtitle(String knowtitle){
    this.knowtitle = knowtitle;
}


}