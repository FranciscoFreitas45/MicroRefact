package com.cg.sprint.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
public class Theatre {

 private  int theatre_id;

 private  String city_name;

 private  String theatre_name;


public String getTheatre_name(){
    return theatre_name;
}


public String getCity_name(){
    return city_name;
}


public void setTheatre_name(String theatre_name){
    this.theatre_name = theatre_name;
}


public int getTheatre_id(){
    return theatre_id;
}


}