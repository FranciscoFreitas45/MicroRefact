package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_knowledge_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgeType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  int total;

 private  int viewnum;

 private  int collectnum;

 private  boolean audit;

 private  String auditer;

 private  String organ;

 private  String parentid;

 private  String knowbaseid;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  boolean datastatus;

 private  boolean navshow;

 private  boolean deskshow;

 private  String icon;


public void setName(String name){
    this.name = name;
}


public void setTotal(int total){
    this.total = total;
}


public String getName(){
    return name;
}


public void setCollectnum(int collectnum){
    this.collectnum = collectnum;
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


public boolean isNavshow(){
    return navshow;
}


public void setAuditer(String auditer){
    this.auditer = auditer;
}


public Date getCreatetime(){
    return createtime;
}


public void setKnowbaseid(String knowbaseid){
    this.knowbaseid = knowbaseid;
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


public String getParentid(){
    return parentid;
}


public void setAudit(boolean audit){
    this.audit = audit;
}


public int getCollectnum(){
    return collectnum;
}


public boolean isAudit(){
    return audit;
}


public String getKnowbaseid(){
    return knowbaseid;
}


public int getViewnum(){
    return viewnum;
}


public void setDeskshow(boolean deskshow){
    this.deskshow = deskshow;
}


public void setViewnum(int viewnum){
    this.viewnum = viewnum;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public String getAuditer(){
    return auditer;
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


public void setNavshow(boolean navshow){
    this.navshow = navshow;
}


public void setCreater(String creater){
    this.creater = creater;
}


public boolean isDeskshow(){
    return deskshow;
}


public int getTotal(){
    return total;
}


public boolean isDatastatus(){
    return datastatus;
}


}