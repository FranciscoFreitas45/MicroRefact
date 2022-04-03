package com.meli.weather.infrastructure.model;
 import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "planets")
public class Planet {

@Id
 private  String id;

 private  String name;

 private  Integer speed;

 private  Integer distance;


public void setName(String name){
    this.name = name;
}


public Integer getDistance(){
    return distance;
}


public void setSpeed(Integer speed){
    this.speed = speed;
}


public String getName(){
    return name;
}


public void setDistance(Integer distance){
    this.distance = distance;
}


public Integer getSpeed(){
    return speed;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


}