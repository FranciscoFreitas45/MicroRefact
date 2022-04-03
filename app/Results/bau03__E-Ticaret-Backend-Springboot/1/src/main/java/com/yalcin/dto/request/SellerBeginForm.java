package com.yalcin.dto.request;
 import javax.persistence.Column;
public class SellerBeginForm {

@Column(name = "content")
 private  String content;


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
}


}