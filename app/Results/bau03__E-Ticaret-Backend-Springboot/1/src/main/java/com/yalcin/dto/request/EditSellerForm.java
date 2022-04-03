package com.yalcin.dto.request;
 import javax.persistence.Column;
public class EditSellerForm {

@Column(name = "userid")
 private  String userid;

@Column(name = "id")
 private  String id;


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


}