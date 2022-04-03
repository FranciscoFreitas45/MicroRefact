package com.meli.weather.domain;
 import java.util.UUID;
public class Planet {

 private  String id;

 private  String name;

 private  Integer speed;

 private  Integer distance;

 private  Location location;

public Planet(String id, String name, Integer speed, Integer distance) {
    this.id = id;
    this.name = name;
    this.speed = speed;
    this.distance = distance;
    this.location = Location.standard(distance);
}public Planet(String name, Integer speed, Integer distance) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.speed = speed;
    this.distance = distance;
    this.location = Location.standard(distance);
}
public Planet move(Integer days){
    this.location = Location.fromMovement(this.location.angle() + (this.speed * days), distance);
    return this;
}


public Integer distance(){
    return distance;
}


public String name(){
    return name;
}


public Location location(){
    return location;
}


public String id(){
    return id;
}


public Integer speed(){
    return speed;
}


}