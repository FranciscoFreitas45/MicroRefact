package com.cg.sprint.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "theatre")
public class Theatre {

@Id
@Column(length = 3)
 private  int theatre_id;

@Column(length = 10)
 private  String city_name;

@Column(name = "theatre_name", length = 10)
 private  String theatre_name;


public void setTheatre_id(int theatre_id){
    this.theatre_id = theatre_id;
}


public String getTheatre_name(){
    return theatre_name;
}


public String getCity_name(){
    return city_name;
}


public void setCity_name(String city_name){
    this.city_name = city_name;
}


public void setTheatre_name(String theatre_name){
    this.theatre_name = theatre_name;
}


public int getTheatre_id(){
    return theatre_id;
}


}