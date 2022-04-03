package com.hmm.DTO;
 public class DailyNecessaryDto {

 private  String id;

 private  String show;

 private  String name;

 private  int number;


public int getNumber(){
    return number;
}


public String getName(){
    return name;
}


public String getShow(){
    return show;
}


public String getId(){
    return id;
}


public void setShow(String show){
    this.show = show;
}


}