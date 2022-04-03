package com.optimize.chapter4.guardedSuspension;
 public class Request {

 private  String name;

public Request(String name) {
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String toString(){
    return "[ Request " + name + " ]";
}


}