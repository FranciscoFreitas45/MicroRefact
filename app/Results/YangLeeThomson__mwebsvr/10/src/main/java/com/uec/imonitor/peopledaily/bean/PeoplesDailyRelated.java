package com.uec.imonitor.peopledaily.bean;
 import java.util.List;
public class PeoplesDailyRelated {

 private  String channel;

 private  String news_id;

 private  String title;

 private  String m_url;

 private  String url;


public String getUrl(){
    return url;
}


public String getTitle(){
    return title;
}


public void setM_url(String m_url){
    this.m_url = m_url;
}


public String getChannel(){
    return channel;
}


public String getNews_id(){
    return news_id;
}


public void setTitle(String title){
    this.title = title;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setNews_id(String news_id){
    this.news_id = news_id;
}


public String getM_url(){
    return m_url;
}


public void setUrl(String url){
    this.url = url;
}


}