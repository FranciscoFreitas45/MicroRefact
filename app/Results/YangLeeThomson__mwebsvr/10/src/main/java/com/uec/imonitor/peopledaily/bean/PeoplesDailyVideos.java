package com.uec.imonitor.peopledaily.bean;
 public class PeoplesDailyVideos {

 private  String title;

 private  String desc;

 private  String coverpic;

 private  String video;

 private  String size;

 private  String duration;


public void setDuration(String duration){
    this.duration = duration;
}


public void setVideo(String video){
    this.video = video;
}


public String getSize(){
    return size;
}


public String getTitle(){
    return title;
}


public void setSize(String size){
    this.size = size;
}


public String getDuration(){
    return duration;
}


public String getVideo(){
    return video;
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


public String getCoverpic(){
    return coverpic;
}


public void setCoverpic(String coverpic){
    this.coverpic = coverpic;
}


}