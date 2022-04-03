package com.cg.sprint.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "city")
public class City {

@Id
@Column(length = 2)
 private  int sno;

@Column(length = 2)
 private  int cityid;

@Column(length = 10)
 private  String citynames;


public void setSno(int sno){
    this.sno = sno;
}


public void setCityid(int cityid){
    this.cityid = cityid;
}


public int getSno(){
    return sno;
}


public void setCitynames(String citynames){
    this.citynames = citynames;
}


public int getCityid(){
    return cityid;
}


public String getCitynames(){
    return citynames;
}


}