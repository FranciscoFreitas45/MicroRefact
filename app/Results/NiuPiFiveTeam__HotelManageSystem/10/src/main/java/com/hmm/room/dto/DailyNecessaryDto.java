package com.hmm.room.dto;
 public class DailyNecessaryDto {

 private  String id;

 private  String show;

 private  String name;

 private  int number;


public int getNumber(){
    return number;
}


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getShow(){
    return show;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setNumber(int number){
    this.number = number;
}


public void setShow(String show){
    this.show = show;
}


}