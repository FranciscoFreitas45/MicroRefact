package com.wxcrm.weixin.pojo;
 public class CommonButton extends Button{

 private  String type;

 private  String key;


public String getKey(){
    return key;
}


public String getType(){
    return type;
}


public void setType(String type){
    this.type = type;
}


public void setKey(String key){
    this.key = key;
}


}