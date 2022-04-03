package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_worktime")
@org.hibernate.annotations.Proxy(lazy = false)
public class WorkTime {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  Date createtime;

 private  Date updatetime;

 private  String day;

 private  String begintime;

 private  String endtime;

 private  int wfrom;

 private  int wto;

 private  String wbegintime;

 private  String wendtime;

 private  int dfrom;

 private  int dto;

 private  String dbegintime;

 private  String dendtime;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setWbegintime(String wbegintime){
    this.wbegintime = wbegintime;
}


public String getDbegintime(){
    return dbegintime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getBegintime(){
    return begintime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setDendtime(String dendtime){
    this.dendtime = dendtime;
}


public String getWendtime(){
    return wendtime;
}


public void setDfrom(int dfrom){
    this.dfrom = dfrom;
}


public void setWendtime(String wendtime){
    this.wendtime = wendtime;
}


public Date getCreatetime(){
    return createtime;
}


public String getWbegintime(){
    return wbegintime;
}


public int getDfrom(){
    return dfrom;
}


public void setDay(String day){
    this.day = day;
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


public void setDto(int dto){
    this.dto = dto;
}


public int getDto(){
    return dto;
}


public void setDbegintime(String dbegintime){
    this.dbegintime = dbegintime;
}


public void setEndtime(String endtime){
    this.endtime = endtime;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setBegintime(String begintime){
    this.begintime = begintime;
}


public String getDendtime(){
    return dendtime;
}


public void setType(String type){
    this.type = type;
}


public String getDay(){
    return day;
}


public int getWfrom(){
    return wfrom;
}


public void setWto(int wto){
    this.wto = wto;
}


public int getWto(){
    return wto;
}


public String getType(){
    return type;
}


public void setWfrom(int wfrom){
    this.wfrom = wfrom;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getEndtime(){
    return endtime;
}


}