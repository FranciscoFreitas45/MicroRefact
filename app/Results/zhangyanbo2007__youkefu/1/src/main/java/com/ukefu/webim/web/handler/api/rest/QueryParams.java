package com.ukefu.webim.web.handler.api.rest;
 public class QueryParams {

 private  String begin;

 private  String end;

 private  String id;

 private  String p;

 private  String type;

 private  String ps;


public String getBegin(){
    return begin;
}


public void setPs(String ps){
    this.ps = ps;
}


public String getPs(){
    return ps;
}


public String getType(){
    return type;
}


public void setBegin(String begin){
    this.begin = begin;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public String getP(){
    return p;
}


public void setEnd(String end){
    this.end = end;
}


public void setP(String p){
    this.p = p;
}


public void setType(String type){
    this.type = type;
}


public String getEnd(){
    return end;
}


}