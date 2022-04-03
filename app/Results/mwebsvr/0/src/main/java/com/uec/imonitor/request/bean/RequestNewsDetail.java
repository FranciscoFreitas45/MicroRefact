package com.uec.imonitor.request.bean;
 public class RequestNewsDetail extends RequestNewsEntity{

 private  String newsTypeName;


public String getNewsTypeName(){
    return newsTypeName;
}


public void setNewsTypeName(String newsTypeName){
    this.newsTypeName = newsTypeName;
}


}