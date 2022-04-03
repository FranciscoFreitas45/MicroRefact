package com.ukefu.util.bi.model;
 public class RequestData {

 private  long serialVersionUID;

 private  int p;

 private  int ps;

 private  int total;

 private  int pages;

 private  String text;

 private  String q;

 private  String id;


public void setTotal(int total){
    this.total = total;
}


public void setPs(int ps){
    this.ps = ps;
}


public void setPages(int pages){
    this.pages = pages;
}


public int getPs(){
    return ps;
}


public String getText(){
    return text;
}


public int getP(){
    return p;
}


public String getId(){
    return id;
}


public String getQ(){
    return q;
}


public void setId(String id){
    this.id = id;
}


public void setQ(String q){
    this.q = q;
}


public int getTotal(){
    return total;
}


public void setP(int p){
    this.p = p;
}


public int getPages(){
    return pages;
}


public void setText(String text){
    this.text = text;
}


}