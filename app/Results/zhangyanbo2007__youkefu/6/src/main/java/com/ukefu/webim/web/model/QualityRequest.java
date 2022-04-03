package com.ukefu.webim.web.model;
 public class QualityRequest {

 private  String[] title;

 private  int[] score;

 private  String[] description;

 private  String[] tag;


public String[] getTitle(){
    return title;
}


public void setTitle(String[] title){
    this.title = title;
}


public void setTag(String[] tag){
    this.tag = tag;
}


public void setDescription(String[] description){
    this.description = description;
}


public int[] getScore(){
    return score;
}


public String[] getTag(){
    return tag;
}


public String[] getDescription(){
    return description;
}


public void setScore(int[] score){
    this.score = score;
}


}