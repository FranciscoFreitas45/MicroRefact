package com.circle.pojo.goodtype;
 public class GoodTypeArg {

 private  int id;

 private  String english_name;

 private  String name;

 private  int type_id;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getEnglish_name(){
    return english_name;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


public void setType_id(int type_id){
    this.type_id = type_id;
}


public void setEnglish_name(String english_name){
    this.english_name = english_name;
}


public int getType_id(){
    return type_id;
}


}