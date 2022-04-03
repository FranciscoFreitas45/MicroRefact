package com.optimize.chapter4.guardedSuspension.future;
 import com.optimize.chapter4.guardedSuspension.Data;
public class Request {

 private  String name;

 private  Data response;

public Request(String name) {
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public Data getResponse(){
    return response;
}


public void setResponse(Data response){
    this.response = response;
}


public String toString(){
    return "[ Request " + name + " ]";
}


}