package com.cg.sprint.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
public class City {

 private  int sno;

 private  int cityid;

 private  String citynames;


public void setCityid(int cityid){
    this.cityid = cityid;
}


public int getSno(){
    return sno;
}


public int getCityid(){
    return cityid;
}


public String getCitynames(){
    return citynames;
}


}