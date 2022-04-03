package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_callcenter_router")
@org.hibernate.annotations.Proxy(lazy = false)
public class RouterRules {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  String hostid;

 private  Date createtime;

 private  Date updatetime;

 private  String regex;

 private  boolean allow;

 private  boolean falsebreak;

 private  int routerinx;

 private  String routercontent;

 private  String field;


public void setName(String name){
    this.name = name;
}


public String getRegex(){
    return regex;
}


public void setField(String field){
    this.field = field;
}


public String getName(){
    return name;
}


public void setRouterinx(int routerinx){
    this.routerinx = routerinx;
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


public void setFalsebreak(boolean falsebreak){
    this.falsebreak = falsebreak;
}


public boolean isAllow(){
    return allow;
}


public void setRoutercontent(String routercontent){
    this.routercontent = routercontent;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public String getRoutercontent(){
    return routercontent;
}


public Date getCreatetime(){
    return createtime;
}


public String getHostid(){
    return hostid;
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


public void setRegex(String regex){
    this.regex = regex;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getField(){
    return field;
}


public void setType(String type){
    this.type = type;
}


public int getRouterinx(){
    return routerinx;
}


public void setAllow(boolean allow){
    this.allow = allow;
}


public String getType(){
    return type;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public boolean isFalsebreak(){
    return falsebreak;
}


}