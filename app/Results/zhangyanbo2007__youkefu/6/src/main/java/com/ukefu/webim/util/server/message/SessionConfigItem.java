package com.ukefu.webim.util.server.message;
 public class SessionConfigItem {

 private  long serialVersionUID;

 private  String workinghours;

 private  String type;

 private  String worktype;


public void setWorkinghours(String workinghours){
    this.workinghours = workinghours;
}


public String getType(){
    return type;
}


public void setWorktype(String worktype){
    this.worktype = worktype;
}


public String getWorkinghours(){
    return workinghours;
}


public void setType(String type){
    this.type = type;
}


public String getWorktype(){
    return worktype;
}


}