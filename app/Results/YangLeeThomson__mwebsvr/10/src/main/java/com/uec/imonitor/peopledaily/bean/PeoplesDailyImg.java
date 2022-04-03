package com.uec.imonitor.peopledaily.bean;
 import java.util.List;
public class PeoplesDailyImg {

 private  String title;

 private  String desc;

 private  String url;


public String getUrl(){
    return url;
}


public String getTitle(){
    return title;
}


public void setTitle(String title){
    this.title = title;
}


public String getDesc(){
    return desc;
}


public void setDesc(String desc){
    this.desc = desc;
}


public void setUrl(String url){
    this.url = url;
}


}