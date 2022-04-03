package com.wxcrm.weixin.pojo;
 public class ViewButton extends Button{

 private  String type;

 private  String url;


public String getUrl(){
    return url;
}


public String getType(){
    return type;
}


public void setType(String type){
    this.type = type;
}


public void setUrl(String url){
    this.url = url;
}


}