package com.circle.pojo.farm;
 public class Farm {

 private  int id;

 private  String farm_name;

 private  String position;

 private  String intro;

 private  String description;

 private  int status;

 private  int create_userid;

 private  String create_time;


public String getFarm_name(){
    return farm_name;
}


public int getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public int getStatus(){
    return status;
}


public void setIntro(String intro){
    this.intro = intro;
}


public String getDescription(){
    return description;
}


public void setPosition(String position){
    this.position = position;
}


public void setStatus(int status){
    this.status = status;
}


public String getIntro(){
    return intro;
}


public String getCreate_time(){
    return create_time;
}


public String getPosition(){
    return position;
}


public void setCreate_userid(int create_userid){
    this.create_userid = create_userid;
}


public void setId(int id){
    this.id = id;
}


public int getCreate_userid(){
    return create_userid;
}


public void setFarm_name(String farm_name){
    this.farm_name = farm_name;
}


public void setCreate_time(String create_time){
    this.create_time = create_time;
}


}