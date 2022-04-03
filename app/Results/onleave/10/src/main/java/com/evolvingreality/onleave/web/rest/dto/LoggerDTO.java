package com.evolvingreality.onleave.web.rest.dto;
 import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.annotation.JsonCreator;
public class LoggerDTO {

 private  String name;

 private  String level;

public LoggerDTO(Logger logger) {
    this.name = logger.getName();
    this.level = logger.getEffectiveLevel().toString();
}@JsonCreator
public LoggerDTO() {
}
public void setName(String name){
    this.name = name;
}


public String getLevel(){
    return level;
}


public String getName(){
    return name;
}


@Override
public String toString(){
    return "LoggerDTO{" + "name='" + name + '\'' + ", level='" + level + '\'' + '}';
}


public void setLevel(String level){
    this.level = level;
}


}