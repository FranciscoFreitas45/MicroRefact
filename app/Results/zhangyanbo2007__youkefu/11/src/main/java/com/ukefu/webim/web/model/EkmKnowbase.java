package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_knowbase")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowbase {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  int total;

 private  int viewnum;

 private  int collectnum;

 private  int audit;

 private  String organ;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  boolean datastatus;

 private  String own;

 private  String kbtype;

 private  String kbviewid;


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


public void setOwn(String own){
    this.own = own;
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


public int getAudit(){
    return audit;
}


public String getKbtype(){
    return kbtype;
}


public Date getCreatetime(){
    return createtime;
}


public String getOwn(){
    return own;
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


public void setAudit(int audit){
    this.audit = audit;
}


public String getKbviewid(){
    return kbviewid;
}


public int getCollectnum(){
    return collectnum;
}


public int getViewnum(){
    return viewnum;
}


public void setKbtype(String kbtype){
    this.kbtype = kbtype;
}


public void setViewnum(int viewnum){
    this.viewnum = viewnum;
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


public int getTotal(){
    return total;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setKbviewid(String kbviewid){
    this.kbviewid = kbviewid;
}


}