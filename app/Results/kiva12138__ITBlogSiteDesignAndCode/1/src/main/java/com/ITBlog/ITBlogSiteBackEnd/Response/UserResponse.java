package com.ITBlog.ITBlogSiteBackEnd.Response;
 public class UserResponse {

 private  int code;

 private  String description;


public void setCode(int code){
    this.code = code;
}


public void setDescription(String description){
    this.description = description;
}


public int getCode(){
    return code;
}


public String getDescription(){
    return description;
}


}