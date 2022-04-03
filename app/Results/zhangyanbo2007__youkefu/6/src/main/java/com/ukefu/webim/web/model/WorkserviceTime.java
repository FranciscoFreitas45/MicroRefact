package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_workservice_time")
@org.hibernate.annotations.Proxy(lazy = false)
public class WorkserviceTime {

 private  long serialVersionUID;

 private  String id;

 private  String timetype;

 private  String scope;

 private  String begin;

 private  String end;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;

 private  String apply;

 private  String week;

public WorkserviceTime() {
}public WorkserviceTime(String id) {
    this.id = id;
}
public String getBegin(){
    return begin;
}


public void setTimetype(String timetype){
    this.timetype = timetype;
}


public void setBegin(String begin){
    this.begin = begin;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setWeek(String week){
    this.week = week;
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


public String getTimetype(){
    return timetype;
}


public String getEnd(){
    return end;
}


public String getWeek(){
    return week;
}


public String getApply(){
    return apply;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setEnd(String end){
    this.end = end;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public void setApply(String apply){
    this.apply = apply;
}


public void setScope(String scope){
    this.scope = scope;
}


public String getScope(){
    return scope;
}


}